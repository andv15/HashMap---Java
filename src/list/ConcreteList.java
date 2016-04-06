package list;
import map.*;

public class ConcreteList extends AbstractList{
	/**
	 * constructor ce initializeaza lista
	 */
	public ConcreteList(){
		first=null;
		last=null;
	}
	
    /**
     * functia parcurge lista pana gaseste o valoare egala 
     * cu cea data ca parametru 
     * @param value the node searched for
     * @return the node from the list that satisfies the criteria
     */
	@Override

	public Node getFirstEqual(Object value) {
		
		for(Node n=first ; n!=null ; n=n.getNext() ){
			if( value instanceof ValueV)
				if( ( (ValueV) n.getValue() ).equals(value))
					return n;
			if( value instanceof Key)
				if(((ValueK)  n.getValue() ).getKeyValue().equals(value)){
					return n;
				}
		}
		return null;
	}

	@Override
	public Node getSortedPosition(Object value) {
		/*returneaza nodul ce contine o valoare >= ca cea trimisa ca parametru*/	

		for(Node n=first ; n!=null ; n=n.getNext() ){
			if( value instanceof ValueV)
				if( ( (ValueV) n.getValue() ).compareTo((ValueV)value) >= 0 ){
					return n;
			}
		}
		return null;	
	}

	@Override
	public void insertBefore(Node node, Object value) {
		
		Node n=new ConcreteNode(value);
		n.setPrev(node.getPrev());
		n.setNext(node);
		
		if( node.getPrev() == null ){	/* insearare la inceput */
			node.setPrev(n);
			first=n;
			return;
		}
		
		node.getPrev().setNext(n);
		node.setPrev(n);
	}

	
	@Override
	public void add(Object value) {
		Node n;
		
		if(value instanceof Key)
				n=new ConcreteNode(new ValueK((ConcreteKey)value));
		else	n=new ConcreteNode(value);
		
		if( first == null)		/*	caz lista vida	*/
		{	
			first=n;
			last=first;
			return;
		}

		n.setPrev(last);		/*	adaugare la sfarsit */
		n.setNext(null);
		last.setNext(n);
		last=n;	

	}

	@Override
	public void remove(Node node) {
		
		if( node == first && node == last ){ 	/* caz un singur nod in lista */
			first=null;
			last=null;
			node=null;
			return;
		}
		
		if( node.getPrev() == null )			/* stergere de la inceput */
		{
			node.getNext().setPrev(null);
			first=node.getNext();
			node=null;
			return;
		}
		
		if(node.getNext() == null){				/* stergere de la sfarsit */
			Node prev=node.getPrev();
			prev.setNext(null);
			last=prev;
			node=null;
			return;

		}
		node.getPrev().setNext(node.getNext());	/* stergere din interior */
		node.getNext().setPrev(node.getPrev());;
		node=null;
	}


	@Override
	public int size() {
		/*	nr de noduri din lista */
		int nr=0;
		if( first == null )
			return 0;
		for(Node n=first ; n!=null ; n=n.getNext() ){
			nr++;
		}
		return nr;
	}

	@Override
	public String toString() {
		String s=new String();
		Object val;
		
		if(first==null && last==null)	/*	lista nu are noduri */
			return "null";
		
		for(Node n=first ; n!=null ; n=n.getNext() ){	/*	lista are cel putin un nod */
			val=n.getValue();
			s+="("+((ValueV)val).getValue()+", "+((ValueV)val).getAp()+")"+" ";
		}
		return s;
	}
}
