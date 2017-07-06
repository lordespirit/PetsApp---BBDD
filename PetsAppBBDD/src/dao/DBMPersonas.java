package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import model.Personas;

public class DBMPersonas extends DBManager<Personas> {

	public DBMPersonas(String dbhost, String dbName, String dbTable) {
		super(dbhost, dbName, dbTable);
	}

	@Override
	protected Personas mapDbToObject(ResultSet resultSet) throws SQLException {
		
    	int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String apellido = resultSet.getString("apellido");
        String email = resultSet.getString("email");
        String telefono = resultSet.getString("telefono");
        String direccion = resultSet.getString("direccion");

        Personas persona = new Personas();    
        persona.setId(id);
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setEmail(email);
        persona.setTelefono(telefono);
        persona.setDireccion(direccion);
        return persona; 
	}

	protected HashMap<String,Object> mapObjectToDb(Personas persona){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("id", persona.getId());
		map.put("nombre",persona.getNombre()); 
		map.put("apellido",persona.getApellido()); 
		map.put("email",persona.getEmail());
		map.put("telefono",persona.getTelefono());
		map.put("direccion",persona.getDireccion());
		return map;	
	}
	
}
