public class AVL {
  public static int comparisons = 0;
  Node root;

  public Node searchNode(int key, Node node) {
    if (node == null) {
      return null;
    }
    if (key == node.item) {
      comparisons++;
      return node;
    } else if (key < node.item) {
      comparisons++;
      return searchNode(key, node.left);
    } else {
      comparisons++;
      return searchNode(key, node.right);
    }
  }

  int height(Node N) {
    if (N == null) {
      return 0;
    }
    return N.height;
  }

  int max(int a, int b) {
    return (a > b) ? a : b;
  }
  
  Node rightRotate(Node y) {
    Node x = y.left;
    Node T2 = x.right;
    x.right = y;
    y.left = T2;
    y.height = max(height(y.left), height(y.right)) + 1;
    x.height = max(height(x.left), height(x.right)) + 1;
    return x;
  }

  Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;
    y.left = x;
    x.right = T2;
    x.height = max(height(x.left), height(x.right)) + 1;
    y.height = max(height(y.left), height(y.right)) + 1;
    return y;
  }

  int getBalanceFactor(Node N) {
    if (N == null) {
      return 0;
    }
    return height(N.left) - height(N.right);
  }
  
  Node insertNode(Node node, int item) {
    if (node == null) {
      return (new Node(item));
    }
    if (item < node.item) {
      node.left = insertNode(node.left, item);
    } else if (item > node.item) {
      node.right = insertNode(node.right, item);
    } else {
      return node;
    }
    node.height = 1 + max(height(node.left), height(node.right));
    int balanceFactor = getBalanceFactor(node);
    if (balanceFactor > 1) {
      if (item < node.left.item) {
        return rightRotate(node);
      } else if (item > node.left.item) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
      }
    }
    if (balanceFactor < -1) {
      if (item > node.right.item) {
        return leftRotate(node);
      } else if (item < node.right.item) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
      }
    }
    return node;
  }
  
  Node nodeWithMimumValue(Node node) {
    Node current = node;
    while (current.left != null) {
      current = current.left;
    }
    return current;
  }
}