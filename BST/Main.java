import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        long startTime = System.nanoTime();
        BST tree = new BST();
        
        int n = 10000000;
        int number = 0;
        Random rand = new Random();
        Integer[] dataNormalDist = new Integer[n/2];
        Integer[] sortedData = new Integer[n/2];

        for(int i = 0; i < (n/ 2); i++) {
            number = rand.nextInt(n);
            dataNormalDist[i] = number;
        }
        for(int i = 0; i < (n / 2); i++) {
            number = rand.nextInt(n);
            sortedData[i] = number;
        }
        
        Arrays.sort(sortedData);

        for(int i = 0; i < (n / 2); i++) {
            tree.insert(dataNormalDist[i]);
        }
        for(int i = 0; i < (n / 2); i++) {
            tree.insert(sortedData[i]);
        }

        float averageComparrisons = 0;
        int tempComparisons = 0;
        String status;

        for (int i = 0; i < dataNormalDist.length; i++) {
            status = tree.search(dataNormalDist[i], tree.root);
            tempComparisons += BST.comparisons;
            BST.comparisons = 0;
        }
        for (int i = 0; i < sortedData.length; i++) {
            status = tree.search(sortedData[i], tree.root);
            tempComparisons += BST.comparisons;
            BST.comparisons = 0;
        }
        averageComparrisons = tempComparisons / n;
        System.out.println("Total Comparisons Performed for " + n + " Values: " + tempComparisons);
        System.out.println("Average Comparisons Performed for " + n + " Values: " + averageComparrisons);
        
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in millis: " + elapsedTime / 1000000);
    }
}