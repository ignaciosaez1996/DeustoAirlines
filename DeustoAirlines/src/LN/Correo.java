package LN;

/**
 * Clase que hace posible crear un objeto correo.
 */
public class Correo 
{
	String usuarioCorreo;
	String contrasenya;
	String rutaArchivo;
	String nombreArchivo;
	String destino;
	String asunto;
	String mensaje;
	
	public String getUsuarioCorreo()
	{
		return usuarioCorreo;
	}
	public void setUsuarioCorreo(String usuarioCorreo)
	{
		this.usuarioCorreo = usuarioCorreo;
	}
	public String getContrasenya()
	{
		return contrasenya;
	}
	public void setContrasenya(String contrasenya)
	{
		this.contrasenya = contrasenya;
	}
	public String getRutaArchivo()
	{
		return rutaArchivo;
	}
	public void setRutaArchivo(String rutaArchivo) 
	{
		this.rutaArchivo = rutaArchivo;
	}
	public String getNombreArchivo()
	{
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo)
	{
		this.nombreArchivo = nombreArchivo;
	}
	public String getDestino()
	{
		return destino;
	}
	public void setDestino(String destino)
	{
		this.destino = destino;
	}
	public String getAsunto()
	{
		return asunto;
	}
	public void setAsunto(String asunto)
	{
		this.asunto = asunto;
	}
	public String getMensaje() 
	{
		return mensaje;
	}
	public void setMensaje(String mensaje)
{
		this.mensaje = mensaje;
	}
}
