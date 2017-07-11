package test;

import java.sql.*;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;
import dao.DBMMascota;
import dao.DBMPersonas;
import main.*;
import model.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDBMMascotas {

	@Test 
	public void test01deleteAll(){
		
		boolean result = true;
		DBMMascota dbManagerMascotas =   new DBMMascota("localhost", "pets", "mascotas"); 	
		DBMPersonas dbManagerPersonas =   new DBMPersonas("localhost", "pets", "personas"); 
		
		try {
			
			dbManagerMascotas.connect("edu","1234"); 
			dbManagerPersonas.connect("edu", "1234");
			
			dbManagerMascotas.deleteAll();
			dbManagerPersonas.deleteAll();
			
		} catch (Exception e) {
			result = false; 
			e.printStackTrace();
		}finally{
			dbManagerMascotas.close(); 
			dbManagerPersonas.close();
		}
	}
	
	@Test
	public void test02insertDatabase(){

		Person per1 = new Person("Edu Valles;666960446;edu@edu.com;Av. Madrid");
		Person per2 = new Person("Ana Garcia;222222222;ana@ana.com;Calle Ana 123");
		Person per3 = new Person("Jose Perez;111111111;jose@perez.com;Calle Perez 456");

		
		Ave ave1 = new Ave("Piolin",0.20f,0.11f,0.04f,0.6f);
		ave1.setPropietario(per1);
		MascotasMod mascota1 = new MascotasMod();
		mascota1.setNombre(ave1.getNombre());
		mascota1.setPeso(ave1.getPeso());
		mascota1.setAltura(ave1.getAltura());
		mascota1.setLargo(ave1.getLargo());
		mascota1.setTipo(ave1.getClass().getSimpleName().toLowerCase());
		mascota1.setCalidad(ave1.getCalidad());
		
		Canido can1 = new Canido("Rex",32f,0.46f,0.84f,0.5f);
		can1.setPropietario(per2);
		MascotasMod mascota2 = new MascotasMod();
		mascota2.setNombre(can1.getNombre());
		mascota2.setPeso(can1.getPeso());
		mascota2.setAltura(can1.getAltura());
		mascota2.setLargo(can1.getLargo());
		mascota2.setTipo(can1.getClass().getSimpleName().toLowerCase());
		mascota2.setCalidad(can1.getCalidad());
		
		Felino fel1 = new Felino("Paco",13f,0.28f,0.52f,0.2f);
		fel1.setPropietario(per3);
		MascotasMod mascota3 = new MascotasMod();
		mascota3.setNombre(fel1.getNombre());
		mascota3.setPeso(fel1.getPeso());
		mascota3.setAltura(fel1.getAltura());
		mascota3.setLargo(fel1.getLargo());
		mascota3.setTipo(fel1.getClass().getSimpleName().toLowerCase());
		mascota3.setCalidad(fel1.getCalidad());
		
		Roedor roe1 = new Roedor("Tod",0.02f,0.1f,0.07f,0.9f);
		roe1.setPropietario(per1);
		MascotasMod mascota4 = new MascotasMod();
		mascota4.setNombre(roe1.getNombre());
		mascota4.setPeso(roe1.getPeso());
		mascota4.setAltura(roe1.getAltura());
		mascota4.setLargo(roe1.getLargo());
		mascota4.setTipo(roe1.getClass().getSimpleName().toLowerCase());
		mascota4.setCalidad(roe1.getCalidad());
		
		Canido can2 = new Canido("Boxer",23f,1.2f,0.9f,0.7f);
		can2.setPropietario(per2);
		MascotasMod mascota5 = new MascotasMod();
		mascota5.setNombre(can2.getNombre());
		mascota5.setPeso(can2.getPeso());
		mascota5.setAltura(can2.getAltura());
		mascota5.setLargo(can2.getLargo());
		mascota5.setTipo(can2.getClass().getSimpleName().toLowerCase());
		mascota5.setCalidad(can2.getCalidad());
		
		UserInterface.addMascota(ave1);
		UserInterface.addMascota(can1);
		UserInterface.addMascota(fel1);
		UserInterface.addMascota(roe1);
		UserInterface.addMascota(can2);

	}
	

	@Test
	public void test03testSelect(){
		boolean result = true;
		DBMMascota dbManagerMascotas =   new DBMMascota("localhost", "pets", "mascotas"); 	
		DBMPersonas dbManagerPersonas =   new DBMPersonas("localhost", "pets", "personas"); 
		
		ArrayList<MascotasMod> resultsMascotas = null;
		ArrayList<PersonasMod> resultsPersonas = null;  

		try {
			
			dbManagerMascotas.connect("edu","1234"); 
			dbManagerPersonas.connect("edu", "1234");
			
			resultsMascotas = dbManagerMascotas.select("id",">=", "0");
			resultsPersonas = dbManagerPersonas.select("id",">=", "0");
			
		} catch (Exception e) {
			result = false; 
			e.printStackTrace();
		}finally{
			dbManagerMascotas.close(); 
			dbManagerPersonas.close();
		}
		
		Assert.assertEquals(true,result);
		Assert.assertEquals(5,resultsMascotas.size());
		Assert.assertEquals(3,resultsPersonas.size());

		
		for(MascotasMod rs: resultsMascotas){
			PersonasMod propietario = null;
			try { 
				dbManagerPersonas.connect("edu", "1234");
				propietario = dbManagerPersonas.select(rs.getIdPropietario());
			} catch (Exception e) {
				
			} finally {
				dbManagerPersonas.close();
			}
			System.out.println("Nombre: " + rs.getNombre() + "\tClase: " + rs.getTipo() + "\t| Peso: " + rs.getPeso() + 
				" Kg\t| Ingreso: " +rs.getIngreso() + " |\tCalidad: " + rs.getCalidad() + " |\tPropietario: " + propietario.getNombre() + " " 
					+ propietario.getApellido());
		}

	}
	
}
