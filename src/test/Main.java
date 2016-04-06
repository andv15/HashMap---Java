package test;

import list.*;
import map.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		
		final int N = 2;
		
		BufferedReader read = new BufferedReader( new FileReader("input.txt") );
		BufferedWriter out = new BufferedWriter( new FileWriter("output.txt") );
		
		HashMap m = new HashMap(N);
		int   dim = N;	//dimensiune maxima vector de liste
		
		AbstractList l;
		String      key = new String();
		String      val = new String();
		ConcreteKey k;

		
		int i;
		String line = read.readLine();
		Integer   n = Integer.valueOf(line);

		for(i=0 ; i<n ; i++){
			line = read.readLine();				/*	citire linie din fisier */
			StringTokenizer tokens = new StringTokenizer(line, " ");
			Integer              x = Integer.valueOf(tokens.nextToken()); /*  optiune	*/
			
			key = tokens.nextToken();				/*	cheie	*/
			if ( tokens.hasMoreTokens() ) {
				val = tokens.nextToken();			/*	 valoarea pentru cheie	*/
			}

			k=new ConcreteKey(key);
			if( k.hashCode() > dim )				/*daca hashCode-ul pentru cheie >
			 											dimensiunea bucketului*/
				{
					m=m.resize(k.hashCode());		/*se face resize*/
					dim=k.hashCode();
				}

			
			switch (x) {
			
			case 0:
				l=m.get(new ConcreteKey(key));		/*lista valori pentru cheia key*/
				if(l!=null)
					out.write(l.toString());
				else	out.write("null");
				out.newLine();
				break;

			case 1:
				/*adauga in lista de valori pentru cheia key, valoarea val*/
				m.put(new ConcreteKey(key), new ValueV(val,1)); 
				break;
				
			case 2:
				/*	verifica daca cheia key exista in bucket */
				if(m.containsKey(new ConcreteKey(key)))
					out.write("true");
				else out.write("false");
				out.newLine();
				break;
				
			case 3:
				/*	sterge cheia key din bucket si afiseaza lista de valori
				 *  existenta inainte de stergere
				 */
				l=m.remove(new ConcreteKey(key));
				if(l!=null)
					out.write(l.toString());
				else	out.write("null");
				out.newLine();
				break;
				
			case 4:
				/*	sterge din lista de valori a cheii key, valoarea val
				 *  afiseaza nr de aparitii al valorii val inainte de stergere
				 */
				out.write((int)m.remove(new ConcreteKey(key),new ValueV(val,1))+"");
				out.newLine();
				break;
				
			default:
				break;
			}
		}
		read.close();
		out.close();
	}

}
