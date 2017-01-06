package LN;

import java.io.Serializable;

public class clsVuelo implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos de la clase clsVuelo
	 */
	String cod_vuelo;
	int capacidad;
	String fecha;
	String cod_postal_o;
	String cod_postal_d;
	double precio;
	
	/**
	 * Constructor con parametros de la clase clsVuelo
	 * @param cod_vuelo
	 * @param capacidad
	 * @param fecha
	 * @param cod_postal_o
	 * @param cod_postal_d
	 * @param precio
	 */
	public clsVuelo(String cod_vuelo, int capacidad, String fecha, String cod_postal_o, String cod_postal_d, double precio) 
	{
		super();
		this.cod_vuelo = cod_vuelo;
		this.capacidad = capacidad;
		this.fecha = fecha;
		this.cod_postal_o = cod_postal_o;
		this.cod_postal_d = cod_postal_d;
		this.precio = precio;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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
	public String getCod_postal_o() {
		return cod_postal_o;
	}

	public void setCod_postal_o(String cod_postal_o) {
		this.cod_postal_o = cod_postal_o;
	}

	public String getCod_postal_d() {
		return cod_postal_d;
	}

	public void setCod_postal_d(String cod_postal_d) {
		this.cod_postal_d = cod_postal_d;
	}

	public String toString()
	{		
		StringBuffer salida = new StringBuffer();
		
		salida.append("C�digo vuelo: ");
		salida.append(this.getCod_vuelo());
		salida.append("Capacidad");
		salida.append(this.getCapacidad());
		salida.append("Fecha:");
		salida.append(this.getFecha());
		salida.append("Codigo postal origen:");
		salida.append(this.getCod_postal_o());
		salida.append("Codigo postal destino:");
		salida.append(this.getCod_postal_d());
		salida.append("Precio por asiento:");
		salida.append(this.precio);
	
		return salida.toString();
	}
	
	
	
}
