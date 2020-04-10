package dev.ashtonjones.reply.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
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
public class AddNewMessageFragment extends Fragment {

    private static final String LOG_TAG = AddNewMessageFragment.class.getSimpleName();


    private TextInputLayout textInputLayoutTitle;
    private TextInputLayout textInputLayoutMessage;

    private TextInputEditText textInputEditTextTitle;
    private TextInputEditText textInputEditTextMessage;

    private FloatingActionButton saveFAB;

    private RadioGroup radioGroupMessageCategories;

    private int selectedCategoryOption;

    public AddNewMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();

        saveFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewMessage();
            }
        });


    }

    private void saveNewMessage() {

        // Check if fields are null
        if (textInputEditTextTitle == null || textInputEditTextTitle.length() == 0 || textInputEditTextMessage == null || textInputEditTextMessage.length() == 0) {

            Toast.makeText(getContext(), "Please enter a title and a message", Toast.LENGTH_SHORT).show();

        } else {

            String newTitleString = textInputEditTextTitle.getText().toString();

            String newMessageString = textInputEditTextMessage.getText().toString();

            MessageCard newMessage = new MessageCard(newTitleString, newMessageString);

            selectedCategoryOption = radioGroupMessageCategories.getCheckedRadioButtonId();

            FirebaseRepository firebaseRepository = new FirebaseRepository();

            if (selectedCategoryOption == -1) {

                Toast.makeText(getContext(), "Please select a category", Toast.LENGTH_SHORT).show();

            }


            if (selectedCategoryOption != -1) {

                switch (selectedCategoryOption) {

                    case R.id.radioButtonPersonalOption:

                        firebaseRepository.addPersonalMessage(newMessage);

                        break;

                    case R.id.radioButtonSocialOption:

                        firebaseRepository.addSocialMessage(newMessage);

                        break;

                    case R.id.radioButtonBusinessOption:

                        firebaseRepository.addBusinessMessage(newMessage);

                        break;

                    case R.id.radioButtonFirstAdditionalOption:

                        firebaseRepository.addPlus1Message(newMessage);

                        break;

                    case R.id.radioButtonSecondAdditionalOption:

                        firebaseRepository.addPlus2Message(newMessage);

                        break;

                    default:

                        Log.d(LOG_TAG, "Error occurred in radio button selection");

                        break;


                }

                Navigation.findNavController(getView()).popBackStack();


            }


        }

    }

    private void initViews() {

        saveFAB = getView().findViewById(R.id.saveCardFAB);

        textInputLayoutMessage = getView().findViewById(R.id.textInputLayoutMessage);

        textInputLayoutTitle = getView().findViewById(R.id.textInputLayoutTitle);

        textInputEditTextMessage = getView().findViewById(R.id.textInputEditTextMessage);

        textInputEditTextTitle = getView().findViewById(R.id.textInputEditTextTitle);

        radioGroupMessageCategories = getView().findViewById(R.id.radioGroupMessageCategories);

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
