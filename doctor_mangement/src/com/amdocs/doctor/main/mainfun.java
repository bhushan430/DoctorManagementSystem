package com.amdocs.doctor.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.amdocs.doctor.dao.DAO;
import com.amdocs.doctor.exp.expHandling;
import com.amdocs.doctor.pojo.doctor;

public class mainfun {
	
	public static void main(String []args) throws SQLException
	{
		Scanner sc=new Scanner(System.in);
		boolean f=true;
		
		do
		{
		System.out.println(" \r\n ");
		System.out.println("=============================================");
		
		System.out.println("welcome to doctor management system ");
		System.out.println("1. Add new doctor\r\n"
				+ "2. Update doctor fees\r\n"
				+ "3. Delete doctor\r\n"
				+ "4. View all doctors\r\n"
				+ "5. Find doctor by specialization\r\n"
				+ "6. Find doctors who’s fees is less than all doctors of given shift\r\n"
				+ "7. Count number of doctors by shift\r\n"
				+ "8. Exit");
		System.out.println(" \r\n ");
		System.out.println("=============================================");
		
		
		System.out.println("enter your choice: ");
		int choice=sc.nextInt();
		
		switch (choice) {
		case 1: {
//			System.out.println("enter doctorID:");
//			int doctorId=sc.nextInt();
			
			System.out.println("enter doctorName:");
			String doctorName=sc.next();
			
			System.out.println("enter mobileNumber:");
			String mobileNumber=sc.next();
			
			System.out.println("enter specialization:");
			String specialization=sc.next();
			
			System.out.println("enter availableShift:");
			String availableShift=sc.next();
			
			System.out.println("enter fees:");
			double fees=sc.nextInt();
			
			
			
			doctor Doctor=new doctor(doctorName,mobileNumber,specialization,availableShift,fees);
			int res=DAO.addDoctor(Doctor);
			try {
				if(res==0) throw new expHandling("Unable to add new doctor");
				else System.out.println("Sucssesfully added");
			} catch (expHandling e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			    
			
			
			break;

		}
		case 2: {
			System.out.println("enter doctor id:");
			int id=sc.nextInt();
			
			System.out.println("Enter new fee amount:");
			double newFee=sc.nextDouble();
			boolean h=DAO.updateDoctorFees(id, newFee);
			try {
				if(h==false) throw new expHandling("Unable to update fees");
				else System.out.println("fees updated sucsessfully");
			} catch (expHandling e) {
				
				System.out.println(e.getMessage());
			}
				
			
			break;
			
		}
		case 3: {
			System.out.println("enter Doctor Id to delete doctor: ");
			int del=sc.nextInt();
			int res=DAO.deleteDoctor(del);
			
			try {
				if(res<=0) throw new expHandling("Unable to delete");
				else System.out.println("deleted succesfully");
			} catch (expHandling e) {
				
				System.out.println(e.getMessage());
			}
		
				
		}
		break;
		
		case 4: {
			System.out.println("All Doctor Details");
			List<doctor> doc=DAO.showAllDoctors();
			try {
				if(doc.isEmpty()) throw new expHandling("Unable to delete");
				else {for(doctor doc1:doc) {
					System.out.println(doc1.toString());
					
		         }
				}
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
			
	          }
		break;
		
		case 5: {
			System.out.println("enter spacialization");
			String sp=sc.next();
			List<doctor> dr=DAO.searchBySpecialization(sp);
			try {
				if(dr.isEmpty()) throw new expHandling("no spacializtion found");
				else {
					for(doctor doc1:dr) {
					System.out.println(doc1.toString());
					
		         }
				}
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
			
		}
		break;


		case 6: {
			System.out.println("Enter shift:");
			String str=sc.next();
			List<doctor> dr=DAO.searchByFeesAndShift(str);
			try {
				if(dr.isEmpty()) throw new expHandling("no doctor found in below given fees and shift");
				else {
					for(doctor doc1:dr) {
						System.out.println(doc1.toString());
					}
				}
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
			
			break;
	}
		
		
		case 7: {
			System.out.println("enter the availabe shift (mornig/evening):");
			String str=sc.next();
			int c=DAO.countDoctorsByShift(str);
			try {
				if(c<0) throw new expHandling("no doctor found in given shift");
				else {
					System.out.println(c+" Doctors Available in "+str+" shift" );
				}
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
	
			
		}
		break;

		case 8: {
			System.out.println("Thank you for using this program");
			f=false;
			break;
		}
		}
	}while(f==true);
	}
}
				
		


