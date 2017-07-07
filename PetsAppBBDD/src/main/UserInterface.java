package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import data.GsonHelper;
import data.StringHelper;
import util.Finder;
import util.Input;

public class UserInterface {
	
	private static final String[] ESPECIES = new String[]{"gato", "perro", "ave", "roedor"};
		
	static Map<String, Class> map = new TreeMap<String, Class>();
	/** Adicione las clases que sea necesario */
	 static {
	        map.put(ESPECIES[0],Felino.class);
	        map.put(ESPECIES[1],Canido.class);
	        map.put(ESPECIES[2],Ave.class);
	        map.put(ESPECIES[3],Roedor.class);
	    } 
	
	public interface CheckFormat{
		boolean checkBadFormat(String str); 
	}
	
	private static CheckFormat  checkFloat  =  new CheckFormat() {
		@Override
		public boolean checkBadFormat(String str) {
			return str.equals("") || !StringHelper.isFloat(str); 
		}
}; 
	
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
			
			if(tipo.equals("canido")){
				
				newCanido = new Canido(name,peso,altura,largo);
				float calidadColmillo;
				do{
					System.out.print("> Calidad de colmillo [formato de 0,0 a 1,0] : ");
					calidadColmillo = Input.scannFloat();
					if(calidadColmillo<0||calidadColmillo>1){
						System.err.println("Calidad colmillo entre 0 y 1");
					}
				}while(calidadColmillo<0||calidadColmillo>1);
				newCanido.setCalidadColmillo(calidadColmillo);
				
			}else if(tipo.equals("felino")){
				
				newFelino = new Felino(name,peso,altura,largo);
				float calidadGarras;
				do{
					System.out.print("> Calidad de garras [formato de 0,0 a 1,0] : ");
					calidadGarras = Input.scannFloat();
					if(calidadGarras<0||calidadGarras>1){
						System.err.println("Calidad garras entre 0 y 1");
					}
				}while(calidadGarras<0||calidadGarras>1);
				newFelino.setCalidadGarras(calidadGarras);
				
			}else if(tipo.equals("roedor")){
				
				newRoedor = new Roedor(name,peso,altura,largo);
				float calidadPelaje;
				do{
					System.out.print("> Calidad de pelaje [formato de 0,0 a 1,0] : ");
					calidadPelaje = Input.scannFloat();
					if(calidadPelaje<0||calidadPelaje>1){
						System.err.println("Calidad pelaje entre 0 y 1");
					}
				}while(calidadPelaje<0||calidadPelaje>1);
				newRoedor.setCalidadPelaje(calidadPelaje);
				
			}else{
				
				newAve = new Ave(name,peso,altura,largo);
				float calidadPlumas;
				do{
					System.out.print("> Calidad de plumas [formato de 0,0 a 1,0] : ");
					calidadPlumas = Input.scannFloat();
					if(calidadPlumas<0||calidadPlumas>1){
						System.err.println("Calidad plumas entre 0 y 1");
					}
				}while(calidadPlumas<0||calidadPlumas>1);
				newAve.setCalidadPlumas(calidadPlumas);
			}	
			
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
		System.out.println("Elije opción para editar");
		System.out.println("> listar [todo]");
		System.out.println("> mascota [busca por nombre de mascota]");
		System.out.println("> propietario [busca por nombre de propietario]");

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
			System.out.print("Elige Opción : ");
			option = Input.scannLine().toLowerCase();
			if(!(option.equals("listar")||option.equals("mascota")||option.equals("propietario"))){
				System.err.println("Error. Opción incorrecta");
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
			System.err.println("Error, opción no válida.");
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
			
			System.out.println("********************** Edición de Mascota ***********************");
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
			System.out.print("> ¿Editar peso? [Dejar en blanco para conservar antiguo dato] ");
			editar = Input.scannLine();
			if(editar.equals("")||editar.toLowerCase().toCharArray()[0]=='n'){
				peso = pesoRespaldo;
			}else{
				System.out.print("> Nuevo peso de mascota: ");
				peso = Input.scannFloat();
			}
			
			System.out.print("> ¿Editar altura? [Dejar en blanco para conservar antiguo dato] ");
			editar = Input.scannLine();
			if(editar.equals("")||editar.toLowerCase().toCharArray()[0]=='n'){
				altura = alturaRespaldo;
			}else{
				System.out.print("> Nueva altura de mascota: ");
				altura = Input.scannFloat();
			}
			
			System.out.print("> ¿Editar largo? [Dejar en blanco para conservar antiguo dato] ");
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
			
			System.out.println("***************************** Edición de Propietario ******************************");
			System.out.println("***********************************************************************************");
			System.out.println("Nombre: " +list.get(index).getPropietario().getFullName() + ", Telefono: " + list.get(index).getPropietario().getPhone() +
					", Correo: " + list.get(index).getPropietario().getEmail() + ", Dirección: " + list.get(index).getPropietario().getAddress());
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
				eMail = Input.scannLine();
				if(eMail.equals(""))
					eMail = eMailRespaldo;
				if(!mailValidator(eMail)){
					System.out.println("El correo no está en formato correcto...");
				}
			}while(!mailValidator(eMail));
			
			System.out.print("> Nueva dirección de propietario: ");
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
					", Correo: " + list.get(index).getPropietario().getEmail() + ", Dirección: " + list.get(index).getPropietario().getAddress());
			System.out.println("***************************************************************************************************");
			
		}
	}
	
	public static void editMascotaListAll(ArrayList<Mascota> list){
		
		if(list.size()==0){
			System.err.println("Lista vacía. No se encuentra resultados");
		}else{
			
			int index;
			System.out.println("********************* Menú edición por listado completo *********************");
			do{
				System.out.println("Escoge un índice a editar : ");
				listAllMascotas(list);
				index = Input.scannInt();
				if(index<1||index>list.size()){
					System.err.println("Posición no válida.");
				}
			}while(index<1||index>list.size());
			editIndex(list, --index);
		}
	}
	
	public static void deleteByMascotaNombre(ArrayList<Mascota> list) {
		
		if(list.size()==0){
			System.err.println("Lista vacía. No se encuentra resultados");
		}else{

			System.out.println("********************* Menú eliminación por búsqueda de Mascota por nombre *********************");
			String nombreMascota = scanNombreMascota();
			ArrayList<Mascota> listadoDeBusqueda= searchNameMascota(list, nombreMascota);
			if(listadoDeBusqueda==null){
				System.err.println("No se encontraron resultados");
			}else if(listadoDeBusqueda.size()==1){
				String estasSeguro;
				System.out.println("Nombre Mascota " + listadoDeBusqueda.get(0).getNombre() + " Tipo: " + listadoDeBusqueda.get(0).getClass().getSimpleName());
				System.out.print("Estás seguro de querer borrarla? : ");
				estasSeguro = Input.scannLine();
				if(estasSeguro.toLowerCase().toCharArray()[0]=='s'){
					removeMascota(0,listadoDeBusqueda);
				}
			}else{
				int index;
				do{
					listAllMascotas(listadoDeBusqueda);
					System.out.println("Hay varias coincidencias.");
					System.out.print("Selecciona el número de índice a eliminar : ");
					index = Input.scannInt();
					if(index<1||index>listadoDeBusqueda.size()){
						System.err.println("Error, índice incorrecto.");
					}
				}while(index<1||index>listadoDeBusqueda.size());
				index--;
				int indexReal = list.indexOf(listadoDeBusqueda.get(index));
				removeMascota(indexReal,list);
			}
		}
	}
	
	public static void deleteByPropietarioNombre(ArrayList<Mascota> list) {
		
		if(list.size()==0){
			System.err.println("Lista vacía. No se encuentra resultados");
		}else{

			System.out.println("********************* Menú eliminación por búsqueda de Propietario por nombre *********************");
			String nombrePropietario = scanNombreMascota();
			ArrayList<Mascota> listadoDeBusqueda= searchNamePropietario(list, nombrePropietario);
			if(listadoDeBusqueda==null){
				System.err.println("No se encontraron resultados");
			}else if(listadoDeBusqueda.size()==1){
				String estasSeguro;
				System.out.println("Nombre Mascota: " + listadoDeBusqueda.get(0).getNombre() + " Tipo: " + listadoDeBusqueda.get(0).getClass().getSimpleName());
				System.out.print("Estás seguro de querer borrarla? : ");
				estasSeguro = Input.scannLine();
				if(estasSeguro.toLowerCase().toCharArray()[0]=='s'){
					removeMascota(0,listadoDeBusqueda);
				}
			}else{
				int index;
				do{
					listAllMascotas(listadoDeBusqueda);
					System.out.println("Hay varias coincidencias.");
					System.out.print("Selecciona el número de índice a eliminar : ");
					index = Input.scannInt();
					if(index<1||index>listadoDeBusqueda.size()){
						System.err.println("Error, índice incorrecto.");
					}
				}while(index<1||index>listadoDeBusqueda.size());
				index--;
				int indexReal = list.indexOf(listadoDeBusqueda.get(index));
				removeMascota(indexReal,list);
			}
		}
	}
	
	public static void editByMascotaNombre(ArrayList<Mascota> list) {
		
		if(list.size()==0){
			System.err.println("Lista vacía. No se encuentra resultados");
		}else{

			System.out.println("********************* Menú edición por búsqueda de Mascota por nombre *********************");
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
					System.out.println("Selecciona el índice a editar");
					index = Input.scannInt();
					if(index<1||index>listadoDeBusqueda.size()){
						System.err.println("Error, índice incorrecto.");
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
			System.err.println("Lista vacía. No se encuentra resultados");
		}else{
			
			System.out.println("********************* Menú edición por búsqueda de Propietario por nombre *********************");
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
					System.out.println("Selecciona el índice a editar");
					index = Input.scannInt();
					if(index<1||index>listadoDeBusqueda.size()){
						System.err.println("Error, índice incorrecto.");
					}
				}while(index<1||index>listadoDeBusqueda.size());
				index--;
				int indexReal = list.indexOf(listadoDeBusqueda.get(index));
				editIndex(list,indexReal);
			}
		}
		
	}

	
	public static void addMascota(Mascota newMascota, ArrayList<Mascota> list) {
			list.add(newMascota);
			GsonHelper.listaMascotasToJson(list);
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
			System.out.println("Lista vacía. No hay resultados.");
		}else{
			System.out.println("*********************************************************************************************************************");
			for(int i=0;i<list.size();i++){
				System.out.println(i+1 + " -> " + "Tipo: " + list.get(i).getClass().getSimpleName() +
						", Nombre: " +list.get(i).getNombre() + ", Peso: " + list.get(i).getPeso() +
						", Altura: " + list.get(i).getAltura() + ", Largo: " + list.get(i).getLargo() + 
						", Estado Nutricion: " + list.get(i).getEstadoNutricion() + ", Ración: " +list.get(i).getPesoRacion());
				System.out.println("Propietario: " + list.get(i).getPropietario().getFullName() + ", " + list.get(i).getPropietario().getPhone() +
				", " +list.get(i).getPropietario().getEmail() + ", " +list.get(i).getPropietario().getAddress());
				System.out.println("*********************************************************************************************************************");
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
		removeMascota(--index, list);
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
