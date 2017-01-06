package LN;

public class clsTrabajador extends clsPersona
{
	/**
	 * Parametros de la clase clsTrabajador
	 */
	String DNI;
	String categoria;
	
	
	/**
	 * Constructor de la clase clsTrabajador con parametros
	 * @param dNI
	 * @param categoria
	 */
	public clsTrabajador(String dNI, String categoria)
	{
		super();
		DNI = dNI;
		this.categoria = categoria;
	}
	
	public String getDNI() 
	{
		return DNI;
	}

	public void setDNI(String dNI) 
	{
		DNI = dNI;
	}

	public String getCategoria() 
	{
		return categoria;
	}
	
	public void setCategoria(String categoria) 
	{
		this.categoria = categoria;
	}
	
	public String toString()
	{		
		StringBuffer salida = new StringBuffer();
	
		salida.append("Nombre: ");
		salida.append(this.getNombre());
		salida.append("Contrasenya:");
		salida.append(this.getContrasenya());
		salida.append("DNI:");
		salida.append(this.getDNI());
		salida.append("Puesto de trabajo:");
		salida.append(this.getCategoria());
	
		return salida.toString();
	}
}
