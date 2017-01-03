package test;


import java.util.ArrayList;



import LD.BasesDeDatos;
import LN.GestorTrabajador;
import LN.clsVuelo;
import junit.framework.TestCase;
import static org.junit.Assert.*;



// testear si añade bien un nuevo cliente
public class JUnitTesting extends TestCase
{

	GestorTrabajador gc;
	ArrayList <clsVuelo> vuelo;
	
	
	public void setUp() throws Exception 
	{

		gc = new GestorTrabajador();
		vuelo = new ArrayList<clsVuelo>();
		BasesDeDatos.initBD("test.bd");
		BasesDeDatos.crearTablaVueloBD();
	}
	
	
	
	
	
	
}
