package dev.ashtonjones.reply.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;


import java.util.ArrayList;
import java.util.List;

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.adapters.SelectableItemBinderMessageCard;
import dev.ashtonjones.reply.datalayer.viewmodel.PersonalMessagesViewModel;
import dev.ashtonjones.reply.datamodels.MessageCard;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;


/**
 * Each inner Fragment class in the Message Fragment has its own RecyclerViewAdapter and its own data table in the database; thus, each inner Fragment class has its own DAO and ViewModel classes as well
 * <p>
 * <p>
 * <p>
 * This class implements the Room Database, which is used to store the data for the Cards in the inner Fragments
 * <p>
 * In addition to Room, this class also implements other Architecture Components, such as LiveData and ViewModel
 * <p>
 * <p>
 * <p>
 * <p>
 * The data for the cards in each inner Fragment are initialized and stored in the Room database, so they can be accessed offline and limit the network requests to keep costs down
 * <p>
 * The data for each inner Fragment is passed up through the Architecture Component layers: The lowest layer is the SQLite (Room) database: this data gets passed up to the UI by passing the data to the RepositoryRoom, which is then passed to the ViewModel, which then passes it on to the UI Constroller (the Fragment, in this case)
 * <p>
 * <p>
 * <p>
 * The data for the RecyclerView is initialized and updated in the set() method, which updates the the cached copy of data from the RepositoryRoom
 * <p>
 * <p>
 * <p>
 * <p>
 * ** MessageFragmentPersonalMessages is the first (first tab) of the inner Fragments in the Message Fragment ***
 */
public class ReplyFragmentPersonalMessages extends Fragment {

    /// LOG TAG
    public static final String LOG_TAG = ReplyFragmentPersonalMessages.class.getSimpleName();

    /// REFERENCES

    /// VIEWS

    /// RECYCLERVIEW
    private RecyclerView recyclerView;

    // Adapter for the RecyclerView
    private MultiViewAdapter multiViewAdapter;

    private SpeedDialView speedDialView;


    private ArrayList<MessageCard> placeholderData = new ArrayList<>();

    private List<MessageCard> messageCards;

    private ListSection<MessageCard> listSection;

    private SelectableItemBinderMessageCard selectableItemBinderMessageCard;

    // DATAMANAGER
//    private DataManager dataManager;

    private MessageCard selectedMessage;

    // TOP APP BAR
    private Toolbar topAppToolbar;

    // VIEWMODEL
    private PersonalMessagesViewModel viewModel;

    /**
     * Required empty public constructor
     */

    public ReplyFragmentPersonalMessages() {


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
        // Inflate the layout for this fragment

        Log.d(LOG_TAG, "onCreateView");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message_personal_messages, container, false);

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

        setUpViewModel();

        // INITIALIZE DATA
        initPlaceholderData();



        initData();

        // SETUP SECTION SELECTION BEHAVIOR
        setUpSectionSelection();

        setUpSpeedDialFab();


    }



    public void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(PersonalMessagesViewModel.class);

    }


    public void initData() {

        // Instantiate ArrayList
        messageCards = new ArrayList<>();

        // Initialize DataManager
//        dataManager = new DataManager();

        // Initialize ArrayList with data
//        messageTemplates = dataManager.getPersonalMessageTemplatesPlaceholderData();

        // Observe the Personal Messages list for changes; once the list changes, update the UI
//        viewModel.getPersonalMessagesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<MessageTemplate>>() {
//            @Override
//            public void onChanged(ArrayList<MessageTemplate> messageTemplates) {
//
//                listSection.set(messageTemplates);
//
//            }
//        });

        // Add the placeholder data to the Section
//        listSection.addAll(placeholderData);

        // Set the selected Message to null
        selectedMessage = null;

        // Replace the placeholder data with Firebase data
        viewModel.getPersonalMessagesLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<MessageCard>>() {
            @Override
            public void onChanged(ArrayList<MessageCard> messageCards) {

                listSection.set(messageCards);

            }
        });


    }



    public void initViews() {

        recyclerView = getView().findViewById(R.id.recycler_view_fragment_message_personal_messages);

        speedDialView = getView().findViewById(R.id.speed_dial_fab_personal_messages_fragment);

        topAppToolbar = getActivity().findViewById(R.id.top_app_bar_toolbar_main_activity);

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

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_custom_theme, R.drawable.ic_email_black_24dp).setFabBackgroundColor(R.color.colorPrimary500).create());

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_search_action, R.drawable.ic_remove_red_eye_black_24dp).setFabBackgroundColor(R.color.colorPrimary600).create());

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_action, R.drawable.ic_add_black_24dp).setFabBackgroundColor(R.color.colorPrimary700).create());

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_replace_action, R.drawable.ic_writing_black_24dp).setFabBackgroundColor(R.color.colorPrimary800).create());

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_remove_action, R.drawable.ic_delete_black_24dp).setFabBackgroundColor(R.color.colorPrimary900).create());





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

                Toast.makeText(getContext(), "Main Action (Close) clicked!", Toast.LENGTH_SHORT).show();

                Log.d(LOG_TAG, "Main Action (Close) clicked!");

                // Clear the selection when the FAB is closed
                listSection.clearSelections();

                selectedMessage = null;

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

                    if(selectedMessage != null) {

                        sendMessage();

                    }

                    else {

                        Toast.makeText(getContext(), "No message selected", Toast.LENGTH_SHORT).show();

                    }

                    return false;
                // Preview message action
                case R.id.fab_search_action:

                    if(selectedMessage != null) {

                        showMessagePreview();

                    }

                    else {

                        Toast.makeText(getContext(), "No message selected", Toast.LENGTH_SHORT).show();

                    }

                    return false;

                // Add card action
                case R.id.fab_add_action:

//                    viewModel.changeMessageList();

//                    ArrayList<MessageTemplate> messageTemplates = new ArrayList<MessageTemplate>();
//
//                    messageTemplates.add(new MessageTemplate("Hello Hello", "Hi"));
//
//                    viewModel.setPersonalMessagesList(messageTemplates);

                    Toast.makeText(getContext(), "Add Action clicked!", Toast.LENGTH_SHORT).show();


//                Navigation.findNavController(speedDialView).navigate(R.id.action_message_fragment_dest_to_add_card_fragment_dest);

                    return false;

                // Edit card action
                case R.id.fab_replace_action:

                    Toast.makeText(getContext(), "Edit Action clicked!", Toast.LENGTH_SHORT).show();

//                    Navigation.findNavController(speedDialView).navigate(R.id.action_message_fragment_dest_to_edit_card_message_fragment_dest);

                    Log.d(LOG_TAG, "Edit action clicked!");

                    return false;

                // Delete card action
                case R.id.fab_remove_action:


                    if(selectedMessage != null) {

                        Toast.makeText(getContext(), "Delete Action clicked!", Toast.LENGTH_SHORT).show();

                        // TODO: ADD DELETE FUNCTION TO DELETE THE MESSAGE FROM THE DATABASE


                    }

                    else {

                        Toast.makeText(getContext(), "No message selected", Toast.LENGTH_SHORT).show();

                    }

//                    Navigation.findNavController(speedDialView).navigate(R.id.action_message_fragment_dest_to_delete_card_message_fragment_dest);

                    Log.d(LOG_TAG, "Delete action clicked!");

                    return false;


                default:

                    Toast.makeText(getContext(), "Error in SpeedDial Fab action", Toast.LENGTH_SHORT).show();

                    Log.d(LOG_TAG, "Error in SpeedDial Fab action");

                    return false;
            }
        });

    }

    public void initPlaceholderData() {

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



    public void setUpSectionSelection() {

        listSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {

            Log.d(LOG_TAG, "Item " + item.getMessage() + " was " + (isSelected ? "" : "un") + "selected");


            if(isSelected) {

                selectedMessage = item;

                Toast.makeText(getContext(),item.getMessage(), Toast.LENGTH_SHORT).show();


            }

            else {

                selectedMessage = null;

            }

        });

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



    }

    public void sendMessage() {

        String message;
        String mimeType = "text/plain";

        if(selectedMessage != null) {

            message = selectedMessage.getMessage();

            Intent shareMessageIntent = new Intent(Intent.ACTION_SEND);

            shareMessageIntent.setType(mimeType);

            shareMessageIntent.putExtra(Intent.EXTRA_TEXT, message);

            Intent shareMessageIntentChooser = Intent.createChooser(shareMessageIntent, "Share Message via: ");


            getContext().startActivity(shareMessageIntentChooser);

        }

        else {

            Toast.makeText(getContext(), "No message selected", Toast.LENGTH_SHORT).show();

        }






    }

    /**
     *
     * Show a dialog to the user the indicates what message the card contains when it is long-pressed
     *
     */

    public void showMessagePreview() {

        String message;

        if(selectedMessage != null) {

            message = selectedMessage.getMessage();

            MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(getContext());

            alertDialog.setTitle("Your Message:");

            alertDialog.setMessage(message);

            alertDialog.setPositiveButton("Got It", null);

            alertDialog.setIcon(R.drawable.ic_email_black_24dp);

            alertDialog.show();

        }

        else {

            message = null;

            Toast.makeText(getContext(), "No Message Selected", Toast.LENGTH_SHORT).show();

        }



    }


    /**
     *
     * Need to set the title of the Top app toolbar in onResume because the ViewPager2 is not controlled/compatible with the Navigation component
     *
     */
    @Override
    public void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "onResume");

        topAppToolbar.setTitle("Personal Messages");

        viewModel.getPersonalMessagesLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<MessageCard>>() {
            @Override
            public void onChanged(ArrayList<MessageCard> messageCards) {

                for(MessageCard messageCard: messageCards) {
                    Log.d("VIEWMODEL_REACTIVE", "Observed change in data stream: " + messageCard.getTitle() + "|"  + messageCard.getMessage());
                }

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}

