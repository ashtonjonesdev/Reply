package dev.ashtonjones.reply.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.datalayer.viewmodel.EditMessageViewModel;
import dev.ashtonjones.reply.datamodels.MessageCard;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditMessageFragment extends Fragment {

    private static final String LOG_TAG = EditMessageFragment.class.getSimpleName();

    private TextInputLayout textInputLayoutTitleEditMessage;
    private TextInputLayout textInputLayoutMessageEditMessage;

    private TextInputEditText textInputEditTextTitleEditMessage;
    private TextInputEditText textInputEditTextMessageEditMessage;

    private FloatingActionButton saveFABEditMessage;

    private MessageCard oldMessage;

    private MessageCard newMessage;

    private EditMessageViewModel viewModel;

    private int fromDestinationArg;


    public EditMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();

        setUpViewModel();

        Bundle args = getArguments();

        MessageCard receivedMessage =(MessageCard) args.get("selectedMessageArg");

        int receivedDestination = args.getInt("fromDestinationArg");

        if(receivedMessage != null) {

            Log.d(LOG_TAG, "Received selected message: " + receivedMessage.getMessage());

            oldMessage = receivedMessage;

            setSelectedMessageData();

        }

        if(receivedDestination != -1 && receivedDestination != 0) {

            fromDestinationArg = receivedDestination;

            Log.d(LOG_TAG, "Received destination: " + fromDestinationArg);

        }

        saveFABEditMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(textInputEditTextTitleEditMessage.getText() != null && textInputEditTextTitleEditMessage.length() > 0 && textInputEditTextMessageEditMessage.getText() != null && textInputEditTextMessageEditMessage.length() > 0) {

                    String newMessageTitle = textInputEditTextTitleEditMessage.getText().toString();

                    String newMessageMessage = textInputEditTextMessageEditMessage.getText().toString();

                    newMessage = new MessageCard(newMessageTitle, newMessageMessage);

                    editMessage();

//                    editMessageInFirebase(oldMessage, newMessage);

                    Navigation.findNavController(getView()).popBackStack();


                }

                else {

                    Toast.makeText(getContext(), "Please enter a title and a message", Toast.LENGTH_SHORT).show();

                }



            }
        });



    }


    public void editMessage() {

        switch (fromDestinationArg) {

            case R.id.reply_fragment_personal_messages_dest:

                Log.d(LOG_TAG, "Edit message in Personal Messages");

                viewModel.editPersonalMessage(oldMessage, newMessage);

                break;

            case R.id.reply_fragment_social_messages_dest:

                Log.d(LOG_TAG, "Edit message in Social Messages");

                viewModel.editSocialMessage(oldMessage, newMessage);

                break;

            case R.id.reply_fragment_business_messages_dest:

                Log.d(LOG_TAG, "Edit message in Business Messages");

                viewModel.editBusinessMessage(oldMessage, newMessage);

                break;

            case R.id.reply_fragment_plus_1_messages_dest:

                Log.d(LOG_TAG, "Edit message in Plus 1 Messages");

                viewModel.editPlus1Message(oldMessage, newMessage);

                break;

            case R.id.reply_fragment_plus_2_messages_dest:

                Log.d(LOG_TAG, "Edit message in Plus 2 Messages");

                viewModel.editPlus2Message(oldMessage, newMessage);

                break;

            default:

                Log.d(LOG_TAG, "Didn't match a case");

                break;


        }


    }


//    private void editMessageInFirebase(MessageCard oldMessage, MessageCard newMessage) {
//
//        FirebaseRepository firebaseRepository = new FirebaseRepository();
//
//        firebaseRepository.editPersonalMessage(oldMessage, newMessage);
//
//    }

    private void setSelectedMessageData() {

        String selectedMessageTitle = oldMessage.getTitle();

        String selectedMessageMessage = oldMessage.getMessage();

        textInputEditTextTitleEditMessage.setText(selectedMessageTitle);

        textInputEditTextMessageEditMessage.setText(selectedMessageMessage);

    }

    private void initViews() {

        textInputLayoutTitleEditMessage = getView().findViewById(R.id.textInputLayoutTitleEditMessage);

        textInputLayoutMessageEditMessage = getView().findViewById(R.id.textInputLayoutMessageEditMessage);

        textInputEditTextMessageEditMessage = getView().findViewById(R.id.textInputEditTextMessageEditMessage);

        textInputEditTextTitleEditMessage = getView().findViewById(R.id.textInputEditTextTitleEditMessage);

        saveFABEditMessage = getView().findViewById(R.id.saveCardFABEditMessage);


    }

    public void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(EditMessageViewModel.class);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event

                Navigation.findNavController(getView()).navigateUp();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }
}
