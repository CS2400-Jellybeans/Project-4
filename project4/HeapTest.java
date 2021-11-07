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
   } // end main
   
   /**
    * Creates two heaps, one using sequential additions and the other using reheap.
    * @return A string with the formatted output.
    * @throws IOException Throws if an error occurs while reading or writing data.
    */
   public static String createHeaps() throws IOException
   {
      MaxHeapInterface<Integer> addHeap = buildMaxHeapWithAdd("project4/data.txt");
      MaxHeapInterface<Integer> reheapHeap = buildMaxHeapWithReheap("project4/data.txt");
      String outputString = "";
      outputString += formattedHeapString(addHeap, true) + "\n"
                   + formattedHeapString(reheapHeap, false);
      return outputString;
   }

   /**
    * Builds a max heap from a file's data using sequential additions.
    * @param fileName The name of the input file.
    * @return A maxHeapInterface containing the file's data.
    * @throws IOException Throws if an error occurs while reading or writing data.
    */
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

   /**
    * Builds a max heap from a file's data using reheap.
    * @param fileName The name of the input file.
    * @return A maxHeapInterface containing the file's data.
    * @throws IOException Throws if an error occurs while reading or writing data.
    */
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

   /**
    * Gets a string from a file.
    * @param fileName The name of the input file.
    * @return A string containing the data in the file.
    * @throws IOException Throws if an error occurs while reading or writing data.
    */
   public static String getStringFromFile(String fileName) throws IOException
   {
      String output = "";
      File file = new File(fileName);
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

   /**
    * Takes the heap and outputs its data in a readable format, also removes 10 values.
    * @param heap The heap to use.
    * @param sequential A boolean indicating whether this heap was built sequentially or not.
    * @return A formatted string containing the heap's data.
    */
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
   
   /**
    * Writes a string to a file.
    * @param fileName The name of the output file.
    * @param content The string to write to the file.
    * @throws IOException Throws if an error occurs while reading or writing data.
    */
   public static void outputToFile(String fileName, String content) throws IOException
   {
      PrintWriter outFile = new PrintWriter(fileName);
      outFile.print(content);
      outFile.close();
   }
}
