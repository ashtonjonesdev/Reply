package dev.ashtonjones.reply.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;
import java.util.List;

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.adapters.SelectableItemBinderMessageCard;
import dev.ashtonjones.reply.datamodels.MessageCard;
import dev.ashtonjones.reply.datalayer.viewmodel.BusinessMessagesViewModel;


import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;


/**
 * TODO: Update this class to use Room (see MessageFragmentPersonalMessages Fragment)
 * <p>
 * The MessageFragment class is a fragment within the Main Activity that shows the user buttons that they can tap to send a predefined message
 * <p>
 * The Fragment uses a RecyclerView with a Card for each item that displays the CardView title and CardView image, and contains a message to be sent once the card is clicked
 * <p>
 * The fragment also uses a TabLayout to change the data of the cards based on the selected tab
 * <p>
 * The tabs represent each "type" of messages, such as "Personal messages" and "Business messages", so on, and reflect the same tabs (types) in the user's My Connect Cards
 * <p>
 * When a different tab is selected, it updates the cards to reflect the appropriate messages; for example, when the Business tab is selected, it will show the appropriate Business message cards
 */
public class ReplyFragmentBusinessMessages extends ReplyBaseFragmentViewPager {

    private static final String LOG_TAG = ReplyFragmentBusinessMessages.class.getSimpleName();


    /// References for member variables.


    private RecyclerView recyclerView;

    private SpeedDialView speedDialView;

    private MultiViewAdapter multiViewAdapter;

    private ListSection<MessageCard> listSection;

    private SelectableItemBinderMessageCard selectableItemBinderMessageCard;

    private MessageCard selectedMessage;

    private Toolbar topAppToolbar;

    private ArrayList<MessageCard> placeholderData = new ArrayList<>();

    private BusinessMessagesViewModel viewModel;


    /**
     * Required empty public constructor
     */

    public ReplyFragmentBusinessMessages() {

    }

    /**
     * Inflate the layout for the Message fragment here
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(LOG_TAG, "onCreateView");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message_business_messages, container, false);

        return view;
    }


    /**
     * Had to move all initialization code here (including the calls to setAdapter() and initializeData() to avoid null pointer exceptions
     *
     * @param view
     * @param savedInstanceState
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(LOG_TAG, "onViewCreated");

        // INITIALIZE VIEWS
        initViews();

        // SETUP MULTIVIEWADAPTER
        setUpMultiViewAdapter();

        // SETUP RECYCLERVIEW
        setUpRecyclerView();

        // SETUP VIEWMODEL
        setUpViewModel();

        // INITIALIZE DATA
        initPlaceholderData();

        initData();

        // SETUP SECTION SELECTION BEHAVIOR
        setUpSectionSelection();

        setUpSpeedDialFab();

    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(BusinessMessagesViewModel.class);

    }

    public void setUpSectionSelection() {

        listSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {

            Log.d(LOG_TAG, "Item " + item.getMessage() + " was " + (isSelected ? "" : "un") + "selected");

            Log.d(LOG_TAG, "Selected items: " + selectedItems.toString());

            /**
             *
             * Handle the case where a new message is being selected and the old one is being deselected
             *
             * Sometimes selectedItems contains one or two elements; the 0th element is always the selected item, so can just set it to the 0th element
             *
             * If selectedItems list size is greater than 0, know that we are dealing with selection, so don't need to handle unselection logic here
             *
             */

            if (selectedItems.size() > 0) {

                Log.d(LOG_TAG, "Setting selected item to: " + selectedItems.get(0).getMessage());

                selectedMessage = selectedItems.get(0);

                Log.d(LOG_TAG, "Selected message variable: " + selectedMessage);

                Toast.makeText(getContext(), item.getMessage(), Toast.LENGTH_SHORT).show();

            }

            /**
             *
             * Handle the case where the same message is being selected/unselected
             *
             */

            else {

                if (isSelected) {

                    Log.d(LOG_TAG, "Setting selected item to: " + item.getMessage());

                    selectedMessage = item;

                    Toast.makeText(getContext(), item.getMessage(), Toast.LENGTH_SHORT).show();

                }

                if (!isSelected) {

                    Log.d(LOG_TAG, "Unselecting item: " + item.getMessage());

                    selectedMessage = null;

                }


                Log.d(LOG_TAG, "Selected message variable: " + selectedMessage);

            }


        });

    }

    private void initPlaceholderData() {

        placeholderData.add(new MessageCard("Placeholder 1", "Message 1"));
        placeholderData.add(new MessageCard("Placeholder 2", "Message 2"));
        placeholderData.add(new MessageCard("Placeholder 3", "Message 3"));
        placeholderData.add(new MessageCard("Placeholder 4", "Message 4"));
        placeholderData.add(new MessageCard("Placeholder 5", "Message 5"));
        placeholderData.add(new MessageCard("Placeholder 6", "Message 6"));
        placeholderData.add(new MessageCard("Placeholder 7", "Message 7"));
        placeholderData.add(new MessageCard("Placeholder 8", "Message 8"));
        placeholderData.add(new MessageCard("Placeholder 9", "Message 9"));
        placeholderData.add(new MessageCard("Placeholder 10", "Message 10"));


    }

    public void initData() {

        Toast.makeText(getContext(), "Loading your messages...", Toast.LENGTH_SHORT).show();

        selectedMessage = null;


    }

    public void setUpRecyclerView() {

        // Create a GridLayoutManager for the RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

        // Set the span size lookup
        gridLayoutManager.setSpanSizeLookup(multiViewAdapter.getSpanSizeLookup());

        // Set the LayoutManager span count
        gridLayoutManager.setSpanCount(2);

        // Set the layout manager for the RecyclerView
        recyclerView.setLayoutManager(gridLayoutManager);

        // Connect the MultiViewAdapter to the RecyclerView
        recyclerView.setAdapter(multiViewAdapter);

        // Attach ItemToucHelper to RecyclerView to enable drag and drop functionality
        multiViewAdapter.getItemTouchHelper().attachToRecyclerView(recyclerView);

    }

    public void setUpMultiViewAdapter() {

        // Initialize the MultiViewAdapter
        multiViewAdapter = new MultiViewAdapter();

        // Initialize the Sections
        listSection = new ListSection<>();

        // Initialize the Binders
        selectableItemBinderMessageCard = new SelectableItemBinderMessageCard();

        // Set the span count on the Adapter (same as the LayoutManager span count)
        multiViewAdapter.setSpanCount(2);

        // Register the Binders with the MultiViewAdapter
        multiViewAdapter.registerItemBinders(selectableItemBinderMessageCard);

        // Add Sections to the MultiViewAdapter
        multiViewAdapter.addSection(listSection);

        listSection.setSelectionMode(Mode.SINGLE);

        // Connect the MultiViewAdapter to the RecyclerView
        recyclerView.setAdapter(multiViewAdapter);

    }

    @SuppressLint("ResourceAsColor")
    private void setUpSpeedDialFab() {

        /**
         *
         * Add Action Items to the SpeedDialFab
         *
         * These are the items that are displayed (opened) after the FAB is clicked
         *
         * Add a send icon to send a message
         *
         * Add an add icon to add a new card
         *
         * Add an edit icon to edit a card
         *
         * Add a delete icon to delete a card
         *
         */

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_custom_theme, R.drawable.ic_email_black_24dp).setFabBackgroundColor(R.color.colorPrimaryLight).create());

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_search_action, R.drawable.ic_remove_red_eye_black_24dp).setFabBackgroundColor(R.color.colorPrimaryLight).create());

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_action, R.drawable.ic_add_black_24dp).setFabBackgroundColor(R.color.colorPrimaryLight).create());

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_replace_action, R.drawable.ic_writing_black_24dp).setFabBackgroundColor(R.color.colorPrimaryLight).create());

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_remove_action, R.drawable.ic_delete_black_24dp).setFabBackgroundColor(R.color.colorPrimaryLight).create());


        /**
         *
         * Used to decide what to do when the FAB is opened or closed
         *
         * The "Main Action" is the action of the FAB when it is opened (the FAB itself becomes the Main Action; in this case, it is used as a close action to close the FAB)
         *
         */
        speedDialView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            // Main Action is the initial Fab clicked after it is open (In this case, it is the close icon)
            public boolean onMainActionSelected() {

                Log.d(LOG_TAG, "Main Action (Close) clicked!");

                // Returning false closes the SpeedDial Fab
                return false;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {

                Log.d(LOG_TAG, "Speed dial toggle state changed. Open = " + isOpen);

            }
        });

        /**
         *
         * Used to decide what to do when one of the action items is selected
         *
         * The "camera" action will be used to open the user's camera to scan another user's QR code
         *
         * The "search" action will be used to search for a user by his/her username
         *
         */

        speedDialView.setOnActionSelectedListener(actionItem -> {
            switch (actionItem.getId()) {

                // Send message action
                case R.id.fab_custom_theme:

                    Log.d(LOG_TAG, "Send action clicked!");

                    if (selectedMessage != null) {

                        sendMessage();

                    } else {

                        Toast.makeText(getContext(), "No Message Selected", Toast.LENGTH_SHORT).show();

                    }

                    return false;
                // Preview message action
                case R.id.fab_search_action:

                    if (selectedMessage != null) {

                        showMessagePreview();

                    } else {

                        Toast.makeText(getContext(), "No Message Selected", Toast.LENGTH_SHORT).show();

                    }

                    return false;

                // Add card action
                case R.id.fab_add_action:

                    Log.d(LOG_TAG, "Add action clicked");

                    Navigation.findNavController(getView()).navigate(R.id.action_global_add_new_message_fragment_dest);

                    return false;

                // Edit card action
                case R.id.fab_replace_action:

                    if (selectedMessage != null) {

                        int businessMessageFragmentDestID = R.id.reply_fragment_business_messages_dest;

                        Log.d(LOG_TAG, "Personal Messages Fragment ID: " + businessMessageFragmentDestID);

                        Bundle bundle = new Bundle();

                        bundle.putSerializable("selectedMessageArg", selectedMessage);

                        bundle.putInt("fromDestinationArg", businessMessageFragmentDestID);

                        Navigation.findNavController(getView()).navigate(R.id.action_global_editMessageFragment, bundle);

                        Log.d(LOG_TAG, "Edit action clicked!");

                    } else {

                        Toast.makeText(getContext(), "No message selected", Toast.LENGTH_SHORT).show();

                    }

                    return false;

                // Delete card action
                case R.id.fab_remove_action:

                    Log.d(LOG_TAG, "Delete action clicked!");

                    if (selectedMessage != null) {

                        MessageCard messageCardToDelete = selectedMessage;

                        // Get the index of the selected item
                        int itemAdapterPosition = SelectableItemBinderMessageCard.itemAdapterPosition;

                        Log.d(LOG_TAG, "Deleting message at position: " + itemAdapterPosition);

                        viewModel.deleteBusinessMessage(messageCardToDelete);

                        refreshUI();

                        Toast.makeText(getContext(), "Message deleted!", Toast.LENGTH_SHORT).show();


                    } else {

                        Toast.makeText(getContext(), "No message selected", Toast.LENGTH_SHORT).show();

                    }


                    return false;


                default:

                    Toast.makeText(getContext(), "Error in SpeedDial Fab action", Toast.LENGTH_SHORT).show();

                    Log.d(LOG_TAG, "Error in SpeedDial Fab action");

                    return false;
            }
        });

    }

    public void initViews() {

        recyclerView = getView().findViewById(R.id.recycler_view_fragment_message_business_messages);

        speedDialView = getView().findViewById(R.id.speed_dial_fab_business_messages);

        topAppToolbar = getActivity().findViewById(R.id.top_app_bar_toolbar_main_activity);


    }

    public void sendMessage() {

        String message;
        String mimeType = "text/plain";


        if (selectedMessage != null) {

            message = selectedMessage.getMessage();

            Intent shareMessageIntent = new Intent(Intent.ACTION_SEND);

            shareMessageIntent.setType(mimeType);

            shareMessageIntent.putExtra(Intent.EXTRA_TEXT, message);

            Intent shareMessageIntentChooser = Intent.createChooser(shareMessageIntent, "Share Message via: ");


            getContext().startActivity(shareMessageIntentChooser);

        } else {

            Toast.makeText(getContext(), "No message selected", Toast.LENGTH_SHORT).show();

        }


    }

    /**
     * Show a dialog to the user the indicates what message the card contains when it is long-pressed
     */

    public void showMessagePreview() {

        String message;

        if (selectedMessage != null) {

            message = selectedMessage.getMessage();

            MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(getContext());

            alertDialog.setTitle("Your Message:");

            alertDialog.setMessage(message);

            alertDialog.setPositiveButton("Got It", null);

            alertDialog.setIcon(R.drawable.ic_email_black_24dp);

            alertDialog.show();

        } else {

            message = null;

            Toast.makeText(getContext(), "Edit Action clicked!", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * Need to set the title of the Top app toolbar in onResume because the ViewPager2 is not controlled/compatible with the Navigation component
     */
    @Override
    public void onResume() {
        super.onResume();

        topAppToolbar.setTitle("Business Messages");

        viewModel.getBusinessMessagesLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<MessageCard>>() {
            @Override
            public void onChanged(ArrayList<MessageCard> messageCards) {
                listSection.set(messageCards);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        selectedMessage = null;

    }

    public void refreshUI() {

        viewModel.getBusinessMessagesLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<MessageCard>>() {
            @Override
            public void onChanged(ArrayList<MessageCard> messageCards) {

                listSection.set(messageCards);

            }
        });
    }

}

