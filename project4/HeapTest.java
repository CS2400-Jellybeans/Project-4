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

      System.out.println("File contents:");
      buildMaxHeapWithAdd("project4/data_random.txt");
   }
   
   public static void buildMaxHeapWithAdd(String fileName) throws IOException
   {
      MaxHeapInterface<Integer> heap = new MaxHeap<Integer>();
      String messageTxt = getStringFromFile(fileName);
      String[] lines = messageTxt.split("\r\n|\n|\r");
      for(int i = 0; i < lines.length; i++)
      {
         String line = lines[i];
         heap.add(Integer.parseInt(line));
      }
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
