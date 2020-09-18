package ds;

public class BasicQueue<X> {
	private X[] data;
	private int front;
	private int end;
	
	//calls the other constructor 
	public BasicQueue() {
		this(1000); //size defaulted to 1000
	}
	
	private BasicQueue(int size) {
		this.front = -1;
		this.end = -1;
		data = (X[])new Object[size];
	}
	
	public int size() {
		if(front == -1 && end == -1) {
			return 0;
		}
		//otherwise we add one to get the inclusive subtraction value
		else {
			return end - front + 1;
		}
	}
	
	public void enQueue(X item) {
		//first see if queue is full
		if((end + 1) % data.length == front) {
			throw new IllegalStateException("The queue is full");
		}
		//otherwise check to see if any items have been added to the queue
		else if(size() == 0) {
			front++;
			end++;
			data[end] = item;
		}
		else {
			end++;
			data[end] = item;
		}
	}
	
	public X deQueue() {
		X item = null;
		
		//if queue is empty , we can't dequeue anything
		if(size() == 0) {
			throw new IllegalStateException("Can't dequeue because the queue is empty!");
		}
		//otherwise if last item on queue, queue needs to get reset to empty
		else if(front == end) {
			item = data[front];
			data[front] = null;
			front = -1;
			end = -1;
		}
		//otherwise grab the front of queue, return it and adjust front pointer
		else {
			item = data[front];
			data[front] = null; 
			front++;
		}
		return item;
	}
	
	//o(n)
	public boolean contains(X item) {
		boolean found = false;
		
		//if queue is empty, return false
		if(size() == 0) {
			return found;
		}
		for(int i = front; i < end; i++) {
			if (data[i].equals(item)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	//O(n)
	public X access(int position) {
		if(size() == 0 || position > size()) {
			throw new IllegalArgumentException("No items in queue");
		}
		
		int trueIndex = 0;
		for(int i = front; i < end; i++) {
			if(trueIndex == position) {
				return data[i];
			}
			trueIndex++;
		}
		
		//if we didnt find the item
		throw new IllegalArgumentException("Could not get queue item at position: " + position);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
