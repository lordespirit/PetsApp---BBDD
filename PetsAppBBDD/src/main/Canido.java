package main;

public class Canido extends Mascota {

	
	// Llamada al método constructor del padre
	public Canido(String nombre, float peso, float altura, float largo, float calidad){
		super(nombre, peso, altura, largo,calidad);
	}
	
	// Métodos sobreescritos
	@Override
	public float getEstadoNutricion(){
		return getPeso()/(getAltura()*getLargo());
	}
	 

	@Override // definir metodo abstracto de herencia
	public float getPesoRacion() {
		return 0.3f*getPeso()*(2f-getCalidad());
	}
	

	@Override
	protected String getTypeClass() {
		return getClass().getName();
	}
	
	@Override
	public String toString() {
		return super.toString() + " tipo Canido"; 
	}
}
