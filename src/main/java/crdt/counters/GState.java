package crdt.counters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Arrays.asList;
import static java.util.Collections.max;
import crdt.CRDT;

/**
 * Grow only state. State is defined with an enum and can advance to the next state with the next ordinal value.
 */
public class GState<T, S extends Enum<S>> implements CRDT<GState<T, S>> {

	private static final long serialVersionUID = 1L;
	private final EnumOrdinalComparator<S> comparator = new EnumOrdinalComparator<S>();

	private Map<T, S> state = new HashMap<T, S>();
	private List<S> states = new ArrayList<S>();
    private Class<S> clazz;

	public GState(Class<S> clazz) {
	    this.clazz = clazz;
	    states.addAll(EnumSet.allOf(clazz));
	    Collections.sort(states, comparator);
    }

	/**
	 * Advance the state for the given key
	 */
    public void increment(T key) {
        S count = state.get(key);
        int ord = count == null ? 0 : count.ordinal() + 1;
        checkState(ord < states.size(), "");

        state.put(key, states.get(ord));
    }

    /**
     * Get the current states
     */
    public Map<T, S> get() {
        return Collections.unmodifiableMap(state);
    }

    /**
     * Merge another counter into this one
     */
    @Override
    public void merge(GState<T, S> other) {
        for(Entry<T, S> e: other.state.entrySet()) {
            T key = e.getKey();
            S value = e.getValue();
            if( state.containsKey(key) )
                state.put(key, max(asList(value, state.get(key)), comparator) );
            else
                state.put(key, value);
        }
    }

    @Override
	public String toString() {
		return "GCounter{" + state + "}";
	}

    @Override
    public GState<T, S> copy() {
    	GState<T, S> copy = new GState<T, S>(clazz);
    	copy.state = new HashMap<T, S>(state);
    	return copy;
    }

    static class EnumOrdinalComparator<E extends Enum<E>> implements Comparator<Enum<E>> {

        @Override
        public int compare(Enum<E> o1, Enum<E> o2) {
            return o1.ordinal() - o2.ordinal();
        }

    }
}
