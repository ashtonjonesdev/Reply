package dev.ashtonjones.reply.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datamodels.MessageCard;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditMessageFragment extends Fragment {

    private TextInputLayout textInputLayoutTitleEditMessage;
    private TextInputLayout textInputLayoutMessageEditMessage;

    private TextInputEditText textInputEditTextTitleEditMessage;
    private TextInputEditText textInputEditTextMessageEditMessage;

    private FloatingActionButton saveFABEditMessage;

    private MessageCard oldMessage;

    private MessageCard newMessage;

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

        Bundle args = getArguments();

        MessageCard messageCard =(MessageCard) args.get("selectedMessageArg");

        if(messageCard != null) {

            Toast.makeText(getContext(), "Received selected message: " + messageCard.getMessage(),Toast.LENGTH_SHORT ).show();

            oldMessage = messageCard;

            setSelectedMessageData();

        }

        saveFABEditMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(textInputEditTextTitleEditMessage.getText() != null && textInputEditTextTitleEditMessage.length() > 0 && textInputEditTextMessageEditMessage.getText() != null && textInputEditTextMessageEditMessage.length() > 0) {

                    String newMessageTitle = textInputEditTextTitleEditMessage.getText().toString();

                    String newMessageMessage = textInputEditTextMessageEditMessage.getText().toString();

                    newMessage = new MessageCard(newMessageTitle, newMessageMessage);

                    editMessageInFirebase(oldMessage, newMessage);

                    Navigation.findNavController(getView()).popBackStack();


                }

                else {

                    Toast.makeText(getContext(), "Please enter a title and a message", Toast.LENGTH_SHORT).show();

                }



            }
        });



    }

    private void editMessageInFirebase(MessageCard oldMessage, MessageCard newMessage) {

        FirebaseRepository firebaseRepository = new FirebaseRepository();

        firebaseRepository.editMessage(oldMessage, newMessage);

    }

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
