package test;

import java.sql.*;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import dao.DBMMascota;
import main.*;
import model.*;

public class TestDBMMascotas {

	@Test
	public void testInsert(){
		boolean result = true;
		DBMMascota dbManager =   new DBMMascota("localhost", "pets", "mascotas"); 	
		
		Ave ave1 = new Ave("Piolin",0.20f,0.11f,0.04f);
		Mascotas mascota1 = new Mascotas();
		mascota1.setNombre(ave1.getNombre());
		mascota1.setIdPropietario(1);
		mascota1.setPeso(ave1.getPeso());
		mascota1.setAltura(ave1.getAltura());
		mascota1.setLargo(ave1.getLargo());
		mascota1.setTipo(ave1.getClass().getSimpleName().toLowerCase());
		
		Canido can1 = new Canido("Rex",32f,0.46f,0.84f);
		Mascotas mascota2 = new Mascotas();
		mascota2.setNombre(can1.getNombre());
		mascota2.setIdPropietario(2);
		mascota2.setPeso(can1.getPeso());
		mascota2.setAltura(can1.getAltura());
		mascota2.setLargo(can1.getLargo());
		mascota2.setTipo(can1.getClass().getSimpleName().toLowerCase());
		
		Felino fel1 = new Felino("Paco",13f,0.28f,0.52f);
		Mascotas mascota3 = new Mascotas();
		mascota3.setNombre(fel1.getNombre());
		mascota3.setIdPropietario(1);
		mascota3.setPeso(fel1.getPeso());
		mascota3.setAltura(fel1.getAltura());
		mascota3.setLargo(fel1.getLargo());
		mascota3.setTipo(fel1.getClass().getSimpleName().toLowerCase());
		
		Roedor roe1 = new Roedor("Tod",0.02f,0.1f,0.07f);
		Mascotas mascota4 = new Mascotas();
		mascota4.setNombre(roe1.getNombre());
		mascota4.setIdPropietario(6);
		mascota4.setPeso(roe1.getPeso());
		mascota4.setAltura(roe1.getAltura());
		mascota4.setLargo(roe1.getLargo());
		mascota4.setTipo(roe1.getClass().getSimpleName().toLowerCase());
		
		
		try {
			dbManager.connect("edu","1234"); 
			dbManager.deleteAll();
			dbManager.insert(mascota1);
			dbManager.insert(mascota2);
			dbManager.insert(mascota3);
			dbManager.insert(mascota4);
		} catch (Exception e) {
			result = false; 
			e.printStackTrace();
			
		}finally{
			dbManager.close(); 
		}
		
		Assert.assertEquals(true,result);
		Assert.assertEquals(true,mascota1.getId()!=-1);
		Assert.assertEquals(true,mascota2.getId()!=-1); 
		Assert.assertEquals(true,mascota3.getId()!=-1); 
		Assert.assertEquals(true,mascota4.getId()!=-1); 

	}
	

	@Test
	public void testSelect(){
		boolean result = true;
		DBMMascota dbManager =   new DBMMascota("localhost", "pets", "mascotas"); 		
		
		ArrayList<Mascotas> results = null;  

		try {
			
			dbManager.connect("edu","1234"); 
			
			results = dbManager.select("nombre","LIKE", "'%'");
			
		} catch (Exception e) {
			result = false; 
			e.printStackTrace();
		}finally{
			dbManager.close(); 
		}
		
		Assert.assertEquals(true,result);
		Assert.assertEquals(4,results.size());
		
		for(Mascotas rs: results)
		System.out.println("Nombre: " + rs.getNombre() + "\tClase: " + rs.getTipo() + "\t| Peso: " + rs.getPeso() + " Kg\t| Ingreso: " +rs.getIngreso());

	}
	
	
}
