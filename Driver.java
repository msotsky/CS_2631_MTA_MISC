import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Liam Keliher
 * @version 2021
 * Maxime Sotsky  0270251
 */
public class Driver {
	//-----------------------------------------------------------------------
	public static void main(String[] args) throws FileNotFoundException {
		Database db = new Database();

		// - prompt the user for the full path name of the disk file containing
		//   the resident database information
		System.out.println("Enter full file path: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		File file = new File(input);
		db.buildDatabase(file);
		boolean executing = true;
		while(executing){
			System.out.println("Enter email address or enter 'end' to exit: ");
			input = sc.nextLine();
			System.out.println("INPUT: " + input);
			
			if(input.equals("end")){
				executing = false;
			}
			else{
				Resident res = db.lookUpEmail(input);
				if(res == null){
					System.out.println("No Canadian with email: " + input);
				}
				else{
					System.out.println("CANADIAN FOUND!");
					System.out.println("Fist name: " + res.getFirstName());
					System.out.println("Last name: " + res.getLastName());
					System.out.println("Street: " + res.getStreet());
					System.out.println("City: " + res.getCity());
					System.out.println("Province: " + res.getProvince());
					System.out.println("Postal code: " + res.getPostalCode());
					System.out.println("Phone number: " + res.getPhone());
					System.out.println("Email adress: " + res.getEmail());
					
					//Resident(String fName, String lName, String street, String city, String prov, String postal, String phone, String email)
					
				}
			}
		}
		sc.close();
		// - construct the corresponding File object, and pass this to db.buildDatabase()
		//
		// - then repeatedly prompt the user for an email address to look up
		// - pass each email address entered to db.lookUpEmail(); if found, print out
		//   the resident's information in a tidy fashion; if not found, print out
		//   an appropriate message
		//
		// - allow the user to exit somehow

	} // main(String[])
	//-----------------------------------------------------------------------
} // class Driver
