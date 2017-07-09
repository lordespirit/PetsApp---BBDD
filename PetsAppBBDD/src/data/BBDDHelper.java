package data;

import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBMMascota;
import dao.DBMPersonas;
import main.Ave;
import main.Canido;
import main.Felino;
import main.Mascota;
import main.Person;
import main.Roedor;
import model.MascotasMod;
import model.PersonasMod;

public class BBDDHelper {

	/*
public static void listToBbdd(ArrayList<Mascota> list) {
		
		MascotasMod mascotaToBbdd = new MascotasMod();
		PersonasMod personaToBbdd = new PersonasMod();
		
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
	*/

	public static ArrayList<Mascota> BbddToList() {
		
		ArrayList<MascotasMod> listMascotasMod = new ArrayList<MascotasMod>();
		@SuppressWarnings("unused")
		ArrayList<PersonasMod> listPersonasMod = new ArrayList<PersonasMod>();
		ArrayList<Mascota> list = new ArrayList<Mascota>();
		
		DBMMascota dbMascota =  new DBMMascota("localhost", "pets", "mascotas"); 	
		DBMPersonas dbPersona = new DBMPersonas("localhost", "pets", "personas");
		
		try {
			dbMascota.connect("edu","1234");
			dbPersona.connect("edu", "1234");
			listMascotasMod = dbMascota.select("id",">=", "0");
			listPersonasMod = dbPersona.select("id",">=", "0");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbMascota.close(); 
			dbPersona.close();
		}
		
		for(int i=0;i<listMascotasMod.size();i++){
			
			PersonasMod propietario = null;
			try {
				propietario = dbPersona.select(listMascotasMod.get(i).getIdPropietario());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String nombrePropietario = propietario.getNombre();
			String apellidoPropietario = propietario.getApellido();
			String telefonoPropietario = propietario.getTelefono();
			String emailPropietario = propietario.getEmail();
			String direccionPropietario = propietario.getDireccion();
			
			Person person = new Person(nombrePropietario+" "+apellidoPropietario+";"+telefonoPropietario+";"+emailPropietario+";"+direccionPropietario);
			
			if(listMascotasMod.get(i).getTipo().equals("canido")){
				Canido mascota = new Canido(listMascotasMod.get(i).getNombre(),listMascotasMod.get(i).getPeso(),listMascotasMod.get(i).getAltura(),listMascotasMod.get(i).getLargo());
				mascota.setCalidadColmillo(listMascotasMod.get(i).getCalidad());
				mascota.setPropietario(person);
				list.add(mascota);
				
			}else if(listMascotasMod.get(i).getTipo().equals("felino")){
				Felino mascota = new Felino(listMascotasMod.get(i).getNombre(),listMascotasMod.get(i).getPeso(),listMascotasMod.get(i).getAltura(),listMascotasMod.get(i).getLargo());
				mascota.setCalidadGarras(listMascotasMod.get(i).getCalidad());
				mascota.setPropietario(person);
				list.add(mascota);
				
			}else if(listMascotasMod.get(i).getTipo().equals("roedor")){
				Roedor mascota = new Roedor(listMascotasMod.get(i).getNombre(),listMascotasMod.get(i).getPeso(),listMascotasMod.get(i).getAltura(),listMascotasMod.get(i).getLargo());
				mascota.setCalidadPelaje(listMascotasMod.get(i).getCalidad());
				mascota.setPropietario(person);
				list.add(mascota);

			}else{
				Ave mascota = new Ave(listMascotasMod.get(i).getNombre(),listMascotasMod.get(i).getPeso(),listMascotasMod.get(i).getAltura(),listMascotasMod.get(i).getLargo());
				mascota.setCalidadPlumas(listMascotasMod.get(i).getCalidad());
				mascota.setPropietario(person);
				list.add(mascota);

			}
		}
		
		return list;
	
	}
	
}
