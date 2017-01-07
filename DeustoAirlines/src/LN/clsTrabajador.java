package LN;

/**
 * Clase que hace posible crear un  objeto clsCliente.
 * Desciende de clsPersona
 */
public class clsTrabajador extends clsPersona
{
	private static final long serialVersionUID = 1L;
	
	String DNI;
	String categoria;
	
	
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
	
	/** 
	 * Método que hace posible sacar los datos del trabajador por pantalla
	 */
	public String toString()
	{		
		StringBuffer salida = new StringBuffer();
	
		salida.append(super.toString());
		salida.append("DNI:");
		salida.append(this.getDNI());
		salida.append("Puesto de trabajo:");
		salida.append(this.getCategoria());
	
		return salida.toString();
	}
}
