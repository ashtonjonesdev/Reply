package dev.ashtonjones.reply.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;
import java.util.List;

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.adapters.SelectableItemBinderMessageCard;
import dev.ashtonjones.reply.datamodels.MessageCard;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;

/**
 * Base class for the inner Fragments
 */
public class ReplyBaseFragmentViewPager extends Fragment {

    private static final String LOG_TAG = ReplyBaseFragmentViewPager.class.getSimpleName();


    public ReplyBaseFragmentViewPager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(LOG_TAG, "onActivityCreated");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "onCreate");

    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "onStart");

    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onResume() {

        super.onResume();

        Log.d(LOG_TAG, "OnResume");
    }
}
