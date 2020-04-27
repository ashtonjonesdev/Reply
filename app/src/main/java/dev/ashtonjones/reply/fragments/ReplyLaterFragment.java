package dev.ashtonjones.reply.fragments;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import dev.ashtonjones.reply.AlarmReceiver;
import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.databinding.FragmentReplyLaterBinding;
import dev.ashtonjones.reply.datalayer.viewmodel.ReplyLaterViewModel;
import dev.ashtonjones.reply.datamodels.MessageCard;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReplyLaterFragment extends Fragment {

    private static final String LOG_TAG = ReplyLaterFragment.class.getSimpleName();

    private FragmentReplyLaterBinding binding;
    private AlarmManager alarmManager;
    private Intent alarmIntent;
    private PendingIntent alarmPendingIntent;
    private NotificationManager notificationManager;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private Toolbar topAppToolbar;

    private MessageCard selectedMessage;

    private ReplyLaterViewModel viewModel;


    public ReplyLaterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReplyLaterBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();

        setUpViewModel();


        // Get the selected Message
        Bundle args = getArguments();

        selectedMessage = (MessageCard) args.getSerializable("selectedMessageTimerAction");

        if(selectedMessage != null) {

            Log.d(LOG_TAG,"Received message: " + selectedMessage.getTitle() + "| " + selectedMessage.getMessage() );

            // Set text view with title of selected message:
            binding.respondSelectedMessageTitleTextView.setText(selectedMessage.getTitle() + ":");

            // Set text view with message of selected message:
            binding.respondSelectedMessageMessageTextView.setText(selectedMessage.getMessage());


        }




        binding.sliderSetTimer.addOnChangeListener((slider, value, fromUser) -> {


            int intValue = (int) value;

            // if value is 1, change "hours" to "hour"
            if(intValue == 1) {

                binding.hoursTextView.setText("hour");

            }

            else {

                binding.hoursTextView.setText("hours");

            }

            binding.hoursValueTextView.setText(String.valueOf(intValue));
        });

        binding.setTimerButton.setOnClickListener(v -> {

            // Get the amount of hours from the text view
            int hours = Integer.parseInt(binding.hoursValueTextView.getText().toString());

            setAlarm(hours);

        });






    }

    private void initViews() {

        topAppToolbar = getActivity().findViewById(R.id.top_app_bar_toolbar_main_activity);

    }

    public void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(ReplyLaterViewModel.class);

    }

    private void setAlarm(int hours) {

        Log.d(LOG_TAG, "Received hours: " + hours);

        /// SET THE ALARAM
        // 1 hour: 3,600,000 milliseconds (1000 * 60 * 60)
        long hoursTimeLengthInMillis = hours * (1000*60*60);

        // test 10 second interval
        alarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + hours*1000, alarmPendingIntent );

        // Set hours interval
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + hoursTimeLengthInMillis, alarmPendingIntent );

        /// SAVE SELECTED MESSAGE TO FIREBASE
        updateReplyLaterMessage();


        if(hours == 1) {

            Toast.makeText(getContext(), "You will be reminded in " + hours + " hour!", Toast.LENGTH_LONG).show();

        }

        else {

            Toast.makeText(getContext(), "You will be reminded in " + hours + " hours!", Toast.LENGTH_LONG).show();


        }

        Navigation.findNavController(getView()).popBackStack();




    }

    private void updateReplyLaterMessage() {

        viewModel.updateReplyLaterMessage(selectedMessage);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alarmManager = (AlarmManager) getContext().getSystemService(getContext().ALARM_SERVICE);

        alarmIntent = new Intent(getContext(), AlarmReceiver.class);

        alarmPendingIntent = PendingIntent.getBroadcast(getContext(), 0, alarmIntent , 0);

        createNotificationChannel();



    }

    @Override
    public void onResume() {
        super.onResume();

        topAppToolbar.setTitle("Reply Later");
    }

    public void createNotificationChannel() {

        notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Notifications", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.argb(255, 51, 57, 89));
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Reminds you to send a message from the timer you set");

            notificationManager.createNotificationChannel(notificationChannel);

        }

    }
}
