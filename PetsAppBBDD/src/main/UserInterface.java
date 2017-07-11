package main;

import java.util.ArrayList;
import java.util.Comparator;

import dao.DBMMascota;
import dao.DBMPersonas;
import model.MascotasMod;
import model.PersonasMod;
import util.Finder;
import util.Input;

public class UserInterface {
	
	public static void showWelcome(){
		System.out.println("**************************************");
		System.out.println("************* BIENVENIDO *************");
		System.out.println("************ PetsApp v2.0a ***********");
		System.out.println("**************************************");
	}

	public static void showMenuInit(){
		System.out.println("\n\nOpciones disponibles :");
		System.out.println("> Añadir [añade mascota]");
		System.out.println("> Eliminar [dar de alta una mascota - listar todo, nombre mascota o nombre propietario]");
		System.out.println("> Listar [lista todas las mascotas mascota]");
		System.out.println("> Buscar [busca mascotas por nombre mascota o nombre propietario o email]");
		System.out.println("> clases [busca mascotas por clases de animal]");
		System.out.println("> Editar [edita una mascota - listar todo, nombre mascota o nombre propietario]");
		System.out.println("> Mascotas [lista y ordena por nombre de mascotas]");
		System.out.println("> Propietarios [lista y ordena por nombre de propietarios]");
		System.out.println("> Peso [lista y ordena por peso de mascotas]");
		System.out.println("> Nutricion [lista y ordena por estado de nutricion de mascotas]");
		System.out.println("> Salir [y guardar cambios]");
		System.out.println("\n");
		System.out.print("> Escoge opción : ");
	}
	
	public static Mascota scanMascota(ArrayList<Mascota> list){
		String name;
		String tipo;
		float peso;	
		float altura;
		float largo;
		float calidad;
		Canido newCanido = null;
		Felino newFelino = null;
		Roedor newRoedor = null;
		Ave newAve = null;

		do{
			System.out.println("******* MASCOTA *******");
			do{
				System.out.print("> Tipo Mascota [canido, felino, roedor, ave] : ");
				tipo = Input.scannLine();
				if(!(tipo.equals("canido")||tipo.equals("felino")||tipo.equals("roedor")||tipo.equals("ave"))){
					System.out.println("ERROR. Debe introducir un tipo de animal mostrado en la lista...");
				}
			}while(!(tipo.equals("canido")||tipo.equals("felino")||tipo.equals("roedor")||tipo.equals("ave")));
			
			System.out.print("> Nombre Mascota : ");
			name = Input.scannLine();
			System.out.print("> Peso  [formato Kg ',']: ");
			peso = Input.scannFloat();
			System.out.print("> Altura [formato metros ','] : ");
			altura = Input.scannFloat();
			System.out.print("> Largo [formato metros ','] : ");
			largo = Input.scannFloat();
			
			do{	System.out.print("> Calidad - Estado de salud visual [formato de 0,0 a 1,0] : ");
				calidad = Input.scannFloat();
				if(calidad<0||calidad>1)
					System.err.println("Calidad entre 0 y 1");
			}while(calidad<0||calidad>1);
			
			if(tipo.equals("canido"))
				newCanido = new Canido(name,peso,altura,largo,calidad);
			else if(tipo.equals("felino"))
				newFelino = new Felino(name,peso,altura,largo,calidad);
			else if(tipo.equals("roedor"))
				newRoedor = new Roedor(name,peso,altura,largo,calidad);
			else
				newAve = new Ave(name,peso,altura,largo,calidad);
			
		}while(name.equals("")||peso<=0||altura<=0||largo<=0);
		
		String nombreProp;
		String apellidoProp;
		String telefono;
		String eMail;
		String address;

		do{
			System.out.println("****** PROPIETARIO ******");
			System.out.print("> Nombre Propietario : ");
			nombreProp = Input.scannLine();
			System.out.print("> Apellido Propietario : ");
			apellidoProp = Input.scannLine();
			
			do{
				System.out.print("> Teléfono : ");
				telefono = Input.scannLine();
				if(!phoneValidator(telefono)){
					System.out.println("Formato teléfono incorrecto\n"
							+ "Formato : únicamente números - Mínimo 9 cifras - Máximo 13");
				}
			}while(!phoneValidator(telefono));
			
			do{
				System.out.print("> Email : ");
				eMail = Input.scannLine();
				if(!mailValidator(eMail)){
					System.out.println("El correo no está en formato correcto...");
				}
			}while(!mailValidator(eMail));
			
			System.out.print("> Dirección : ");
			address = Input.scannLine();
		
		}while(nombreProp.equals("")||apellidoProp.equals("")||telefono.equals("")|| eMail.equals("")||address.equals(""));
		
		
		Person newPerson = new Person(nombreProp + " " + apellidoProp + ";" + telefono + ";" + eMail + ";" + address);
		
		if(tipo.equals("canido")){
			newCanido.setPropietario(newPerson);
			return newCanido;
			
		}else if(tipo.equals("felino")){
			newFelino.setPropietario(newPerson);
			return newFelino;
			
		}else if(tipo.equals("roedor")){
			newRoedor.setPropietario(newPerson);
			return newRoedor;
			
		}else{
			newAve.setPropietario(newPerson);
			return newAve;		
		}
	}
	
	
	public static void showMenuEdit(){
		System.out.println("**************** MENÚ EDICIÓN ****************");
		System.out.println("Escoge la tabla a editar : ");
		System.out.println("> Mascota ");
		System.out.println("> Propietario ");

	}
	
	public static void showMenuDelete(){
		System.out.println("**************** MENÚ ELIMINAR ****************");
		System.out.println("Elije opción para eliminar");
		System.out.println("> listar [todo]");
		System.out.println("> mascota [busca por nombre de mascota]");
		System.out.println("> propietario [busca por nombre de propietario]");

	}
	
	public static String scanOption(ArrayList<Mascota> list) {
		String option;
		do{
			System.out.print("Opción : ");
			option = Input.scannLine().toLowerCase();
			if(!(option.equals("listar")||option.equals("mascota")||option.equals("propietario"))){
				System.err.println("Error. Opción incorrecta");
			}
		}while(!(option.equals("listar")||option.equals("mascota")||option.equals("propietario")));
		
		return option;
	}
	
	public static void editMascota(int index){
		
		DBMMascota dbManagerMascotas = new DBMMascota("localhost","pets","mascotas");
		MascotasMod mascotaResult = null;
		try {
			dbManagerMascotas.connect("edu", "1234");
			mascotaResult = dbManagerMascotas.select(index);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbManagerMascotas.close(); 
		}
		
		System.out.println("********************************************* Editar Mascota *****************************************************");
		System.out.println("Nombre: " +mascotaResult.getNombre() + ", Peso: " + mascotaResult.getPeso() +
				", Altura: " + mascotaResult.getAltura() + ", Largo: " + mascotaResult.getLargo() + ", Calidad: "+ mascotaResult.getCalidad());
		
		System.out.println("********************************************** Menú edición ******************************************************");
		
			String nombre;
			String nombreRespaldo = mascotaResult.getNombre();
			Float peso;
			Float pesoRespaldo = mascotaResult.getPeso();	
			Float altura;
			Float alturaRespaldo= mascotaResult.getAltura();
			Float largo;
			Float largoRespaldo = mascotaResult.getLargo();
			Float calidad;
			Float calidadRespaldo = mascotaResult.getCalidad();
			
			System.out.println(" [ Dejar en blanco si se desea conservar el dato ] ");
			System.out.print("> Nuevo nombre de mascota: ");
			nombre = Input.scannLine();
			if(nombre.equals(""))
				nombre = nombreRespaldo;
			
			String editar;
			System.out.print("> ¿Editar peso? s/n [Dejar en blanco para conservar antiguo dato] ");
			editar = Input.scannLine();
			if(editar.equals("")||editar.toLowerCase().toCharArray()[0]=='n'){
				peso = pesoRespaldo;
			}else{
				System.out.print("> Nuevo peso de mascota: ");
				peso = Input.scannFloat();
			}
			
			System.out.print("> ¿Editar altura? s/n [Dejar en blanco para conservar antiguo dato] ");
			editar = Input.scannLine();
			if(editar.equals("")||editar.toLowerCase().toCharArray()[0]=='n'){
				altura = alturaRespaldo;
			}else{
				System.out.print("> Nueva altura de mascota: ");
				altura = Input.scannFloat();
			}
			
			System.out.print("> ¿Editar largo? s/n [Dejar en blanco para conservar antiguo dato] ");
			editar = Input.scannLine();
			if(editar.equals("")||editar.toLowerCase().toCharArray()[0]=='n'){
				largo = largoRespaldo;
			}else{
				System.out.print("> Nuevo largo de mascota: ");
				largo = Input.scannFloat();
			}
			
			System.out.print("> ¿Editar calidad? s/n [Dejar en blanco para conservar antiguo dato] ");
			editar = Input.scannLine();
			if(editar.equals("")||editar.toLowerCase().toCharArray()[0]=='n'){
				calidad = calidadRespaldo;
			}else{
				do{	
					System.out.print("> Nueva calidad de la mascota. Estado de salud visual [formato de 0,0 a 1,0] : ");
					calidad = Input.scannFloat();
					if(calidad<0||calidad>1)
						System.err.println("Calidad entre 0 y 1");
				}while(calidad<0||calidad>1);
			}
				
			mascotaResult.setNombre(nombre);
			mascotaResult.setAltura(altura);
			mascotaResult.setLargo(largo);
			mascotaResult.setPeso(peso);
			mascotaResult.setCalidad(calidad);
			
			try {
				dbManagerMascotas.connect("edu", "1234");
				dbManagerMascotas.update(mascotaResult);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbManagerMascotas.close(); 
			}
			
			System.out.println("********************* Cambios realizados correctamente *********************");
			System.out.println("Nombre: " +mascotaResult.getNombre() + ", Peso: " + mascotaResult.getPeso() +
					", Altura: " + mascotaResult.getAltura() + ", Largo: " + mascotaResult.getLargo()+ ", Calidad: " + mascotaResult.getCalidad());
			System.out.println("****************************************************************************");
			
		}
	
	public static void editPropietario(int index){
		
		DBMPersonas dbManagerPersonas = new DBMPersonas("localhost", "pets", "personas");
		PersonasMod personaResult = null;
		try {
			dbManagerPersonas.connect("edu", "1234");
			personaResult = dbManagerPersonas.select(index);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbManagerPersonas.close(); 
		}
		
			String nombreRespaldo = personaResult.getNombre();
			String apellidoRespaldo = personaResult.getApellido();
			String telefonoRespaldo = personaResult.getTelefono();
			String eMailRespaldo = personaResult.getEmail();
			String addressRespaldo = personaResult.getDireccion();
			String nombre;
			String apellido;
			String telefono;
			String email;
			String direccion;
			
			System.out.println("***************************** Edición de Propietario ******************************");
			System.out.println("***********************************************************************************");
			System.out.println("Nombre: " + personaResult.getNombre() + ", Apellido:  "+ personaResult.getApellido() + ", Telefono: " + 
					personaResult.getTelefono() + ", Correo: " + personaResult.getEmail() + ", Dirección: " + personaResult.getDireccion());
			System.out.println("***********************************************************************************");
			System.out.println(" [ Dejar en blanco si se desea conservar el dato ] ");
			System.out.print("> Nuevo nombre de propietario: ");
			nombre = Input.scannLine();
			System.out.print("> Nuevo apellido de propietario: ");
			apellido = Input.scannLine();
			
			do{
				System.out.print("> Nuevo teléfono de propietario: ");
				telefono = Input.scannLine();
				if(telefono.equals(""))
					telefono = telefonoRespaldo;
				if(!phoneValidator(telefono)){
					System.out.println("Formato teléfono incorrecto\n"
							+ "Formato : únicamente números - Mínimo 9 cifras - Máximo 13");
				}
			}while(!phoneValidator(telefono));
			
			do{
				System.out.print("> Nuevo correo electrónico de propietario: ");
				email = Input.scannLine();
				if(email.equals(""))
					email = eMailRespaldo;
				if(!mailValidator(email)){
					System.out.println("El correo no está en formato correcto...");
				}
			}while(!mailValidator(email));
			
			System.out.print("> Nueva dirección de propietario: ");
			direccion = Input.scannLine();
			
			if(nombre.equals(""))
				nombre  = nombreRespaldo;
			if(apellido.equals(""))
				apellido = apellidoRespaldo;
			if(direccion.equals(""))
				direccion = addressRespaldo;
			
			personaResult.setNombre(nombre);
			personaResult.setApellido(apellido);
			personaResult.setTelefono(telefono);
			personaResult.setEmail(email);
			personaResult.setDireccion(direccion);
			
			try {
				dbManagerPersonas.connect("edu", "1234");
				dbManagerPersonas.update(personaResult);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbManagerPersonas.close(); 
			}
			
			System.out.println("******************************** Cambios realizados correctamente **********************************");
			System.out.println("Nombre: " +personaResult.getNombre() + ", Apellido: " + personaResult.getApellido() + ", Telefono: " + personaResult.getTelefono() +
					", Correo: " +personaResult.getEmail() + ", Dirección: " + personaResult.getDireccion());
			System.out.println("***************************************************************************************************");
			
			
	}
	
	
	public static void deleteByMascotaNombre(ArrayList<Mascota> list) {
		
		if(list.size()==0){
			System.err.println("Lista vacía. No se encuentra resultados");
		}else{

			System.out.println("********************* Menú eliminación por búsqueda de Mascota por nombre *********************");
			String nombreMascota = scanNombreMascota();
			DBMMascota dbManagerMascotas = new DBMMascota("localhost", "pets", "mascotas");
			ArrayList<MascotasMod> resultado = null;
			try {
				dbManagerMascotas.connect("edu","1234");
				
				resultado = dbManagerMascotas.select("nombre", "LIKE", "'"+nombreMascota+"%'");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbManagerMascotas.close(); 
			}
						if(resultado==null){
				System.err.println("No se encontraron resultados");
			}else{
				Integer index;
				Integer arrayIndex[];
				Integer i = 0;
				boolean isCorrect = false;
				do{
					for(MascotasMod mascota: resultado)
						i++;
					arrayIndex = new Integer[i];
					i=0;
					System.out.println("Listado de coincidencias:");
					for(MascotasMod mascota: resultado){
						System.out.println("ID: " + mascota.getId() +" | Nombre: " +mascota.getNombre() + " | Tipo: " + mascota.getTipo());
						arrayIndex[i++] = mascota.getId();
					}
					System.out.print("Selecciona una ID del listado : ");
					index = Input.scannInt();
					isCorrect = checkIndex(arrayIndex,index);
					if(!isCorrect){
						System.err.println("\nError, índice incorrecto.");
					}
				}while(!isCorrect);
			
				try {
					dbManagerMascotas.connect("edu", "1234");
					dbManagerMascotas.delete(index);
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					dbManagerMascotas.close();
				}
			}
		}
	}
	
	public static void deleteByPropietarioNombre(ArrayList<Mascota> list) {
		
		if(list.size()==0){
			System.err.println("Lista vacía. No se encuentra resultados");
		}else{

			System.out.println("********************* Menú eliminación por búsqueda de Propietario por nombre *********************");
			System.out.println("************************* Eliminar al propietario eliminará sus Mascotas **************************");

			String nombrePropietario = scanNombrePropietario();
			DBMPersonas dbManagerPersonas = new DBMPersonas("localhost", "pets", "personas");
			ArrayList<PersonasMod> resultado = null;
			try {
				dbManagerPersonas.connect("edu","1234");
				
				resultado = dbManagerPersonas.select("nombre", "LIKE", "'"+nombrePropietario+"%'");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbManagerPersonas.close(); 
			}
						if(resultado==null){
				System.err.println("No se encontraron resultados");
			}else{
				Integer index;
				Integer arrayIndex[];
				Integer i = 0;
				boolean isCorrect = false;
				do{
					for(PersonasMod person: resultado)
						i++;
					arrayIndex = new Integer[i];
					i=0;
					System.out.println("Listado de coincidencias: ");
					for(PersonasMod person: resultado){
						System.out.println("ID: " + person.getId() +" | Nombre: " +person.getNombre() + " | Apellido: " + person.getApellido());
						arrayIndex[i++] = person.getId();
					}
					System.out.print("Escoge una ID del listado anterior: ");
					index = Input.scannInt();
					isCorrect = checkIndex(arrayIndex,index);
					if(!isCorrect){
						System.err.println("\nError, índice incorrecto.");
					}
				}while(!isCorrect);
				
				DBMPersonas dbManagerPersonasRemove = new DBMPersonas("localhost", "pets", "personas");
				DBMMascota dbManagerMascotas =   new DBMMascota("localhost", "pets", "mascotas");
				
				ArrayList<MascotasMod> mascotasTodas = null;
				
				try {
					dbManagerMascotas.connect("edu", "1234");
					mascotasTodas = dbManagerMascotas.select("id",">=","0");
					for(MascotasMod mascota: mascotasTodas){
						if(mascota.getIdPropietario()==index){
							try {
								dbManagerMascotas.connect("edu","1234");
								dbManagerMascotas.delete(mascota.getId());
								
							} catch (Exception e) {
								e.printStackTrace();
							}finally{
								dbManagerMascotas.close();
							}
						}
					}
					
					dbManagerMascotas.connect("edu","1234"); 
					dbManagerPersonasRemove.connect("edu","1234");
					dbManagerPersonasRemove.delete(index);
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					dbManagerMascotas.close(); 
					dbManagerPersonasRemove.close();
				}
			}
		}
	}
	
	private static boolean checkIndex(Integer[] arrayIndex, Integer index) {
		for(int i=0;i<arrayIndex.length;i++){
			if(index==arrayIndex[i]){
				return true;
			}
		}
		return false;
	}

	public static void editByMascotaNombre(ArrayList<Mascota> list) {

		if(list.size()==0){
			System.err.println("Lista vacía. No se encuentra resultados");
		}else{

			System.out.println("********************* Menú edición por búsqueda de Mascota por nombre *********************");
			String nombreMascota = scanNombreMascota();
			
			DBMMascota dbManagerMascotas =   new DBMMascota("localhost", "pets", "mascotas");
			
			ArrayList<MascotasMod> mascotasList = null;
			
			try {
				dbManagerMascotas.connect("edu", "1234");
				mascotasList = dbManagerMascotas.select("nombre","LIKE","'"+nombreMascota+"%'");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbManagerMascotas.close(); 
			}
			
			if(mascotasList.size()==0){
				System.err.println("La búsqueda no ha dado resultados");
			}else{
				Integer index;
				Integer arrayIndex[];
				Integer contador = 0;
				
				for(MascotasMod mascota: mascotasList)
					contador++;
				
				arrayIndex = new Integer[contador];
				contador = 0;
				for(MascotasMod mascota: mascotasList)
					arrayIndex[contador++] = mascota.getId();
				
				for(MascotasMod mascota: mascotasList){
					System.out.println("ID: " + mascota.getId() + "\t Nombre: " + mascota.getNombre() + "\t Tipo: " + mascota.getTipo() +
							"\t Peso: " + mascota.getPeso() + "\t Altura: " + mascota.getAltura() + "\t Largo: " + mascota.getLargo());
				}
				
				boolean isCorrect;
				do{
					System.out.print("Escoge una ID de la lista anterior para editar esa mascota: ");
					index = Input.scannInt();
					isCorrect = checkIndex(arrayIndex, index);
					if(!isCorrect)
						System.err.println("\nID incorrecta... vuelve a probar");
				}while(!isCorrect);
				
				editMascota(index);
			}
		}
	}
	
	public static void editByPropietarioNombre(ArrayList<Mascota> list) {
		
		if(list.size()==0){
			System.err.println("Lista vacía. No se encuentra resultados");
		}else{

			System.out.println("********************* Menú edición tabla Propietario *********************");
			String nombrePersona = scanNombrePropietario();
			
			DBMPersonas dbManagerPersonas =   new DBMPersonas("localhost", "pets", "personas");
			
			ArrayList<PersonasMod> personasList = null;
			
			try {
				dbManagerPersonas.connect("edu", "1234");
				personasList = dbManagerPersonas.select("nombre","LIKE","'"+nombrePersona+"%'");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbManagerPersonas.close(); 
			}
			
			if(personasList.size()==0){
				System.err.println("La búsqueda no ha dado resultados");
			}else{
				Integer index;
				Integer arrayIndex[];
				Integer contador = 0;
				
				for(PersonasMod persona: personasList)
					contador++;
				
				arrayIndex = new Integer[contador];
				contador = 0;
				for(PersonasMod persona: personasList)
					arrayIndex[contador++] = persona.getId();
				
				for(PersonasMod persona: personasList){
					System.out.println("ID: " + persona.getId() + "\t Nombre: " + persona.getNombre() + "\t Apellido: " + persona.getApellido() +
							"\t Telefono: " + persona.getTelefono() + "\t Email: " + persona.getEmail() + "\t Dirección: " + persona.getDireccion());
				}
				
				boolean isCorrect;
				do{
					System.out.print("Escoge una ID de la lista anterior para editar esa mascota: ");
					index = Input.scannInt();
					isCorrect = checkIndex(arrayIndex, index);
					if(!isCorrect)
						System.err.println("\nID incorrecta... vuelve a probar");
				}while(!isCorrect);
				
				editPropietario(index);
			}
		}
		
	}

	
	public static void addMascota(Mascota newMascota) {
		
		ArrayList<MascotasMod> listMascotasMod = new ArrayList<MascotasMod>();
		ArrayList<PersonasMod> listPersonasMod = new ArrayList<PersonasMod>();
		
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
		
		boolean existsMascota = false;
		
		for(int i=0;i<listMascotasMod.size();i++){
			if(listMascotasMod.get(i).getNombre().equals(newMascota.getNombre())&&
					listMascotasMod.get(i).getPeso()==newMascota.getPeso()&&
						listMascotasMod.get(i).getLargo()==newMascota.getLargo()&&
							listMascotasMod.get(i).getTipo()==newMascota.getClass().getSimpleName().toLowerCase()&&
								listMascotasMod.get(i).getAltura()==newMascota.getAltura()){
				existsMascota = true;
				break;
			}
		}
		
		if(existsMascota){
			System.err.println("La mascota a introducir ya existe con el mismo tipo de mascota, nombre, largo, alto y peso");
		}else{

			int id = -1;
			int newid = 0;
			MascotasMod mascota = new MascotasMod();
			
			for(int i=0;i<listPersonasMod.size();i++){
				if(newMascota.getPropietario().getName().toLowerCase().equals(listPersonasMod.get(i).getNombre().toLowerCase())&&
						newMascota.getPropietario().getSurname().toLowerCase().equals(listPersonasMod.get(i).getApellido().toLowerCase())&&
								newMascota.getPropietario().getEmail().toLowerCase().equals(listPersonasMod.get(i).getEmail().toLowerCase())&&
									newMascota.getPropietario().getPhone().equals(listPersonasMod.get(i).getTelefono())){
					id = listPersonasMod.get(i).getId();
					break;
				}
			}
			
			if(id>=0){
				
				mascota.setNombre(newMascota.getNombre());
				mascota.setIdPropietario(id);
				mascota.setPeso(newMascota.getPeso());
				mascota.setAltura(newMascota.getAltura());
				mascota.setLargo(newMascota.getLargo());
				mascota.setTipo(newMascota.getClass().getSimpleName().toLowerCase());
				mascota.setCalidad(newMascota.getCalidad());	
				
			}else{
				
				PersonasMod persona = new PersonasMod();
				persona.setNombre(newMascota.getPropietario().getName());
				persona.setApellido(newMascota.getPropietario().getSurname());
				persona.setEmail(newMascota.getPropietario().getEmail());
				persona.setTelefono(newMascota.getPropietario().getPhone());
				persona.setDireccion(newMascota.getPropietario().getAddress());
				
				DBMPersonas dbManager =   new DBMPersonas("localhost", "pets", "personas"); 
				
				try {
					dbManager.connect("edu","1234"); 
					newid = dbManager.insert(persona);
				} catch (Exception e) {
					e.printStackTrace();
					
				}finally{
					dbManager.close(); 
				}
				
					mascota.setNombre(newMascota.getNombre());
					mascota.setIdPropietario(newid);
					mascota.setPeso(newMascota.getPeso());
					mascota.setAltura(newMascota.getAltura());
					mascota.setLargo(newMascota.getLargo());
					mascota.setTipo(newMascota.getClass().getSimpleName().toLowerCase());
					mascota.setCalidad(newMascota.getCalidad());
	
			}
			
			DBMMascota connectMascota =   new DBMMascota("localhost", "pets", "mascotas"); 
			
			try {
				connectMascota.connect("edu","1234"); 
				newid = connectMascota.insert(mascota);
			} catch (Exception e) {
				e.printStackTrace();
				
			}finally{
				connectMascota.close(); 
			}
			
			}
		
		
	}
	
	private static boolean mailValidator(String mail){
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(mail.matches(pattern)){
			return true;
		}else{
			return false;
		}
	}
	
	private static boolean phoneValidator(String phone) {
		String pattern = "^[0-9]{9,13}$";
				if(phone.matches(pattern)){
					return true;
				}else{
					return false;
				}
	}

	public static void listAllMascotas(ArrayList<Mascota> list) {
		if(list.size()==0){
			System.out.println("Lista vacía. No hay resultados.");
		}else{
			System.out.println("***********************************************************************************************************************************");
			for(int i=0;i<list.size();i++){
			System.out.print(i+1 + " -> " + "Tipo: " + list.get(i).getClass().getSimpleName() +
					", Nombre: " +list.get(i).getNombre() + ", Peso: " + list.get(i).getPeso() +
					", Altura: " + list.get(i).getAltura() + ", Largo: " + list.get(i).getLargo() + 
					", Estado Nutricion: " + list.get(i).getEstadoNutricion() + ", Ración: " +list.get(i).getPesoRacion());
			
				if(list.get(i).getClass().getSimpleName().toLowerCase().equals("canido")){
					Canido mascota = (Canido) list.get(i);
					System.out.print(", Calidad de Colmillos: " + mascota.getCalidad());
				}else if(list.get(i).getClass().getSimpleName().toLowerCase().equals("felino")){
					Felino mascota = (Felino) list.get(i);
					System.out.print(", Calidad de Garras: " + mascota.getCalidad());
				}else if(list.get(i).getClass().getSimpleName().toLowerCase().equals("roedor")){
					Roedor mascota = (Roedor) list.get(i); 
					System.out.print(", Calidad de Pelaje: " + mascota.getCalidad());
				}else{
					Ave mascota = (Ave) list.get(i);
					System.out.print(", Calidad de Pico: " + mascota.getCalidad());
				}
				
				System.out.println("\nPropietario: " + list.get(i).getPropietario().getFullName() + ", " + list.get(i).getPropietario().getPhone() +
				", " +list.get(i).getPropietario().getEmail() + ", " +list.get(i).getPropietario().getAddress());
				System.out.println("***********************************************************************************************************************************");
			}
		}
		
	}

	public static void removeFromIndexMascotas(ArrayList<Mascota> list) {
		listAllMascotas(list);
		if(list.size()!=0){
			int index;
			System.out.println("*******************************************");
			do{
				System.out.print("Introduce el índice a eliminar : ");
				index  = Input.scannInt();
				if(index<1||index>list.size()){
					System.out.println("Error, índice incorrecto, vuelve a probar");
				}
			}while(index<1||index>list.size());
			
			DBMMascota dbManagerMascotas =   new DBMMascota("localhost", "pets", "mascotas");
			
			try {
				
				dbManagerMascotas.connect("edu","1234"); 
				
				dbManagerMascotas.delete(index);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbManagerMascotas.close(); 
			}
		}
	}
	
	public static ArrayList<Mascota> searchNameMascota(ArrayList<Mascota> list, String name){
		
		Finder<Mascota> finder = new Finder<Mascota>();
		ArrayList<Mascota> resultListMascota = finder.find(list, name , new Finder.ContainChecker<Mascota>() {
			@Override
			public boolean containChecker(Mascota mascota, Object patron) {
				String str = (String) patron;
				return mascota.getNombre().toLowerCase().contains(str);
			}	
		});
		
		return resultListMascota;
	}
	
public static ArrayList<Mascota> searchMailPropietario(ArrayList<Mascota> list, String name){
		
		Finder<Mascota> finder = new Finder<Mascota>();
		ArrayList<Mascota> resultListMascota = finder.find(list, name , new Finder.ContainChecker<Mascota>() {
			@Override
			public boolean containChecker(Mascota mascota, Object patron) {
				String str = (String) patron;
				return mascota.getPropietario().getEmail().toLowerCase().contains(str);
			}	
		});
		
		return resultListMascota;
	}
	
	public static ArrayList<Mascota> searchNamePropietario(ArrayList<Mascota> list, String name){
		
		Finder<Mascota> finder = new Finder<Mascota>();
		ArrayList<Mascota> resultListMascota = finder.find(list, name , new Finder.ContainChecker<Mascota>() {
			@Override
			public boolean containChecker(Mascota mascota, Object patron) {
				String str = (String) patron;
				return mascota.getPropietario().getFullName().toLowerCase().contains(str);
			}	
		});
		
		return resultListMascota;
	}

	public static String scanNombreMascota() {
		String name;
		do{
			System.out.print("Introduce nombre de la mascota : ");
			name = Input.scannLine().toLowerCase();
			if(name.equals("")){
				System.out.println("\nDebes introducir un nombre!");
			}
		}while(name.equals(""));
		return name;
	}
	
	public static String scanNombrePropietario() {
		String name;
		do{
			System.out.print("Introduce nombre del Propietario : ");
			name = Input.scannLine().toLowerCase();
			if(name.equals("")){
				System.out.println("\nDebes introducir un nombre!");
			}
		}while(name.equals(""));
		return name;
	}

	public static ArrayList<Mascota> showMenuBuscar(ArrayList<Mascota> list) {
		System.out.println("******************************");
		System.out.println("**** Buscador de mascotas ****");
		System.out.println("******************************");
		String option;
			System.out.println("\n Puedes buscar por:");
			System.out.println("> Mascota [por defecto]");
			System.out.println("> Propietario");
			System.out.println("> Mail [del propietario]");
			System.out.print("Escoge opción : ");
			option = Input.scannLine().toLowerCase();
			if(option.equals("propietario")){
				String name;
				do{
					System.out.print("Introduce nombre del Propietario : ");
					name = Input.scannLine().toLowerCase();
					if(name.equals("")){
						System.out.println("\nDebes introducir un nombre!");
					}
				}while(name.equals(""));
				
				return searchNamePropietario(list, name);
				
			}else if(option.equals("mail")){
				String email;
				do{
					System.out.print("Introduce correo electrónico o parte de él : ");
					email = Input.scannLine().toLowerCase();
					if(email.equals("")){
						System.out.println("\nNo has introducido ningún valor");
					}
				}while(email.equals(""));
				
				return searchMailPropietario(list, email);
			}else{
				String name;
				do{
					System.out.print("Introduce nombre de la mascota : ");
					name = Input.scannLine().toLowerCase();
					if(name.equals("")){
						System.out.println("\nDebes introducir un nombre!");
					}
				}while(name.equals(""));
				
				return searchNameMascota(list, name);
			}
	}

	public static ArrayList<Mascota> sortMascotasByName(ArrayList<Mascota> list) {
		ArrayList<Mascota> listaOrdenadaNombreMascota =  list;
		listaOrdenadaNombreMascota.sort
		(new Comparator<Mascota>() 
			{
				// AQUÍ ESTA LA DEFINICIÓN DE ESTA CLASE ANONIMA
				public int compare(Mascota o1, Mascota o2) {
					return o1.getNombre().toLowerCase().compareTo(o2.getNombre().toLowerCase());	
				}
			}
		);
		
		return listaOrdenadaNombreMascota;
	}
	
	public static ArrayList<Mascota> sortMascotasByPropietarios(ArrayList<Mascota> list) {
		ArrayList<Mascota> listaOrdenadaNombrePropietario =  list;
		listaOrdenadaNombrePropietario.sort
		(new Comparator<Mascota>() 
			{
				// AQUÍ ESTA LA DEFINICIÓN DE ESTA CLASE ANONIMA
				public int compare(Mascota o1, Mascota o2) {
					return o1.getPropietario().getFullName().toLowerCase().compareTo(o2.getPropietario().getFullName().toLowerCase());	
				}
			}
		);
		
		return listaOrdenadaNombrePropietario;
	}

	public static ArrayList<Mascota> sortMascotasByPeso(ArrayList<Mascota> list) {
		ArrayList<Mascota> listaOrdenadaPesoMascota =  list;
		listaOrdenadaPesoMascota.sort
		(new Comparator<Mascota>() 
			{
				// AQUÍ ESTA LA DEFINICIÓN DE ESTA CLASE ANONIMA
				public int compare(Mascota o1, Mascota o2) {
					int r = 0;
					if(o1.getPeso()<o2.getPeso()){
						r = 1;
					}else if(o1.getPeso()>o2.getPeso()){
						r = -1;
					}
					return r;
				}
			}
		);
		
		return listaOrdenadaPesoMascota;
	}
	
	public static ArrayList<Mascota> sortMascotasByNutricion(ArrayList<Mascota> list) {
		ArrayList<Mascota> listaOrdenadaNutricion =  list;
		listaOrdenadaNutricion.sort
		(new Comparator<Mascota>() 
			{
				// AQUÍ ESTA LA DEFINICIÓN DE ESTA CLASE ANONIMA
				public int compare(Mascota o1, Mascota o2) {
					int r = 0;
					if(o1.getEstadoNutricion()<o2.getEstadoNutricion()){
						r = 1;
					}else if(o1.getEstadoNutricion()>o2.getEstadoNutricion()){
						r = -1;
					}
					return r;
				}
			}
		);
		
		return listaOrdenadaNutricion;
	}

	public static ArrayList<Mascota> showMenuTipos(ArrayList<Mascota> list) {
		System.out.println("******************************");
		System.out.println("***** Buscador de clases *****");
		System.out.println("******************************");
		String option;
		do{
			System.out.println("\nIntroduce tipo de animal a buscar [canido, felino, ave, roedor]");
			System.out.print("Elige opción : ");
			option = Input.scannLine().toLowerCase();
			if(!(option.equals("canido")||option.equals("felino")||option.equals("ave")||option.equals("roedor"))){
				System.err.print("Opción no válida.");
			}
		}while(!(option.equals("canido")||option.equals("felino")||option.equals("ave")||option.equals("roedor")));
		Finder<Mascota> finder = new Finder<Mascota>();
		if(option.equals("canido")){
			String patron =  "Canido";
			ArrayList<Mascota> resultList = finder.find(list, patron , new Finder.ContainChecker<Mascota>() {
				@Override
				public boolean containChecker(Mascota mascota, Object patron) {
					return mascota.getClass().getSimpleName().contains((CharSequence) patron);
				}	
			});
			return resultList;
		}else if(option.equals("felino")){
			String patron =  "Felino";
			ArrayList<Mascota> resultList = finder.find(list, patron , new Finder.ContainChecker<Mascota>() {
				@Override
				public boolean containChecker(Mascota mascota, Object patron) {
					return mascota.getClass().getSimpleName().contains((CharSequence) patron);
				}	
			});
			return resultList;
		}else if(option.equals("ave")){
			String patron = "Ave";
			ArrayList<Mascota> resultList = finder.find(list, patron , new Finder.ContainChecker<Mascota>() {
				@Override
				public boolean containChecker(Mascota mascota, Object patron) {
					return mascota.getClass().getSimpleName().contains((CharSequence) patron);
				}	
			});
			return resultList;
		}else{
			String patron =  "Roedor";
			ArrayList<Mascota> resultList = finder.find(list, patron , new Finder.ContainChecker<Mascota>() {
				@Override
				public boolean containChecker(Mascota mascota, Object patron) {
					return mascota.getClass().getSimpleName().contains((CharSequence) patron);
				}	
			});
			return resultList;
		}
		
	}
	
}
