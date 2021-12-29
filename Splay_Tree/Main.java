import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class Main {
    public static void main(String [] args) {
        
        long startTime = System.nanoTime();
        Splay_Tree tree = new Splay_Tree();

        int n = 10000000;
        int number = 0;
        Random rand = new Random();
        Integer[] dataNormalDist = new Integer[n/2];
        Integer[] sortedData = new Integer[n/2];

        for (int i = 0; i < (n / 2); i++) {
            number = rand.nextInt(n);
            dataNormalDist[i] = number;
        }
        for (int i = 0; i < (n / 2); i++) {
            number = rand.nextInt(n);
            sortedData[i] = number;
        }
        
        Arrays.sort(sortedData);

        for (int i = 0; i < dataNormalDist.length; i++) {
            tree.insert(dataNormalDist[i]);
        }
        for(int i = 0; i < (n / 2); i++) {
            tree.insert(sortedData[i]);
        }

        float averageComparrisons = 0;
        float averageRotations = 0;
        int tempComparisons = 0;
        int tempRotations = 0;
        Node node;

        for (int i = 0; i < dataNormalDist.length; i++) {
            node = tree.searchTree(dataNormalDist[i]);
            tempComparisons += Splay_Tree.comparisons;
            tempRotations += Splay_Tree.rotations;
            Splay_Tree.comparisons = 0;
            Splay_Tree.rotations = 0;
        }
        for (int i = 0; i < sortedData.length; i++) {
            node = tree.searchTree(sortedData[i]);
            tempComparisons += Splay_Tree.comparisons;
            tempRotations += Splay_Tree.rotations;
            Splay_Tree.comparisons = 0;
            Splay_Tree.rotations = 0;
        }

        averageComparrisons = tempComparisons / n;
        averageRotations = tempRotations / n;
        System.out.println("Total Comparisons Performed for " + n + " Values: " + tempComparisons);
        System.out.println("Average Comparisons Performed for " + n + " Values: " + averageComparrisons);
        System.out.println("Total Rotations Performed for " + n + " Values: " + tempRotations);
        System.out.println("Average Rotations Performed for " + n + " Values: " + averageRotations);
        
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in millis: " + elapsedTime / 1000000);
    }
}
