package capaNegocio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiaWeather {



	private String condicion;
	private int temperatura;
	List<Weather> lista=new ArrayList<Weather>();


	public DiaWeather(){
	}


	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public List<Weather> getLista() {
		return lista;
	}

	public void setLista(List<Weather> lista) {
		this.lista = lista;
	}
	public void agregarInformacion(Weather diaSiguiente){
		this.lista.add(diaSiguiente);
	}
}