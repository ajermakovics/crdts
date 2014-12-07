package crdt.counters;

import static java.lang.Math.max;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Grow only counter. Can only be incremented
 */
public class GCounter<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<T, Integer> counts = new HashMap<>();

	/**
	 * Increment a given key
	 */
    public void increment(T key) {
        Integer count = counts.get(key);
        if( count == null )
            count = 0;

        counts.put(key, count + 1);
    }

    public int get() {
        int sum = 0;
        for(int count: counts.values())
            sum += count;
        return sum;
    }

    /**
     * Merge another counter into this one
     */
    public void merge(GCounter<T> other) {
        for(Entry<T, Integer> e: other.counts.entrySet()) {
            T key = e.getKey();
            if( counts.containsKey(key) )
                counts.put(key, max(e.getValue(), counts.get(key)) );
            else
                counts.put(key, e.getValue());
        }
    }

    @Override
	public String toString() {
		return "GCounter{" + counts + "}";
	}
}
