package main;

public class Roedor extends Mascota {

	private float calidadPelaje;
	
	// Llamada al método constructor del padre
	public Roedor(String nombre, float peso, float altura, float largo) {
		super(nombre, peso, altura, largo);
		this.calidadPelaje = 1;
	}

	@Override
	public float getPesoRacion() {
		return 0.1f*getPeso()*(2f-calidadPelaje);
	}
	
	@Override
	public float getEstadoNutricion(){
		return getPeso()/((0.3f*getAltura())*getLargo());
	}

	public float getCalidadPelaje() {
		return calidadPelaje;
	}

	public void setCalidadPelaje(float calidadPelaje) {
		this.calidadPelaje = calidadPelaje;
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
