package main;
import java.util.ArrayList;

import util.FileHelper;
import data.BBDDHelper;
import data.GsonHelper;
import model.MascotasMod;
import util.Input;

public class PetsApp {

	public static void main(String args[]){	
		
		ArrayList<Mascota> list = null;
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
					UserInterface.listAllMascotas(BBDDHelper.dbAddToList());
					break;
				case "eliminar":
					UserInterface.showMenuDelete();
					String optionDelete = UserInterface.scanOption(list);
					if(optionDelete.equals("listar")){
						UserInterface.removeFromIndexMascotas(BBDDHelper.dbAddToList());
					}else if(optionDelete.equals("mascota")){
						UserInterface.deleteByMascotaNombre(BBDDHelper.dbAddToList());
					}else{
						UserInterface.deleteByPropietarioNombre(BBDDHelper.dbAddToList());
					}
					break;
				case "editar":
					UserInterface.showMenuEdit();
					String optionEdit = UserInterface.scanOption(BBDDHelper.dbAddToList());
					if(optionEdit.equals("mascota")){
						UserInterface.editByMascotaNombre(BBDDHelper.dbAddToList());
					}else if(optionEdit.equals("propietario")){
						UserInterface.editByPropietarioNombre(BBDDHelper.dbAddToList());
					}
					break;
				case "buscar":
					UserInterface.listAllMascotas(UserInterface.showMenuBuscar(BBDDHelper.dbAddToList()));
					break;
				case "mascotas":
					UserInterface.listAllMascotas(UserInterface.sortMascotasByName(BBDDHelper.dbAddToList()));
					break;
				case "propietarios":
					UserInterface.listAllMascotas(UserInterface.sortMascotasByPropietarios(BBDDHelper.dbAddToList()));
					break;
				case "clases":
					UserInterface.listAllMascotas(UserInterface.showMenuTipos(BBDDHelper.dbAddToList()));
					break;
				case "peso":
					UserInterface.listAllMascotas(UserInterface.sortMascotasByPeso(BBDDHelper.dbAddToList()));
					break;
				case "nutricion":
					UserInterface.listAllMascotas(UserInterface.sortMascotasByNutricion(BBDDHelper.dbAddToList()));
					break;
				case "salir":
					System.out.println(" <******  GRACIAS POR USAR PETS APP V2.0  ******>");
					break;
				default:
					System.out.println("Opción incorrecta. Vuelve a intentarlo.");
					break;
			}	
		}while(!option.equals("salir"));
		
	}
	
	
}
