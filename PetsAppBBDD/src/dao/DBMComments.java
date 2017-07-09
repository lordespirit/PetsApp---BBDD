package dao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import model.CommentsMod;

public class DBMComments extends DBManager<CommentsMod>{

	public DBMComments(String dbhost, String dbName, String dbTable) {
		super(dbhost, dbName, dbTable);
	}

	@Override
	protected CommentsMod mapDbToObject(ResultSet resultSet) throws SQLException {
		// lee el resultado i 
    	int id = resultSet.getInt("id");
        String user = resultSet.getString("myuser");
        String email = resultSet.getString("email");
        String webpage = resultSet.getString("webpage");
        String summary = resultSet.getString("summary");
        Date date = resultSet.getDate("datum");
        String comments = resultSet.getString("comments");
   
        CommentsMod comment = new CommentsMod();    
        comment.setId(id);
        comment.setEmail(email);
        comment.setDatum(date);
        comment.setMyUser(user);
        comment.setSummary(summary);
        comment.setComments(comments);
        comment.setWebpage(webpage);
        return comment; 
	}

	protected HashMap<String,Object> mapObjectToDb(CommentsMod comment){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("myuser",comment.getMyUser()); 
		map.put("email",comment.getEmail()); 
		map.put("webpage",comment.getWebpage());
		map.put("datum",comment.getDatum());
		map.put("summary",comment.getSummary());
		map.put("comments",comment.getComments());
		return map;	
	}

}