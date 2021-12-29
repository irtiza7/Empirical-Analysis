public class BST {
    public static int comparisons = 0;
    public Node root;
    
    BST() {
        this.root = null;
    }

    String search(int key, Node root) {
        if (root == null) {
            return "Not Found";
        }
        if (root.key == key) {
            comparisons++;
            return "Found"; 
        } else if (key < root.key) {
            comparisons++;
            search(key, root.left);
        } else if (key > root.key) {
            comparisons++;
            search(key, root.right);
        }
        return "Not Found";
    }

    void insert(int key) {
         root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }
}