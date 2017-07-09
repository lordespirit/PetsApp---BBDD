package main;
import java.util.ArrayList;

import util.FileHelper;
import data.BBDDHelper;
import data.GsonHelper;
import model.MascotasMod;
import util.Input;

public class PetsApp {

	public static void main(String args[]){	
		
		//final String FILE = "veterinaria.txt";
		//String str = null; 
		ArrayList<Mascota> list = null;
		
		//str = FileHelper.readFileAsString(FILE);
		//list = GsonHelper.jsonFromArrayListMascotaToJson(str);
		
		String option = null;
		UserInterface.showWelcome();
		do{
			UserInterface.showMenuInit();	
			option = Input.scannLine().toLowerCase();
			switch(option){
				case "añadir":
					Mascota newMascota = UserInterface.scanMascota(list);
					UserInterface.addMascota(newMascota);
					break;
				case "listar":
					UserInterface.listAllMascotas(BBDDHelper.BbddToList());
					break;
				case "eliminar":
					UserInterface.showMenuDelete();
					String optionDelete = UserInterface.scanOption(list);
					if(optionDelete.equals("listar")){
						UserInterface.removeFromIndexMascotas(list);
					}else if(optionDelete.equals("mascota")){
						UserInterface.deleteByMascotaNombre(list);
					}else{
						UserInterface.deleteByPropietarioNombre(list);
					}
					break;
				case "editar":
					UserInterface.showMenuEdit();
					String optionEdit = UserInterface.scanOption(list);
					if(optionEdit.equals("listar")){
						UserInterface.editMascotaListAll(list);
					}else if(optionEdit.equals("mascota")){
						UserInterface.editByMascotaNombre(list);
					}else{
						UserInterface.editByPropietarioNombre(list);
					}
					break;
				case "buscar":
					UserInterface.listAllMascotas(UserInterface.showMenuBuscar(list));
					break;
				case "mascotas":
					UserInterface.listAllMascotas(UserInterface.sortMascotasByName(list));
					break;
				case "propietarios":
					UserInterface.listAllMascotas(UserInterface.sortMascotasByPropietarios(list));
					break;
				case "clases":
					UserInterface.listAllMascotas(UserInterface.showMenuTipos(list));
					break;
				case "peso":
					UserInterface.listAllMascotas(UserInterface.sortMascotasByPeso(list));
					break;
				case "nutricion":
					UserInterface.listAllMascotas(UserInterface.sortMascotasByNutricion(list));
					break;
				case "salir":
					System.out.println(" <******  GRACIAS POR USAR PETS APP V2.0  ******>");
					break;
				default:
					System.out.println("Opción incorrecta. Vuelve a intentarlo.");
					break;
			}	
		}while(!option.equals("salir"));
		//str = GsonHelper.listaMascotasToJson(list);
		//FileHelper.writeFileAsString(str, FILE);
		//BBDDHelper.listToBbdd(list);
	}
	
	
}
