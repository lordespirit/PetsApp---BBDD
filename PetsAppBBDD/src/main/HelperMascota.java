package main;

import java.util.ArrayList;
import java.util.Comparator;

public class HelperMascota {

	
	
    public static void sortArrayMascotaByLength(ArrayList<Mascota> list){
		list.sort(new Comparator<Mascota>() {

			@Override
			public int compare(Mascota o1, Mascota o2) {
				//-1 si es menor
				int r =0;				
				if(o1.getLargo()<o2.getLargo()){
					r=-1;
				}else if(o1.getLargo()>o2.getLargo()){
					r=1;
				}
				
				return r;
			}
		});		
	}

    
	public static void sortArrayMascotaByName(ArrayList<Mascota> list){
		list.sort(new Comparator<Mascota>() {

			@Override
			public int compare(Mascota o1, Mascota o2) {
				
				return o1.getNombre().toLowerCase()
						.compareTo(o2.getNombre().toLowerCase());
				
			}
		});
		
	}
	
    public static void sortArrayMascotaByEmailOwner(ArrayList<Mascota> list){
		list.sort(new Comparator<Mascota>(){
			
			@Override
			public int compare(Mascota o1, Mascota o2) {
				return o1.getPropietario().getEmail().toLowerCase()
						.compareTo(o2.getPropietario().getEmail().toLowerCase());
			}
		});
		
	}
    
	
}
