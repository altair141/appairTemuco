package myapplicacion.altair141.airetemuco;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import capaNegocio.Condicion;
import capaNegocio.Contaminacion;
import capaNegocio.EstacionMonitoreo;
import capaNegocio.Fecha;

/**
 * Created by altair141 on 02-11-2015.
 */
public class PaginaPrincipal extends AppCompatActivity {
    String TAG = "";

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageView museo;
    private ImageView encinas;
    private ImageView padre;
    private boolean checkmuseo = false;
    private boolean checkencinas = false;
    private boolean checkpadre = false;
    private LinearLayout layoutTiempo;
    private LinearLayout layoutTabla;
    private LinearLayout condicion_emergencia;
    // Set the duration of the splash screen
    private String fechaString;
    private String fechaFija;
    private Date fecha;
    private TextView fechaText;
    private TextView diafechaText;
    private TextView mesfechaText;
    private ImageView diaAtras;
    private ImageView diaDespues;
    private ImageView informacion_restriccion;
    private String textoPrueba;
    private static final long SPLASH_SCREEN_DELAY = 3000;
    private EstacionMonitoreo estaciones;
    private List<Contaminacion> listaLasEncinas = new ArrayList<>();
    private List<Contaminacion> listaPadreLasCasas = new ArrayList<>();
    private List<Contaminacion> listaMuseoFerroviario = new ArrayList<>();
    private TableLayout tabla_contaminacion;
    private TextView tipoCondicion;
    private String mensaje;
    //elementos pop_up
    ImageButton btn_cerrar_sinres;
    ImageButton btn_Cerrar1;
    ImageButton btn_der1;
    ImageButton btn_Cerrar2;
    ImageButton btn_der2;
    ImageButton btn_izq2;
    ImageButton btn_Cerrar3;
    ImageButton btn_izq3;
    View popupView_sinres;
    PopupWindow popupWindow_sinres;
    View popupView1;
    PopupWindow popupWindow1;
    View popupView2;
    PopupWindow popupWindow2;
    LayoutInflater layoutInflater;
    View popupView3;
    PopupWindow popupWindow3;
    //fin elementos pop_up

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mensaje = "";
        final Fecha fechaObj = new Fecha();
        tabla_contaminacion = (TableLayout) findViewById(R.id.tabla_contaminacion);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        fechaText = (TextView) findViewById(R.id.idFecha);
        diafechaText = (TextView) findViewById(R.id.diaFecha);
        mesfechaText = (TextView) findViewById(R.id.mesFecha);
        informacion_restriccion = (ImageView) findViewById(R.id.informacion_restriccion);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        layoutTiempo = (LinearLayout) findViewById(R.id.tiempo);
        layoutTabla = (LinearLayout) findViewById(R.id.layoutTabla);
        condicion_emergencia = (LinearLayout) findViewById(R.id.condicion_emergencia);
        museo = (ImageView) findViewById(R.id.imgMuseo);
        encinas = (ImageView) findViewById(R.id.imgEncinas);
        padre = (ImageView) findViewById(R.id.imgPadreLasCasas);
        diaAtras = (ImageView) findViewById(R.id.diaAtras);
        diaDespues = (ImageView) findViewById(R.id.diaSiguiente);
        tipoCondicion = (TextView) findViewById(R.id.condicionTexto);


        //------------fecha---------------------

        fechaString = fechaObj.generarFecha();
        fechaFija = fechaString;
        String[] datoFecha = fechaObj.dividirFecha(fechaString);
        diafechaText.setText(fechaString);
        diafechaText.setText(fechaObj.mayusculaDia(datoFecha[0]));
        fechaText.setText(datoFecha[1]);
        mesfechaText.setText(fechaObj.mayusculaMes(datoFecha[2]));

        //------------fecha---------------------

        //-------toolbar-------------------------
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setSubtitle("Temuco y Padre Las Casas");

        // TextView mTitle =(TextView) toolbar.getTitle();

        toolbar.setTitle("CALIDAD DEL AIRE");
        setSupportActionBar(toolbar);

        //---------------------------------

        //----------------------------menu lateral---------------------------------------
        //Initializing NavigationView

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
                //menuItem.setTitle(menuItem.getItemId());
                String tituloMenu = (String) menuItem.getTitle();
                if (tituloMenu.equals("Inicio")) {
                    drawerLayout.closeDrawers();
                } else if (tituloMenu.equals("Recomendaciones")) {
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(PaginaPrincipal.this, Recomendacion.class);

                    intent.putExtra("tipoCondicion", mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                } else if (tituloMenu.equals("Glosario")) {
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(PaginaPrincipal.this, Glosario.class);

                    intent.putExtra("tipoCondicion", mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                return true;
            }

        });

//---------------------------------------------------------------------------------------------
        // Initializing Drawer Layout and ActionBarToggle

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


        layoutTabla.setVisibility(LinearLayout.GONE);



        //----------------------------------------estacion de monitoreo----------------------------------------

        museo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!checkmuseo) {
                    v.setAlpha(1);
                    checkmuseo = true;
                    layoutTiempo.setVisibility(LinearLayout.GONE);
                    layoutTabla.setVisibility(LinearLayout.VISIBLE);

                } else {
                    v.setAlpha((float) 0.3);
                    checkmuseo = false;
                    layoutTiempo.setVisibility(LinearLayout.VISIBLE);
                    layoutTabla.setVisibility(LinearLayout.GONE);
                }
                checkencinas = false;
                checkpadre = false;
                padre.setAlpha((float) 0.3);
                encinas.setAlpha((float) 0.3);

                //---------------------metodos para borrar los datos de contaminacion y cambiar de estacion de monitoreo---------------------
                tabla_contaminacion.removeAllViews();
               /*     int childCount = tabla_contaminacion.getChildCount();

                    // Remove all rows except the first one
                    if (childCount > 1) {
                        tabla_contaminacion.removeViews(1, childCount - 1);
                    }
*/
                //---------------------fin metodos para borrar los datos de contaminacion y cambiar de estacion de monitoreo---------------------


                ;
                    /*)                  LinearLayout.LayoutParams lp = new TableRow.LayoutParams();
                   lp.gravity= Gravity.CENTER;
                    txt.setLayoutParams(lp);

*/
                for (Contaminacion museos : listaMuseoFerroviario) {
                    TableRow tbrow0 = new TableRow(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    TableRow.LayoutParams param = new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    param.height = (int) getResources().getDimension(R.dimen.fila_tabla_estacion_monitoreo);
                    TextView txt = new TextView(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    txt.setText(museos.getHora());
                    // txt.setText("asd");
                    txt.setTextColor(Color.WHITE);
                    txt.setLayoutParams(param);
                    txt.setGravity(Gravity.CENTER);
                    txt.setTextSize(getResources().getDimension(R.dimen.texto_filas_tabla_estacion_monitoreo));

                    TextView txt2 = new TextView(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    txt2.setText(museos.getMp25());
                    //txt2.setText("asd2");
                    txt2.setTextColor(Color.WHITE);
                    txt2.setLayoutParams(param);
                    txt2.setGravity(Gravity.CENTER);
                    txt2.setTextSize(getResources().getDimension(R.dimen.texto_filas_tabla_estacion_monitoreo));

                    //  txt2.setLayoutParams(lp);
                    TextView txt3 = new TextView(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    txt3.setText(museos.getMp10());
                    //txt3.setText("asd2");
                    txt3.setTextColor(Color.WHITE);
                    txt3.setLayoutParams(param);
                    txt3.setTextSize(getResources().getDimension(R.dimen.texto_filas_tabla_estacion_monitoreo));
                    txt3.setGravity(Gravity.CENTER);

                    // txt3.setLayoutParams(lp);
                    tbrow0.addView(txt);
                    tbrow0.addView(txt2);
                    tbrow0.addView(txt3);
                    tabla_contaminacion.addView(tbrow0);
                }
            }
        });

        encinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkencinas) {
                    v.setAlpha(1);
                    checkencinas = true;
                    layoutTiempo.setVisibility(LinearLayout.GONE);
                    layoutTabla.setVisibility(LinearLayout.VISIBLE);
                } else {
                    v.setAlpha((float) 0.3);
                    checkencinas = false;
                    layoutTiempo.setVisibility(LinearLayout.VISIBLE);
                    layoutTabla.setVisibility(LinearLayout.GONE);
                }
                checkmuseo = false;
                checkpadre = false;
                museo.setAlpha((float) 0.3);
                padre.setAlpha((float) 0.3);
                //---------------------metodos para borrar los datos de contaminacion y cambiar de estacion de monitoreo---------------------
                tabla_contaminacion.removeAllViews();
              /*      int childCount = tabla_contaminacion.getChildCount();

                    // Remove all rows except the first one
                    if (childCount > 1) {
                        tabla_contaminacion.removeViews(1, childCount - 1);
                    }
*/
                //---------------------fin metodos para borrar los datos de contaminacion y cambiar de estacion de monitoreo---------------------

                for (Contaminacion museos : listaLasEncinas) {
                    TableRow tbrow0 = new TableRow(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    TableRow.LayoutParams param = new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    param.height = (int) getResources().getDimension(R.dimen.fila_tabla_estacion_monitoreo);
                    TextView txt = new TextView(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    txt.setText(museos.getHora());
                    // txt.setText("asd");
                    txt.setTextColor(Color.WHITE);
                    txt.setLayoutParams(param);
                    txt.setGravity(Gravity.CENTER);
                    txt.setTextSize(getResources().getDimension(R.dimen.texto_filas_tabla_estacion_monitoreo));

                    TextView txt2 = new TextView(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    txt2.setText(museos.getMp25());
                    //txt2.setText("asd2");
                    txt2.setTextColor(Color.WHITE);
                    txt2.setLayoutParams(param);
                    txt2.setGravity(Gravity.CENTER);
                    txt2.setTextSize(getResources().getDimension(R.dimen.texto_filas_tabla_estacion_monitoreo));

                    //  txt2.setLayoutParams(lp);
                    TextView txt3 = new TextView(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    txt3.setText(museos.getMp10());
                    //txt3.setText("asd2");
                    txt3.setTextColor(Color.WHITE);
                    txt3.setLayoutParams(param);
                    txt3.setTextSize(getResources().getDimension(R.dimen.texto_filas_tabla_estacion_monitoreo));
                    txt3.setGravity(Gravity.CENTER);

                    // txt3.setLayoutParams(lp);
                    tbrow0.addView(txt);
                    tbrow0.addView(txt2);
                    tbrow0.addView(txt3);
                    tabla_contaminacion.addView(tbrow0);
                }
            }
        });

        padre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkpadre) {
                    v.setAlpha(1);
                    checkpadre = true;
                    layoutTiempo.setVisibility(LinearLayout.GONE);
                    layoutTabla.setVisibility(LinearLayout.VISIBLE);
                } else {
                    v.setAlpha((float) 0.3);
                    checkpadre = false;
                    layoutTiempo.setVisibility(LinearLayout.VISIBLE);
                    layoutTabla.setVisibility(LinearLayout.GONE);
                }
                checkmuseo = false;
                checkencinas = false;
                encinas.setAlpha((float) 0.3);
                museo.setAlpha((float) 0.3);
                //---------------------metodos para borrar los datos de contaminacion y cambiar de estacion de monitoreo---------------------
                tabla_contaminacion.removeAllViews();
                  /*  int childCount = tabla_contaminacion.getChildCount();

                    // Remove all rows except the first one
                    if (childCount > 1) {
                        tabla_contaminacion.removeViews(1, childCount - 1);
                    }
*/
                //---------------------fin metodos para borrar los datos de contaminacion y cambiar de estacion de monitoreo---------------------
                for (Contaminacion museos : listaPadreLasCasas) {
                    TableRow tbrow0 = new TableRow(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    TableRow.LayoutParams param = new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    param.height = (int) getResources().getDimension(R.dimen.fila_tabla_estacion_monitoreo);
                    TextView txt = new TextView(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    txt.setText(museos.getHora());
                    // txt.setText("asd");
                    txt.setTextColor(Color.WHITE);
                    txt.setLayoutParams(param);
                    txt.setGravity(Gravity.CENTER);
                    txt.setTextSize(getResources().getDimension(R.dimen.texto_filas_tabla_estacion_monitoreo));

                    TextView txt2 = new TextView(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    txt2.setText(museos.getMp25());
                    //txt2.setText("asd2");
                    txt2.setTextColor(Color.WHITE);
                    txt2.setLayoutParams(param);
                    txt2.setGravity(Gravity.CENTER);
                    txt2.setTextSize(getResources().getDimension(R.dimen.texto_filas_tabla_estacion_monitoreo));

                    //  txt2.setLayoutParams(lp);
                    TextView txt3 = new TextView(PaginaPrincipal.this.tabla_contaminacion.getContext());
                    txt3.setText(museos.getMp10());
                    //txt3.setText("asd2");
                    txt3.setTextColor(Color.WHITE);
                    txt3.setLayoutParams(param);
                    //txt3.setTextSize(getResources().getDimension(R.dimen.activity_horizontal_margin));
                    txt3.setGravity(Gravity.CENTER);
                    txt3.setTextSize(getResources().getDimension(R.dimen.texto_filas_tabla_estacion_monitoreo));
                    // txt3.setLayoutParams(lp);
                    tbrow0.addView(txt);
                    tbrow0.addView(txt2);
                    tbrow0.addView(txt3);
                    tabla_contaminacion.addView(tbrow0);
                }
            }
        });


        diaAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecha = fechaObj.stringToDate(fechaString);
                Date fechaF = fechaObj.stringToDate(fechaFija);
                fechaF = fechaObj.sumarRestarDiasFecha(fechaF, -1);
                if (fecha.after(fechaF)) {
                    fecha = fechaObj.sumarRestarDiasFecha(fecha, -1);
                    fechaString = fechaObj.dateToString(fecha);
                    String[] datoFecha = fechaObj.dividirFecha(fechaString);
                    diafechaText.setText(fechaObj.mayusculaDia(datoFecha[0]));
                    fechaText.setText(datoFecha[1]);
                    mesfechaText.setText(fechaObj.mayusculaMes(datoFecha[2]));
                }
            }
        });

        diaDespues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecha = fechaObj.stringToDate(fechaString);
                Date fechaF = fechaObj.stringToDate(fechaFija);
                fechaF = fechaObj.sumarRestarDiasFecha(fechaF, 1);
                if (fecha.before(fechaF)) {
                    fecha = fechaObj.sumarRestarDiasFecha(fecha, 1);
                    fechaString = fechaObj.dateToString(fecha);
                    String[] datoFecha = fechaObj.dividirFecha(fechaString);
                    diafechaText.setText(fechaObj.mayusculaDia(datoFecha[0]));
                    fechaText.setText(datoFecha[1]);
                    mesfechaText.setText(fechaObj.mayusculaMes(datoFecha[2]));
                }
            }
        });


        AsyncCallWS task = new AsyncCallWS();
        //ejecuta
        task.execute();

        // fechaText.setText(listaMuseoFerroviario.size());
        condicion_emergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //borrar desde aqui
                Condicion condicion = new Condicion();
                condicion.start();
                //borrar asta aqui
                layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView1 = layoutInflater.inflate(R.layout.pop_up_res1, null);
                popupWindow1 = new PopupWindow(popupView1, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                popupView2 = layoutInflater.inflate(R.layout.pop_up_res2, null);
                popupWindow2 = new PopupWindow(popupView2, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                popupView3 = layoutInflater.inflate(R.layout.pop_up_res3, null);
                popupWindow3 = new PopupWindow(popupView3, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                popupView_sinres = layoutInflater.inflate(R.layout.pop_up_sin_res, null);
                popupWindow_sinres = new PopupWindow(popupView_sinres, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);

                //boton pop_sin_res
                btn_cerrar_sinres = (ImageButton) popupView_sinres.findViewById(R.id.cerrarSinRes);
                btn_cerrar_sinres.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        popupWindow_sinres.dismiss();
                    }
                });
                //--------------------------------------------------------
                //botones pop1
                btn_Cerrar1 = (ImageButton) popupView1.findViewById(R.id.cerrarRes1);
                btn_Cerrar1.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        popupWindow1.dismiss();
                    }
                });
                btn_der1 = (ImageButton) popupView1.findViewById(R.id.derechaRes1);
                btn_der1.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //cerar pop1 y abrir pop2
                        popupWindow1.dismiss();
                        popupWindow2.showAtLocation(popupView3, 1, 1, 0);
                    }
                });
                //-----------------------------------------------------
                //botones pop2
                btn_Cerrar2 = (ImageButton) popupView2.findViewById(R.id.cerrarRes2);
                btn_Cerrar2.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        popupWindow2.dismiss();
                    }
                });

                btn_der2 = (ImageButton) popupView2.findViewById(R.id.derechaRes2);
                btn_der2.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //cerar pop2 y abrir pop3
                        popupWindow2.dismiss();
                        popupWindow3.showAtLocation(popupView3, 1, 1, 0);
                    }
                });
                btn_izq2 = (ImageButton) popupView2.findViewById(R.id.izquerdaRes2);
                btn_izq2.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //cerar pop2 y abrir pop1
                        popupWindow2.dismiss();
                        popupWindow1.showAtLocation(popupView1, 1, 1, 0);
                    }
                });
                //--------------------------------------------
                //botones pop3
                btn_Cerrar3 = (ImageButton) popupView3.findViewById(R.id.cerrarRes3);
                btn_Cerrar3.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        popupWindow3.dismiss();
                    }
                });
                btn_izq3 = (ImageButton) popupView3.findViewById(R.id.izquerdaRes3);
                btn_izq3.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //cerar pop3 y abrir pop2
                        popupWindow3.dismiss();
                        popupWindow2.showAtLocation(popupView1, 1, 1, 0);
                    }
                });
                //--------------------------------------------

                if (condicion.getTipoAlerta().equals("Restricción") || condicion.getTipoAlerta().equals("RESTRICCIÓN") ||
                        condicion.getTipoAlerta().equals("restricción") || condicion.getTipoAlerta().equals("restriccion")) {
                    popupWindow1.showAtLocation(popupView1,1,1,0);
                } else if (condicion.getTipoAlerta().equals("Sin Restricción") || condicion.getTipoAlerta().equals("SIN RESTRICCIÓN") ||
                        condicion.getTipoAlerta().equals("sin restricción") || condicion.getTipoAlerta().equals("sin restriccion")
                        ) {
                    popupWindow_sinres.showAtLocation(popupView_sinres,1,1,0);
                }

            }
        });

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
                Intent intent = new Intent(PaginaPrincipal.this, PaginaPrincipal.class);
                AlertDialog.Builder dialogo = new AlertDialog.Builder(PaginaPrincipal.this);

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

    //Crea tarea Asincronica (2 plano)
    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            Log.i(TAG, "doInBackground");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            // fechaText.setText(listaPadreLasCasas.size() + "");
            if (tipoCondicion.getText().equals("SIN CONEXIÓN") || tipoCondicion.getText().equals("Sin Conexión") ||
                    tipoCondicion.getText().equals("sin conexión") || tipoCondicion.getText().equals("sin conexion") ||
                    tipoCondicion.getText().equals("sinconexión") || tipoCondicion.getText().equals("SINCONEXIÓN")) {
                tipoCondicion.setText("SIN CONEXIÓN");
                diaDespues.setVisibility(LinearLayout.GONE);
                diaAtras.setVisibility(LinearLayout.GONE);
                padre.setVisibility(LinearLayout.GONE);
                encinas.setVisibility(LinearLayout.GONE);
                museo.setVisibility(LinearLayout.GONE);
                layoutTiempo.setVisibility(LinearLayout.GONE);
            } else {
                diaDespues.setVisibility(LinearLayout.VISIBLE);
                diaAtras.setVisibility(LinearLayout.VISIBLE);
                padre.setVisibility(LinearLayout.VISIBLE);
                encinas.setVisibility(LinearLayout.VISIBLE);
                museo.setVisibility(LinearLayout.VISIBLE);
                layoutTiempo.setVisibility(LinearLayout.VISIBLE);
            }
        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
            //  txtResult.setText("Calculando...");
            try {
                Condicion condicion = new Condicion();
                if (isOnline()) {
                    condicion.start();
                    if (condicion.getTipoAlerta().equals("Sin Información") || condicion.getTipoAlerta().equals("SIN INFORMACIÓN") ||
                            condicion.getTipoAlerta().equals("sin información") || condicion.getTipoAlerta().equals("sin informacion")
                            ) {
                        informacion_restriccion.setImageResource(R.drawable.sin_informacion_bar);

                    } else if (condicion.getTipoAlerta().equals("Restricción") || condicion.getTipoAlerta().equals("RESTRICCIÓN") ||
                            condicion.getTipoAlerta().equals("restricción") || condicion.getTipoAlerta().equals("restriccion")) {

                        informacion_restriccion.setImageResource(R.drawable.restriccion);
                    } else if (condicion.getTipoAlerta().equals("Sin Restricción") || condicion.getTipoAlerta().equals("SIN RESTRICCIÓN") ||
                            condicion.getTipoAlerta().equals("sin restricción") || condicion.getTipoAlerta().equals("sin restriccion")
                            ) {

                        informacion_restriccion.setImageResource(R.drawable.sin_restriccion);
                    }


                    if (condicion.getTipoCondicion().equals("alerta") || condicion.getTipoCondicion().equals("Alerta") || condicion.getTipoCondicion().equals("ALERTA")) {
                        tipoCondicion.setText("ALERTA");
                        mensaje = "alerta";
                        drawerLayout.setBackgroundResource(R.drawable.background_alerta_regularcondition);
                    } else if (condicion.getTipoCondicion().equals("bueno") || condicion.getTipoCondicion().equals("Bueno") || condicion.getTipoCondicion().equals("BUENO")) {
                        tipoCondicion.setText("BUENO");
                        mensaje = "bueno";
                        drawerLayout.setBackgroundResource(R.drawable.background_buenocondition);
                    } else if (condicion.getTipoCondicion().equals("regular") || condicion.getTipoCondicion().equals("Regular") || condicion.getTipoCondicion().equals("REGULAR")) {
                        tipoCondicion.setText("REGULAR");
                        mensaje = "regular";
                        drawerLayout.setBackgroundResource(R.drawable.background_alerta_regularcondition);
                    } else if (condicion.getTipoCondicion().equals("preemergencia") || condicion.getTipoCondicion().equals("Preemergencia") || condicion.getTipoCondicion().equals("PREEMERGENCIA")) {
                        tipoCondicion.setText("PREEMERGENCIA");
                        mensaje = "preemergencia";
                        drawerLayout.setBackgroundResource(R.drawable.background_preemergenciacondition);
                    } else if (condicion.getTipoCondicion().equals("emergencia") || condicion.getTipoCondicion().equals("Emergencia") || condicion.getTipoCondicion().equals("EMERGENCIA")) {
                        tipoCondicion.setText("EMERGENCIA");
                        mensaje = "emergencia";
                        drawerLayout.setBackgroundResource(R.drawable.background_emergenciacondition);
                    } else if (condicion.getTipoAlerta().equals("Sin Información") || condicion.getTipoAlerta().equals("SIN INFORMACIÓN") ||
                            condicion.getTipoAlerta().equals("sin información") || condicion.getTipoAlerta().equals("sin informacion")
                            ) {
                        tipoCondicion.setText("SIN INFORMACIÓN");
                        mensaje = "sin informacion";
                        drawerLayout.setBackgroundResource(R.drawable.background_sininformacioncondition);

                    }

                    estaciones = new EstacionMonitoreo();

                    listaMuseoFerroviario = estaciones.getListaContaminacion1();
                    //Collections.reverse(listaMuseoFerroviario);
                    listaLasEncinas = estaciones.getListaContaminacion2();
                    // Collections.reverse(listaLasEncinas);
                    listaPadreLasCasas = estaciones.getListaContaminacion3();

                    // Collections.reverse(listaPadreLasCasas);
                } else {

                    drawerLayout.setBackgroundResource(R.drawable.background_sininformacioncondition);
                    informacion_restriccion.setImageResource(R.drawable.sin_conexion_bar);
                    tipoCondicion.setText("SIN CONEXIÓN");
                }

            } catch (NullPointerException e) {
                drawerLayout.setBackgroundResource(R.drawable.background_sininformacioncondition);
                informacion_restriccion.setImageResource(R.drawable.sin_conexion_bar);
                tipoCondicion.setText("SIN CONEXIÓN");
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i(TAG, "onProgressUpdate");

        }

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
