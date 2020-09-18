package ds;

public class BasicStack<X> {
	private X [] data; //handled internally in BasicStack class
	private int stackPointer;
	
	public BasicStack() {
		data = (X[]) new Object[1000];
		stackPointer = 0;
	}
	
	public void push(X newItem) {
		data[stackPointer++] = newItem; //postincrement 
	}
	
	public X pop() {
		if (stackPointer == 0) {
			throw new IllegalStateException("no more items in the stack");
		}
		return data[--stackPointer]; //predecrement
	}
	
	//O(n)
	public boolean contains(X item) {
		boolean found = false;
		
		for (int i = 0; i < stackPointer;i++) {
			if(data[i].equals(item)) {
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	//O(n)
	public X access(X item) {
		while(stackPointer > 0) {
			X tempItem = pop();
			if(item.equals(tempItem)) {
				return tempItem;
			}
		}
		
		//if item not found
		throw new IllegalArgumentException("Couldn't find the item on the stack: " + item);
	}
	
	public int size() {
		return stackPointer;
	}
}


