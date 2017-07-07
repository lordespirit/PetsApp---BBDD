package util;

import java.util.ArrayList;

import main.Mascota;


public class Finder<T> {

	public interface ContainChecker<T>{
		boolean containChecker(T object, Object patron);
	}
		
	public ArrayList<T> find(ArrayList<T> list, Object patron, ContainChecker<T> checker){	

		ArrayList<T> listResult = new ArrayList<T>();

		for(int i=0;i<list.size();i++){
			if(checker.containChecker(list.get(i), patron)){
				listResult.add(list.get(i));
			}
		}
		
		return listResult;
	}
	
}
