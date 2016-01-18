package myapplicacion.altair141.airetemuco;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
 * Created by altair141 on 20-11-2015.
 */
public class Recomendacion extends AppCompatActivity {
    public float init_x;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageView museo;
    private ImageView encinas;
    private ImageView padre;
    private boolean checkmuseo=false;
    private boolean checkencinas=false;
    private boolean checkpadre=false;
    private LinearLayout layoutTiempo;
    private LinearLayout layoutTabla;
    private LinearLayout condicion_emergencia;
    private ViewFlipper vf;
    private String mensaje="";
    ImageView image1, image2, image3;
    TextView textView;
    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recomendacion);
        Bundle bundle = this.getIntent().getExtras();
        mensaje=bundle.getString("tipoCondicion");
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setSubtitle("Temuco y Padre Las Casas");

        // TextView mTitle =(TextView) toolbar.getTitle();

        toolbar.setTitle("CALIDAD DEL AIRE");
        setSupportActionBar(toolbar);


        image1 = (ImageView)findViewById(R.id.imageView12);
        image2 = (ImageView)findViewById(R.id.imageView14);
        textView=( TextView ) findViewById(R.id.textView7);
        //int backgroundOpacity = 100 * 0x01000000;
        //  image1.setBackgroundColor(40 + 000000);
        // image2.setBackgroundColor(100 + 000000);
        // textView.setBackgroundColor(40 + 000000);




        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int[][] states = new int[][] {
                        // default
                        new int[] { android.R.attr.state_focused},  // pressed
                        new int[] {android.R.attr.state_pressed },
                        new int[] { android.R.attr.state_selected }, // selected
                        new int[] {},
                };

                int[] colors = new int[] {

                        Color.parseColor("#ffffff"),
                        Color.parseColor("#ffffff"),
                        Color.parseColor("#ffffff"),
                        Color.parseColor("#535D6A")
                };

                ColorStateList myList = new ColorStateList(states, colors);
                navigationView.setItemTextColor(myList);
                navigationView.setItemIconTintList(myList);
                int idItem=menuItem.getItemId();
                String tituloMenu= (String) menuItem.getTitle();
                if(tituloMenu.equals("Inicio")){
                    drawerLayout.closeDrawers();


                    Intent intent = new Intent(Recomendacion.this, PaginaPrincipal.class);
                    try {
                        Thread.sleep(400);
                    }
                    catch (InterruptedException e){}

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(Recomendacion.this);

                    dialogo.setTitle("Espere por favor");
                    dialogo.setMessage("Cargando datos...");

                    dialogo.create();
                    dialogo.show();

                    intent.putExtra("tipoCondicion",mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                else if(tituloMenu.equals("Recomendaciones")){
                    drawerLayout.closeDrawers();
                }

                else if(tituloMenu.equals("Glosario")){
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(Recomendacion.this, Glosario.class);
                    intent.putExtra("tipoCondicion",mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                else if (tituloMenu.equals("Zonas de Restricción")) {
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(Recomendacion.this, Mapa.class);

                    intent.putExtra("tipoCondicion", mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                }
              /*  navigationView.setItemIconTintList(new ColorStateList(
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
                ));*/

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



        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        vf.setOnTouchListener(new ListenerTouchViewFlipper());


        if (mensaje.equals("alerta") || mensaje.equals("Alerta") || mensaje.equals("ALERTA")) {

            drawerLayout.setBackgroundResource(R.drawable.background_alerta_regularcondition);
        } else if (mensaje.equals("bueno") || mensaje.equals("Bueno") || mensaje.equals("BUENO")) {

            drawerLayout.setBackgroundResource(R.drawable.background_buenocondition);
        } else if (mensaje.equals("regular") || mensaje.equals("Regular") || mensaje.equals("REGULAR")) {

            drawerLayout.setBackgroundResource(R.drawable.background_alerta_regularcondition);
        } else if (mensaje.equals("preemergencia") || mensaje.equals("Preemergencia") || mensaje.equals("PREEMERGENCIA")) {

            drawerLayout.setBackgroundResource(R.drawable.background_preemergenciacondition);
        } else if (mensaje.equals("emergencia") || mensaje.equals("Emergencia") || mensaje.equals("EMERGENCIA")) {

            drawerLayout.setBackgroundResource(R.drawable.background_emergenciacondition);
        } else if (mensaje.equals("Sin Información") || mensaje.equals("SIN INFORMACIÓN") ||
                mensaje.equals("sin información") || mensaje.equals("sin informacion")||mensaje.equals("")
                ) {

            drawerLayout.setBackgroundResource(R.drawable.background_sininformacioncondition);

        }

    }
    private class ListenerTouchViewFlipper implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: //Cuando el usuario toca la pantalla por primera vez
                    init_x=event.getX();
                    return true;
                case MotionEvent.ACTION_UP: //Cuando el usuario deja de presionar
                    float distance =init_x-event.getX();

                    if(distance>0)
                    {
                        vf.setInAnimation(inFromRightAnimation());
                        vf.setOutAnimation(outToLeftAnimation());
                        vf.showPrevious();
                    }

                    if(distance<0)
                    {
                        vf.setInAnimation(inFromLeftAnimation());
                        vf.setOutAnimation(outToRightAnimation());
                        vf.showNext();
                    }

                default:
                    break;
            }

            return false;
        }

    }
    private Animation inFromRightAnimation() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
                Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f );

        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());

        return inFromRight;

    }

    private Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    private Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    private Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }


}
