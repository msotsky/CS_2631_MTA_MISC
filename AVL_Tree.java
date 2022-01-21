import javax.lang.model.util.ElementScanner6;

import jdk.internal.jimage.ImageReader.Node;

/** 
 * @author Liam Keliher
 * @version 2021
 *Maxime Sotsky  0270251
 * Implements an AVL tree.
 */
public class AVL_Tree {
	private AVL_Node root;
	private int numNodes;
	//--------------------------------------------------------------------
	public AVL_Tree() {
		root = null;
		numNodes = 0;
	} // constructor AVL_Tree()
	//--------------------------------------------------------------------
	public int getNumNodes() {
		return numNodes;
	} // getNumNodes()

	//--------------------------------------------------------------------
	/**
	 * Searches the AVL tree for a node which is equal to the node passed in
	 * as an argument (in the sense that the compareTo() method in the AVL_Node
	 * class indicates that they are the same).  Returns a reference to the
	 * matching node in the tree if successful, or null otherwise.
	 */

	public AVL_Node search(AVL_Node target) {
		
		AVL_Node r = this.root;
		while(r != null){
			if ((target.compareTo(r) < 0))
				r = r.getLeftChild();
			
			else if ((target.compareTo(r) > 0))
				r = r.getRightChild();
			
			else
				return r; // found
		}
		return null; // !found
		
	} // search(AVL_Node)

	//--------------------------------------------------------------------
	/**
	 * Tries to insert (a defensive copy of) the node passed as an argument
	 * into the AVL tree.  If a node with the same key already exists, the
	 * insertion is not performed and false is returned.  Otherwise, the
	 * insertion *is* performed and true is returned.
	 */
	public boolean insert(AVL_Node newNode) {

		AVL_Node copy = newNode.clone();
		copy.setBalanceFactor(0);
		// - insert copy into the tree
		// - update numNodes as appropriate
		if(this.root == null){
			this.root = copy;
			numNodes++;
			return true;
		}
		else{
			AVL_Node r = this.root;
			while(true){
				if(copy.compareTo(r) < 0){
					if(r.getLeftChild() == null){
						r.setLeftChild(copy);
						copy.setParent(r);
						numNodes++;
						changeBF(copy);
						return true;
					}
					else
						r = r.getLeftChild();
				}
				else if (copy.compareTo(r) > 0){
					if(r.getRightChild() == null){
						r.setRightChild(copy);
						copy.setParent(r);
						numNodes++;
                        changeBF(copy);
						//Boolean n2 = changeBF(copy);
						return true;
					}
					else
						r = r.getRightChild();
				}
				else //else if (nNode == r) -value already contained
					return false; 
	
			}
		}
	} // insert(AVL_Node)
	//^
	//|
	private boolean changeBF(AVL_Node nNode){

        if(nNode.getBalanceFactor() != 1 || nNode.getBalanceFactor() != 0 || nNode.getBalanceFact() != -1){
            if(nNode.getBalanceFactor() > 0){//RL
                if(nNode.getRightChild().getBalanceFactor() < 0){
                    rotateRight(nNode.getRightChild());
                    rotateLeft(nNode);
                }
                else//L
                    rotateLeft(nNode);
            }
            else if (nNode.getBalanceFactor() < 0){//LR
                if(nNode.getLeftChild().getBalanceFactor() > 0){
                    rotateLeft(nNode.getLeftChild());
                    rotateRight(nNode);
                }
                else//R
                    rotateRight(nNode);
            }
			return true;
		}
		if(nNode.getParent() != null){//incP
			if(nNode.equals(nNode.getParent().getLeftChild()))
				nNode.getParent().setBalanceFactor(nNode.getParent().getBalanceFactor()-1);
		
			if(nNode.equals(nNode.getParent().getRightChild()))
				nNode.getParent().setBalanceFactor(nNode.getParent().getBalanceFactor()+1);
	
			if (nNode.getParent().getBalanceFactor() != 0)
				changeBF(nNode.getParent());
		}
        else{
            return false;
        }

	}
	//===============================

	private void rotateLeft(AVL_Node nNode){

		AVL_Node nNodeR = nNode.getRightChild();
		nNode.setRightChild(nNodeR.getLeftChild());

		if(nNodeR.getLeftChild() != null)
			nNodeR.getLeftChild().setParent(nNode);
			
		nNodeR.setParent(nNode.getParent());

		if(nNode.getParent() == null)
			this.root = nNodeR;
			
		else if(nNode == nNode.getParent().getLeftChild())
			nNode.getParent().setLeftChild(nNodeR);
			
		else
			nNode.getParent().setRightChild(nNodeR);
			
		nNodeR.setLeftChild(nNode);
		nNode.setParent(nNodeR);
		//-------------------------------
		if(nNodeR.getBalanceFactor() < 0)
			nNode.setBalanceFactor(nNode.getBalanceFactor() -1);
		else
			nNode.setBalanceFactor(nNode.getBalanceFactor() -1 - nNodeR.getBalanceFactor());

		if(nNode.getBalanceFactor() > 0)
			nNodeR.setBalanceFactor(nNodeR.getBalanceFactor() - 1);
		else
			nNodeR.setBalanceFactor(nNodeR.getBalanceFactor() - 1 + nNode.getBalanceFactor());
			
	}
	private void rotateRight(AVL_Node nNode){

		AVL_Node nNodeL = nNode.getLeftChild();
		nNode.setLeftChild(nNodeL.getRightChild());

		if(nNodeL.getRightChild() != null)
			nNodeL.getRightChild().setParent(nNode);
			
		nNodeL.setParent(nNode.getParent());

		if(nNode.getParent() == null)
			this.root = nNodeL;
			
		else if(nNode == nNode.getParent().getRightChild())
			nNode.getParent().setRightChild(nNodeL);
			
		else
			nNode.getParent().setLeftChild(nNodeL);
			
		nNodeL.setRightChild(nNode);
		nNode.setParent(nNodeL);
		//-------------------------------
		if(nNodeL.getBalanceFactor() > 0)
			nNode.setBalanceFactor(nNode.getBalanceFactor() + 1);
		else
			nNode.setBalanceFactor(nNode.getBalanceFactor() + 1 - nNodeL.getBalanceFactor());

		if(nNode.getBalanceFactor() < 0)
			nNodeL.setBalanceFactor(nNodeL.getBalanceFactor() + 1);
		else
			nNodeL.setBalanceFactor(nNodeL.getBalanceFactor() + 1 + nNode.getBalanceFactor());
	
	}
	//--------------------------------------------------------------------
	/** 
	 * This method does two things.  (1) If the subtree rooted at currRoot
	 * is an AVL tree, the method returns the height of the subtree.
	 * (2) If it is not an AVL tree, it returns -1.  A tree could fail to
	 * be an AVL tree for any of four reasons:  (a) One or more nodes
	 * has a balance factor other than {-1,0,1}; (b) The balance factor
	 * stored in a node does not equal the *actual* balance factor of
	 * the node (independent of whether or not these are "good" values,
	 * i.e., values in {-1,0,1}); (c) the binary search tree property is
	 * violated somewhere; (d) some references are wrong -- I believe that
	 * the only way this can occur when everything else is OK is if a node
	 * does not correctly reference its parent.
	 */
	private int verifyNodeAndGetHeight(AVL_Node curr, AVL_Node parent) {
		int leftHeight, rightHeight, actualBF;
		AVL_Node left, right;

		if (curr == null) {
			return 0;
		} // if
		else{
			//-- Check that the node references its parent --
			if (curr.getParent() != parent) {
				System.out.println("PROBLEM -- Node does not reference its parent");
				System.out.println("  Email in node = " + curr.getResident().getEmail());
				return -1;
			} // if

			//-- Get the heights of the subtrees rooted at curr --
			left = curr.getLeftChild();
			right = curr.getRightChild();
			leftHeight = verifyNodeAndGetHeight(left, curr);
			rightHeight = verifyNodeAndGetHeight(right, curr);

			//-- If problem with a subtree, just return -1 --
			if ((leftHeight == -1) || (rightHeight == -1)) {
				return -1;
			} // if


			actualBF = rightHeight - leftHeight;

			//-- Check that actualBF is in {-1,0,1} --
			if ((actualBF < -1) || (actualBF > 1)) {
				System.out.println("PROBLEM -- Actual bf is NOT in {-1,0,1}");
				System.out.println("   Email in node = " + curr.getResident().getEmail());
				System.out.println("   Stated bf = " + curr.getBalanceFactor());
				System.out.println("   Actual bf = " + actualBF);
				return -1;
			} // if

			//-- Check that bf stored in node equals actualBF
			if (curr.getBalanceFactor() != actualBF) {
				System.out.println("PROBLEM -- Found node whose stated bf != actual bf");
				System.out.println("   Email in node = " + curr.getResident().getEmail());
				System.out.println("   Stated bf = " + curr.getBalanceFactor());
				System.out.println("   Actual bf = " + actualBF);
				return -1;
			} // if


			//-- Check that the search tree property holds --
			if ((left != null) && (left.compareTo(curr) >= 0)) {
				System.out.println("PROBLEM -- BST property violated");
				System.out.println("   Email in node = " + curr.getResident().getEmail());
				System.out.println("   Email in LEFT child = " + left.getResident().getEmail());
				return -1;
			} // if

			if ((right != null) && (right.compareTo(curr) <= 0)) {
				System.out.println("PROBLEM -- BST property violated");
				System.out.println("   Email in node = " + curr.getResident().getEmail());
				System.out.println("   Email in RIGHT child = " + right.getResident().getEmail());
				return -1;
			} // if


			if (rightHeight > leftHeight) {
				return rightHeight + 1;
			} // if
			else{
				return leftHeight + 1;
			} // else
		} // else
	} // getHeight(AVL_Node,AVL_Node)
	//--------------------------------------------------------------------
	/**
	 * Traverses the tree to count the number of nodes present.  If all operations
	 * have been performed correctly, the value returned by this method should match
	 * the value stored in the instance variable numNodes (which can be accessed
	 * outside this class by calling getNumNodes()).
	 */
	private int countNodes(AVL_Node curr) {
		if (curr == null){
			return 0;
		} // if

		int sum = 1;
		sum += countNodes(curr.getLeftChild());
		sum += countNodes(curr.getRightChild());

		return sum;
	} // countNodes(AVL_Node)
	//--------------------------------------------------------------------
	/**
	 * Checks that the balance factors of all the nodes are correct, and
	 * that they all belong to {-1,0,1}.  Also checks if the actual number
	 * of nodes in the tree is equal to numNodes.
	 * 
	 * - Return true if everything is OK, else return false.
	 */
	public boolean verifyAVL() {
		int result = verifyNodeAndGetHeight(root, null);
		boolean check1 = (result != -1);

		int nodeCount = countNodes(root);
		System.out.println("nodeCount = " + nodeCount);

		boolean check2 = (nodeCount == numNodes);

		if (!check2) {
			System.out.println("PROBLEM -- Actual number of nodes in tree does not equal instance variable numNodes");
			System.out.println("   Actual number of nodes in tree = " + nodeCount);
			System.out.println("   numNodes = " + numNodes);			
		} // if

		if (check1 && check2) {
			return true;
		} // if
		else {
			return false;
		} // else
	} // verifyAVL()
	//--------------------------------------------------------------------
} // class AVL_Tree

