package map;
import list.AbstractList;
import list.ConcreteList;

/*	Clasa cu valori pentru lista de chei
 *  contine o cheie si o lista de valori pentru cheie
 */

public class ValueK implements Value{
	private ConcreteKey key=new ConcreteKey();
	private ConcreteList v=new ConcreteList();
	
	
	public ValueK(Object o){
		/*	Creaza un element de tip ValueK	*/
		key=new ConcreteKey();
		key=(ConcreteKey)o;
		v=new ConcreteList();
	}
	
	@Override
	public int compareTo(Value o) {
		/*	Compara cheia cu cea primita ca parametru	*/
		return this.key.compareTo(((ValueK)o).getKeyValue());
	}
	
	public ConcreteKey getKeyValue(){
		return key;
	}
	
	public AbstractList getListValue(){
		return v;
	}

}
