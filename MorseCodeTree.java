import java.util.*;

/**
 * This is a MorseCodeTree which is specifically used for the conversion of morse code to english
 * It relies on a root (reference to root of the tree)
 * The root is set to null when the tree is empty.
 * The class uses an external generic TreeNode class which consists of a reference to the data and a reference to the left and right child. The TreeNode is parameterized as a String, TreeNode This class uses a private member root (reference to a TreeNode)
 * @author abiol
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	protected TreeNode<String> root;
	
	public MorseCodeTree() {
		buildTree();
	}
	
	
	@Override
	public TreeNode<String> getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		// TODO Auto-generated method stub
		this.root = newNode;
	}

	@Override
	public void insert(String code, String result) {
		// TODO Auto-generated method stub
		addNode(root, code, result);
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		// TODO Auto-generated method stub
		if(code.length() == 1) {
			if(code.charAt(0) == '.') {
				root.lNode = new TreeNode<String>(letter);
			}else {
				root.rNode = new TreeNode<String>(letter);
			}
			
		}else {
			if(code.charAt(0) == '.') {
				addNode(root.lNode, code.substring(1), letter);
			}else {
				addNode(root.rNode, code.substring(1), letter);
			}
		}
	}

	@Override
	public String fetch(String code) {
		// TODO Auto-generated method stub
		return fetchNode(root, code);
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		// TODO Auto-generated method stub
		String val = "";
		
		if(code.length() <= 1) {
			if(code.equals(".")) {
				val = root.lNode.data;
			}else if (code.equals("-")){
				val = root.rNode.data;
			}
		}else {
			if(code.charAt(0) == '.') {
				val = fetchNode(root.lNode, code.substring(1));
			}else {
				val = fetchNode(root.rNode, code.substring(1));
			}
		}
		
		return val;
	}

	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported");
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported");
	}

	@Override
	public void buildTree() {
		// TODO Auto-generated method stub
		root = new TreeNode<String>("");
		
		//Level 1
		insert(".", "e");
		insert("-", "t");
		
		//Level 2
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		//Level 3
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		//Level 4
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
	public ArrayList<String> toArrayList() {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<>();
		LNRoutputTraversal(root, list);
		return list;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		// TODO Auto-generated method stub
		if(root != null) {
			LNRoutputTraversal(root.lNode, list);
			list.add(root.data);
			LNRoutputTraversal(root.rNode, list);
		}
	}
	
	
}
