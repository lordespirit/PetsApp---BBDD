package main;

public class Canido extends Mascota {

	private float calidadColmillo;
	
	// Llamada al método constructor del padre
	public Canido(String nombre, float peso, float altura, float largo){
		super(nombre, peso, altura, largo);
		this.calidadColmillo = 1;
	}
	
	// Métodos sobreescritos
	@Override
	public float getEstadoNutricion(){
		return getPeso()/(getAltura()*getLargo());
	}
	 

	@Override // definir metodo abstracto de herencia
	public float getPesoRacion() {
		return 0.3f*getPeso()*(2f-calidadColmillo);
	}
	
	public float getCalidadColmillo() {
		return calidadColmillo;
	}


	public void setCalidadColmillo(float calidadColmillo) {
		this.calidadColmillo = calidadColmillo;
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
