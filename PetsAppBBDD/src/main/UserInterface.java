package main;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import dao.DBMMascota;
import dao.DBMPersonas;
import data.GsonHelper;
import model.MascotasMod;
import model.PersonasMod;
import util.Finder;
import util.Input;

public class UserInterface {
	
	private static final String[] ESPECIES = new String[]{"gato", "perro", "ave", "roedor"};
		
	@SuppressWarnings("rawtypes")
	static Map<String, Class> map = new TreeMap<String, Class>();
	/** Adicione las clases que sea necesario */
	 static {
	        map.put(ESPECIES[0],Felino.class);
	        map.put(ESPECIES[1],Canido.class);
	        map.put(ESPECIES[2],Ave.class);
	        map.put(ESPECIES[3],Roedor.class);
	    } 

	public static void showWelcome(){
		System.out.println("**************************************");
		System.out.println("************* BIENVENIDO *************");
		System.out.println("************ PetsApp v2.0a ***********");
		System.out.println("**************************************");
	}

	public static void showMenuInit(){
		System.out.println("\n\nOpciones disponibles :");
		System.out.println("> A�adir [a�ade mascota]");
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
		System.out.print("> Escoge opci�n : ");
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
				System.out.print("> Tel�fono : ");
				telefono = Input.scannLine();
				if(!phoneValidator(telefono)){
					System.out.println("Formato tel�fono incorrecto\n"
							+ "Formato : �nicamente n�meros - M�nimo 9 cifras - M�ximo 13");
				}
			}while(!phoneValidator(telefono));
			
			do{
				System.out.print("> Email : ");
				eMail = Input.scannLine();
				if(!mailValidator(eMail)){
					System.out.println("El correo no est� en formato correcto...");
				}
			}while(!mailValidator(eMail));
			
			System.out.print("> Direcci�n : ");
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
		System.out.println("**************** MEN� EDICI�N ****************");
		System.out.println("Elije opci�n para editar");
		System.out.println("> listar [todo]");
		System.out.println("> mascota [busca por nombre de mascota]");
		System.out.println("> propietario [busca por nombre de propietario]");

	}
	
	public static void showMenuDelete(){
		System.out.println("**************** MEN� ELIMINAR ****************");
		System.out.println("Elije opci�n para eliminar");
		System.out.println("> listar [todo]");
		System.out.println("> mascota [busca por nombre de mascota]");
		System.out.println("> propietario [busca por nombre de propietario]");

	}
	
	public static String scanOption(ArrayList<Mascota> list) {
		String option;
		do{
			System.out.print("Elige Opci�n : ");
			option = Input.scannLine().toLowerCase();
			if(!(option.equals("listar")||option.equals("mascota")||option.equals("propietario"))){
				System.err.println("Error. Opci�n incorrecta");
			}
		}while(!(option.equals("listar")||option.equals("mascota")||option.equals("propietario")));
		
		return option;
	}
	
	public static void editIndex(ArrayList<Mascota> list, int index){
		
		System.out.println("************************************************ Registro a editar ********************************************************");
		System.out.println("Nombre: " +list.get(index).getNombre() + ", Peso: " + list.get(index).getPeso() +
				", Altura: " + list.get(index).getAltura() + ", Largo: " + list.get(index).getLargo());
		System.out.println("Propietario: " + list.get(index).getPropietario().getFullName() + ", " + list.get(index).getPropietario().getPhone() +
		", " +list.get(index).getPropietario().getEmail() + ", " +list.get(index).getPropietario().getAddress());
		System.out.println("***************************************************************************************************************************");
		
		String option;
		do{
		System.out.println("Deseas editar la mascota o el propietario? : ");
		option = Input.scannLine().toLowerCase();
		if(!(option.equals("mascota")||option.equals("propietario"))){
			System.err.println("Error, opci�n no v�lida.");
		}
		}while(!(option.equals("mascota")||option.equals("propietario")));
		
		if(option.equals("mascota")){
			String nombre;
			String nombreRespaldo = list.get(index).getNombre();
			Float peso;
			Float pesoRespaldo = list.get(index).getPeso();	
			Float altura;
			Float alturaRespaldo= list.get(index).getAltura();
			Float largo;
			Float largoRespaldo = list.get(index).getLargo();
			
			System.out.println("********************** Edici�n de Mascota ***********************");
			System.out.println("*****************************************************************");
			System.out.println("Nombre: " +list.get(index).getNombre() + ", Peso: " + list.get(index).getPeso() +
					", Altura: " + list.get(index).getAltura() + ", Largo: " + list.get(index).getLargo());
			System.out.println("*****************************************************************");
			
			System.out.println(" [ Dejar en blanco si se desea conservar el dato ] ");
			System.out.print("> Nuevo nombre de mascota: ");
			nombre = Input.scannLine();
			if(nombre.equals(""))
				nombre = nombreRespaldo;
			
			String editar;
			System.out.print("> �Editar peso? [Dejar en blanco para conservar antiguo dato] ");
			editar = Input.scannLine();
			if(editar.equals("")||editar.toLowerCase().toCharArray()[0]=='n'){
				peso = pesoRespaldo;
			}else{
				System.out.print("> Nuevo peso de mascota: ");
				peso = Input.scannFloat();
			}
			
			System.out.print("> �Editar altura? [Dejar en blanco para conservar antiguo dato] ");
			editar = Input.scannLine();
			if(editar.equals("")||editar.toLowerCase().toCharArray()[0]=='n'){
				altura = alturaRespaldo;
			}else{
				System.out.print("> Nueva altura de mascota: ");
				altura = Input.scannFloat();
			}
			
			System.out.print("> �Editar largo? [Dejar en blanco para conservar antiguo dato] ");
			editar = Input.scannLine();
			if(editar.equals("")||editar.toLowerCase().toCharArray()[0]=='n'){
				largo = largoRespaldo;
			}else{
				System.out.print("> Nuevo largo de mascota: ");
				largo = Input.scannFloat();
			}
				
			list.get(index).setNombre(nombre);
			list.get(index).setAltura(altura);
			list.get(index).setLargo(largo);
			list.get(index).setPeso(peso);
			
			System.out.println("********************* Cambios realizados correctamente *********************");
			System.out.println("Nombre: " +list.get(index).getNombre() + ", Peso: " + list.get(index).getPeso() +
					", Altura: " + list.get(index).getAltura() + ", Largo: " + list.get(index).getLargo());
			System.out.println("****************************************************************************");
			
		}else{
			
			String nombreRespaldo = list.get(index).getPropietario().getName();
			String apellidoRespaldo = list.get(index).getPropietario().getSurname();
			String telefonoRespaldo = list.get(index).getPropietario().getPhone();
			String eMailRespaldo = list.get(index).getPropietario().getEmail();
			String addressRespaldo = list.get(index).getPropietario().getAddress();
			String nombre;
			String apellido;
			String telefono;
			String eMail;
			String address;
			
			System.out.println("***************************** Edici�n de Propietario ******************************");
			System.out.println("***********************************************************************************");
			System.out.println("Nombre: " +list.get(index).getPropietario().getFullName() + ", Telefono: " + list.get(index).getPropietario().getPhone() +
					", Correo: " + list.get(index).getPropietario().getEmail() + ", Direcci�n: " + list.get(index).getPropietario().getAddress());
			System.out.println("***********************************************************************************");
			System.out.println(" [ Dejar en blanco si se desea conservar el dato ] ");
			System.out.print("> Nuevo nombre de propietario: ");
			nombre = Input.scannLine();
			System.out.print("> Nuevo apellido de propietario: ");
			apellido = Input.scannLine();
			
			do{
				System.out.print("> Nuevo tel�fono de propietario: ");
				telefono = Input.scannLine();
				if(telefono.equals(""))
					telefono = telefonoRespaldo;
				if(!phoneValidator(telefono)){
					System.out.println("Formato tel�fono incorrecto\n"
							+ "Formato : �nicamente n�meros - M�nimo 9 cifras - M�ximo 13");
				}
			}while(!phoneValidator(telefono));
			
			do{
				System.out.print("> Nuevo correo electr�nico de propietario: ");
				eMail = Input.scannLine();
				if(eMail.equals(""))
					eMail = eMailRespaldo;
				if(!mailValidator(eMail)){
					System.out.println("El correo no est� en formato correcto...");
				}
			}while(!mailValidator(eMail));
			
			System.out.print("> Nueva direcci�n de propietario: ");
			address = Input.scannLine();
			
			if(nombre.equals(""))
				nombre  = nombreRespaldo;
			if(apellido.equals(""))
				apellido = apellidoRespaldo;
			if(address.equals(""))
				address = addressRespaldo;
			
			list.get(index).getPropietario().setName(nombre);
			list.get(index).getPropietario().setSurname(apellido);
			list.get(index).getPropietario().setAddress(address);
			list.get(index).getPropietario().setEmail(eMail);
			list.get(index).getPropietario().setPhone(telefono);
			
			System.out.println("******************************** Cambios realizados correctamente **********************************");
			System.out.println("Nombre: " +list.get(index).getPropietario().getFullName() + ", Telefono: " + list.get(index).getPropietario().getPhone() +
					", Correo: " + list.get(index).getPropietario().getEmail() + ", Direcci�n: " + list.get(index).getPropietario().getAddress());
			System.out.println("***************************************************************************************************");
			
		}
	}
	
	public static void editMascotaListAll(ArrayList<Mascota> list){
		
		if(list.size()==0){
			System.err.println("Lista vac�a. No se encuentra resultados");
		}else{
			
			int index;
			System.out.println("********************* Men� edici�n por listado completo *********************");
			do{
				System.out.println("Escoge un �ndice a editar : ");
				listAllMascotas(list);
				index = Input.scannInt();
				if(index<1||index>list.size()){
					System.err.println("Posici�n no v�lida.");
				}
			}while(index<1||index>list.size());
			editIndex(list, --index);
		}
	}
	
	public static void deleteByMascotaNombre(ArrayList<Mascota> list) {
		
		if(list.size()==0){
			System.err.println("Lista vac�a. No se encuentra resultados");
		}else{

			System.out.println("********************* Men� eliminaci�n por b�squeda de Mascota por nombre *********************");
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
					System.out.print("Selecciona el n�mero de �ndice a eliminar : ");
					for(MascotasMod mascota: resultado){
						System.out.println("ID: " + mascota.getId() +" | Nombre: " +mascota.getNombre() + " | Tipo: " + mascota.getTipo());
						arrayIndex[i++] = mascota.getId();
					}
					index = Input.scannInt();
					isCorrect = checkIndex(arrayIndex,index);
					if(!isCorrect){
						System.err.println("Error, �ndice incorrecto.");
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
			System.err.println("Lista vac�a. No se encuentra resultados");
		}else{

			System.out.println("********************* Men� eliminaci�n por b�squeda de Propietario por nombre *********************");
			System.out.println("************************* Eliminar al propietario eliminar� sus Mascotas **************************");

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
					System.out.print("Selecciona el n�mero de �ndice a eliminar : ");
					for(PersonasMod person: resultado){
						System.out.println("ID: " + person.getId() +" | Nombre: " +person.getNombre() + " | Apellido: " + person.getApellido());
						arrayIndex[i++] = person.getId();
					}
					index = Input.scannInt();
					isCorrect = checkIndex(arrayIndex,index);
					if(!isCorrect){
						System.err.println("Error, �ndice incorrecto.");
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
			System.err.println("Lista vac�a. No se encuentra resultados");
		}else{

			System.out.println("********************* Men� edici�n por b�squeda de Mascota por nombre *********************");
			String nombreMascota = scanNombreMascota();
			ArrayList<Mascota> listadoDeBusqueda= searchNameMascota(list, nombreMascota);
			if(listadoDeBusqueda==null){
				System.err.println("No se encontraron resultados");
			}else if(listadoDeBusqueda.size()==1){
				editIndex(listadoDeBusqueda,0);
			}else{
				int index;
				do{
					listAllMascotas(listadoDeBusqueda);
					System.out.println("Selecciona el �ndice a editar");
					index = Input.scannInt();
					if(index<1||index>listadoDeBusqueda.size()){
						System.err.println("Error, �ndice incorrecto.");
					}
				}while(index<1||index>listadoDeBusqueda.size());
				index--;
				int indexReal = list.indexOf(listadoDeBusqueda.get(index));
				editIndex(list,indexReal);
			}
		}
	}
	
	public static void editByPropietarioNombre(ArrayList<Mascota> list) {
		
		if(list.size()==0){
			System.err.println("Lista vac�a. No se encuentra resultados");
		}else{
			
			System.out.println("********************* Men� edici�n por b�squeda de Propietario por nombre *********************");
			String nombrePropietario = scanNombrePropietario();
			ArrayList<Mascota> listadoDeBusqueda= searchNamePropietario(list, nombrePropietario);
			if(listadoDeBusqueda==null){
				System.err.println("No se encontraron resultados");
			}else if(listadoDeBusqueda.size()==1){
				editIndex(listadoDeBusqueda,0);
			}else{
				int index;
				do{
					listAllMascotas(listadoDeBusqueda);
					System.out.println("Selecciona el �ndice a editar");
					index = Input.scannInt();
					if(index<1||index>listadoDeBusqueda.size()){
						System.err.println("Error, �ndice incorrecto.");
					}
				}while(index<1||index>listadoDeBusqueda.size());
				index--;
				int indexReal = list.indexOf(listadoDeBusqueda.get(index));
				editIndex(list,indexReal);
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
	
	public static void removeMascota(int indiceMascota, ArrayList<Mascota> list) {
			list.remove(indiceMascota);
			GsonHelper.listaMascotasToJson(list);
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
			System.out.println("Lista vac�a. No hay resultados.");
		}else{
			System.out.println("***********************************************************************************************************************************");
			for(int i=0;i<list.size();i++){
			System.out.print(i+1 + " -> " + "Tipo: " + list.get(i).getClass().getSimpleName() +
					", Nombre: " +list.get(i).getNombre() + ", Peso: " + list.get(i).getPeso() +
					", Altura: " + list.get(i).getAltura() + ", Largo: " + list.get(i).getLargo() + 
					", Estado Nutricion: " + list.get(i).getEstadoNutricion() + ", Raci�n: " +list.get(i).getPesoRacion());
			
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
				System.out.print("Introduce el �ndice a eliminar : ");
				index  = Input.scannInt();
				if(index<1||index>list.size()){
					System.out.println("Error, �ndice incorrecto, vuelve a probar");
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
			System.out.print("Escoge opci�n : ");
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
					System.out.print("Introduce correo electr�nico o parte de �l : ");
					email = Input.scannLine().toLowerCase();
					if(email.equals("")){
						System.out.println("\nNo has introducido ning�n valor");
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
				// AQU� ESTA LA DEFINICI�N DE ESTA CLASE ANONIMA
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
				// AQU� ESTA LA DEFINICI�N DE ESTA CLASE ANONIMA
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
				// AQU� ESTA LA DEFINICI�N DE ESTA CLASE ANONIMA
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
				// AQU� ESTA LA DEFINICI�N DE ESTA CLASE ANONIMA
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
			System.out.print("Elige opci�n : ");
			option = Input.scannLine().toLowerCase();
			if(!(option.equals("canido")||option.equals("felino")||option.equals("ave")||option.equals("roedor"))){
				System.err.print("Opci�n no v�lida.");
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
