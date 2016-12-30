package LN;

import java.io.Serializable;

public class clsVuelo implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String cod_vuelo;
	int capacidad;
	String fecha;
	
	
	public clsVuelo(String cod_vuelo, int capacidad, String fecha) 
	{
		super();
		this.cod_vuelo = cod_vuelo;
		this.capacidad = capacidad;
		this.fecha = fecha;
	}
	
	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getCod_vuelo() {
		return cod_vuelo;
	}

	public void setCod_vuelo(String cod_vuelo) {
		this.cod_vuelo = cod_vuelo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String toString()
	{		
		StringBuffer salida = new StringBuffer();
		
		salida.append("Código vuelo: ");
		salida.append(this.getCod_vuelo());
		salida.append("Capacidad");
		salida.append(this.getCapacidad());
		salida.append("Fecha:");
		salida.append(this.getFecha());
		
		
		return salida.toString();
	}
	
	
	
}
