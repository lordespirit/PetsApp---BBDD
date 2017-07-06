package test;

import java.sql.Date;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

import dao.DBMMascota;
import model.Mascotas;

public class TestDBMMascotas {

	@Test
	public void testInsert(){
		boolean result = true;
		DBMMascota dbManager =   new DBMMascota("localhost", "pets", "mascotas"); 	
		
		Mascotas mascota1 = new Mascotas();
		mascota1.setNombre("Piolin");
		mascota1.setIdPropietario(1);
		mascota1.setPeso(0.20f);
		mascota1.setAltura(0.11f);
		mascota1.setLargo(0.04f);
		
		Mascotas mascota2 = new Mascotas();
		mascota2.setNombre("Rex");
		mascota2.setIdPropietario(2);
		mascota2.setPeso(10f);
		mascota2.setAltura(0.28f);
		mascota2.setLargo(0.52f);
		
		
		try {
			dbManager.connect("edu","1234"); 
			dbManager.deleteAll();
			dbManager.insert(mascota1);
			dbManager.insert(mascota2);
		} catch (Exception e) {
			result = false; 
			e.printStackTrace();
			
		}finally{
			dbManager.close(); 
		}
		
		Assert.assertEquals(true,result);
		Assert.assertEquals(true,mascota1.getId()!=-1);
		Assert.assertEquals(true,mascota2.getId()!=-1); 

	}
	

	@Test
	public void testSelect(){
		boolean result = true;
		DBMMascota dbManager =   new DBMMascota("localhost", "pets", "mascotas"); 		
		
		ArrayList<Mascotas> results = null;  

		try {
			
			dbManager.connect("edu","1234"); 
			
			results = dbManager.select("nombre","LIKE", "'pio%'");
			
		} catch (Exception e) {
			result = false; 
			e.printStackTrace();
		}finally{
			dbManager.close(); 
		}
		
		Assert.assertEquals(true,result);
		Assert.assertEquals(1,results.size());

	}
	
	
}
