package LN;

import java.io.Serializable;

/**
 * Clase que será base para crear objetos clsCliente y clsTrabajador, ya que no se creará ningún objeto clsPersona en todo el
 * programa.
 * Implementa Serializable
 */
public class clsPersona implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String contrasenya;

	public clsPersona()
	{
		
	}
	
	public clsPersona(String nombre, String contrasenya) 
	{
		super();
		this.nombre = nombre;
		this.contrasenya = contrasenya;
	}
	
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
	
	/** 
	 * Método que hace posible sacar los datos de la persona por pantalla
	 */
	public String toString()
	{		
		StringBuffer salida = new StringBuffer();
		
		salida.append("Nombre: ");
		salida.append(this.getNombre());
		salida.append("Contraseña:");
		salida.append(this.getContrasenya());
		
		return salida.toString();
	}
	
	
	
	
	
	
	
	
	

}
