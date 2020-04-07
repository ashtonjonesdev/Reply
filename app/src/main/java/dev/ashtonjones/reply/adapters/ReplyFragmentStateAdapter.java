package dev.ashtonjones.reply.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import dev.ashtonjones.reply.fragments.ReplyFragmentBusinessMessages;
import dev.ashtonjones.reply.fragments.ReplyFragmentFirstAdditionalCardMessages;
import dev.ashtonjones.reply.fragments.ReplyFragmentPersonalMessages;
import dev.ashtonjones.reply.fragments.ReplyFragmentSecondAdditionalCardMessages;
import dev.ashtonjones.reply.fragments.ReplyFragmentSocialMessages;

/**
 *
 * A Pager Adapter for the ViewPager2 in the MessageFragment
 *
 */

public class ReplyFragmentStateAdapter extends FragmentStateAdapter {



    private int mNumOfTabs;

    public ReplyFragmentStateAdapter(@NonNull Fragment fragment, int numOfTabs) {

        super(fragment);

        mNumOfTabs = numOfTabs;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ReplyFragmentPersonalMessages();
            case 1:
                return new ReplyFragmentSocialMessages();
            case 2:
                return new ReplyFragmentBusinessMessages();
            case 3:
                return new ReplyFragmentFirstAdditionalCardMessages();
            case 4:
                return new ReplyFragmentSecondAdditionalCardMessages();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {

        return mNumOfTabs;

    }
}
