package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import model.MascotasMod;

public class DBMMascota extends DBManager<MascotasMod>{

	public DBMMascota(String dbhost, String dbName, String dbTable) {
		super(dbhost, dbName, dbTable);
	}

	@Override
	protected MascotasMod mapDbToObject(ResultSet resultSet) throws SQLException {
		
    	int id = resultSet.getInt("id");
    	int idPropietario = resultSet.getInt("idPropietario");
        String nombre = resultSet.getString("nombre");
        String tipo = resultSet.getString("tipo");
        Timestamp ingreso = resultSet.getTimestamp("ingreso");
        float peso = resultSet.getFloat("peso");
        float altura = resultSet.getFloat("altura");
        float largo = resultSet.getFloat("largo");
        float calidad = resultSet.getFloat("calidad");
   
        MascotasMod mascota = new MascotasMod();    
        mascota.setId(id);
        mascota.setTipo(tipo);
        mascota.setIdPropietario(idPropietario);
        mascota.setNombre(nombre);
        mascota.setIngreso(ingreso);
        mascota.setPeso(peso);
        mascota.setAltura(altura);
        mascota.setLargo(largo);
        mascota.setCalidad(calidad);
        return mascota; 
	}

	protected HashMap<String,Object> mapObjectToDb(MascotasMod mascota){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("id", mascota.getId());
		map.put("tipo", mascota.getTipo());
		map.put("idPropietario",mascota.getIdPropietario());
		map.put("nombre",mascota.getNombre()); 
		map.put("peso",mascota.getPeso()); 
		//map.put("ingreso",mascota.getIngreso());
		map.put("altura",mascota.getAltura());
		map.put("largo",mascota.getLargo());
		map.put("calidad", mascota.getCalidad());
		return map;	
	}

}