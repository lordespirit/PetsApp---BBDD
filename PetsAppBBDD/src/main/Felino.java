package main;

public class Felino extends Mascota {

	
	// Llamada al método constructor del padre
	public Felino(String nombre, float peso, float altura, float largo, float calidad){
		super(nombre, peso, altura, largo,calidad);
	}

	@Override
	public float getPesoRacion() {
		return 0.2f*getPeso()*(2f-getCalidad());
	}
	
	@Override
	public float getEstadoNutricion(){
		return getPeso()/(getAltura()*getLargo());
	}


	
	@Override
	protected String getTypeClass() {
		return getClass().getName();
	}
	
	@Override
	public String toString() {
		return super.toString() + " tipo Felino"; 
	}
}
