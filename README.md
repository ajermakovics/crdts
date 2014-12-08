CRDTs 
================

Simple Conflict-free Replicated Data Types (CRDTs) for Java without external dependencies.

The folloinw CRDTs are implemented:
 - G-Counter - Grow only counter
 - PN-Counter - Increment/decrement counter 
 - G-Set - Grow only set
 - 2P-Set - Set that allows adds and removes but an item can be removed only once


Serialization
===========

All CRDT classes implement Java Serializable

Requirements
============

Java 1.6


License
=======

MIT
