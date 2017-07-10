package main;

public class Roedor extends Mascota {

	
	// Llamada al método constructor del padre
	public Roedor(String nombre, float peso, float altura, float largo, float calidad){
		super(nombre, peso, altura, largo, calidad);
	}

	@Override
	public float getPesoRacion() {
		return 0.1f*getPeso()*(2f-getCalidad());
	}
	
	@Override
	public float getEstadoNutricion(){
		return getPeso()/((0.3f*getAltura())*getLargo());
	}


	@Override
	protected String getTypeClass() {
		return getClass().getName();
	}
	
	@Override
	public String toString() {
		return super.toString() + " tipo Roedor"; 
	}
}
