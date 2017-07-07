package data;

import java.util.ArrayList;

import dao.DBMMascota;
import dao.DBMPersonas;
import main.Mascota;
import model.Mascotas;
import model.Personas;

public class BBDDHelper {

public static void listToBbdd(ArrayList<Mascota> list) {
		
		Mascotas mascotaToBbdd = new Mascotas();
		Personas personaToBbdd = new Personas();
		
		DBMMascota dbMascota =  new DBMMascota("localhost", "pets", "mascotas"); 	
		DBMPersonas dbPersona = new DBMPersonas("localhost", "pets", "personas");
		
		try {
			dbPersona.connect("edu","1234"); 
			dbMascota.connect("edu","1234"); 
			dbPersona.deleteAll();
			dbMascota.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbMascota.close(); 
			dbPersona.close();
		}
		
		for(Mascota mascota: list){

			personaToBbdd.setNombre(mascota.getPropietario().getName());
			personaToBbdd.setApellido(mascota.getPropietario().getSurname());
			personaToBbdd.setEmail(mascota.getPropietario().getEmail());
			personaToBbdd.setTelefono(mascota.getPropietario().getPhone());
			personaToBbdd.setDireccion(mascota.getPropietario().getAddress());
		
			try {
				dbPersona.connect("edu","1234"); 
				dbPersona.insert(personaToBbdd);
			} catch (Exception e) {
				//result = false; 
				e.printStackTrace();
				
			}finally{
				dbMascota.close(); 
			}
			
			mascotaToBbdd.setNombre(mascota.getNombre());
			mascotaToBbdd.setIdPropietario(personaToBbdd.getId());
			mascotaToBbdd.setLargo(mascota.getLargo());
			mascotaToBbdd.setTipo(mascota.getClass().getSimpleName().toLowerCase());
			mascotaToBbdd.setPeso(mascota.getPeso());
			mascotaToBbdd.setAltura(mascota.getAltura());

			try {
				dbMascota.connect("edu","1234"); 
				dbMascota.insert(mascotaToBbdd);
			} catch (Exception e) {
				//result = false; 
				e.printStackTrace();
				
			}finally{
				dbMascota.close(); 
			}
			
		}
		
		
	}

	public static ArrayList<Mascotas> BbddToList() {
		
		ArrayList<Mascotas> listMascotas = new ArrayList<Mascotas>();
		ArrayList<Personas> listPersonas = new ArrayList<Personas>();
		
		Mascotas mascotaToBbdd = new Mascotas();
		Personas personaToBbdd = new Personas();
		
		DBMMascota dbMascota =  new DBMMascota("localhost", "pets", "mascotas"); 	
		DBMPersonas dbPersona = new DBMPersonas("localhost", "pets", "personas");
		
		try {
			dbMascota.connect("edu","1234");
			dbPersona.connect("edu", "1234");
			listMascotas = dbMascota.select("id",">=", "0");
			listPersonas = dbPersona.select("id",">=", "0");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbMascota.close(); 
			dbPersona.close();
		}
		return listMascotas;
	
	}
	
}
