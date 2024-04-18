

/**
 * The external Tree Node for Linked Trees
 * @author abiol
 *
 */
public class TreeNode<T> {
	protected T data;
	protected TreeNode<T> lNode, rNode;
	
	/**
	 * Create a new TreeNode with left and right child set to null and data set to the dataNode
	 * @param dataNode - the data to be stored in the TreeNode
	 */
	public TreeNode(T dataNode) {
		this.lNode = this.rNode = null;
		this.data = dataNode;
	}
	
	/**
	 * Used for making deep copies
	 * @param node - node to make copy of
	 */
	public TreeNode(TreeNode<T> node) {
		this.lNode = new TreeNode<>(node.lNode);
		this.rNode = new TreeNode<>(node.rNode);
		this.data = node.data;
	}
	
	
	/**
	 * Return the data within this TreeNode
	 * @return the data within the TreeNode
	 */
	public T getData() {
		return this.data;
	}
}
