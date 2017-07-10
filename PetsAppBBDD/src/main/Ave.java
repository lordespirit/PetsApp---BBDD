package main;

public class Ave extends Mascota {
	
	// Llamada al método constructor del padre
	public Ave(String nombre, float peso, float altura, float largo, float calidad){
		super(nombre, peso, altura, largo, calidad);
	}

	@Override
	public float getPesoRacion() {
		return 0.1f*getPeso()*(2f-getCalidad());
	}
	
	@Override
	public float getEstadoNutricion(){
		return getPeso()/((0.5f*getAltura())*getLargo());
	}

	@Override
	protected String getTypeClass() {
		return getClass().getName();
	}
	
	@Override
	public String toString() {
		return super.toString() + " tipo Ave"; 
	}
}
