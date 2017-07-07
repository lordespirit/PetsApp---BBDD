package main;

public class Ave extends Mascota {
	
	private float calidadPlumas;
			
	// Llamada al método constructor del padre
	public Ave(String nombre, float peso, float altura, float largo){
		super(nombre, peso, altura, largo);
		this.calidadPlumas = 1;
	}

	@Override
	public float getPesoRacion() {
		return 0.1f*getPeso()*(2f-calidadPlumas);
	}
	
	@Override
	public float getEstadoNutricion(){
		return getPeso()/((0.5f*getAltura())*getLargo());
	}

	public float getCalidadPlumas() {
		return calidadPlumas;
	}

	public void setCalidadPlumas(float calidadPlumas) {
		this.calidadPlumas = calidadPlumas;
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
