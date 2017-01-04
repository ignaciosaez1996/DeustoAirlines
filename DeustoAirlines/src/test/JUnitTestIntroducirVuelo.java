package test;


import java.util.ArrayList;



import LD.BasesDeDatos;
import LN.GestorTrabajador;
import LN.clsVuelo;
import junit.framework.TestCase;
import static org.junit.Assert.*;



// testear si añade bien un nuevo vuelo
public class JUnitTestIntroducirVuelo extends TestCase
{

	GestorTrabajador gc;
	ArrayList <clsVuelo> vuelo;
	
	
	public void setUp() throws Exception 
	{

		gc = new GestorTrabajador();
		vuelo = new ArrayList<clsVuelo>();
		//BasesDeDatos.initBD("test.bd");
		BasesDeDatos.initBD("DeustoAirlinesBD");
		BasesDeDatos.crearTablaVueloBD();
	}
	
	public void tearDown() throws Exception 
	{
		BasesDeDatos.close();
	}
	
	public void testCrearVuelo()
	{
		
		gc.CrearVuelos(BasesDeDatos.getStatement(), "234244", "34", "2016/13/23", "442324", "6543354", 340);
		
		gc.CrearVuelos(BasesDeDatos.getStatement(), "787656", "213", "2017/19/23", "89790", "345345", 874);
		
		gc.CrearVuelos(BasesDeDatos.getStatement(), "65454", "120", "2018/23/23", "7876867", "234242", 44);
		
		int num1 = gc.DevolverVuelos(BasesDeDatos.getStatement(), "234244").size() + 1;
		
		gc.CrearVuelos(BasesDeDatos.getStatement(), "45656", "15", "2014/01/15", "89779", "879789", 97);
		
		int num2 = gc.DevolverVuelos(BasesDeDatos.getStatement(), "45656").size();
		
		assertEquals(num1, num2, 4);
	}
	
	public void testDevolverVuelos() 
	{
		
		String codigo = "2423";
		String codigo2 = "";
		
		gc.CrearVuelos(BasesDeDatos.getStatement(), codigo, "34", "2016/01/30", "453453", "87686", 265);
	
		vuelo = gc.DevolverVuelos(BasesDeDatos.getStatement(), codigo);
		
		for (int i=0; i < vuelo.size(); i++){
			if(vuelo.get(i).getFecha() == "2016/01/30"){
				codigo2 = "2423";
			}
		}
		
		assertEquals(codigo2, codigo, "2423");
		
	}	
	
	
	
}
