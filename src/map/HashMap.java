package map;

import list.*;

public class HashMap extends AbstractHashMap{
	
	/*	costructor HashMap
	 * 	initializare buckets de marime n
	 */
	public HashMap(int n){
		buckets=new ConcreteList[n];
	}
		
	@Override
	public AbstractList get(Key key) {
		Node n;
		Object val;
		int h = key.hashCode();						/*	codul pentru cheie	*/
		
		if( buckets[h] != null )					/*	codul exista in bucket	*/
		{		
			n = (buckets[h]).getFirstEqual(key);	/* nodul din lista de chei ce contine cheia key*/
			if( n != null )							/* cheia exista in bucket */
			{	
				val = n.getValue();					/* valoarea pentru nodul ce contine cheia*/
				AbstractList lv = ( (ValueK)val ).getListValue();	/* lista de valori pentru cheie*/
				return lv;
			}
		}
		return null;								/* cheia nu exista in bucket */
	}

	
	@Override
	public boolean containsKey(Key key) {
		int h = key.hashCode();						/*	codul pentru cheie */
		/* if(h>=buckets.length)
			return false; */
		if( buckets[h] == null )					/*	codul nu exista in bucket*/
			return false;
		if( buckets[h].getFirstEqual(key) != null )	/*	exista nod ce contine cheia*/
				return true;
		return false;								/*	nu exista nod ce contine cheia*/
	}

	@Override
	public boolean put(Key key, Value value) {
		Node nkey,nval,n;
		AbstractList l;
		String s;
		int nr,h=key.hashCode();

		if( containsKey(key)==false)			/*cheia nu exista inca in bucket*/
		{	
			if( buckets[h] == null )			/*bucketul pentru hashCode nu exista*/
				buckets[h] = new ConcreteList();
			
			buckets[h].add(key);						/*adaug cheia*/
			n = buckets[h].getFirstEqual( key );		/*	nodul ce contine cheia */
			/*lista de valori pentru cheie */
			AbstractList al = ( (ValueK)n.getValue() ).getListValue();	
			al.add(value);					/*	adaugare valoare in lista de valori a cheii primite*/
			return true;					
		}
			/*	cheia exista in bucket	*/
		
			nkey = buckets[h].getFirstEqual( key ); 			/*	nodul ce contine cheia	*/
			l = ( (ValueK)nkey.getValue() ).getListValue();		/*	lista de valori pentru cheie */
			nval = l.getSortedPosition( value );				/*	nodul cu valoare >= value	*/
			
			if( nval != null )									/*exista valori >= value*/
				{	
					nr = ( (ValueV) nval.getValue() ).getAp();	/*	nr aparitii pentru value */
					s = ( (ValueV) nval.getValue() ).getValue();/*	valoarea (String-ul)*/
					
				/*	daca valoarea gasita e egala cu cea primita ca parametru
				 * 	se incrementeaza nr de aparitii
				 * 	altfel se adauga, inainte de valoarea gasita, valoarea primita ca parametru
				 */
					if(s.compareTo( ( (ValueV)value ).getValue()) ==0 )
							((ValueV)nval.getValue()).setValue(s, nr+1);
					else l.insertBefore(nval, value);
					return false;
				}
			
			l.add(value);	/*	se adauga la sfarsitul listei de valori 
			 				*	valoarea primita ca parametru
			 				*/
		return false;				
}

	@Override
	public int remove(Key key, Value value) {
		int nr=0;

		if( containsKey(key) == false )		/*	cheia nu exista in bucket	*/
			return 0;
		
		AbstractList lv = get(key);			/*	lista de valori pentru cheie */
		Node n= lv.getFirstEqual(value);	/*	nodul cu aceeasi valoare ca value*/
		if( n != null )
			{	lv.remove(n);				/*	stergere nod	*/
				nr=( (ValueV) n.getValue() ).getAp();	/* nr de aparitii valoare */
				
				/*	daca nodul gasit este singurul din lista
				 *  se sterge si cheia din bucket
				 */
				if( n.getPrev() == null && n.getNext() == null )
					remove(key);
			}

		return nr;
	}

	@Override
	public AbstractList remove(Key key) {

		if( containsKey(key) == false )
			return null;
		
		AbstractList lv = get(key);				/*	lista valori pentru cheie*/
		int h = key.hashCode();					/*	codul pentru cheie*/
		Node n = buckets[h].getFirstEqual(key); /*	nodul ce contine cheia*/
		if( n != null)
				buckets[h].remove(n);			/*	stergere nod ce contine cheia*/
		
		return lv;								/*	returneaza lista valori*/
	}
	
	public HashMap resize(int n){
		HashMap m=new HashMap(n+1);				/* creare HashMap nou de dimensiune n+1
		 										 * copiere referinte vector
		 										 */
		for(int i = 0; i < buckets.length; i++){
			m.buckets[i]=buckets[i];
		}
		return m;
	}
	
}
