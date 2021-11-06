package JUnit;

import org.junit.*;

import junit.framework.TestCase;
import project4.MaxHeap;
import project4.MaxHeapInterface;

public class HeapJUnit extends TestCase
{
   MaxHeapInterface<Integer> integerHeap;
   MaxHeapInterface<String> stringHeap;
   MaxHeapInterface<Character> charHeap;
   String expectedOutput;

   @Override
   protected void setUp() throws Exception
   {
      integerHeap = new MaxHeap<>();
      stringHeap = new MaxHeap<>();
   }

   //Testing heap creation with sequential add.
   @Test
   public void testAdd()
   {
      expectedOutput = "5, 4, 2, 1, 3";
      int[] input = new int[]{1, 2, 3, 4, 5};
      for(int num : input)
      {
         integerHeap.add(num);
      }
      assertEquals(expectedOutput, integerHeap.toString());
   }

   //Testing heap creation with reheap.
   @Test
   public void testReheap()
   {
      expectedOutput = "5, 4, 3, 1, 2";
      Integer[] input = new Integer[]{1, 2, 3, 4, 5};
      integerHeap = new MaxHeap<Integer>(input);
      assertEquals(expectedOutput, integerHeap.toString());
   }

   //Should get the correct root.
   @Test
   public void testGetMax()
   {
      Integer[] input = new Integer[]{1, 2, 3, 4, 5};
      integerHeap = new MaxHeap<Integer>(input);
      Integer num = 5;
      assertEquals(num, integerHeap.getMax());
   }

   //Testing root removal.
   @Test
   public void testRemove()
   {
      expectedOutput = "4, 2, 3, 1";
      Integer[] input = new Integer[]{1, 2, 3, 4, 5};
      integerHeap = new MaxHeap<Integer>(input);
      integerHeap.removeMax();
      assertEquals(expectedOutput, integerHeap.toString());
   }

   //Testing heap creation with a different data type.
   //Organizes lexographically.
   @Test
   public void testGeneric()
   {
      expectedOutput = "You, Today, Are, How, Hello";
      String[] input = new String[]{"Hello", "How", "Are", "You", "Today"};
      stringHeap = new MaxHeap<String>(input);
      assertEquals(expectedOutput, stringHeap.toString());
   }
}
   