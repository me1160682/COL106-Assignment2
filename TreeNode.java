import java.util.ArrayList;
public class TreeNode
{
	//A single node is used to implement the tree and the binary search tree as well
	//Hence no need for a link pointer to connect information from both trees since they are different attributes of the same node
	//An (inbuilt) ArrayList is used to store children of a node
	//searchParent, leftchild, rightchild are attributes for the Binary Search Tree
	//Proper Constructors, Access Methods, Boolean returning methods and Update Methods are defined for manipulating Node class
	
	
	//Instance fields
	private String key;
	private ArrayList<TreeNode> list;
	private TreeNode boss;
	private int level;
	private TreeNode leftchild;
	private TreeNode rightchild;
	private TreeNode searchParent;
	
	
	//Constructors
	public TreeNode(String name, TreeNode parent)
	{
		key = name;
		boss = parent;
		level = parent.getLevel() + 1;
		list = new ArrayList<TreeNode>();
	}
	
	public TreeNode(String name)
	{
		key = name;
		boss = null;
		list = new ArrayList<TreeNode>();
		level = 1;
	}
	
	public TreeNode() { this(null) ;}
	
	//Access Methods
	public TreeNode getBoss() { return boss ; }
	public int getLevel() { return level;}
	public String getName() { return key;}
	public ArrayList<TreeNode> getChildren() { return list;}
	public TreeNode getSearchParent() { return searchParent ;}
		
	public TreeNode getRightChild()
	{
		return rightchild;
	}
	
	public TreeNode getLeftChild() 
	{
		return leftchild;
	}
	
	//Boolean Methods
	public boolean isLeafNode() { return list.isEmpty() ; }
	public boolean hasRightChild() { return rightchild != null; }
	public boolean hasLeftChild() { return leftchild != null; }
	
	//Update Methods
	public void setBoss(TreeNode parent)
	{
		boss = parent; 
		level = parent.getLevel() + 1;
	}
	
	public void setName(String string)
	{
		key = string;
	}
	
	public void addChild(String name)
	{
		TreeNode n = new TreeNode(name, this);
		list.add(n);
	}
	
	public void removeChild(String name)
	{
		for(TreeNode i : list)
		{
			if (i.getName().equals(name))
			{
				list.remove(i);
				return ;
			}
		}
		//throw new Exception;
	}
	
	public void setChildren(ArrayList<TreeNode> list1) { list = list1;}
	public void setLeftChild(TreeNode n) { leftchild = n ;}
	public void setRightChild(TreeNode n) { rightchild = n;}
	public void setSearchParent(TreeNode n) { searchParent = n;}
	public void removeRightChild() { rightchild = null;}
	public void removeLeftChild() { leftchild = null;}
}
	
	
	
	
	
	
	
	
		
