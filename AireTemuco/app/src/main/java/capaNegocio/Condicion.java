package capaNegocio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by altair141 on 02-11-2015.
 */
public class Condicion {
    private String tipoAlerta;
    private String tipoCondicion;

    public Condicion() {

    }

    public String getTipoAlerta() {

        return tipoAlerta;
    }

    public String getTipoCondicion() {
        return tipoCondicion;
    }

    public void setTipoCondicion(String tipoCondicion) {
        this.tipoCondicion = tipoCondicion;
    }

    public void setTipoAlerta(String tipoAlerta) {

        this.tipoAlerta = tipoAlerta;
    }

    public void start(){
        Document doc = null;


        try {
            doc = Jsoup.connect("http://www.airetemuco.cl/").get();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        Elements tables = doc.getElementsByClass("r_dato");
        Elements tables2 = doc.getElementsByClass("r_condicion_sininfo");
        this.setTipoAlerta(tables.get(0).text());
        this.setTipoCondicion(tables2.get(0).text());
    }

}
