import java.util.ArrayList;

/**
 * @author ckim137
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	
	private TreeNode<String> root;
	
	/**
	 * default constructor
	 */
	public MorseCodeTree() {
		buildTree();
	}
	
	@Override
	/**
	 * gets root
	 * @return root
	 */
	public TreeNode<String> getRoot() {
		return root;
	}
	
	@Override
	/**
	 * sets root 
	 * @param newNode
	 */
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}
	
	@Override
	/**
	 * adds result to correct position in tree using addNode
	 * @param code
	 * @param result
	 */
	public void insert(String code, String result) {
		addNode(root, code, result);
	}
	
	@Override
	/**
	 * adds result to correct position in tree
	 * @param root 
	 * @param code 
	 * @param letter
	 */
	public void addNode(TreeNode<String> root, String code, String letter) {
		
		//base case
		if(code.isEmpty()) {
			root.setData(letter);
		}
		
		//otherwise 
		else if(code.charAt(0) == '.') { //if first char is '.', check left child
			
			if(root.getLeft() == null) //creating new node if left child is null
				root.setLeft(new TreeNode<String>(""));
			
			addNode(root.getLeft(), code.substring(1), letter); //calling addNode again to left child
		}
		
		else if(code.charAt(0) == '-') { //if first char is '-', check right child
			
			if(root.getRight() == null) //creating new node if right child is null
				root.setRight(new TreeNode<String>("")); 
			
			addNode(root.getRight(), code.substring(1), letter); //calling addNode again to right child
		}
	}
	
	@Override
	/**
	 * fetches data using fetchNode method
	 * @param code
	 */
	public String fetch(String code) {
		return fetchNode(root, code);
	}
	
	@Override
	/**
	 * fetches data of treenode that corresponds with code
	 * @param root
	 * @param code
	 * @return string data in node
	 */
	public String fetchNode(TreeNode<String> root, String code) {
		
		//base case - if string is empty, just return root data
		if(code.isEmpty())
			return root.getData();
		
		//otherwise
			
		if(code.charAt(0) == '.') { //if first char is '.', check left child
			
			if(root.getLeft() == null) //depending on whether left child is empty or not, return data accordingly
				return null;
			
			return fetchNode(root.getLeft(), code.substring(1));
		}
		
		else if(code.charAt(0) == '-') { //if first char is '-', check right child
			
			if(root.getRight() == null) //depending on whether right child is empty or not, return data accordingly
				return null;
			
			return fetchNode(root.getRight(), code.substring(1)); 
		}
		
		//failsafe
		return null;
	}
	
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	/**
	 * builds tree by inserting tree nodes into their proper locations
	 */
	public void buildTree() {
		setRoot(new TreeNode<String>(""));

		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}
	
	@Override
	/**
	 * returns an arraylist of the items in the tree using LNRoutputTraversal method
	 * @return ArrayList<String> - representation of items in tree
	 */
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<>();
		LNRoutputTraversal(root, list);
		return list;
	}
	
	@Override
	/**
	 * recursive method to put contents of tree in arraylist in LNR order
	 * @param root 
	 * @param list 
	 */
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		
		//base case
		if(root == null)
			return;
		
		//otherwise, traverse tree until root is null
		LNRoutputTraversal(root.getLeft(), list);
		list.add(root.getData());
		LNRoutputTraversal(root.getRight(), list);
	}
	
	
	
	
	
}
