package com.kh.jdbc.day01.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentDAO {
	public List<Student> selectAll() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "STUDENT";
		String password = "STUDENT";
		String sql = "SELECT * FROM STUDENT_TBL";
		Student student = null;
		List<Student> sList = null;
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. DB 연결 생성
			Connection conn = DriverManager.getConnection(url, user, password);
			// 3. 쿼리문 실행준비(Statement 생성)
			Statement stmt = conn.createStatement();
			// 4. 쿼리문 실행 및 결과 받기
			ResultSet rset = stmt.executeQuery(sql);
			sList = new ArrayList<Student>();
			// 후처리
			while(rset.next()) {
				student = new Student();
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setGender(rset.getString("GENDER"));
				student.setAge(rset.getInt("AGE"));
				// 컬럼명 적어서 필드 값 가져올때 해당 자료형으로 적어줌
				student.setEmail(rset.getString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				student.setAddress(rset.getString("ADDRESS"));
				student.setHobby(rset.getString("HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
				sList.add(student);
			}
			// 자원해제
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList;			
	}
	
	public int insertMember(Student student) { 
		// 1. 드라이버등록
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "STUDENT";
		String password = "STUDENT";
		String sql = "INSERT INTO STUDENT_TBL VALUES("
					+ "'"+student.getStudentId()+"',"
						+ "'"+student.getStudentPwd()+"',"
							+ "'"+student.getStudentName()+"',"
								+ "'"+student.getGender()+"',"
									+ student.getAge() +","
										+ "'khuser03@naver.com',"
											+ "'01088320393',"
												+ "'서울시 동대문구',"
													+ "'자전거,축구',"
														+ "SYSDATE)";
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			// 쿼리문 실행 - DML(INSERT, UPDATE, DELETE)
			result = stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;		
	}
}
