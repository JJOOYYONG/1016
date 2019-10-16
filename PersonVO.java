package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonVO {


	private IntegerProperty no;
	private StringProperty name;
	private StringProperty email;
	private StringProperty tel;
	

	public PersonVO(){
		no = new SimpleIntegerProperty();
		name = new SimpleStringProperty();
		email = new SimpleStringProperty();
		tel =  new SimpleStringProperty();
		
	}
	
	
	
	
	public PersonVO(int no, String name, String email, String tel) {
	
		this.no = new SimpleIntegerProperty(no);
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
		this.tel = new SimpleStringProperty(tel);
	}
	
	
	
	
	
	
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email.get();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name.get();
	}
	/**
	 * @return the no
	 */
	public int getNo() {
		return no.get();
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel.get();
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email.set(email);
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name.set(name);
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no.set(no);;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel.set(tel);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonVO [no=");
		builder.append(no.get());
		builder.append(", name=");
		builder.append(name.get());
		builder.append(", email=");
		builder.append(email.get());
		builder.append(", tel=");
		builder.append(tel.get());
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//personVO
