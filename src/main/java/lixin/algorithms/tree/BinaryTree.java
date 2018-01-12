package lixin.algorithms.tree;

public class BinaryTree {
    //根节点
    Node root;

    //内部节点类
    private class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;
        private Node parent;

        public Node(int value) {
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
            current.rightChild.parent = current;
        }
        //若被比较值比当前节点小，则跟左孩子节点比较
        else if (compareValue < value) {
            current.leftChild = put(current.leftChild, compareValue);
            current.leftChild.parent = current;
        } else {
            current.value = compareValue;
        }
        return current;
    }

    //遍历二叉树
    //中序遍历

    public void inOrder() {
        print(root);
    }

    public void print(Node current) {
        //终止条件
        if (null == current) {
            return;
        }
        //先遍历左孩子节点
        print(current.leftChild);
        //再遍历跟节点
        System.out.print(current.value+" ");
        //最后遍历右孩子节点
        print(current.rightChild);
    }

    public boolean search(int value) {
        return find(root, value);
    }

    public boolean find(Node current, int value) {
        if (null == current) {
            return false;
        }
        if (value > current.value) {
            return find(current.rightChild, value);
        } else if (value < current.value) {
            return find(current.leftChild, value);
        } else {
            return true;
        }
    }

    public Node findNode(Node current, int value) {
        if (null == current) {
            return null;
        }
        if (value < current.value) {
            return findNode(current.leftChild, value);
        } else if (value > current.value) {
            return findNode(current.rightChild, value);
        }
        else {
            return current;
        }
    }

    public Node findMax(Node root) {
        if (null == root.rightChild) {
            return root;
        }
        return findMax(root.rightChild);
    }
    //删除节点
    public Node delete(int value) {
        //找到即将被删除的节点
        Node deleteNode = findNode(root, value);
        if (null == deleteNode) {
            return null;
        }
        //1、若为叶子节点，则直接删除
        if (null == deleteNode.leftChild && null == deleteNode.rightChild) {
            //若是根节点
            if (deleteNode == root) {
                root = null;
                return deleteNode;
            }
            if (deleteNode.value < deleteNode.parent.value) {
                deleteNode.parent.leftChild = null;
            }else {
                deleteNode.parent.rightChild = null;
            }
            deleteNode.parent = null;
        }
        //2、若只有一侧的孩子节点，则把孩子节点赋给父节点
        //只有右节点
        else if (null == deleteNode.leftChild && null != deleteNode.rightChild) {
            //若是根节点
            if (deleteNode == root) {
                root = deleteNode.rightChild;
            }
            if (deleteNode.value < deleteNode.parent.value) {
                deleteNode.parent.leftChild = deleteNode.rightChild;
                deleteNode.rightChild.parent = deleteNode.parent;
            }else {
                deleteNode.parent.rightChild = deleteNode.rightChild;
                deleteNode.rightChild.parent = deleteNode.parent;
            }
        }
        //只有左节点
        else if (null != deleteNode.leftChild && null == deleteNode.rightChild) {
            //若是根节点
            if (deleteNode == root) {
                root = deleteNode.leftChild;
            }
            if (deleteNode.value < deleteNode.parent.value) {
                deleteNode.parent.leftChild = deleteNode.leftChild;
                deleteNode.leftChild.parent = deleteNode.parent;
            }
        }
        //3、左右节点都有
        else {
            //若是根节点
            if (deleteNode == root) {
                Node newRoot = findMax(deleteNode.leftChild);
                //将maxNode的左孩子节点赋予给maxNode的父节点
                if (newRoot != deleteNode.leftChild) {
                    newRoot.parent.rightChild = newRoot.leftChild;
                    if (newRoot.leftChild != null) {
                        newRoot.leftChild.parent = newRoot.parent;
                    }
                    root = newRoot;
                    root.leftChild = deleteNode.leftChild;
                    root.leftChild.parent = root;
                    root.rightChild = deleteNode.leftChild;
                    root.rightChild.parent = root;
                }else {
                    root = newRoot;
                    root.rightChild = deleteNode.rightChild;
                    if (root.rightChild != null) {
                        root.rightChild.parent = root;
                    }
                }
                return deleteNode;
            }
            //找左子树中的最大值
            Node maxNode = findMax(deleteNode.leftChild);
            if (maxNode != deleteNode.leftChild) {
                //将maxNode的左孩子节点赋予给maxNode的父节点
                maxNode.parent.rightChild = maxNode.leftChild;
                if (maxNode.leftChild != null) {
                    maxNode.leftChild.parent = maxNode.parent;
                }
                //将maxNode挪至被删除节点的位置
                if (deleteNode.value < deleteNode.parent.value) {
                    deleteNode.parent.leftChild = maxNode;
                    maxNode.parent = deleteNode.parent;
                }else {
                    deleteNode.parent.rightChild = maxNode;
                    maxNode.parent = deleteNode.parent;
                }
                //将deleteNode的左子树赋予给maxNode
                maxNode.leftChild = deleteNode.leftChild;
                deleteNode.leftChild.parent = maxNode;
                //将deleteNode的左子树赋予给maxNode
                maxNode.rightChild = deleteNode.rightChild;
                deleteNode.rightChild.parent = maxNode;
            }else {
                maxNode.rightChild = deleteNode.rightChild;
                maxNode.rightChild.parent = maxNode;
                if (deleteNode.value < deleteNode.parent.value) {
                    deleteNode.parent.leftChild = maxNode;
                    maxNode.parent = deleteNode.parent;
                }else {
                    deleteNode.parent.rightChild = maxNode;
                    maxNode.parent = deleteNode.parent;
                }
            }
        }
        return deleteNode;
    }
}
