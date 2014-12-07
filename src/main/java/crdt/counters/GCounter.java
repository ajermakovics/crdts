package crdt.counters;

import static java.lang.Math.max;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GCounter implements Serializable {

	private static final long serialVersionUID = 1L;

	Map<String, Integer> counts = new HashMap<>();

    public void increment(String key) {
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

    public GCounter merge(GCounter other) {
        for(Entry<String, Integer> e: other.counts.entrySet()) {
            String key = e.getKey();
            if( counts.containsKey(key) )
                counts.put(key, max(e.getValue(), counts.get(key)) );
            else
                counts.put(key, e.getValue());
        }
        return this;
    }

    @Override
	public String toString() {
		return "GCounter{" + counts + "}";
	}
}
