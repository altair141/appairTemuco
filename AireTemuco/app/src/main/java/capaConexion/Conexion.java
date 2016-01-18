package capaConexion;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import capaNegocio.DiaWeather;
import capaNegocio.Weather;

/**
 * Created by altair141 on 27-11-2015.
 */
public class Conexion {
    public final String urlDias16 = "http://api.openweathermap.org/data/2.5/forecast/day?cnt=16&mode=json&units=metric&q=Temuco&appid=f9aca80935031c0378572448713caf2a";
    public final String urlDia = "http://api.openweathermap.org/data/2.5/weather?q=Temuco&units=metric&appid=f9aca80935031c0378572448713caf2a";

    public String getWeatherData(String url) {
        HttpURLConnection con = null;
        InputStream is = null;

        try {
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }

        return null;

    }


    public DiaWeather transformarInformacionDia(String json) {
        DiaWeather infoDia = new DiaWeather();
        try {
            JsonElement jelement = new JsonParser().parse(json);
            JsonObject jobject = jelement.getAsJsonObject();
            JsonObject jobject3 = jobject.get("main").getAsJsonObject();
            infoDia.setTemperatura((int)jobject3.get("temp").getAsDouble());
            JsonArray list = jobject.getAsJsonArray("weather");
            for (int i = 0; i < list.size(); i++) {
                JsonObject jobject2 = list.get(i).getAsJsonObject();
                infoDia.setCondicion(jobject2.get("description").getAsString());
            }
        } catch (NullPointerException e) {

        } catch (UnsupportedOperationException e) {

        } catch (IllegalStateException e) {

        }
        return infoDia;
    }

    public List<Weather> transformarInformacionOtrosDias(String json) {
        System.out.println(json);
        List<Weather> lista = new ArrayList<Weather>();
try {
    JsonElement jelement = new JsonParser().parse(json);
    JsonObject jobject = jelement.getAsJsonObject();
    JsonArray list = jobject.getAsJsonArray("list");
    for (int i = 0; i < list.size(); i++) {
        Weather clima = new Weather();
        JsonObject jobject2 = list.get(i).getAsJsonObject();
        String fecha = jobject2.get("dt_txt").getAsString();
        clima.setFecha(stringtodate(fecha));
        JsonObject tempera = jobject2.get("main").getAsJsonObject();
        clima.setTemperatura((int) tempera.get("temp").getAsDouble());
        JsonArray list2 = jobject2.getAsJsonArray("weather");
        for (int j = 0; j < list2.size(); j++) {
            JsonObject jobject3 = list2.get(j).getAsJsonObject();
            clima.setCondicion(jobject3.get("description").getAsString());
        }
        lista.add(clima);
    }
    System.out.println(lista.size() + "total lista");
}catch (NullPointerException e) {
    System.out.println("lista vacia");
} catch (UnsupportedOperationException e) {
    System.out.println("lista no soportada");
} catch (IllegalStateException e) {
    System.out.println("lista ilegal");
}
        return lista;
    }

    public static Date stringtodate(String fecha) {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str1 = fecha;
        Date date;
        try {
            date = f.parse(str1);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public DiaWeather establecerInformacion() {
        DiaWeather dia = new DiaWeather();
        try {
            String jsonDia = getWeatherData(urlDia);
            String jsondiasSiguientes = getWeatherData(urlDias16);
            System.out.println(jsondiasSiguientes);
            dia = transformarInformacionDia(jsonDia);
            List<Weather> listaDiasSiguientes = transformarInformacionOtrosDias(jsondiasSiguientes);
            dia.setLista(listaDiasSiguientes);
        } catch (NullPointerException e) {
            System.out.println("lista vvvv");
        } catch (UnsupportedOperationException e) {
            System.out.println("lista unsu");
        } catch (IllegalStateException e) {
            System.out.println("lista xcds");
        }
        return dia;
    }
}
