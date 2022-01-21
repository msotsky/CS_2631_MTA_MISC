import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Liam Keliher
 * @version 2021
 * Maxime Sotsky  0270251
 * Implements a database of residents using an AVL tree.
 */
public class Database {
	private AVL_Tree avl;
	//-----------------------------------------------------------------------
	public Database() {
		avl = new AVL_Tree();
	} // constructor Database()
	//-----------------------------------------------------------------------
	public void buildDatabase(File inputFile) throws FileNotFoundException{
		// - this method should read in the entire database from the CSV file
        // - for each entry in the file, construct a Resident object, construct
		//   an AVL node, and then insert the AVL node into the AVL tree
        Scanner sc = new Scanner(inputFile);
		boolean verify = true;
        while(sc.hasNextLine() && verify == true){
            String line = sc.nextLine();
            String[] arrLine = line.split(",");
			AVL_Node nNode = new AVL_Node(new Resident(arrLine[0], arrLine[1], arrLine[2], arrLine[3], arrLine[4], arrLine[5], arrLine[6], arrLine[7]));
			boolean duplicate = avl.insert(nNode);// check var later
			verify = avl.verifyAVL();							
        }
		sc.close();
    }
		// - *** IMPORTANT ***:  after *each* insertion, call the verifyAVL()
        //   method in the AVL_Tree class to check the structure of your tree;
        //   the boolean value returned by verifyAVL() should always be true
        //
		// - also note that each call to the AVL_Tree insert() method should
        //   return true, unless the resident already exists in the database,
        //   in which case it should return false;  during your debugging process,
        //   it might be a good idea to print out each file entry that causes insert
		//   to return false, and then manually check that these are actually
		//   duplicate entries (i.e., have identical email addresses)

	 // buildDatabase(File)
	//-----------------------------------------------------------------------
	/**
	 * Searches the database for an entry associated with the email address
	 * passed in as an argument.  If found, returns a reference to the Resident
	 * object in the AVL node.  If not found, returns null.
	 */
	public Resident lookUpEmail(String targetEmail) {
		Resident targetRes = new Resident(null, null, null, null, null, null, null, targetEmail);
		AVL_Node node = new AVL_Node(targetRes);
		AVL_Node result = avl.search(node);
		if (result == null) {
			return null;
		} // if
		else {
			return result.getResident();
		} // else
	} // lookUpEmail(String)
	//-----------------------------------------------------------------------
} // class Database
