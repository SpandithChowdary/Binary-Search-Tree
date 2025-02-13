package Binary_Tree;
import java.util.*;

class BSTNode{
	int key;
	BSTNode left, right;
	public BSTNode(int item) {
		key = item;
		left = right = null;
	}
}

public class BST {
	static BSTNode root;
	
	public BST(){
		root = null;
	}
	
	public void insert(int key){
		root = insertIn(root,key);
	}
	
	//Inserting values in each node of Tree
	private BSTNode insertIn(BSTNode root, int key) {
		if(root==null) {
			root = new BSTNode(key);
			return root;
		}
		if(key > root.key) {
			root.right = insertIn(root.right , key);
		}
		else if(key < root.key) {
			root.left = insertIn(root.left , key);
		}
		return root;
	}
	
	public void print1() {
		preorder(root);
	}
	
	public void print2() {
		postorder(root);
	}
	
	public void print3() {
		inorder(root);
	}
	
	// To print PreOrder
	public void preorder(BSTNode root) {
		if(root!=null) {
			System.out.print(root.key+" ");
			preorder(root.left);
			preorder(root.right);
		}
	}
	
	// To print PostOrder
	public void postorder(BSTNode root) {
		if(root!=null) {
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.key+" ");
		}
	}
	
	// To print inOrder
	public void inorder(BSTNode root) {
		if(root!=null) {
			inorder(root.left);
			System.out.print(root.key+" ");
			inorder(root.right);
		}
	}
	
	// To print Maximum in tree
	private int Max(BSTNode root) {
		while(root.right!=null) root = root.right;
		return root.key;
	}
	
	// To print Minimum in tree
	private int Min(BSTNode root) {
		while(root.left!=null) root = root.left;
		return root.key;
	}
	
	// To Delete a node from tree
	public BSTNode delete(BSTNode root , int key) {
		if(root == null) return null;
		if(key<root.key)  root.left = delete(root.left,key);
		else if(key>root.key) root.right = delete(root.right,key);
		else {
			// No Children
			if(root.left == null && root.right == null) return null;
			
			//One Child
			else if(root.left == null) return root.right;
			else if(root.right == null) return root.left;
			
			//Two Children
			else {
			BSTNode minNode = minNode(root.right);
			root.key = minNode.key;
			root.right = delete(root.right,minNode.key);
			}
		}
		return root;
	}
	
	private BSTNode minNode(BSTNode root) {
		while(root.left!=null) root = root.left;
		return root;
	}

	//Searching node
	public boolean search(BSTNode root,int key) {
		if(root == null) return false;
		if(key==root.key)return true;
		return key<root.key ? search(root.left,key): search(root.right,key);
	}
	
	//Main Code
	public static void main(String[] args) {
	  Scanner sc = new Scanner(System.in);
	  BST tree = new BST();
	  
	  System.out.println("Enter no.of node you want to insert :");
	  int size = sc.nextInt();
	  
	  for(int i=0;i<size ; i++) {
		  int a = sc.nextInt();
		  tree.insert(a);
	  }
	  
	  System.out.println("Pre-Order");
	  tree.print1();
	  
	  System.out.println("\nPost-Order");
	  tree.print2();
	  
	  System.out.println("\nIn-Order");
	  tree.print3();
	  
	  System.out.print("\nMinimun value in BT = "+ tree.Min(BST.root));
	  System.out.print("\nMaximun value in BT = "+ tree.Max(BST.root));
	  
	  System.out.println("\nEnter a value to Search in tree");
	  int key = sc.nextInt();
	  if(tree.search(BST.root, key)) {
		  System.out.println("The value = "+key+" exists in tree");
	  }else {
		  System.out.println("The value = "+key+" not exists in tree");
	  }
  
	  System.out.println("\nEnter a value to delete");
	  key = sc.nextInt();
	  BST.root = tree.delete(BST.root, key);
	  tree.print3();
	  sc.close();
	}
}

