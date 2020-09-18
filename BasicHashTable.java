package ds;


//key, value
public class BasicHashTable<X, Y> {
	private HashEntry[] data;
	private int capacity; //how big the hash table is
	private int size;
	
	//Constructor for BasicHashTable that allows us to create init table size
	public BasicHashTable(int tableSize) {
		this.capacity = tableSize;
		this.data = new HashEntry[this.capacity];
		this.size = 0;
	}
	
	//0(1)
	public Y get(X key) {
		int hash = calculateHash(key);
		
		//if we dont have anything for the given key, just return null
		if(data[hash] == null) {
			return null;
		}
		//otherwise get the hash entry for the key and return its value
		else {
			return (Y)data[hash].getValue();
		}
	}
	
	//O(1)
	public void put(X key, Y value) {
		int hash = calculateHash(key);
		
		data[hash] = new HashEntry<X, Y>(key, value);
		size++;
	}
	
	//depending on collisions. worse case: O(n) or O(n^2).
	public Y delete(X key) {
		//first get the value for this key so it can be returned
		Y value = get(key);
		
		//clear out the hashtable slot for the key and return the removed value
		if(value != null) {
			int hash = calculateHash(key);
			data[hash] = null;
			size--;
			hash = (hash + 1) % this.capacity; //looks for hash in the next slot up because we might have had a collision
			
			//if the next slot isnt empty we should re add it so we can keep the hash algorithms clear
			//can be problematic if hash table isnt big enough
			while(data[hash] != null) { //breaks loop if theres no collisions
				HashEntry he = data[hash];
				data[hash] = null;
				System.out.println("Rehashing: " + he.getKey() + " - " + he.getValue());
				put((X)he.getKey(), (Y)he.getValue()); //rehashing the struct back in
				//we repositioned the hash item and didnt really add a new one so back off the size
				size--;
				hash = (hash + 1) % this.capacity;
			}
		}
		return value;
	}
	
	//O(1)
	public boolean hasKey(X key) {
		int hash = calculateHash(key);
		
		//if we dont have anything for the given key, we can just return false
		if (data[hash] == null) {
			return false;
		}
		//otherwise get the hasEntry for the key and see if it matches the given key
		else {
			if (data[hash].getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	//O(n)
	public boolean hasValue(Y value) {
		//loop through all of the hash entries
		for (int i = 0; i < this.capacity; i++) {
			HashEntry entry = data[i];
			
			//this this hash entry isn't null and the value equals the one i passed in, the hash table has this value
			if (entry != null && entry.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return this.size;
	}
	
	//O(n)
	//mult collisions will make put and get 0(n) time.
	//data struc should be initialized to a large enough size to prevent collisions
	//takes incoming key and gets the hashCode off of it
	private int calculateHash(X key) {
		int hash = (key.hashCode() % this.capacity); //find a slot inside the hash table using mod and size. this returns hash slot
		//this is for collisions. 
		//hashcode is unique. hash slot isn't. This while loop checks to see if this hash slot is appropriate for the key
		while (data[hash] != null && !data[hash].getKey().equals(key)) {
			hash = (hash + 1) % this.capacity;	
		}
		return hash;
	}
	
	private class HashEntry<X, Y> {
		private X key;
		private Y value;
		
		//represents our key value pair
		public HashEntry(X key, Y value) {
			this.key = key;
			this.value = value;
		}
		
		//getters and setters generated to help us set and retrieve our values
		public X getKey() {
			return key;
		}
		public void setKey(X key) {
			this.key = key;
		}
		public Y getValue() {
			return value;
		}
		public void setValue(Y value) {
			this.value = value;
		}
		
	}
}
