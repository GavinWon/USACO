package DFS;

public class PathSum {
	
	public static class TreeNode {
		int val;
		TreeNode left, right;
		
		public TreeNode(int x) { val = x;}
	}
	
	public static boolean pathSum (TreeNode root, int sum)
	{
		if (root == null)
			return false;
		else if (root.left == null & root.right == null)
			return root.val == sum;
		else
			sum -= root.val;
			return (pathSum(root.left, sum) || pathSum(root.right, sum));
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left  = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);
		
		System.out.println(pathSum(root, 22));
		System.out.println(null == null);

	}

}
