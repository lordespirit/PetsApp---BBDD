package model;

import java.sql.Date;

/**
 * Clase POJO 
 * @author adminCIFO
 *
 */

public class CommentsMod extends Table {
	    

	    private String myUser; 
		private String email;
		private String webpage; 
		private Date   datum;
		private String summary;
		private String comments; 
		
		public CommentsMod(){
			setId(-1); 
		}
	
		public String getMyUser() {
			return myUser;
		}
		public void setMyUser(String myUser) {
			this.myUser = myUser;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getWebpage() {
			return webpage;
		}
		public void dbManager(String webpage) {
			this.webpage = webpage;
		}
		public Date getDatum() {
			return datum;
		}
		public void setDatum(Date datum) {
			this.datum = datum;
		}
		public void setWebpage(String webpage) {
			this.webpage = webpage;
		}

		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}
		
}