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

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.databinding.FragmentAddNewMessageBinding;
import dev.ashtonjones.reply.datalayer.viewmodel.AddNewMessageViewModel;
import dev.ashtonjones.reply.datamodels.MessageCard;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewMessageFragment extends Fragment {

    private static final String LOG_TAG = AddNewMessageFragment.class.getSimpleName();

    private FragmentAddNewMessageBinding binding;

    private int selectedCategoryOption;

    private AddNewMessageViewModel viewModel;

    public AddNewMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddNewMessageBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

        binding.saveCardFABAddNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewMessage();
            }
        });


    }

    private void saveNewMessage() {

        // Check if fields are null
        if (binding.textInputEditTextTitleAddNewMessage.getText() == null || binding.textInputEditTextTitleAddNewMessage.length() == 0 || binding.textInputEditTextMessageAddNewMessage.getText() == null || binding.textInputEditTextMessageAddNewMessage.length() == 0) {

            Toast.makeText(getContext(), "Please enter a title and a message", Toast.LENGTH_SHORT).show();

        } else {

            String newTitleString = binding.textInputEditTextTitleAddNewMessage.getText().toString();

            String newMessageString = binding.textInputEditTextMessageAddNewMessage.getText().toString();

            MessageCard newMessage = new MessageCard(newTitleString, newMessageString);

            selectedCategoryOption = binding.radioGroupMessageCategories.getCheckedRadioButtonId();

            if (selectedCategoryOption == -1) {

                Toast.makeText(getContext(), "Please select a category", Toast.LENGTH_SHORT).show();

            }


            if (selectedCategoryOption != -1) {

                switch (selectedCategoryOption) {

                    case R.id.radioButtonPersonalOption:

                        viewModel.addPersonalMessage(newMessage);

                        break;

                    case R.id.radioButtonSocialOption:

                        viewModel.addSocialMessage(newMessage);

                        break;

                    case R.id.radioButtonBusinessOption:

                        viewModel.addBusinessMessage(newMessage);

                        break;

                    case R.id.radioButtonFirstAdditionalOption:

                        viewModel.addPlus1Message(newMessage);

                        break;

                    case R.id.radioButtonSecondAdditionalOption:

                        viewModel.addPlus2Message(newMessage);

                        break;

                    default:

                        Log.d(LOG_TAG, "Error occurred in radio button selection");

                        break;


                }

                Navigation.findNavController(getView()).popBackStack();


            }


        }

    }


    public void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(AddNewMessageViewModel.class);

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
