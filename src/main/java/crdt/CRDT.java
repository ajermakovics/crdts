package crdt;

public interface CRDT<T extends CRDT<T>> {
    void merge(T other);
    T copy();
}
