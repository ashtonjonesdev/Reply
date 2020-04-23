package dev.ashtonjones.reply.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.databinding.FragmentSetTimerBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetTimerFragment extends Fragment {

    private FragmentSetTimerBinding binding;

    private CountDownTimer countDownTimer;
    private AlertDialog.Builder alertDialogBuilder;


    private final Slider.OnSliderTouchListener touchListener =
            new Slider.OnSliderTouchListener() {
                @Override
                public void onStartTrackingTouch(Slider slider) {
                    Snackbar.make(slider, "Slider started being touch", Snackbar.LENGTH_SHORT)
                            .show();
                }

                @Override
                public void onStopTrackingTouch(Slider slider) {
                    Snackbar.make(slider, "Slider stopped being touch", Snackbar.LENGTH_SHORT)
                            .show();
                }
            };

    public SetTimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSetTimerBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alertDialogBuilder = new MaterialAlertDialogBuilder(getContext());

        alertDialogBuilder.setTitle("Set Timer");


        alertDialogBuilder.setView(R.layout.dialog_custom_view_set_timer);

        alertDialogBuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Timer set!", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Timer cancelled!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.slider1.addOnSliderTouchListener(touchListener);

        binding.slider1.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                binding.sliderValueTextView.setText(String.valueOf(value));
            }
        });






    }

    @Override
    public void onResume() {
        super.onResume();

        // Create a Countdown timer for 30 seconds, with countdown interval of 1 second (onTick callback will be called every second to update the text view)
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsLeft = millisUntilFinished / 1000;

                binding.timerTextView.setText(String.valueOf(secondsLeft));

            }

            @Override
            public void onFinish() {

                binding.timerTextView.setText("Time's up!");

            }
        };

        countDownTimer.start();

//        alertDialogBuilder.show();


    }
}
