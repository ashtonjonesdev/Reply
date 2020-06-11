package dev.ashtonjones.reply.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.datamodels.MessageCard;
import dev.ashtonjones.reply.datamodels.User;

import static android.app.Activity.RESULT_OK;
import static com.firebase.ui.auth.AuthUI.EMAIL_LINK_PROVIDER;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    private static final int RC_SIGN_IN = 123;
    private static final String LOG_TAG = SignInFragment.class.getSimpleName();
    private SignInButton signInButton;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signInButton = getView().findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();

            }
        });
    }

    private void signIn() {

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build()
                );

        // Start the FirebaseUI Sign in Activity
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers).setIsSmartLockEnabled(false)
                        .build(),
                RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //
        if (requestCode == RC_SIGN_IN) {

            // Handle the SignInResponse
            handleSignInResponse(resultCode, data);

        }
    }

    @SuppressLint("RestrictedApi")
    private void handleSignInResponse(int resultCode, @Nullable Intent data) {

        // Get the Authentication response
        IdpResponse response = IdpResponse.fromResultIntent(data);






        // Successfully signed in: Go to SignedIn Activity
        if (resultCode == RESULT_OK) {

            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            Log.i(LOG_TAG, "Successfully signed in user: " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

            // Get the Provider used to sign in
            if(response.getProviderType() != null) {
                switch (response.getProviderType()) {
                    case GoogleAuthProvider.PROVIDER_ID:
                        Log.d(LOG_TAG, "Signed in using: " + GoogleAuthProvider.PROVIDER_ID);
                        break;
                    case FacebookAuthProvider.PROVIDER_ID:
                        Log.d(LOG_TAG, "Signed in using: " + FacebookAuthProvider.PROVIDER_ID);
                        break;
                    case TwitterAuthProvider.PROVIDER_ID:
                        Log.d(LOG_TAG, "Signed in using: " + TwitterAuthProvider.PROVIDER_ID);
                        break;
                    case EmailAuthProvider.PROVIDER_ID:
                        Log.d(LOG_TAG, "Signed in using: email provider");
                        break;
                    case PhoneAuthProvider.PROVIDER_ID:
                        Log.d(LOG_TAG, "Signed in using: " + PhoneAuthProvider.PROVIDER_ID);
                        break;
                    case EMAIL_LINK_PROVIDER:
                        Log.d(LOG_TAG, "Signed in using: " + EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD);
                        break;
                    case FirebaseAuthProvider.PROVIDER_ID:
                        // Ignore this provider, it's not very meaningful
                        break;
                    default:
                        Log.d(LOG_TAG, "Did not sign in with a provider");
                }

            }


            // Get user info from the provider used
            Log.d(LOG_TAG, TextUtils.isEmpty(response.getUser().getName()) ? "No user name" : "User name: " + response.getUser().getName());

            Log.d(LOG_TAG, TextUtils.isEmpty(response.getEmail()) ? "No user email" : "User email: " + response.getEmail());

            Log.d(LOG_TAG, TextUtils.isEmpty(response.getPhoneNumber()) ? "No user phone number" : response.getPhoneNumber());

            Log.d(LOG_TAG, TextUtils.isEmpty(Objects.requireNonNull(response.getUser().getPhotoUri()).toString()) ? "No user photo": "User photo: " + response.getUser().getPhotoUri().toString());


            //
//            new GraphRequest(
//                    AccessToken.getCurrentAccessToken(),
//                    "/{friend-list-id}",
//                    null,
//                    HttpMethod.GET,
//                    new GraphRequest.Callback() {
//                        public void onCompleted(GraphResponse response) {
//                            response.getRawResponse();
//                        }
//                    }
//            ).executeAsync();


            if (response.isNewUser()) {

                Toast.makeText(getContext(), "Welcome, " + firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();

                // Create a new User in the database
                createNewUser();

                Navigation.findNavController(getView()).navigate(R.id.action_global_welcome_nav_graph);


            } else {

                Navigation.findNavController(getView()).navigate(R.id.action_global_reply_fragment_dest);


            }


        } else {
            // Sign in failed
            if (response == null) {
                // User pressed back button
                Toast.makeText(getContext(), "Cancelled Sign In", Toast.LENGTH_SHORT).show();
                return;
            }

            if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                Toast.makeText(getContext(), "No internet Connection", Toast.LENGTH_SHORT).show();
                return;
            }

            if (response.getError().getErrorCode() == ErrorCodes.ERROR_USER_DISABLED) {
                Toast.makeText(getContext(), "Account is disabled by administrator", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "Unknown error", Toast.LENGTH_SHORT).show();

            Log.e(LOG_TAG, "Sign-in error: ", response.getError());
        }
    }

    /**
     * Create a new User in the users collection in the database, using the FirebaseUser id as the uID
     */

    private void createNewUser() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        ArrayList<MessageCard> starterList = new ArrayList<MessageCard>();

        starterList.add(new MessageCard("Welcome, " + firebaseUser.getDisplayName(), "Add your own message!"));

        MessageCard replyLaterMessage = new MessageCard("Reply Later Placeholder", "Reply later");

        User newUser = new User(firebaseUser.getDisplayName(), firebaseUser.getUid(), starterList, starterList, starterList, starterList, starterList, replyLaterMessage);

        firebaseFirestore.collection("users").document(firebaseUser.getUid()).set(newUser);


    }
}

