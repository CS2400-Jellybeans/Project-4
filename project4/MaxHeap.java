package project4;

public final class MaxHeap<T extends Comparable<? super T>>
             implements MaxHeapInterface<T>
{
   private T[] heap;      // Array of heap entries; ignore heap[0]
   private int lastIndex; // Index of last entry and number of entries
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
   
   public MaxHeap()
   {
      this(DEFAULT_CAPACITY); // Call next constructor
   } // end default constructor
   
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
      integrityOK = true;
   } // end constructor
   
   public MaxHeap(T[] contents)
   {
      int initialCapacity = contents.length;
      // Is initialCapacity too small?
      if (initialCapacity < DEFAULT_CAPACITY)
         initialCapacity = DEFAULT_CAPACITY;
      else // Is initialCapacity too big?
         checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
      heap = tempHeap;
      for(int i = 0; i < contents.length; i++)
      {
         heap[i] = contents[i];
      }
      lastIndex = initialCapacity;
      reheap(0);
      integrityOK = true;
   } // end constructor


   public void add(T newEntry)
   {
   // See Segment 27.8.
   // Slide 19.
   } // end add

   public T removeMax()
   {
      checkIntegrity();             // Ensure initialization of data fields
      T root = null;
   
      if (!isEmpty())
      {
         root = heap[1];            // Return value
         heap[1] = heap[lastIndex]; // Form a semiheap
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
      int leftChildIndex = 2 * rootIndex + 1;
   
      while (!done && (leftChildIndex <= lastIndex))
      {
         int largerChildIndex = leftChildIndex;
         int rightChildIndex = leftChildIndex + 1;
   
         if ( (rightChildIndex <= lastIndex) &&
               heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
         {
            largerChildIndex = rightChildIndex;
         } // end if
   
         if (orphan.compareTo(heap[largerChildIndex]) < 0)
         {
            heap[rootIndex] = heap[largerChildIndex];
            rootIndex = largerChildIndex;
            leftChildIndex = 2 * rootIndex + 1;
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

   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempted to create a heap whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   }
   public void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException("Heap object is corrupt.");
   }
   
} // end MaxHeap
