package crdt.sets;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Two Phase Set. Items can be added and removed but only removed once.
 */
public class TwoPSet<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private GSet<T> adds = new GSet<T>();
	private GSet<T> removes = new GSet<T>();

	public void add(T item) {
		if(removes.contains(item))
			throw new IllegalArgumentException("Item was already removed");
		adds.add(item);
	}

	public void remove(T item) {
		if( adds.contains(item) )
			removes.add(item);
	}

	public Set<T> get() {
		Set<T> added = new LinkedHashSet<T>( adds.get() );
		added.removeAll( removes.get() );
		return added;
	}

	public void merge(TwoPSet<T> set) {
		adds.addAll( set.adds.get() );
		removes.addAll( set.removes.get() );
	}
}
