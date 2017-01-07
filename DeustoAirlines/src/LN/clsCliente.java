package LN;
/**
 * Clase que hace posible crear un  objeto clsCliente.
 * Desciende de clsPersona
 */
public class clsCliente extends clsPersona
{
	private static final long serialVersionUID = 1L;
	String correo_cliente;


	public clsCliente(String correo_cliente) 
	{
		super();
		this.correo_cliente = correo_cliente;
	}
	
	public String getCorreo_cliente() 
	{
			return correo_cliente;
	}

	public void setCorreo_cliente(String correo_cliente) 
	{
		this.correo_cliente = correo_cliente;
	}

	/** 
	 * Método que hace posible sacar los datos del cliente por pantalla
	 */
	public String toString()
	{		
	
		StringBuffer salida = new StringBuffer();
	
		salida.append(super.toString());
		salida.append("Correo electrónico:");
		salida.append(this.getCorreo_cliente());
	
		return salida.toString();
	}

	
}
