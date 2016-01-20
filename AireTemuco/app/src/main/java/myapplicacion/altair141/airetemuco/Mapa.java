package myapplicacion.altair141.airetemuco;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import capaNegocio.Posicion;

/**
 * Created by altair141 on 21-12-2015.
 */
public class Mapa extends AppCompatActivity implements LocationListener {
    public float init_x;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private String mensaje = "";

    TextView textView;
    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;
    private LocationManager locationManager;
    private String provider;
    private GoogleMap mMap;
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
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        determinarFondo();




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

        setUpMapIfNeeded();
        drawPoligono(Posicion.POLIGONO);
        drawPoligono(Posicion.POLIGONO2);
        drawPoligono(Posicion.POLIGONO3);
        drawPoligono(Posicion.POLIGONO4);
        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(true);
        provider = locationManager.getBestProvider(criteria, false);
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        Location location = locationManager.getLastKnownLocation(provider);

    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }
    @Override
    public void onLocationChanged(Location location) {
        int lat = (int) (location.getLatitude());
        int lng = (int) (location.getLongitude());
        System.out.println(location.getLatitude() + "possition" + location.getLongitude());
        //latituteField.setText(String.valueOf(lat));
        //longitudeField.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
    @Override
    protected void onPause() {
        super.onPause();
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Intent intent = new Intent(Mapa.this, Mapa.class);
                AlertDialog.Builder dialogo = new AlertDialog.Builder(Mapa.this);
                intent.putExtra("tipoCondicion",mensaje);
                dialogo.setTitle("Espere por favor");
                dialogo.setMessage("Cargando datos...");

                dialogo.create();
                dialogo.show();

                startActivity(intent);

                setResult(Activity.RESULT_OK);

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void determinarFondo(){
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
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        /*if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                // setUpMap();
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                mMap.setMyLocationEnabled(true);

            }
        }*/
        if (mMap == null) {
            //Instanciamos el objeto mMap a partir del MapFragment definido bajo el Id "map"
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Chequeamos si se ha obtenido correctamente una referencia al objeto GoogleMap
            if (mMap != null) {
                // setUpMap();
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.setMyLocationEnabled(true);

                mMap.getUiSettings().setRotateGesturesEnabled(true);
                // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-38.738181, -72.592422), 12));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(
                        new LatLng(-38.738181, -72.592422)).zoom(12).build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setCompassEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);

                mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                    @Override
                    public boolean onMyLocationButtonClick() {
                        Location myLocation = mMap.getMyLocation();

                        if (myLocation != null) {
                            LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                                    myLocation.getLongitude());
                            System.out.println(myLocation.getLatitude() + "------long----- " + myLocation.getLongitude());

                            CameraPosition myPosition = new CameraPosition.Builder()
                                    .target(myLatLng).zoom(17).bearing(90).tilt(30).build();
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
                        }
                        return false;
                    }
                });

            }
        }
    }
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
    private void setMarker(LatLng position, String titulo, String info) {
        // Agregamos marcadores para indicar sitios de interéses.
        Marker myMaker = mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)  //Agrega un titulo al marcador
                .snippet(info)   //Agrega información detalle relacionada con el marcador
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))); //Color del marcador
    }
    private void drawPoligono(PolygonOptions opts){
        Polygon poligono = mMap.addPolygon(opts);
    }
}