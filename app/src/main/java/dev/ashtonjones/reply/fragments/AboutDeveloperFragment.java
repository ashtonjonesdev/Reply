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
import android.widget.FrameLayout;
import android.widget.Toast;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import dev.ashtonjones.reply.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutDeveloperFragment extends Fragment {

    public AboutDeveloperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_developer, container, false);
    }

    /**
     *
     * Had to implement custom back navigation to navigate back to Reply Fragment when the back button is pressed:
     *
     * https://developer.android.com/guide/navigation/navigation-custom-back
     *
     * @param savedInstanceState
     */
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();


    }

    private void initViews() {

        FrameLayout frameLayout = getView().findViewById(R.id.frame_layout_about_developer);



        AboutView view = AboutBuilder.with(getContext())
                .setAppIcon(R.drawable.ic_forum_black_24dp)
                .setPhoto(R.drawable.profile_picture_large)
                .setCover(R.drawable.associate_android_developer_badge_small)
                .setName("Ashton Jones")
                .setSubTitle("\uD83D\uDC68\u200D\uD83D\uDCBB Google Certified Android Engineer |\n" +
                        "✍ Writer |\n" +
                        "\uD83E\uDDD8\u200D♂️ Stoic && Meditator")
                .setBrief("Hi, I’m Ash \uD83D\uDC4B\uD83C\uDFFC Let’s connect!\n" +
                        "\uD83C\uDF10 ashtonjones.dev | \uD83D\uDC65 @TJgrapes | ✉️ ashtonjonesdev@gmail.com")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .setLinksColumnsCount(5)
                .addWebsiteLink("https://ashtonjones.dev/")
                .addEmailLink("ashtonjonesdev@gmail.com")
                .addLinkedInLink("https://www.linkedin.com/in/tjgrapes/")
                .addGooglePlayStoreLink("https://play.google.com/store/apps/developer?id=Ashton+Jones")
                .addGitHubLink("https://github.com/ashtonjonesdev")

                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        frameLayout.addView(view);

    }


}
