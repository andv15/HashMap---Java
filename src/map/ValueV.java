package map;

/*	Clasa cu valori pentru lista de valori
 *  contine un sir de caractere si un numar de aparitii
 */
public class ValueV implements Value{
	private String s;
	private int n=0;
	
	
	public ValueV(Object s, int n) {
		this.s=new String((String)s);
		this.n=n;
	}

	@Override
	public int compareTo(Value o) {
		return this.s.compareTo(( (ValueV) o ).getValue());
	}
	
	@Override
	public boolean equals(Object o) {
		if( this.s.compareTo(( (ValueV) o ).getValue() ) == 0 )
				return true;
		return false;
	}
	
	public void setValue(String s, int n){
		this.s=s;
		this.n=n;
	}
	
	public String getValue(){
		return s;
	}
	
	public int getAp(){
		return n;
	}
}
