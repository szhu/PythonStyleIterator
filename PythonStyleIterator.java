import java.util.Iterator;


abstract public class PythonStyleIterator<T> implements Iterator<T>, Iterable<T> {
    private T cache;
    private boolean cachedHasNext;
    private boolean isCached = false;

    abstract public T tryNext() throws StopIteration;

    @Override
    public final boolean hasNext() {
        advanceCache();
        return cachedHasNext;
    }

    @Override
    public final T next() {
        advanceCache();
        isCached = false;
        return cache;
    }

    public final T peek() {
        advanceCache();
        return cache;
    }

    /**
     * Allows subclass to indicate that there are no more items.
     *
     * Can be used in tryNext();
     */
    protected final void stopIteration() throws StopIteration {
        throw new StopIteration();
    }

    /**
     * Allows subclass to set a next item if it is already known.
     *
     * Can be used in constructor and tryNext();
     */
    protected void setCache(T item) {
        if (isCached) throw new IllegalStateException();
        cache = item;
        cachedHasNext = true;
        isCached = true;
    }

    private void advanceCache() {
        if (isCached) return;
        try {
            cache = tryNext();
            cachedHasNext = true;
        }
        catch (StopIteration e) {
            cache = null;
            cachedHasNext = false;
        }
        isCached = true;
    }

    /**
     * Allows the iterator to double as an Iterable.
     */
    @Override
    public Iterator<T> iterator() {
        return this;
    }

    public static class StopIteration extends Exception {}
}
