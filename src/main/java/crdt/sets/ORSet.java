package crdt.sets;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import crdt.CRDT;

/**
 * Observed-Removed Set. Supports adding and removing items multiple times.
 * In each addition items are tagged and an add wins over a remove during merge.
 */
public class ORSet<T> implements CRDT<ORSet<T>> {

    private static final long serialVersionUID = 1L;

    private Multimap<T, UUID> observed = LinkedHashMultimap.create();
    private Multimap<T, UUID> removed = LinkedHashMultimap.create();

    public void add(T item) {
        observed.put(item, UUID.randomUUID());
    }

    public void remove(T item) {
        Collection<UUID> removedTags = observed.removeAll(item);
        removed.putAll(item, removedTags);
    }

    public Set<T> get() {
        return observed.keySet();
    }

    @Override
    public void merge(ORSet<T> other) {
        observed.putAll(other.observed);
        removed.putAll(other.removed);

        observed.removeAll(removed); // adds will stay
    }

    @Override
    public ORSet<T> copy() {
        ORSet<T> copy = new ORSet<T>();
        copy.observed = LinkedHashMultimap.create(observed);
        copy.removed = LinkedHashMultimap.create(removed);
        return copy;
    }
}
