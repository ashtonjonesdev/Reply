package dev.ashtonjones.reply.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import dev.ashtonjones.reply.R;

public class MainActivity extends AppCompatActivity {

    Toolbar topAppToolbar;

    // Navigation Component references
    private NavHostFragment navHostFragment;

    private NavController navController;

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        // Apply the toolbar as the Top app bar
        setSupportActionBar(topAppToolbar);

        initUIComponentsWithNavigation();

        showOrHideToolbar();

        setDefaultPreferences();

    }


    private void setDefaultPreferences() {

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

    }

    private void initUIComponentsWithNavigation() {

        NavigationUI.setupWithNavController(topAppToolbar, navController, appBarConfiguration);
    }

    private void initViews() {

        topAppToolbar = findViewById(R.id.top_app_bar_toolbar_main_activity);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        navController = navHostFragment.getNavController();

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.reply_fragment_dest).build();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Have NavigationUI handle onOptionsItemSelected with the onNavDestinationSelected helper method. If the menu item is not meant to navigate, handle with super.onOptionsItemSelected
        // The menu item options in the top_app_bar_main_menu will now navigate to the corresponding destinations, or will make the call to super if the menu item is not meant to navigate
       if (item.getItemId() == R.id.sign_out_button) {

           signOut();

       }

        if(item.getItemId() == R.id.medium_article_tips) {

            openMediumTipsArticle();

        }

        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    private void openMediumTipsArticle() {

        String articleUrl = "https://medium.com/@TJgrapes/introducing-reply-3d0b57ec6744";

        Uri articleUri = Uri.parse(articleUrl);


        Intent intent = new Intent(Intent.ACTION_VIEW, articleUri);

        startActivity(intent);

    }

    private void signOut() {

        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Signed out", Toast.LENGTH_SHORT).show();

                navController.navigate(R.id.action_global_sign_in_nav_graph);
            }
        });

    }

    private void showOrHideToolbar() {

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                // Hide the Toolbar in the Sign In Fragment and Welcome Fragment
                if(destination.getId() == R.id.sign_in_fragment_dest || destination.getId() == R.id.welcome_fragment_dest) {

                    topAppToolbar.setVisibility(View.GONE);

                }

                else {

                    topAppToolbar.setVisibility(View.VISIBLE);

                }
            }
        });

    }
}
