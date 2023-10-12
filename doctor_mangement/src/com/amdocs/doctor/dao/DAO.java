package com.amdocs.doctor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import com.amdocs.doctor.exp.expHandling;
import com.amdocs.doctor.pojo.doctor;

public class DAO {
	
	
	public static int addDoctor(doctor Doctor) {
		
		int rowsAffected=0;
		
			try {
				Connection connection = com_connection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO doctor (doctorId,doctorName,mobileNumber,specialization,availableShift,fees) VALUES (seq.nextval, ?, ?, ?, ?, ?)");
				
				//preparedStatement.setLong(1, Doctor.getDoctorId());
				preparedStatement.setString(1, Doctor.getDoctorName());
				preparedStatement.setString(2, Doctor.getMobileNumber());
				preparedStatement.setString(3, Doctor.getSpecialization());
				preparedStatement.setString(4, Doctor.getAvailableShift());
				preparedStatement.setLong(5,  (long) Doctor.getFees());
				
				rowsAffected = preparedStatement.executeUpdate();
				
				
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return rowsAffected;
		
		
		
	}
	
	public static boolean updateDoctorFees(int id,double newFee) {
		int status=0;
		try {
			Connection connection= com_connection.getConnection();
			PreparedStatement ptr=connection.prepareStatement("UPDATE doctor SET fees = ? WHERE doctorid = ?");
			
			ptr.setLong(1, (long) newFee);
			ptr.setLong(2, id);
			
			status = ptr.executeUpdate();
			
			 ptr.close();
			 connection.close();
		} catch (SQLException e) {
			System.out.println("Failed to update");
		}
         
		if (status>0) {
			return true;
			
		}else
			return false;
	
	}
	
	public static int deleteDoctor(int id) throws SQLException
	{
		Connection connection= com_connection.getConnection();
		PreparedStatement ptr=connection.prepareStatement("DELETE FROM DOCTOR WHERE DOCTORID= ?");
		
		ptr.setInt(1, id);
		
		int status=ptr.executeUpdate();
		
		
		
		ptr.close();
        connection.close();
        
        
        return status;
		
	}
	
	public static List<doctor> showAllDoctors()
	{
		
		List<doctor> temp= new ArrayList<>();
		
		try {
			Connection connection= com_connection.getConnection();
			Statement statement = connection.createStatement();

			// Execute a SQL query to select all employees
			
			ResultSet rs = statement.executeQuery("SELECT * FROM doctor");
			
			while (rs.next())
			{
				doctor d=new doctor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6));
				temp.add(d);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return temp;
	}
	
	public static List<doctor> searchBySpecialization(String sp)
	{
		List<doctor> temp= new ArrayList<>();
		try {
			Connection connection= com_connection.getConnection();
			PreparedStatement ptr=connection.prepareStatement("select * FROM DOCTOR WHERE Specialization= ?");
			ptr.setString(1, sp);
			
			ResultSet rs = ptr.executeQuery();
			while (rs.next())
			{
				doctor d=new doctor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6));
				temp.add(d);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return temp ;
	} 
	
	public static List<doctor> searchByFeesAndShift(String shift)
	{
		List<doctor> temp= new ArrayList<>();
		try {
			Connection connection= com_connection.getConnection();
			PreparedStatement ptr=connection.prepareStatement("select * from doctor where fees < (select min(fees) from doctor where availableshift like ?)");
			ptr.setString(1, shift);
			
			ResultSet rs = ptr.executeQuery();
			while (rs.next())
			{
				doctor d=new doctor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6));
				temp.add(d);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	return temp ;
	}
	
	public static int countDoctorsByShift(String shift)
	{
		int result=0;
		try {
			Connection connection= com_connection.getConnection();
			PreparedStatement ptr=connection.prepareStatement("SELECT COUNT(* ) FROM doctor WHERE availableShift = ?");
			ptr.setString(1, shift);
			
			ResultSet resultSet = ptr.executeQuery();
			

			if (resultSet.next()) {
			    result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
		
		
	}
	

