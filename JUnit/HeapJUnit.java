package JUnit;

import org.junit.*;

import junit.framework.TestCase;
import project4.MaxHeap;
import project4.MaxHeapInterface;

public class HeapJUnit extends TestCase
{
   MaxHeapInterface<Integer> testHeap;
   String expectedOutput;

   @Override
   protected void setUp() throws Exception
   {
      testHeap = new MaxHeap<>();
   }

   //Testing heap creation with sequential add.
   @Test
   public void testAdd()
   {
      expectedOutput = "5, 4, 2, 1, 3";
      int[] input = new int[]{1, 2, 3, 4, 5};
      for(int num : input)
      {
         testHeap.add(num);
      }
      assertEquals(expectedOutput, testHeap.toString());
   }

   //Testing heap creation with reheap.
   @Test
   public void testReheap()
   {
      expectedOutput = "5, 4, 3, 1, 2";
      Integer[] input = new Integer[]{1, 2, 3, 4, 5};
      testHeap = new MaxHeap<Integer>(input);
      assertEquals(expectedOutput, testHeap.toString());
   }

   //Should get the correct root.
   @Test
   public void testGetMax()
   {
      Integer[] input = new Integer[]{1, 2, 3, 4, 5};
      testHeap = new MaxHeap<Integer>(input);
      Integer num = 5;
      assertEquals(num, testHeap.getMax());
   }

   //Testing root removal.
   @Test
   public void testRemove()
   {
      expectedOutput = "4, 2, 3, 1";
      Integer[] input = new Integer[]{1, 2, 3, 4, 5};
      testHeap = new MaxHeap<Integer>(input);
      testHeap.removeMax();
      assertEquals(expectedOutput, testHeap.toString());
   }
}
   