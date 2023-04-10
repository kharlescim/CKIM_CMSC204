
/**
 * @author ckim137
 */
public class TreeNode<T> {
	
	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;
	
	/**
	 * constructors
	 * @param dataNode to set root
	 * default constructor - set left and right child to null
	 */
	public TreeNode(T dataNode) {
		data = dataNode;
		left = null;
		right = null;
	}
	
	/**
	 * deep copy constructor
	 * @param node
	 */
	public TreeNode(TreeNode<T> node) {
		data = node.data;
		left = node.left;
		right = node.right;
	}
	
	/**
	 * getters
	 * gets data
	 * @return data
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * gets left child
	 * @return left
	 */
	public TreeNode<T> getLeft() {
		return left;
	}
	
	/**
	 * gets right child
	 * @return right
	 */
	public TreeNode<T> getRight() {
		return right;
	}
	
	/**
	 * setters
	 * sets data
	 * @param data
	 */
	public void setData(T dataNode) {
		data = dataNode;
	}
	
	/**
	 * set left child
	 * @param left
	 */
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}
	
	/**
	 * set right child
	 * @param right
	 */
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}
	
	
	
}
