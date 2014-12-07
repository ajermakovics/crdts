package crdt.sets;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Grow only set. Items can only be added
 */
public class GSet<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<T> items = new LinkedHashSet<>();

	public void add(T item) {
		items.add(item);
	}

	public Set<T> get() {
		return Collections.unmodifiableSet(items);
	}

	/**
	 * Merge another set into this one
	 */
	public void merge(GSet<T> set) {
		items.addAll(set.items);
	}

	public boolean contains(T item) {
		return items.contains(item);
	}

	public void addAll(Collection<T> itemToAdd) {
		items.addAll(itemToAdd);
	}
}
