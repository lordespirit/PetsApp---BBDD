package model;
import java.sql.Timestamp;

public class MascotasMod extends Table  {
		
	private int idPropietario;
    private String nombre; 
    private String tipo;
	private Timestamp ingreso;
	private float peso;
	private float altura;
	private float largo;
	private float calidad;

	public MascotasMod(){
		setId(-1); 
	}


	/* GETTERS AND SETTERS */

	public int getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(int idPropietario) {
		this.idPropietario = idPropietario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	
	public String getTipo(){
		return tipo;
	}

	public Timestamp getIngreso() {
		return ingreso;
	}

	public void setIngreso(Timestamp ingreso) {
		this.ingreso = ingreso;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getLargo() {
		return largo;
	}

	public void setLargo(float largo) {
		this.largo = largo;
	}
	
	public float getCalidad() {
		return calidad;
	}
	
	public void setCalidad(float calidad) {
		this.calidad = calidad;
	}
	
	
}