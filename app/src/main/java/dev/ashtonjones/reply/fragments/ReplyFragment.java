package dev.ashtonjones.reply.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.adapters.ReplyFragmentStateAdapter;
import dev.ashtonjones.reply.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.reply.datamodels.MessageCard;


/**
 * A simple {@link Fragment} subclass.
 */


public class ReplyFragment extends Fragment {

    /// VIEWPAGER2

    private ViewPager2 viewPager2;

    /// VIEWPAGER2 ADAPTER
    private ReplyFragmentStateAdapter fragmentStateAdapter;

    /// TABLAYOUT
    private TabLayout messageFragmentTabLayout;

    public ReplyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reply, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();

        initTabLayoutAndViewPager2();
    }



    public void initViews() {

        // Initialize TabLayout
        messageFragmentTabLayout = getView().findViewById(R.id.tab_layout_reply_fragment);

        // Initialize ViewPager2
        viewPager2 = getView().findViewById(R.id.viewPager2_reply_fragment);

    }

    public void initTabLayoutAndViewPager2() {

        // Set up the TabLayout
        messageFragmentTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Set up the ViewPager2 Adapter and connect it to the ViewPager2

        fragmentStateAdapter = new ReplyFragmentStateAdapter(this, messageFragmentTabLayout.getTabCount());

        viewPager2.setAdapter(fragmentStateAdapter);


        // Get an array of drawables that will be used to set the icon of each tab

        int[] drawableIcons = new int[] {R.drawable.ic_person_black_24dp, R.drawable.ic_group_black_24dp, R.drawable.ic_business_center_black_24dp, R.drawable.ic_exposure_plus_1_black_24dp, R.drawable.ic_exposure_plus_2_black_24dp};

        // Setup the TabLayout with the ViewPager2
        // Enables the tabs to switch the Fragments when a tab is tapped
        // The last argument is the TabConfigurationStrategy, which is used to configure the tabs (set the text and icons of the tabs)

        // Set icons only
        new TabLayoutMediator(messageFragmentTabLayout, viewPager2, (tab, position) -> tab.setIcon(drawableIcons[position])).attach();

    }

    @Override
    public void onResume() {
        super.onResume();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser == null) {

            Navigation.findNavController(getView()).navigate(R.id.action_global_sign_in_nav_graph);

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FirebaseRepository firebaseRepository = new FirebaseRepository();

        firebaseRepository.getMessages().observe(getViewLifecycleOwner(), new Observer<ArrayList<MessageCard>>() {
            @Override
            public void onChanged(ArrayList<MessageCard> messageCards) {

                for(MessageCard messageCard: messageCards) {
                    Log.d("FIREBASE_REACTIVE", "Observed change in data stream: " + messageCard.getTitle() + "|"  + messageCard.getMessage());
                }
            }
        });
    }
}
