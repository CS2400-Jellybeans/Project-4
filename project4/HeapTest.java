import java.io.*;
import java.util.Scanner;

import project4.MaxHeap;

public class HeapTest
{
   public static void main(String[] args) throws IOException
   {
      
      String fileName = "";
      Scanner kb = new Scanner(System.in);
      System.out.print("Enter the name of the file: ");
      fileName = kb.nextLine();

      System.out.println("File contents:");
      String fileText = getStringFromFile(fileName);
      System.out.println(fileText);
   }
   
   public static void buildMaxHeapWithAdd()
   {
      MaxHeap heap = new MaxHeap<T>();
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
