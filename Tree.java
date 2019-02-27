import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.* ;

//The tree is defined which contains information of the heirarchy as well the Binary Search Tree
public class Tree
{
	//Main function
	public static void main(String args[])
	{
	try
	{
		FileInputStream fstream = new FileInputStream(args[0]);
		Scanner s = new Scanner(fstream);
		int n = s.nextInt();
		String employee1 = s.next();
		String ceo = s.next();
		Tree company1 = new Tree(ceo);
		company1.addEmployee(employee1,ceo);
		for (int i = 0 ; i < n-2 ; i++)
		{
			company1.addEmployee(s.next(),s.next());
		}
		n = s.nextInt();
		for (int i = 0 ; i < n ; i++)
		{
			int command = s.nextInt();
			if (command == 0)
			{
				company1.addEmployee(s.next(),s.next());
			}
			
			if (command == 1) 
			{
				company1.deleteEmployee(s.next(),s.next());
			}
			
			if (command == 2) 
			{
				company1.lowestCommonBoss(s.next(),s.next());
			}
			if (command == 3) 
			{
				company1.printEmployees();
			}
		}
	}
	catch(FileNotFoundException e)
	{
		System.out.println("Error: File Not Found");
	}
	catch(MyException e)
	{
		System.out.println(e);
	}
	}
		
	// Program functions
	public void addEmployee(String S,String S_) throws MyException
	{
		TreeNode boss = Find(S_,Root);
		TreeNode n = new TreeNode(S,boss);
		boss.getChildren().add(n);
		Insert(n);
	}
	
	public void deleteEmployee(String S, String S_) throws MyException
	{
		TreeNode n = Find(S,Root);
		TreeNode boss = n.getBoss();
		TreeNode q = Find(S_,Root);
		if (n.getLevel() != q.getLevel()) throw new MyException("Error: S and S' are at different levels");
		for (TreeNode i : n.getChildren())
		{
			q.getChildren().add(i);
			i.setBoss(q);
		}
		boss.removeChild(S);
		Delete(n);
		//n = null;
	}
	
	public void printEmployees()
	{
		Queue<TreeNode> q = new LinkedList<TreeNode>(); //Using an inbuilt queue to implement printEmployees in O(n)
		q.add(Root);
		while (q.peek()!=null)
		{
			TreeNode n = q.remove();
			System.out.println(n.getName());
			for(TreeNode i : n.getChildren())
			{
				q.add(i);
			}
		}
	}
	
	public void lowestCommonBoss(String S, String S_) throws MyException
	{
		TreeNode a = Find(S,Root);
		TreeNode b = Find(S_,Root);
		if (a.getLevel() < b.getLevel())
		{
			while (a.getLevel() != b.getLevel())
			{
				b = b.getBoss();
			}
		}
		else if (a.getLevel() > b.getLevel())
		{
			while (a.getLevel() != b.getLevel())
			{
				a = a.getBoss();
			}
		}
		while (!(a.getBoss().getName().equals(b.getBoss().getName())))
		{
			a = a.getBoss();
			b = b.getBoss();
		}
		System.out.println(a.getBoss().getName());
	}
	
	//Instance variables
	private TreeNode Root;
	
	//Constructors
	public Tree()
	{
		Root = new TreeNode("CEO");
	}
	
	public Tree(String string)
	{
		Root = new TreeNode(string);
	}
	
	public Tree(TreeNode n)
	{
		Root = n;
	}
	
	//Access Methods
	public TreeNode Find(String string, TreeNode root) throws MyException //Find method uses recursion and properties of BST
	{
		if (root.getName().compareTo(string)==0)
		{
			return root;
		}
		else if (root.getName().compareTo(string) < 0)
		{
			if (root.getRightChild() == null) throw new MyException("Error: Worker is not present in the company");
			return Find(string, root.getRightChild());
		}
		else 
		{
			if (root.getLeftChild() == null) throw new MyException("Error: Worker is not present in the company");
			return Find(string, root.getLeftChild());
		}
	}
	
	public TreeNode getRoot() { return Root;}
	
	//Update Methods
	public void Insert(TreeNode q) throws MyException //Same algorithms as taught in class
	{
		String string = q.getName();
		TreeNode n = Root;
		while (true)
		{
			if (n.getName().equals(string))
			{
				// throw exception there is already such a node
				throw new MyException("Error: A worker with the given name already exists");
			}
			else if (n.getName().compareTo(string) < 0)
			{
				if (!n.hasRightChild())
				{
					n.setRightChild(q);
					q.setSearchParent(n);
					return;
				}
				else 
				{
					n = n.getRightChild();
					continue;
				}
			}
			else if (n.getName().compareTo(string) > 0)
			{
				if (!n.hasLeftChild())
				{
					n.setLeftChild(q);
					q.setSearchParent(n);
					return;
				}
				else 
				{
					n = n.getLeftChild();
					continue;
				}
			}
		}	
	}
	
	public void Delete(TreeNode n) //Same algorithms as taught in class
	{
		if (!n.hasRightChild())
		{
			if (!n.hasLeftChild())
			{
				if ((n.getSearchParent().hasRightChild()) && (n.getSearchParent().getRightChild().getName().equals(n.getName())))
				{
					n.getSearchParent().removeRightChild();
					return;
				}
				else
				{
					n.getSearchParent().removeLeftChild();
					return;
				}
			}
			else
			{
				if ((n.getSearchParent().hasRightChild()) && (n.getSearchParent().getRightChild().getName().equals(n.getName())))
				{
					n.getSearchParent().setRightChild(n.getLeftChild());
					return;
				}
				else
				{
					n.getSearchParent().setLeftChild(n.getLeftChild());
					return;
				}
				
			}
		}
		else if (!n.hasLeftChild())
		{
			if (n.getSearchParent().hasRightChild() &&  n.getSearchParent().getRightChild().getName().equals(n.getName()))
			{
				n.getSearchParent().setRightChild(n.getRightChild());
				return;
			}
			else
			{
				n.getSearchParent().setLeftChild(n.getRightChild());
				return;
			}
		}
		else
		{
			TreeNode q = n.getRightChild();
			while (q.getLeftChild() != null)
			{
				q = q.getLeftChild();
			}
			n.setChildren(q.getChildren());
			String name = n.getName();
			n.setName(q.getName());
			q.setName(name);
			n.setBoss(q.getBoss());
			n.getBoss().getChildren().remove(q);
			n.getBoss().getChildren().add(n);
			Delete(q);
		}
	}
}
		
			
		
					
			
			
			
			
			
	
