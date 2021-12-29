import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class GenerateData {
   public static void main(String args[]) throws Exception {
      
      int n = 5000000;
      int number = 0;
      Random rand = new Random();
      Integer[] dataNormalDist = new Integer[n];

      for (int i = 0; i < n; i++) {
         number = rand.nextInt(n);
         dataNormalDist[i] = number;
      }

      // Writing data to file.
      BufferedWriter writer = new BufferedWriter(new FileWriter("data_10000000.txt", false));
        
      for(int i = 0; i < dataNormalDist.length; i++) {
         writer.write(dataNormalDist[i].toString());
         writer.newLine();
      }
      writer.flush();
      writer.close();

      System.out.println("Done");
   } 
}