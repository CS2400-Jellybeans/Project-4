package project4;

import java.io.*;
import java.util.Scanner;

public class HeapTest
{
   public static void main(String[] args) throws IOException
   {
      createHeaps();
      String outputString = createHeaps();
      outputToFile("project4/output.txt", outputString);
      System.out.print("Heaps succesfully created. Output stored in the file \"output.txt.\"");
   }
   public static String createHeaps() throws IOException
   {
      MaxHeapInterface<Integer> addHeap = buildMaxHeapWithAdd("project4/data.txt");
      MaxHeapInterface<Integer> reheapHeap = buildMaxHeapWithReheap("project4/data.txt");
      String outputString = "";
      outputString += formattedHeapString(addHeap, true) + "\n"
                   + formattedHeapString(reheapHeap, false);
      return outputString;
   }
   
   public static MaxHeapInterface<Integer> buildMaxHeapWithAdd(String fileName) throws IOException
   {
      MaxHeapInterface<Integer> heap = new MaxHeap<Integer>();
      String messageTxt = getStringFromFile(fileName);
      String[] lines = messageTxt.split("\r\n|\n|\r");
      for(int i = 0; i < lines.length; i++)
      {
         String line = lines[i];
         heap.add(Integer.parseInt(line));
      }
      return heap;
   }
   public static MaxHeapInterface<Integer> buildMaxHeapWithReheap(String fileName) throws IOException
   {
      String messageTxt = getStringFromFile(fileName);
      String[] lines = messageTxt.split("\r\n|\n|\r");
      Integer[] nums = new Integer[lines.length];
      for(int i = 0; i < lines.length; i++)
      {
         String line = lines[i];
         nums[i] = Integer.parseInt(line);
      }
      MaxHeapInterface<Integer> heap = new MaxHeap<Integer>(nums);
      return heap;
   }

   public static String getStringFromFile(String filename) throws IOException
   {
      String output = "";
      File file = new File(filename);
      if (!file.exists())
      {
         System.out.println("file not found.");
         System.exit(0);
      }
      Scanner inputFile = new Scanner(file);
      while (inputFile.hasNextLine())
      {
         output += inputFile.nextLine() + "\n";
      }
      inputFile.close();
      return output;
   }

   public static String formattedHeapString(MaxHeapInterface<Integer> heap, boolean sequential)
   {
      String output = "";
      output += "Heap built using " + (sequential ? "sequential insertions" : "optimal method")
             + "\n" + heap.toString() + "\nNumber of swaps done in the heap creation: "
             + heap.getSwaps() + "\n";
      for(int i = 0; i < 10; i++)
      {
         heap.removeMax();
      }
      output += "Heap after 10 removals:\n" + heap.toString() + "\n";
      return output;
   }
   
   public static void outputToFile(String fileName, String content) throws IOException
   {
      PrintWriter outFile = new PrintWriter(fileName);
      outFile.print(content);
      outFile.close();
   }
}
