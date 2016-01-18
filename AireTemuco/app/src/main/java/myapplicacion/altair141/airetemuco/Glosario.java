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
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import capaNegocio.Condicion;
import capaObjetos.AdaptadorListaExpandible;

/**
 * Created by altair141 on 20-11-2015.
 */
public class Glosario extends AppCompatActivity {
    public float init_x;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private String mensaje="";

    //---------------------------------------
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;


// ---------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_glosario);
        Bundle bundle = this.getIntent().getExtras();
        mensaje=bundle.getString("tipoCondicion");
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
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(Glosario.this, PaginaPrincipal.class);
                    try {
                        Thread.sleep(400);
                    }
                    catch (InterruptedException e){}

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(Glosario.this);

                    dialogo.setTitle("Espere por favor");
                    dialogo.setMessage("Cargando datos...");

                    dialogo.create();
                    dialogo.show();
                    intent.putExtra("tipoCondicion", mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                else if(tituloMenu.equals("Recomendaciones")){
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(Glosario.this, Recomendacion.class);
                    intent.putExtra("tipoCondicion",mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                else if(tituloMenu.equals("Glosario")){
                    drawerLayout.closeDrawers();

                }
                else if (tituloMenu.equals("Zonas de Restricción")) {
                    drawerLayout.closeDrawers();
                    Intent intent = new Intent(Glosario.this, Mapa.class);

                    intent.putExtra("tipoCondicion", mensaje);
                    startActivity(intent);
                    setResult(Activity.RESULT_OK);
                    finish();
                }


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

        createGroupList();
        createCollection();


        expListView = (ExpandableListView) findViewById(R.id.laptop_list);
        final AdaptadorListaExpandible expListAdapter = new AdaptadorListaExpandible(
                this, groupList, laptopCollection);
        expListView.setAdapter(expListAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);


                return true;
            }
        });
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });





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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    private void cambioalphaBackground(){
        //laptopCollection
        //expListView.getAdapter().getView(2,2,2);

    }
    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Concentración de Material Particulado");
        groupList.add("Contaminación");
        groupList.add("Contaminación Atmosférica");
        groupList.add("Contaminante");
        groupList.add("Contaminante Primario");
        groupList.add("Contaminante Secundario");
        groupList.add("Emisiones Difusas");
        groupList.add("Emisiones Fugitivas");
        groupList.add("Estación de Monitoreo de Material Particulado Respirable");
        groupList.add("Exposición");
        groupList.add("Fracción Fina");
        groupList.add("Fracción Gruesa");
        groupList.add("Fuente de Contaminación Atmosférica Móvil o Fuente Móvil");
        groupList.add("Fuente Estacionaria o Fuente Fija");
        groupList.add("Material Particulado (MP)");
        groupList.add("Material Particulado Respirable");
        groupList.add("Microgramo (μg)");
        groupList.add("Norma de Emisión");
        groupList.add("Norma Primaria de Calidad Ambiental");
        groupList.add("Microgramo (μg)");
        groupList.add("Norma Secundaria de Calidad Ambiental");
        groupList.add("Ozono (O3)");
        groupList.add("Plan de Descontaminación");
        groupList.add("Plan de Prevención");
        groupList.add("MP 2.5");
        groupList.add("Promedio Móvil");
        groupList.add("Riesgo en Salud, Grupos");
        groupList.add("Validación");
        groupList.add("Zona Latente");
        groupList.add("Zona Saturada");

    }

    private void createCollection() {
        // preparing laptops collection(child)
        String[] c1 = { "El valor promedio temporal detectado en el aire en microgramos por metro cúbico normal (μg/m3N) de material particulado." };
        String[] c2 = { "La presencia en el ambiente de sustancias, elementos, energía o combinación de ellos, en concentraciones o concentraciones y permanencia superiores o inferiores, según corresponda, a las establecidas en la legislación vigente, de manera que puedan ser nocivos para la salud, seguridad o bienestar humano, la vida animal o vegetal, o impidan el aprovechamiento normal de un ecosistema." };
        String[] c3 = { "Presencia de contaminantes en la atmósfera, tales como polvo, gases o humo en cantidades y durante períodos de tiempo tales que resultan dañinos para los seres humanos, la vida silvestre y la propiedad.\n\nEstos contaminantes pueden ser de origen natural o producidos por el hombre directa o indirectamente." };
        String[] c4 = { "Todo elemento, compuesto, sustancia, derivado químico o biológico, energía, radiación, vibración, ruido, o una combinación de ellos, cuya presencia en el ambiente, en ciertos niveles, concentraciones o períodos de tiempo, pueda constituir un riesgo a la salud de las personas, a la calidad de vida de la población, a la preservación de la naturaleza o a la conservación del patrimonio ambiental." };
        String[] c5 = { "Contaminante producido directamente por la actividad humana o la naturaleza" };
        String[] c6 = { "Contaminante producido a partir de algún(os) contaminante(s) primario(s) y otras sustancias." };
        String[] c7 = {"Son aquellas emisiones, no necesariamente visibles, imposibles de canalizar por un ducto. \n\n Ejemplo de estas son los caminos de tierra, extracción de mineral, detonaciones, canchas de fundición, etc."};
        String[] c8= {"Emisiones que se escapan del sistema de captación, debido a un mal diseño o desperfectos en él.\n\nEstas emisiones pueden salir por ductos, filtros, campanas, etc."};
        String[] c9= {"Estación de Monitoreo de Material Particulado Respirable MP10 con Representatividad Poblacional para Gases (EMRPG)\n\nUna estación de monitoreo que se encuentra localizada en un área habitada.\n\nSe entiende como área habitada, una porción del territorio donde vive habitual y permanentemente un conjunto de personas."};
        String[] c10= {"Esta determinada por la cantidad de contaminante que estuvo en contacto con una persona, población o medio y el tiempo que dicho contaminante actúa directamente sobre esa persona.\n\nAlgunos de los agentes contaminantes presentan un comportamiento acumulativo, por lo tanto, mientras más tiempo permanezcan en un medio, el daño que causan se va acumulando o es mayor, como por ejemplo los rayos UV sobre la piel."};
        String[] c11= {"Es la fracción del MP10 con diámetro menor a 2,5 μm.\n\nDenominado también PM 2.5.\n\nContiene material particulado secundario (generado por la conversión de gas a sólido), partículas originarias de combustión y compuestos orgánicos y metales recondensados.\n\nContiene la mayor parte de la acidez y de la actividad mutagénica."};
        String[] c12= {"Es la fracción del MP10 mayor a 2,5 μm en diámetro aerodinámico.\n\nContiene material de la corteza terrestre, polvo fugitivo de caminos e industrias."};
        String[] c13= {"Es toda aquella fuente que tiene un elemento propulsor propio (motor), que es capaz de desplazarse entre distintos puntos pudiendo utilizar las vías públicas y que genera contaminantes."};
        String[] c14= {"Es toda fuente diseñada para operar en lugar fijo, cuyas emisiones se descargan a través de un ducto o chimenea.\n\nSe incluyen aquellas montadas sobre vehículos transportables para facilitar su desplazamiento."};
        String[] c15= {"Es una mezcla de partículas líquidas, sólidas o líquidas y sólidas suspendidas en el aire que difieren en tamaño, composición y origen.\n\nEl tamaño de las partículas suspendidas en la atmósfera varía en más de cuatro órdenes de magnitud, desde unos pocos nanómetros a decenas de micrómetros.\n\nEl Material Particulado conviene clasificarlo por sus propiedades aerodinámicas (Diámetro Aerodinámico), dado que éstas son un factor decisivo para el transporte y la remoción de las partículas desde el aire.\n\nTambién, son determinantes para la depositación en el sistema respiratorio y están asociadas con la composición química y las fuentes de esas partículas, cuando se habla del tamaño de una partícula, se está hablando de su diámetro aerodinámico."};
        String[] c16= {"Comprende las partículas de diámetro aerodinámico (d.a.) menor a 10 μm.\n\nRepresenta una mezcla compleja de substancias orgánicas e inorgánicas.\n\nEstas partículas penetran a lo largo de todo el sistema respiratorio hasta los pulmones, produciendo irritaciones e incidiendo en diversas enfermedades.\n\nDe acuerdo a masa y composición se tienden a dividir en dos grupos principales, MP Grueso, de d.a. mayor a 2,5 μm y menor a 10 μm y MP Fino menor a 2,5 μm en d.a., existiendo también el denominado MP ultrafino de alrededor de 0,1 μm."};
        String[] c17= {"Unidad de masa que corresponde a la millonésima parte de un gramo."};
        String[] c18= {"La que establece la cantidad máxima permitida para un contaminante, en forma de concentración o de emisión másica, medida en el efluente de la fuente emisora."};
        String[] c19= {"Unidad de masa que corresponde a la millonésima parte de un gramo."};
        String[] c20= {"Aquella que establece los valores de las concentraciones y períodos, máximos o mínimos permisibles de elementos, compuestos, sustancias, derivados químicos o biológicos, energías, radiaciones, vibraciones, ruidos o combinación de ellos, cuya presencia o carencia en el ambiente pueda constituir un riesgo para la vida o la salud de la población y definen los niveles que originan situaciones de emergencia.\n\nPor ejemplo, una Norma Primaria de Calidad del Aire establece límites para la presencia de contaminantes en la atmósfera, es decir el aire que respiramos, con el objeto de proteger la salud de las personas."};
        String[] c21= {"Aquélla que establece los valores de las concentraciones y períodos, máximos o mínimos permisibles de sustancias, elementos, energía o combinación de ellos, cuya presencia o carencia en el ambiente pueda constituir un riesgo para la protección o la conservación del medio ambiente, o la preservación de la naturaleza."};
        String[] c22= {"Molécula compuesta por tres átomos de oxígeno. Juega un papel esencial en la protección de los organismos vivos del planeta debido a que en la estratósfera actúa como filtro de la radiación ultravioleta dañina."};
        String[] c23= {"Según la legislación chilena es un instrumento de gestión ambiental destinado a reducir la presencia de contaminantes a los niveles fijados por las normas primarias o secundarias en una zona saturada."};
        String[] c24= {"Es un instrumento de gestión ambiental que, en una zona latente, busca evitar que las normas ambientales primarias o secundarias sean sobrepasadas."};
        String[] c25= {"Corresponde a la fracción fina del MP10, con un diámetro aerodinámico inferior a 2,5 μm, lo que les permite penetrar más por el sistema respiratorio llegando a los alvéolos pulmonares."};
        String[] c26= {"Es el promedio calculado para un lapso de horas a una hora determinada.\n\nPor ejemplo, el promedio móvil de 24 horas a una hora determinada, es el promedio de las concentraciones de cada hora, tomado para las 24 horas anteriores a dicha hora determinada."};
        String[] c27= {"En base a experiencia clínica se definen grupos de mayor riesgo, como aquellos compuestos por niños menores de cinco años y por adultos mayores de 65 años.\n\nDe especial importancia son aquellos que presentan problemas de salud de carácter crónico (respiratorio o cardiovascular)."};
        String[] c28={"Antes que los datos de monitoreo sean aceptados en una base de datos final, los datos erróneos deben ser filtrados o extraídos.\n\nEste proceso de “filtraje” recibe el nombre de validación, y corresponde a la verificación cuantitativa y cualitativa de la exactitud, integridad y consistencia de la información generada, tomando en consideración criterios cuantitativos y cualitativos.\n\nLos criterios cuantitativos hacen referencia a información histórica del sitio de medición, estado operacional de los equipos, calibraciones, rango de medición, etc.\n\nLos criterios cualitativos incorporan información relevante respecto de condiciones de la medición, observaciones adicionales, tales como meteorología u otros contaminantes, mediciones independientes de otras estaciones u otros sistemas de medición."};
        String[] c29={"Aquella área geográfica en que la medición de la concentración de contaminantes en el aire, agua o suelo se sitúa entre el 80% y el 100% del valor de la respectiva norma de calidad ambiental."};
        String[] c30={"Aquélla área geográfica en que una o más normas de calidad ambiental se encuentran sobrepasadas."};

        laptopCollection = new LinkedHashMap<String, List<String>>();

        for (String laptop : groupList) {
            if (laptop.equals("Concentración de Material Particulado")) {
                loadChild(c1);
            } else if (laptop.equals("Contaminación"))
                loadChild(c2);
            else if (laptop.equals("Contaminación Atmosférica"))
                loadChild(c3);
            else if (laptop.equals("Contaminante"))
                loadChild(c4);
            else if (laptop.equals("Contaminante Primario"))
                loadChild(c5);
            else if (laptop.equals("Contaminante Secundario"))
                loadChild(c6);
            else if (laptop.equals("Emisiones Difusas"))
                loadChild(c7);
            else if (laptop.equals("Emisiones Fugitivas"))
                loadChild(c8);
            else if (laptop.equals("Estación de Monitoreo de Material Particulado Respirable"))
                loadChild(c9);
            else if (laptop.equals("Exposición"))
                loadChild(c10);
            else if (laptop.equals("Fracción Fina"))
                loadChild(c11);
            else if (laptop.equals("Fracción Gruesa"))
                loadChild(c12);
            else if (laptop.equals("Fuente de Contaminación Atmosférica Móvil o Fuente Móvil"))
                loadChild(c13);
            else if (laptop.equals("Fuente Estacionaria o Fuente Fija"))
                loadChild(c14);
            else if (laptop.equals("Material Particulado (MP)"))
                loadChild(c15);
            else if (laptop.equals("Material Particulado Respirable"))
                loadChild(c16);
            else if (laptop.equals("Microgramo (μg)"))
                loadChild(c17);
            else if (laptop.equals("Norma de Emisión"))
                loadChild(c18);
            else if (laptop.equals("Norma Primaria de Calidad Ambiental"))
                loadChild(c19);
            else if (laptop.equals("Microgramo (μg)"))
                loadChild(c20);
            else if (laptop.equals("Norma Secundaria de Calidad Ambiental"))
                loadChild(c21);
            else if (laptop.equals("Ozono (O3)"))
                loadChild(c22);
            else if (laptop.equals("Plan de Descontaminación"))
                loadChild(c23);
            else if (laptop.equals("Plan de Prevención"))
                loadChild(c24);
            else if (laptop.equals("PM 2.5"))
                loadChild(c25);
            else if (laptop.equals("Promedio Móvil"))
                loadChild(c26);
            else if (laptop.equals("Riesgo en Salud, Grupos"))
                loadChild(c27);
            else if (laptop.equals("Validación"))
                loadChild(c28);
            else if (laptop.equals("Zona Latente"))
                loadChild(c29);
            else if (laptop.equals("Zona Saturada"))
                loadChild(c30);


            laptopCollection.put(laptop, childList);
        }
    }

    private void loadChild(String[] laptopModels) {
        childList = new ArrayList<String>();
        for (String model : laptopModels)
            childList.add(model);
    }

}
