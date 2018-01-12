package lixin.algorithms.tree;

import javax.swing.tree.TreeNode;

/**
 * @Author lixin@wecash.net
 * @Date 2018/1/12 11:07
 * @Description 平衡二叉树
 */
public class AVLtree {

    //内部节点类
    private class TreeNode{
        private TreeNode leftChild;
        private TreeNode rightChild;
        private int height;


    }

    public int height(TreeNode node) {
        return node == null ? -1 : node.height;
    }

    //右旋
    public TreeNode rightRotate(TreeNode root) {
        //将左节点暂存
        TreeNode newRoot = root.leftChild;
        //将子树根节点的左节点的右孩子赋给根节点
        root.leftChild = newRoot.rightChild;
        //将根节点赋给根节点的左孩子
        newRoot.rightChild = root;
        //重新计算原根节点的高度
        root.height = Math.max(height(root.leftChild), height(root.rightChild)) + 1;
        //重新计算新的新的根节点的高度
        newRoot.height = Math.max(height(newRoot.leftChild), height(newRoot.rightChild)) + 1;
        return newRoot;
    }

    //左旋
    public TreeNode leftRotate(TreeNode root) {
        //将右节点暂存
        TreeNode newRoot = root.rightChild;
        //将新的根节点的左孩子赋给原根节点
        root.rightChild = newRoot.leftChild;
        //将原根节点赋给新根节点的左孩子
        newRoot.leftChild = root;
        //重新计算原根节点的高度
        root.height = Math.max(height(root.leftChild), height(root.rightChild)) + 1;
        //重新计算新根节点的高度
        newRoot.height = Math.max(height(newRoot.leftChild), height(newRoot.rightChild)) + 1;
        return newRoot;
    }
    //左右旋
    //右左旋

}
