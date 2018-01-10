package lixin.algorithms.tree;

public class BinaryTree {
    //根节点
    Node root;
    //内部节点类
    private class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value){
            this.value = value;
        }
    }

    //插入数据
    public void put(int value) {
        root = put(root, value);
    }

    public Node put(Node current, int compareValue) {
        if (null == current) {
            return new Node(compareValue);
        }
        //将被比较的值与当前节点的value值进行比较
        int value = current.value;
        //若被比较值比当前节点大，则跟右孩子节点比较
        if (compareValue > value) {
            current.rightChild = put(current.rightChild, compareValue);
        }
        //若被比较值比当前节点小，则跟左孩子节点比较
        else if (compareValue < value) {
            current.leftChild = put(current.leftChild, compareValue);
        }
        else {
            current.value = compareValue;
        }
        return current;
    }

    //遍历二叉树
    //中序遍历

    public void inOrder() {
        print(root);
    }

    public void print(Node current){
        //终止条件
        if (null == current) {
            return;
        }
        //先遍历左孩子节点
        print(current.leftChild);
        //再遍历跟节点
        System.out.println(current.value);
        //最后遍历右孩子节点
        print(current.rightChild);
    }
}
