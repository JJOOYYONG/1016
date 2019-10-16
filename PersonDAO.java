package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.command.ddl.CreateTable;

public class PersonDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public PersonDAO() {
		
		createTable();
		
	}
	

	private Connection getConnection() throws Exception {
		Class.forName("org.h2.Driver"); // DB드라이버 클래스 로딩
		Connection con = DriverManager.getConnection("jdbc:h2:~/javafx_test", "sa", "");
		
		/*
		 * create table if not exists person(
			  no INT PRIMARY KEY,
			 name VARCHAR(30),
			 email VARCHAR(30),
			 tel VARCHAR(30)
			);
		  
		 * */
		return con;
	}//getConnection
	
	
	private void close(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(rs !=null) {
			try {
				rs.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
	}//close
	
	
	
	public void createTable(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE IF NOT EXISTS person ( ");
		sb.append("    no INT PRIMARY KEY, ");
		sb.append("    name VARCHAR(30), ");
		sb.append("    email VARCHAR(30), ");
		sb.append("    tel VARCHAR(30) ");
		sb.append(") ");
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.executeUpdate(); // 테이블 없으면 생성
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,null);
		}
	}//createTable method
	
	
	public int insert(PersonVO personVO) {
		int count = 0;
		String sql = "INSERT INTO person(no,name,email,tel) VALUES(?,?,?,?)";
		
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, personVO.getNo());
			pstmt.setString(2, personVO.getName());
			pstmt.setString(3, personVO.getEmail());
			pstmt.setString(4, personVO.getTel());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, null);
		}
		
		
		return count;
	}//insert method
	
	
	public List<PersonVO>getPersons(){
		List<PersonVO>list = new ArrayList<PersonVO>();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("select * from person order by no");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PersonVO personVO = new PersonVO();
				personVO.setNo(rs.getInt("no"));
				personVO.setName(rs.getString("name"));
				personVO.setEmail(rs.getString("email"));
				personVO.setTel(rs.getString("tel"));
				
				list.add(personVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
	 return list;
	
	}//getPersons
	
	
	
	public int deleteByNo(int no) {
		int count = 0;
		String sql = "delete from person where no = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			//실행
			count = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,null);
		}
		
		
		return count;
	}//deleteByno
	
	
	
	public static void main(String[] args) {
		
		
		new PersonDAO();
		
	}//main

	
}//personDao
