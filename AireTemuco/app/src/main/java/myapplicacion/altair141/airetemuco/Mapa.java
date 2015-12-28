package myapplicacion.altair141.airetemuco;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by altair141 on 21-12-2015.
 */
public class Mapa extends AppCompatActivity {
    public float init_x;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private String mensaje = "";

    TextView textView;
    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mapa);
        Bundle bundle = this.getIntent().getExtras();
        mensaje = bundle.getString("tipoCondicion");
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setSubtitle("Temuco y Padre Las Casas");

        // TextView mTitle =(TextView) toolbar.getTitle();

        toolbar.setTitle("CALIDAD DEL AIRE");
        setSupportActionBar(toolbar);





        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int[][] states = new int[][]{
                        // default
                        new int[]{android.R.attr.state_focused},  // pressed
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_selected}, // selected
                        new int[]{},
                };

                int[] colors = new int[]{

                        Color.parseColor("#ffffff"),
                        Color.parseColor("#ffffff"),
                        Color.parseColor("#ffffff"),
                        Color.parseColor("#535D6A")
                };

                ColorStateList myList = new ColorStateList(states, colors);
                navigationView.setItemTextColor(myList);
                navigationView.setItemIconTintList(myList);
                int idItem = menuItem.getItemId();
                String tituloMenu = (String) menuItem.getTitle();
                if (tituloMenu.equals("Inicio")) {
                    drawerLayout.closeDrawers();


                    Intent intent = new Intent(Mapa.this, PaginaPrincipal.class);
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                    }

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(Mapa.this);

                    dialogo.setTitle("Espere por favor");
                    dialogo.setMessage("Cargando datos...");

                    dialogo.create();
                    dialogo.show();

                    intent.putExtra("tipoCondicion", mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                } else if (tituloMenu.equals("Recomendaciones")) {
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(Mapa.this, Recomendacion.class);
                    intent.putExtra("tipoCondicion", mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                } else if (tituloMenu.equals("Glosario")) {
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(Mapa.this, Glosario.class);
                    intent.putExtra("tipoCondicion", mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                } else if (tituloMenu.equals("Zonas de Restricción")) {
                    drawerLayout.closeDrawers();

                }

                return true;
            }

        });
        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

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


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

}