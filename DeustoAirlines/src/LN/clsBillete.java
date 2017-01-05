package LN;

import java.io.Serializable;

public class clsBillete implements Serializable
{
	double precio;
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
	public void setPrecio(double precio) 
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
	@Override
	public String toString() {
		return "clsBillete [precio=" + precio + ", codigo_billete="
				+ codigo_billete + "]";
	}
	
	
	
	
	
}
