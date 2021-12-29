public class Splay_Tree {
    public static int rotations = 0;
    public static int comparisons = 0;
    private Node root;

    public Splay_Tree() {
        root = null;
    }
    public Node searchTreeHelper(Node node, int key) {
        if (node == null || key == node.data) {
            comparisons++;
            return node;
        }
        if (key < node.data) {
            comparisons++;
            return searchTreeHelper(node.left, key);
        }
        comparisons++;
        return searchTreeHelper(node.right, key);
    }
    public void leftRotate(Node x) {
        rotations++;
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    public void rightRotate(Node x) {
        rotations++;
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }
    public void splay(Node x) {
        while (x.parent != null) {
            if (x.parent.parent == null) {
                if (x == x.parent.left) {
                    rightRotate(x.parent);
                } else {
                    leftRotate(x.parent);
                }
            } else if (x == x.parent.left && x.parent == x.parent.parent.left) {
                rightRotate(x.parent.parent);
                rightRotate(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.right) {
                leftRotate(x.parent.parent);
                leftRotate(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.left) {
                leftRotate(x.parent);
                rightRotate(x.parent);
            } else {
                rightRotate(x.parent);
                leftRotate(x.parent);
            }
        }
    }
    public Node join(Node s, Node t){
        if (s == null) {
            return t;
        }

        if (t == null) {
            return s;
        }
        Node x = maximum(s);
        splay(x);
        x.right = t;
        t.parent = x;
        return x;
    }
    // public void preOrderHelper(Node node) {
    //     if (node != null) {
    //         System.out.print(node.data + " ");
    //         preOrderHelper(node.left);
    //         preOrderHelper(node.right);
    //     }
    // }
    // public void inOrderHelper(Node node) {
    //     if (node != null) {
    //         inOrderHelper(node.left);
    //         System.out.print(node.data + " ");
    //         inOrderHelper(node.right);
    //     }
    // }
    // public void postOrderHelper(Node node) {
    //     if (node != null) {
    //         postOrderHelper(node.left);
    //         postOrderHelper(node.right);
    //         System.out.print(node.data + " ");
    //     }
    // }
    // public void preorder() {
    //     preOrderHelper(this.root);
    // }
    // public void inorder() {
    //     inOrderHelper(this.root);
    // }
    // public void postorder() {
    //     postOrderHelper(this.root);
    // }
    public Node searchTree(int k) {
        Node x = searchTreeHelper(root, k);
        if (x != null) {
            splay(x);
        }
        return x;
    }
    public Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    public Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    public Node successor(Node x) {
        if (x.right != null) {
            return minimum(x.right);
        }
        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }
    public Node predecessor(Node x) {
        if (x.left != null) {
            return maximum(x.left);
        }
        Node y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }
    public void insert(int key) {
        Node node = new Node(key);
        Node y = null;
        Node x = this.root;

        while (x != null) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }
        splay(node);
    }
}