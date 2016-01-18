package capaNegocio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by altair141 on 02-11-2015.
 */
public class EstacionMonitoreo {
    private String url;
    private int id;
    private List<Contaminacion> listaContaminacion1=new ArrayList<Contaminacion>();
    private List<Contaminacion> listaContaminacion2=new ArrayList<Contaminacion>();
    private List<Contaminacion> listaContaminacion3=new ArrayList<Contaminacion>();

    public EstacionMonitoreo(){

        estacionLasEncinas();

        estacionPadreLasCasas();

        estacionMuseoFerroviario();
    }

    private void estacionPadreLasCasas(){
        this.setUrl("http://sinca.mma.gob.cl/cgi-bin/registrostable.cgi?domain=CONAMA&stn=902&output=Padre%20Las%20Casas%20II");
        coneccionHtml(this.url,3);
    }

    private void estacionLasEncinas(){
        this.setUrl("http://sinca.mma.gob.cl/cgi-bin/registrostable.cgi?domain=CONAMA&stn=901&output=Las%20Encinas%20Temuco");
        coneccionHtml(this.url,2);
    }
    private void estacionMuseoFerroviario(){
        this.setUrl("http://sinca.mma.gob.cl/cgi-bin/registrostable.cgi?domain=CONAMA&stn=904&output=Museo%20Ferroviario");
        coneccionHtml(this.url,1);
    }


    public List<Contaminacion> getListaContaminacion1() {
        return listaContaminacion1;
    }

    public void setListaContaminacion1(List<Contaminacion> listaContaminacion) {
        this.listaContaminacion1 = listaContaminacion;
    }

    public void agregarContaminacion1(Contaminacion contaminacion){
        this.listaContaminacion1.add(contaminacion);
    }
    public List<Contaminacion> getListaContaminacion2() {
        return listaContaminacion2;
    }

    public void setListaContaminacion2(List<Contaminacion> listaContaminacion) {
        this.listaContaminacion2 = listaContaminacion;
    }

    public void agregarContaminacion2(Contaminacion contaminacion){
        this.listaContaminacion2.add(contaminacion);
    }
    public List<Contaminacion> getListaContaminacion3() {
        return listaContaminacion3;
    }

    public void setListaContaminacion3(List<Contaminacion> listaContaminacion) {
        this.listaContaminacion3 = listaContaminacion;
    }

    public void agregarContaminacion3(Contaminacion contaminacion){
        this.listaContaminacion3.add(contaminacion);
    }

    private void coneccionHtml(String url, int identificador) {
        Fecha fecha=new Fecha();
       String fechaStringhoy= fecha.generarFecha2();
        Date fechaHoy=fecha.stringToDate2(fechaStringhoy);
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        Elements tables = doc.getElementsByClass("gob");

        Element table = tables.get(1);
        Elements tbody = table.getElementsByTag("tbody");
        Element trBody = tbody.get(0);
        Elements rows = table.getElementsByTag("TR");
        // Elements rows = trBody.getElementsByTag("TR");

        for (int j = rows.size() - 1; j > 0; j--) {
            // Elements tds = row.getElementsByTag("TD");
            Elements tds = rows.get(j).getElementsByTag("TD");
            Contaminacion contaminacion = new Contaminacion();
            for (int i = 0; i < tds.size(); i++) {
                if (!tds.get(i).text()
                        .equals("UTC-4 horario de invierno Chile continental")
                        && !tds.get(i).text().equals("Información preliminar")) {


                   // if(fechaHoy.before(fecha.stringToDate2(tds.get(i).text()))) {
                    //if(fecha.stringToDate2(tds.get(i).text()).after(fechaHoy)) {
                    //if(fechaHoy.before()){


                    if (i == 0) {
                        System.out.println("fecha" + tds.get(i).text() + " " + i);
                        System.out.println(fechaStringhoy);
                        contaminacion.setDia(tds.get(i).text());

                    }
                        if (i == 1) {
                            System.out.println("hora " + tds.get(i).text() + " " + i);
                            contaminacion.setHora(tds.get(i).text());
                        }

                        if (i == 2) {
                            System.out.println("mp2,5 " + tds.get(i).text() + " " + i);
                            String mp2 = tds.get(i).text();
                            if (mp2.equals("")) {
                                contaminacion.setMp25("");
                            } else {
                                contaminacion.setMp25(mp2 + "\nµg/m3");
                            }
                        }

                        if (i == 3) {
                            System.out.println("mp10 " + tds.get(i).text() + " " + i);
                            String mp1 = tds.get(i).text();
                            if (mp1.equals("")) {
                                contaminacion.setMp10("");
                            } else {
                                contaminacion.setMp10(mp1 + "\nµg/m3");
                            }

                        }
                   // }
                }
            }

            if(contaminacion.getHora()!=null &&fechaStringhoy.equals(fecha.dateToString2(contaminacion.getDia().getDia()))){
                if (identificador == 1) {
                    this.agregarContaminacion1(contaminacion);
                } else if (identificador == 2) {
                    this.agregarContaminacion2(contaminacion);
                } else if (identificador == 3) {
                    this.agregarContaminacion3(contaminacion);
                }
            }
        }
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

