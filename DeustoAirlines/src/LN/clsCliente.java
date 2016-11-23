package LN;

public class clsCliente extends clsPersona
{
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

public String toString()
{		
	StringBuffer salida = new StringBuffer();
	
	salida.append("Nombre: ");
	salida.append(this.getNombre());
	salida.append("Contrasenya:");
	salida.append(this.getContrasenya());
	salida.append("Correo electrónico:");
	salida.append(this.getCorreo_cliente());
	
	
	
	return salida.toString();
}

	
}
