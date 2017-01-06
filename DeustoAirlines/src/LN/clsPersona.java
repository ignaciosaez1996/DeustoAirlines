package LN;

import java.io.Serializable;

public class clsPersona implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variables de la clase clsPersona
	 */
	private String nombre;

	/**
	 * Constructor vac�o de la clase clsPersona
	 */
	
	public clsPersona()
	{
		
	}
	
	/**
	 * Constructor con parametros de la clase clsPersona
	 * @param nombre
	 * @param contrasenya
	 */
	
	public clsPersona(String nombre, String contrasenya) 
	{
		super();
		this.nombre = nombre;
		this.contrasenya = contrasenya;
	}
	
	
	private String contrasenya;
	
	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public String getContrasenya() 
	{
		return contrasenya;
	}
	public void setContrasenya(String contrasenya)
	{
		this.contrasenya = contrasenya;
	}
	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	
	
	public String toString()
	{		
		StringBuffer salida = new StringBuffer();
		
		salida.append("Nombre: ");
		salida.append(this.getNombre());
		salida.append("Contrasenya:");
		salida.append(this.getContrasenya());
		
		return salida.toString();
	}
	
	
	
	
	
	
	
	
	

}
