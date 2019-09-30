package DFS;

public class Max_Depth {
	
	static class TreeNode {
		int val; 
		TreeNode left, right;
		TreeNode(int x) { val = x;}
	}
		
	public static int maxD (TreeNode t)
	{
		if (t == null)
			return 0;
		else if (t.right == null && t.left == null)
			return 1;
		else
		{
			int leftD = maxD(t.left);
			int rightD = maxD(t.right);
			int depth = Integer.max(leftD, rightD);
			return depth + 1;
		}
	}
	
	public static void main (String args[])
		{
			TreeNode tree = new TreeNode(3);
			tree.left = new TreeNode(9);
			tree.right = new TreeNode(10);
			tree.right.left = new TreeNode(15);
			tree.left.right = new TreeNode(7);
			System.out.println(maxD(tree));
		}

}
