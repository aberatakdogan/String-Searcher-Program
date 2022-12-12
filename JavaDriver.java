
//Title: Java Driver Class
//Author: Ahmet Berat Akdogan, Basar Aslan
//Section: 1
//Assignment: 5
//Description: This class is the main class of our trie implementation which makes specific operations benefiting from the Trie class.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JavaDriver {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		

		Trie<Integer> st = new Trie<Integer>(); //Creating an object for Trie class to use most of the implementation.
		Scanner keyboard = new Scanner(System.in);

		File file = new File("input1.txt");        
		Scanner sc = new Scanner(file);       

		int totalWords = 0;


		String words[] = new String[100];

		while(sc.hasNext() == true) {
			words[totalWords] = sc.next();
			totalWords++;

		}

		while(true) {


			System.out.print("(enter words file): ");
			String fileName = keyboard.next();



			System.out.print("(represent search function): ");
			int number;
			number = keyboard.nextInt();


			// Checks if the word is in the trie or not. Prints true if it is. Otherwise prints false. Search method is used.
			if(number == 1) {

				System.out.print("(enter search word): ");
				String word = keyboard.next();

				int i=0;
				while(i<totalWords)
				{
					if(words[i].equalsIgnoreCase(word))
					{
						st.put(word, i); 

					}
					i++;
				}

				if(st.Search(word) )
				{
					System.out.println("True");
					System.out.println();
				}
				else 
				{
					System.out.println("False"); 
					System.out.println();
				}




			}

			//Checks the first letter of the word in the trie and prints the words that starts with the entered letter with lexicographic order.
			// autoComplete method is used.
			else if (number == 2) {
				System.out.print("(enter auto-completed word): ");
				String word = keyboard.next();

				for(int i=0; i<totalWords; i++)
				{
					st.put(words[i], i);	 

				}   

				st.autoComplete(word);
				

				System.out.println();
				System.out.println();
			}

			//Checks the last letter of the word in the trie and prints the words that ends with the entered letter with lexicographic order.
			// reverseAutoComplete method is used.
			else if(number==3)
			{
				System.out.print("(enter reverse auto-completed word): ");
				String word = keyboard.next();
				

				for(int i=0; i<totalWords; i++)
				{
					st.put(words[i], i);	 

				}   


				for(int i=0; i<totalWords; i++)
				{
					if(word.charAt(word.length()-1)==(words[i].charAt(words[i].length()-1)))
					{
						st.reverseAutoComplete(words[i]);
                        
						
						
							System.out.print(words[i].toLowerCase()+", ");   
						   	  
					}
					

				}
				System.out.println();
				System.out.println();
				
         
			}
			
			//Checks the word that starts with certain prefix and ends with certain suffix.
			//FullAutoComplete method is used.
			else if(number==4)
			{
				System.out.print("(enter full auto-completed word): ");
				String word = keyboard.next();
				word+=keyboard.nextLine();
		
				String[] parts = word.split(" ",2);
				String s1 = parts[0];
				String s2 = parts[1];
				
				for(int i=0; i<totalWords; i++)
				{
					st.put(words[i], i);	 

				} 
				
					st.FullAutoComplete(s1,s2);
				
				System.out.println();
		        System.out.println();
					
				
			}
			
			//Checks the most occurred k word in the trie.
			//findTopK method is used.
			else if(number == 5)
			{
				System.out.print("(enter number K): ");
				int k = keyboard.nextInt();
				
				
				for(int i=0; i<totalWords; i++)
				{
					st.put(words[i], i);	 

				} 
				st.findTopK(k);
	
	
			}
		
			
				
		}

	}

}
