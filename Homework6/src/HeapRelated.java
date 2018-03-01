

public class HeapRelated {

	// Return true if the array is a legal heap. Returns false if all other
	// situations.
	public static <T extends Comparable<T>> boolean isHeap(T[] heap, int heapSz) {
		boolean result = true;
		for (int i = 1; i <= (heapSz / 2); i++) {
			int leftChild = i * 2;
			int rightChild = leftChild + 1;
			if (heap[leftChild].compareTo(heap[i]) > 0) {
				result = false;
			}
			if (rightChild >= heapSz) {
				break;
			}
			if (heap[rightChild].compareTo(heap[i]) > 0) {
				result = false;
			}
		}
		return result;
	}

	// (See exercise 26.5) Change an element in the heap. This change will usually
	// violate the heap property.
	// Efficiently make the change, while making any necessary repairs.
	public static <T extends Comparable<T>> void changeHeap(T[] heap, int heapSz, int whichElement, T newValue) {
		// compare newvalue with parents
		if (newValue.compareTo(heap[whichElement / 2]) > 0) {
			while (newValue.compareTo(heap[whichElement / 2]) > 0) {
				heap[whichElement] = heap[whichElement / 2];
				whichElement = whichElement / 2;
			}
		} else {
			// compare newValue with its leftchild and rightchild
			boolean flag = false;
			int leftChild = whichElement * 2;
			// while the proper position is not found and newValue has child
			while (!flag && leftChild <= heapSz) {
				int largerChild = leftChild; // Assume larger
				int rightChild = leftChild + 1;
				// find largerChild
				if ((rightChild <= heapSz) && heap[rightChild].compareTo(heap[largerChild]) > 0) {
					largerChild = rightChild;
				}
				// get the proper position
				if (newValue.compareTo(heap[largerChild]) < 0) {
					heap[whichElement] = heap[largerChild];
					whichElement = largerChild;
				} else {
					flag = true;
				}
			}
		}
		// new value at its correct position
		heap[whichElement] = newValue;
	}
}
