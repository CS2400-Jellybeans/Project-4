package project4;

import java.io.*;
import java.util.Scanner;

public class HeapTest
{
   public static void main(String[] args) throws IOException
   {
      //String fileName = "";
      //Scanner kb = new Scanner(System.in);
      //System.out.print("Enter the name of the file: ");
      //fileName = kb.nextLine();

      MaxHeapInterface<Integer> addHeap = buildMaxHeapWithAdd("project4/data_random.txt");
      System.out.println("Heap built with sequential insertions:\n" + addHeap.toString());

      MaxHeapInterface<Integer> reheapHeap = buildMaxHeapWithAdd("project4/data_random.txt");
      System.out.println("Heap built using optimal method:\n" + reheapHeap.toString());
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
}
