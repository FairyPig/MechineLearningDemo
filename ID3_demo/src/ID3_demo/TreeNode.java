package ID3_demo;

public class TreeNode {
	boolean isLeaft;
	TreeNode parent;
	TreeNode[] childNodes;
	String[] attributes;
	int attribute_id;
	
	String parentAttribute;
	String type = null;
	
	public TreeNode(){
		isLeaft = false;
		parent = null;
		childNodes = null;
	}
}
