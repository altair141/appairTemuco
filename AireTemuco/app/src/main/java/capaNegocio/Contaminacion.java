package capaNegocio;

import java.util.Date;

/**
 * Created by altair141 on 02-11-2015.
 */
public class Contaminacion {
    private Condicion condicion;
    private Fecha dia;
    private String hora;
    private String mp25;
    private String mp10;


    public Contaminacion() {
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public Fecha getDia() {
        return dia;
    }

    public void setDia(String diaString) {
        this.dia=new Fecha();
        this.dia.setDia(this.dia.stringToDate2(diaString));
    }

    public String getMp25() {
        return mp25;
    }

    public void setMp25(String mp25) {
        this.mp25 = mp25;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMp10() {
        return mp10;
    }

    public void setMp10(String mp10) {
        this.mp10 = mp10;
    }

}
