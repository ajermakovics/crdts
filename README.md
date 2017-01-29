CRDTs 
================

Simple [Conflict-free Replicated Data Types](http://en.wikipedia.org/wiki/Conflict-free_replicated_data_type) (CRDTs) for distributed systems. CRDTs on different replicas can diverge from one another but at the end they can be safely merged providing an eventually consistent value. In other words, CRDTs have a **merge** method that is *idempotent*, *commutative* and *associative*.

The following CRDTs are currently implemented:
 - G-Counter - Grow only counter
 - PN-Counter - Increment/decrement counter 
 - G-Set - Grow only set. Allows only adds
 - 2P-Set - Two Phase Set. Allows adds and removes but an item can be removed only once
 - OR-Set- Ovserved-Removed Set. Allows multiple adds and removes. Adds win in case of a conflict during merge.

Examples
===========
PN-Counter:
```java
        PNCounter<String> replica1 = new PNCounter<>();
        replica1.increment("hostname1");
        
        PNCounter<String> replica2 = replica1.copy();

        replica1.increment("hostname2");
        replica2.decrement("hostname2");
        
        replica1.merge( replica2 ); 
        replica1.get(); // counter is 1
```
2-P Set:
```java
	TwoPSet<String> replica1 = new TwoPSet<>();

	replica1.add("a");
	replica1.add("b");
        
	TwoPSet<String> replica2 = replica1.copy();
	replica2.remove("b");
	replica2.add("c");

	replica1.merge(replica2); 
	replica1.get(); // set is {"a", "c"}
```

Download
========

Maven/Gradle dependency: https://jitpack.io/#ajermakovics/crdts


Serialization
===========

All CRDT classes implement Java Serializable but you should be able to use any other library.

Requirements
============

Java 1.6  
The only dependency is Google Guava

More info
=========
 - http://book.mixu.net/distsys/eventual.html
 - [A comprehensive study of
Convergent and Commutative Replicated Data Types](http://hal.upmc.fr/file/index/docid/555588/filename/techreport.pdf)

License
=======

MIT
