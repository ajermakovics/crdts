package crdt;

import java.io.Serializable;

/** Interface for all implemented CRDTs **/
public interface CRDT<T extends CRDT<T>> extends Serializable {

	/** Merge another CRDT into this one **/
    void merge(T other);

    /** Create a copy of this CRDT **/
    T copy();

}
