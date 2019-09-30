package DFS;

public class SameTree {
	
	public static class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int x) {val = x;} 
	}
	
	public static boolean sameT (TreeNode root, TreeNode root1)
	{
		if (root == null && root1 == null)
			return true;
		else if (root == null && root1 != null || root != null && root1 == null)
			return false;
		return root.val == root1.val && sameT(root.left, root1.left) && sameT(root.right, root1.right);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		
		TreeNode root2 = new TreeNode(1);
		root1.right = new TreeNode(2);
		
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(2);
		root3.right = new TreeNode(1);
		
		TreeNode root4 = new TreeNode(1);
		root4.left = new TreeNode(1);
		root4.right = new TreeNode(2);
		
		System.out.println(sameT(root, root));
		System.out.println(sameT(root1, root2));
		System.out.println(sameT(root3, root4));
		
	}

}
