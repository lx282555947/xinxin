package lixin.algorithms.tree;

import javax.swing.tree.TreeNode;

/**
 * @Author lixin@wecash.net
 * @Date 2018/1/12 11:07
 * @Description 平衡二叉树
 */
public class AVLtree {

    private TreeNode root;
    //内部节点类
    private class TreeNode{

        private TreeNode leftChild;
        private TreeNode rightChild;
        private int height;
        private int value;

        public TreeNode(int value, int height) {
            this.value = value;
            this.height = height;
        }
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
    public TreeNode leftRightRotate(TreeNode root) {
        //根节点的左孩子先左旋
        root.leftChild = leftRotate(root.leftChild);
        //再以根节点右旋并返回
        return rightRotate(root);

    }

    //右左旋
    public TreeNode rightLeftRotate(TreeNode root) {
        //根节点的右孩子右旋
        root.rightChild = rightRotate(root.rightChild);
        //再以根节点左旋并返回
        return leftRotate(root);
    }

    //插入数据
    public void put(int value) {
        root = put(root, value);
    }
    private TreeNode put(TreeNode current,int value){
        if (null == current) {
            return new TreeNode(value, 0);
        }
        if (value < current.value) {
            current.leftChild = put(current.leftChild, value);
        } else if (value > current.value) {
            current.rightChild = put(current.rightChild, value);
        }else {
            current.value = value;
        }
        //重新计算高度
        current.height = Math.max(height(current.leftChild), height(current.rightChild)) + 1;
        //判断是否打破平衡
        //若左侧节点数量比右侧节点数量大2，则在整体上需要右旋
        if ((height(current.leftChild) - height(current.rightChild)) >= 2) {
            //若左节点的子节点与之同侧
            if (height(current.leftChild.leftChild) >= height(current.leftChild.rightChild)) {
                //直接右旋
                rightRotate(current);
            }else {
                //左右旋
                leftRightRotate(current);
            }
        } else if ((height(current.leftChild) - height(current.rightChild)) <= 2) {
            //若右节点的子节点与之同侧
            if (height(current.rightChild.rightChild) >= height(current.rightChild.leftChild)) {
                //直接左旋
                leftRightRotate(current);
            } else {
                //右左旋
                rightLeftRotate(current);
            }
        }
        return current;
    }
}
