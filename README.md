PythonStyleIterator
===================

Does this sound like you?

 - You like iterators, but...
 - You don't want to write a separate `hasNext()` method because it would involve caching.
 - You want to peek at iterator outputs.
 - You really Python's iterator interface.

If so, try out `PythonStyleIterator`! Simply subclass it, and fill in the constructor (if you so desire) and the `tryNext` method. Feel free to call `setCache` and `stopIteration` to affect the iterator at any time.
