package myapplication.altair141.airetemuco;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.support.design.widget.NavigationView;
import android.widget.Toast;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pre_emergencia);


        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("CALIDAD DEL AIRE ");
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

         @Override
         public boolean onNavigationItemSelected(MenuItem menuItem) {
             navigationView.setItemIconTintList(new ColorStateList(
                     new int [] [] {
                             new int [] {android.R.attr.state_pressed},
                             new int [] {android.R.attr.state_focused},
                             new int [] {}
                     },
                     new int [] {
                             Color.parseColor("#ffffff"),
                             Color.rgb (0, 0, 0),
                             Color.parseColor("#535D6A"),
                     }));

             navigationView.setItemTextColor(new ColorStateList(
                     new int [] [] {
                             new int [] {android.R.attr.state_pressed},
                             new int [] {android.R.attr.state_focused},
                             new int [] {}
                     },
                     new int [] {
                             Color.parseColor("#ffffff"),
                             Color.rgb (0, 0, 0),
                             Color.parseColor("#535D6A"),
                     }
             ));

             return true;
         }

         });
            // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        ponerColorTextoMenu();
    }
    public void ponerColorTextoMenu(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
