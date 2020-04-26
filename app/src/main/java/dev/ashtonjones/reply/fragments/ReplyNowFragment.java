package dev.ashtonjones.reply.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.databinding.FragmentReplyNowBinding;
import dev.ashtonjones.reply.datalayer.viewmodel.ReplyNowViewModel;
import dev.ashtonjones.reply.datamodels.MessageCard;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReplyNowFragment extends Fragment {

    private static final String LOG_TAG = ReplyNowFragment.class.getSimpleName();

    private FragmentReplyNowBinding binding;
    private ReplyNowViewModel viewModel;
    private Toolbar topAppToolbar;

    public ReplyNowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReplyNowBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();

        setUpViewModel();

        setOnClickListeners();
    }

    private void setOnClickListeners() {

        binding.replyNowSendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.replyNowMessageMessageTextView.getText() != null && binding.replyNowMessageMessageTextView.getText().toString().length() != 0) {

                    String messageToSend = binding.replyNowMessageMessageTextView.getText().toString();

                    sendMessage(messageToSend);


                }




            }
        });

    }

    public void sendMessage(String messageToSend) {

        String mimeType = "text/plain";


        if(messageToSend != null) {

            Intent shareMessageIntent = new Intent(Intent.ACTION_SEND);

            shareMessageIntent.setType(mimeType);

            shareMessageIntent.putExtra(Intent.EXTRA_TEXT, messageToSend);

            Intent shareMessageIntentChooser = Intent.createChooser(shareMessageIntent, "Share Message via: ");


            getContext().startActivity(shareMessageIntentChooser);

        }

        else {

            Log.d(LOG_TAG, "Error in sending message");

        }



    }

    private void initViews() {

        topAppToolbar = getActivity().findViewById(R.id.top_app_bar_toolbar_main_activity);

    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(ReplyNowViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();

        topAppToolbar.setTitle("Reply Now");


        viewModel.getReplyLaterMessageLiveData().observe(this, new Observer<MessageCard>() {
            @Override
            public void onChanged(MessageCard messageCard) {

                if(messageCard != null) {

                    if(messageCard.getTitle() != null && messageCard.getMessage() != null) {

                        String title = messageCard.getTitle();
                        String message = messageCard.getMessage();

                        binding.replyNowMessageTitleTextView.setText(title + ":");
                        binding.replyNowMessageMessageTextView.setText(message);

                    }
                }



            }
        });

    }
}
