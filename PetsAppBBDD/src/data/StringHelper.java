package data;

public class StringHelper {
	public static boolean isInteger(String num){
		char[] numByNum = num.toCharArray();
		
		for (int i=0; i<numByNum.length; i++){
			if(numByNum[i]<'0' || numByNum[i]>'9'){
				return false;
			}
		}return true;
	}
	
	public static boolean isFloat(String num){
		boolean isFloat=false;
		try{
			Float.valueOf(num);
			isFloat=true;
		}catch (NumberFormatException e) {
			isFloat=false;
		}
		return isFloat;
	}
	
	public static boolean isEmail(String email){
		char[] chrByChr = email.toCharArray();
		boolean arroba=false;
		
		for (int i=0; i<chrByChr.length; i++){
			if(chrByChr[i]=='@'){
				arroba=true;
			}else if (arroba && chrByChr[i]=='.'){
				return true;
				
			}
		}return false;
	}
}