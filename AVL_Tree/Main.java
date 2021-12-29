import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class Main {
  public static void main(String[] args) {
      
    long startTime = System.nanoTime();
    AVL tree = new AVL();
      
    int n = 10000000;
    int number = 0;
    Random rand = new Random();
    Integer[] dataNormalDist = new Integer[n/2];
    Integer[] sortedData = new Integer[n/2];

    for (int i = 0; i < (n/ 2); i++) {
      number = rand.nextInt(n);
      dataNormalDist[i] = number;
    }
    for (int i = 0; i < (n / 2); i++) {
      number = rand.nextInt(n);
      sortedData[i] = number;
    }
        
    Arrays.sort(sortedData);

    for(int i = 0; i < (n / 2); i++) {
      tree.root = tree.insertNode(tree.root, dataNormalDist[i]);
    }
    for(int i = 0; i < (n / 2); i++) {
      tree.root = tree.insertNode(tree.root, sortedData[i]);
    }

    float averageComparrisons = 0;
    int tempComparisons = 0;
    Node node;

    for (int i = 0; i < dataNormalDist.length; i++) {
      node = tree.searchNode(dataNormalDist[i], tree.root);
      tempComparisons += AVL.comparisons;
      AVL.comparisons = 0;
    }
    for (int i = 0; i < sortedData.length; i++) {
      node = tree.searchNode(sortedData[i], tree.root);
      tempComparisons += AVL.comparisons;
      AVL.comparisons = 0;
    }
    
    averageComparrisons = tempComparisons / n;
    System.out.println("n = " + n);
    System.out.println("Total Comparisons:" + tempComparisons);
    System.out.println("Average Comparisons: " + averageComparrisons);
        
    long elapsedTime = System.nanoTime() - startTime;
    System.out.println("Execution Time: " + elapsedTime / 1000000 + " milliseconds");
     
      
    // tree.root = tree.insertNode(tree.root, 33);
  }
}