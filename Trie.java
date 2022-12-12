
//Title: Trie Class
//Author: Ahmet Berat Akdoðan, Baþar Aslan
//Section: 1
//Assignment: 5
//Description: This class defines a trie implementation to contribute the Trie class.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trie<Value> {
	
	//Variable Declarations: R integer represents extended ASCII, root variable to represent initial point of Node, n integer to keep number of keys in trie.
	private static final int R = 256;      
	private Node root;     
	private int n;          
	
	
	//R-way trie node
	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}

    //Empty constructor
	public Trie() {
	}
	
	public boolean Search(String arg) {
		//Precondition: Checks the string key which is arg. Returns true if key is not null.
		//Postcondition: Returns the value of the keys.
		
		if (arg == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(arg) != null;
	}
	
	public void autoComplete(String prefix)
	{
		// Precondition: Checks if the trie element starts with the given prefix.
		// Postcondition: Prints all strings starts with given prefix in the trie. 
		
		
		Queue<String> results = new Queue<String>();
		Node x = get(root, prefix, 0);
		collect(x, new StringBuilder(prefix), results);
		
		if(results.isEmpty())
		{
			System.out.println("No words");
		}
		else
		{
			System.out.print(results);
		}
	}
	
	public void reverseAutoComplete(String suffix)
	{
		// Precondition: Checks if the elements in the trie ends with the given parameter or not.
		// Postcondition: print all strings end with given suffix in the trie.
		
		Queue<String> results = new Queue<String>();
		Node x = get(root, suffix, 0);
		collect(x, new StringBuilder(suffix), results);
		
	}
	
	public void FullAutoComplete(String prefix, String suffix)
	{
		// Precondition: Concatenates the prefix and suffix to use it as a pattern in the helper collect method.
		// Postcondition: prints all strings start with given prefix and end with given suffix in the trie.
		
	  String pattern=prefix+" "+suffix;
	  Queue<String> results = new Queue<String>();
	  collect(root, new StringBuilder(), pattern, results);
	  
	    if(results.isEmpty())
		{
			System.out.println("No word");
		}
		else
		{
			System.out.print(results);
		}
	}
	
	

	public void findTopK(int k) throws FileNotFoundException
	{
		//Precondition: Checks the most occurred words in the trie.
		//Postcondition: Prints the top k words that have most occurrences.
		
		 int totalWords = 0;
		 String words[] = new String[100];
		File file = new File("input1.txt");        
		Scanner sc = new Scanner(file);  
		while(sc.hasNext() == true) {
			words[totalWords] = sc.next();
			totalWords++;

		}

		int mostOccur1=0,mostOccur2=0;
		int count=0,count2=0;
		String mostWord1="";
		String mostWord2="";

		for(int i=0;i<totalWords;i++){
			count=0;

			for(int j=0;j<totalWords;j++){
				if(words[i].equals(words[j])){
					
					count++;

				}

				if(count>mostOccur1) {

					mostOccur1=count;
					mostWord1=words[i];
					
				}
				
			}
			
	}
		for(int i=0;i<totalWords;i++){
			count2=0;

			for(int j=0;j<totalWords;j++){
				if(words[i].equals(words[j]) ){
					
					if(!(words[i].equals(mostWord1)))
					{
						count2++;
					}
					

				}
                
				if(count2>mostOccur2) {

					mostOccur2=count2;
					mostWord2=words[i];
					
					
				}

	}	
	
}
		System.out.println(mostWord2.toLowerCase()+", "+mostWord1.toLowerCase());
		System.out.println();
	}


	public Value get(String key) {
		
		//Precondition: Checks if the key exist or null in the trie.
		//Postcondition: Returns the value associated with the key in the trie. If it is not in the trie, returns null.
			
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		Node x = get(root, key, 0);
		if (x == null) return null;
		return (Value) x.val;
	}

	//-------------------------------------------
	

	private Node get(Node x, String key, int d) {
		
		//Precondition: private helper method for get to check if the key exist or null in t and if it isnot nullhe trie.
		//Postcondition: Returns the desired value if it is in the trie. If the node is null, returns null.
		
		if (x == null) return null;
		if (d == key.length()) return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d+1);
	}


	public void put(String key, Value val) {
		
		//Precondition: Checks the null condition of the key. If it is not null, makes insert operation.
		//Postcondition: Inserts a new key-value pair to the trie.
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		
		else root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Value val, int d) {
		
		//Precondition: Private helper method.Makes insert operation.
		//Postcondition: Puts a new key-value pair to the trie.
		
		if (x == null) x = new Node();
		if (d == key.length()) {
			if (x.val == null) n++;
			x.val = val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d+1);
		return x;
	}


	public int size() {
		// Precondition: Finds the size of trie.
    	// Postcondition: Returns number of key-value pairs in the trie.
		return n;
	}


	public boolean isEmpty() {
		
		// Precondition: Checks if the trie is empty or not.
		// Postcondition: Returns false if the trie is empty.
			return size() == 0;
	}

	

	

	

	


	private void collect(Node x, StringBuilder prefix, Queue<String> results) {
		
		// Precondition: Gets x, prefix and results as a parameter and queue data structure is used to hold value.
		// Postcondition: If the value is null, inserts the prefix that is given in the parameter to the queue.
		
		if (x == null) return;
		if (x.val != null) results.enqueue(prefix.toString());
		for (char c = 0; c < R; c++) {
			prefix.append(c);
			collect(x.next[c], prefix, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	


	private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
		
		// Precondition: Private helper method for collect. Gets the parameters x, prefix, pattern and results.
		// Postcondition: If the value is null, inserts the prefix that is given in the parameter to the queue.
		
		if (x == null) return;
		int d = prefix.length();
		if (d == pattern.length() && x.val != null)
			results.enqueue(prefix.toString());
		if (d == pattern.length())
			return;
		char c = pattern.charAt(d);
		if (c == ' ') {
			for (char ch = 0; ch < R; ch++) {
				prefix.append(ch);
				collect(x.next[ch], prefix, pattern, results);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}
		else {
			prefix.append(c);
			collect(x.next[c], prefix, pattern, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}


}
