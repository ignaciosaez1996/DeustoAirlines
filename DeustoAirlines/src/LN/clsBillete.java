package LN;

import java.io.Serializable;

/**
 * Clase que hace posible crear un objeto clsBillete.
 * Implementa Serializable.
 */
public class clsBillete implements Serializable
{
	private static final long serialVersionUID = 1L;

	int precio;
	String codigo_billete;
	String Cod_vuelo;
	
	
	public String getCod_vuelo() {
		return Cod_vuelo;
	}
	public void setCod_vuelo(String cod_vuelo) {
		Cod_vuelo = cod_vuelo;
	}
	public double getPrecio() 
	{
		return precio;
	}
	public void setPrecio(int precio) 
	{
		this.precio = precio;
	}
	public String getCodigo_billete() 
	{
		return codigo_billete;
	}
	public void setCodigo_billete(String codigo_billete) 
	{
		this.codigo_billete = codigo_billete;
	}
	
	/** 
	 * Método que hace posible sacar los datos del billete por pantalla
	 */
	@Override
	public String toString() 
	{
		StringBuffer retorno = new StringBuffer();
		retorno.append("-------BILLETE-------"+"\n");
		retorno.append("Código de billete: " + this.codigo_billete+"\n");
		retorno.append("Precio: "+ this.precio+"\n");
		retorno.append("Código de vuelo: "+ this.Cod_vuelo+"\n");
		return retorno.toString();
	}
	
	
	
	
	
}
