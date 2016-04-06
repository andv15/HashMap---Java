package map;

/**
 *
 * @author Stefan
 */
public interface Key extends Comparable<Key>{
    
    @Override
    //TODO obligatoriu de implementat propriul hash
    public int hashCode();
    
    @Override
    public boolean equals(Object other);
}
