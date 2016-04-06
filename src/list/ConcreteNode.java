package list;
import map.Value;


public class ConcreteNode implements Node{
	private Node prev;
	private Node next;
	private Value val;

	/* constructor initializare	*/
	public ConcreteNode(Object o) {
		super();
		val=(Value)o;
		prev=null;
		next=null;
	}
	
	@Override
	public Node getNext() {
		if(next!=null)
			return next;
		return null;
	}

	@Override
	public Node getPrev() {
		if(prev!=null)
			return prev;
		return null;
	}

	@Override
	public void setNext(Node node) {	
		next=node;
	}

	@Override
	public void setPrev(Node node) {
		prev=node;
	}

	@Override
	public int compareTo(Node o) {
		/*	compara valorile a doua noduri */
			return val.compareTo((Value)o.getValue());
	}

	@Override
	public Object getValue() {
		return val;
	}
}
