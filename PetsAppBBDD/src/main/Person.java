package main;

public class Person {
	
	private String name; 
	private String surname; 
	private String phone; 
	private String email; 
	private String address;
	
	/**
	 * Inicia las variables del objeto a partir de una cadena con formato:
	 * format:  name surname;phone;email;address
	 *  
	 * @param contactFileFormat  String con formato 
	 */
	public Person(String contactFileFormat) { 
	
		String[] values = contactFileFormat.split(";"); 
		if(values.length<4)
			throw new RuntimeException("Formato incorrecto en contactFileFormat"); 
		
		String  fullName[] = values[0].split(" "); 
		String nombre = fullName.length==2?fullName [0]:values[0];
		String apellido  = fullName.length==2?fullName [1]:""; 
		
		//setName( nombre );
		//setSurname(apellido);
		
		this.name = nombre;
		this.surname = apellido; 
		
		
		setPhone(values[1]);
		setEmail(values[2]);
		setAddress(values[3]);	
	}
	
	public Person(){
		
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
			this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	@Override
	public String toString(){
		String baseStr = super.toString(); 	
		return baseStr + "    person: " + name  + " " + surname + " " + email + " " + phone + ""  + address;	
	}



	@Override
	public boolean equals(Object obj) {
	    boolean r=false; 
		Person person = (Person) obj; 	
		if(this.name.equals(person.getName())&& 
				this.surname.equals(person.getSurname())&& 
					this.phone.equals(person.getPhone()) &&	
						this.email.equals(person.getEmail())  &&	
						  this.address.equals(person.getAddress()) 
							){
			r = true; 
		}
		return r; 
	}


	
	public String getFullName(){
		return getName() + " " + getSurname();
	}
	
	
	public static String getNameFromFullName(String strfullName){
		String  fullName[] = strfullName.split(" "); 
		return fullName [0];
	}

	public static String getSurnameFromFullName(String strfullName){
		String  fullName[] = strfullName.split(" "); 
		return fullName.length==2?fullName [1]:""; 
	}
}