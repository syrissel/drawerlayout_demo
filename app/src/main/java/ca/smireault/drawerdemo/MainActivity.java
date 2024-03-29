package ca.smireault.drawerdemo;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Toolbar to use as app bar since drawer will slide under the
        // default app bar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set the toolbar to act as the app's app bar and set home indicator
        // to the hamburger menu drawable.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        // Fetch the drawerLayout and frameLayout views.
        drawerLayout = findViewById(R.id.drawer_layout);
        frameLayout = findViewById(R.id.content_frame);

        NavigationView navigationView = findViewById(R.id.nav_view);

        // Need this or else clicks won't work.
        navigationView.bringToFront();

        // Use anonymous class to implement the onNavigationItemSelectedListener.
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        if (menuItem.getItemId() == R.id.nav_darkgreen) {

                            frameLayout.setBackgroundColor(Color.GREEN);
                        } else if (menuItem.getItemId() == R.id.nav_purple) {

                            frameLayout.setBackgroundColor(Color.MAGENTA);
                            Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                        }

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

    // When the home button is clicked, the drawer menu will open.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
