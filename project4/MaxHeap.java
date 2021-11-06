package project4;

import java.util.Arrays;

public final class MaxHeap<T extends Comparable<? super T>>
             implements MaxHeapInterface<T>
{
   private T[] heap;      // Array of heap entries; ignore heap[0]
   private int lastIndex; // Index of last entry and number of entries
   private boolean integrityOK = false;
   private int swapsDone;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
   
   public MaxHeap()
   {
      this(DEFAULT_CAPACITY); // Call next constructor
   } // end default constructor
   
   /**
    * Builds an empty max heap.
    * @param initialCapacity the initial capacity of the heap array.
    */
   public MaxHeap(int initialCapacity)
   {
      // Is initialCapacity too small?
      if (initialCapacity < DEFAULT_CAPACITY)
         initialCapacity = DEFAULT_CAPACITY;
      else // Is initialCapacity too big?
         checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
      heap = tempHeap;
      lastIndex = 0;
      swapsDone = 0;
      integrityOK = true;
   } // end constructor
   
   /**
    * Builds a max heap using the reheap method.
    * @param entries An array of data to build the heap from.
    */
   public MaxHeap(T[] entries)
   {
      this(entries.length); // Call other constructor
      lastIndex = entries.length;
      // Assertion: integrityOK = true

      // Copy given array to data field
      for (int index = 0; index < entries.length; index++)
         heap[index + 1] = entries[index];

      // Create heap
      for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
         reheap(rootIndex);
   } // end constructor

   /**
    * Adds a new entry to this heap.
    * @param newEntry An object to be added.
    */
   public void add(T newEntry)
   {
      checkIntegrity();        // Ensure initialization of data fields
      int newIndex = lastIndex + 1;
      int parentIndex = newIndex / 2;
      while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
      {
         heap[newIndex] = heap[parentIndex];
         newIndex = parentIndex;
         parentIndex = newIndex / 2;
         swapsDone ++;
      } // end while
   
      heap[newIndex] = newEntry;
      lastIndex++;
      ensureCapacity();
   } // end add

   /**
    * Removes the root data of this heap.
    * @return The data that was removed.
    */
   public T removeMax()
   {
      checkIntegrity();             // Ensure initialization of data fields
      T root = null;

      if (!isEmpty())
      {
         root = heap[1];            // Return value
         heap[1] = heap[lastIndex]; // Form a semiheap
         heap[lastIndex]= null;
         lastIndex--;               // Decrease size
         reheap(1);                 // Transform to a heap
      } // end if

   return root;
   } // end removeMax

   public T getMax()
   {
		checkIntegrity();
      T root = null;
      if (!isEmpty())
         root = heap[1];
      return root;
   } // end getMax

   public void reheap(int rootIndex)
   {
      boolean done = false;
      T orphan = heap[rootIndex];
      int leftChildIndex = 2 * rootIndex;
   
      while (!done && (leftChildIndex <= lastIndex))
      {
         int largerChildIndex = leftChildIndex;
         int rightChildIndex = leftChildIndex + 1;
         swapsDone++;
   
         if ( (rightChildIndex <= lastIndex) &&
               heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
         {
            largerChildIndex = rightChildIndex;
         } // end if
   
         if (orphan.compareTo(heap[largerChildIndex]) < 0)
         {
            heap[rootIndex] = heap[largerChildIndex];
            rootIndex = largerChildIndex;
            leftChildIndex = 2 * rootIndex;
         }
         else
            done = true;
      } // end while
   
      heap[rootIndex] = orphan;
   } // end reheap

   public boolean isEmpty()
   {
      return lastIndex < 1;
   } // end isEmpty

   public int getSize()
   {
      return lastIndex;
   } // end getSize

   public int getSwaps()
   {
      return swapsDone;
   } // end getSwaps

   public void clear()
   {
		checkIntegrity();
      while (lastIndex > -1)
      {
         heap[lastIndex] = null;
         lastIndex--;
      } // end while
      lastIndex = 0;
   } // end clear

   public String toString()
   {
      String result = "";
      for(int i = 1; i < heap.length; i++)
      {
         T value = heap[i];
         if(value != null)
         {
            result += (value.toString());
            if(i < heap.length - 1 && heap[i+1] != null)
            {
               result += ", ";
            }
         }
      }
      return result;
   } // end toString

   private void ensureCapacity()
   {
      if (lastIndex >= heap.length - 1) // If array is full, double its size
      {
         int newLength = 2 * heap.length;
         checkCapacity(newLength);
         heap = Arrays.copyOf(heap, newLength);
      } // end if
   } // end ensureCapacity
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempted to create a heap whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   } // end checkCapacity
   public void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException("Heap object is corrupt.");
   } // end checkIntegrity
   
} // end MaxHeap
