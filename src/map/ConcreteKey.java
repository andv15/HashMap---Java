package map;

public class ConcreteKey implements Key{
	private String key;
	
	
	public ConcreteKey() {	
		key=new String();
	}
	
	public ConcreteKey(String s) {
		key=new String(s);
	}

	@Override
	public int compareTo(Key other) {
		/*	comparare cu o cheie primita ca parametru */
		return this.key.compareTo(((ConcreteKey)other).key);
	}
	
	@Override
	public boolean equals(Object o) {
		/*	comparare egalitate cu un alt obiect */
		if( this.key.compareTo(((ConcreteKey)o).key) ==0 )
			return true;
		return false;
	}
	
	
	@Override
    public int hashCode(){
		/*	generare cod ca suma a caracterelor ascii */
		int h=0;
		char c=key.toCharArray()[0];
		{
			
				h+=(int)c-65;
		}
		return h;
    }
	
	public String getKey(){
		/*	returneaza cheia */
		return key;
	}

}
