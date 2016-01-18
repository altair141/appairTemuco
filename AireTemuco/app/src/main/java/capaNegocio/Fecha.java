package capaNegocio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by altair141 on 02-11-2015.
 */
public class Fecha {
    private Date dia;

    public Fecha() {
    }

    public Fecha(Date dia) {
        this.dia = dia;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public  Date stringToDate(String fecha) {
        if (validarFecha(fecha)) {
            try {

                DateFormat f = new SimpleDateFormat("EEEE-dd-MM");
                String str1 = fecha;
                Date date = f.parse(str1);
                return date;

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            } catch (NullPointerException c) {
                return null;
            }
        }
        return null;
    }
    public  Date stringToDate2(String fecha) {
        if (validarFecha2(fecha)) {
            try {

                DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String str1 = fecha;
                Date date = f.parse(str1);
                return date;

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            } catch (NullPointerException c) {
                return null;
            }
        }
        return null;
    }
    private boolean validarFecha(String fecha) {
        try {
            DateFormat f = new SimpleDateFormat("EEEE-dd-MM");
            String str1 = fecha;
            Date date = f.parse(str1);
            return true;
        } catch (ParseException e) {
            return false;
        } catch (NullPointerException c) {
            return false;
        }

    }
    private boolean validarFecha2(String fecha) {
        try {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String str1 = fecha;
            Date date = f.parse(str1);
            return true;
        } catch (ParseException e) {
            return false;
        } catch (NullPointerException c) {
            return false;
        }

    }
    public String dateToString(Date date){

        DateFormat f = new SimpleDateFormat("EEEE-dd-MM");
        String fecha=f.format(date);
        return fecha;
    }
    public String dateToString2(Date date){

        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String fecha=f.format(date);
        return fecha;
    }
    public String generarFecha(){
        Date date = new Date();
        DateFormat f = new SimpleDateFormat("EEEE-dd-MM");
        String fecha=f.format(date);

        return fecha;
    }
    public String generarFecha2(){
        Date date = new Date();
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String fecha=f.format(date);

        return fecha;
    }
    public  Date sumarRestarDiasFecha(Date fecha, int dias){
        System.out.println(fecha.toString()+"-p--");
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
        //return new Date();
    }
    public String [] dividirFecha(String fecha){
        String []fechaDividida=fecha.split("-");
        return fechaDividida;
    }

    public String mayusculaDia(String dia){
        if(dia.equals("lunes")){
            return "Lunes";
        }else if(dia.equals("martes")){
            return "Martes";
        }else if(dia.equals("miércoles")){
            return "Miércoles";
        }else if(dia.equals("jueves")){
            return "Jueves";
        }else if(dia.equals("viernes")){
            return "Viernes";
        }else if(dia.equals("sábado")){
            return "Sábado";
        }else if(dia.equals("domingo")){
            return "Domingo";
        }
        else{
            return dia;
        }
    }
    public String mayusculaMes(String mes){
        if(mes.equals("enero")||mes.equals("01")){
            return "Enero";
        }else if(mes.equals("febrero")||mes.equals("02")){
            return "Febrero";
        }else if(mes.equals("marzo")||mes.equals("03")){
            return "Marzo";
        }else if(mes.equals("abril")||mes.equals("04")){
            return "Abril";
        }else if(mes.equals("mayo")||mes.equals("05")){
            return "Mayo";
        }else if(mes.equals("junio")||mes.equals("06")){
            return "Junio";
        }else if(mes.equals("julio")||mes.equals("07")){
            return "Julio";
        }else if(mes.equals("agosto")||mes.equals("08")){
            return "Agosto";
        }else if(mes.equals("septiembre")||mes.equals("09")){
            return "Septiembre";
        }else if(mes.equals("octubre")||mes.equals("10")){
            return "Octubre";
        }else if(mes.equals("noviembre")||mes.equals("11")){
            return "Noviembre";
        }else if(mes.equals("diciembre")||mes.equals("12")){
            return "Diciembre";
        }else{
            return mes;
        }
    }

}
