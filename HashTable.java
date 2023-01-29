//author: Joel Deville
//Hashtable implementation using parallel arrays and linear probing to solve collisions

import java.util.Arrays;

public class HashTable<K, V> {
    //parallel arrays linking key to value
    private K[] keys;
    private V[] values;
    //arrays are resized and values are reassigned into new indices if possible
    private double loadFactor;
    private int size;
    private static final int DEFAULT_SIZE = 10;
    private static final double DEFAULT_LOAD_LIMIT = 0.75;

    public HashTable() {
        this(DEFAULT_LOAD_LIMIT);
    }

    //pre: 0 < loadFactor < 1
    @SuppressWarnings("unchecked")
    public HashTable(double loadFactor) {
        //checking precon
        if (loadFactor <= 0 || loadFactor >= 1) {
            throw new IllegalArgumentException("Load factor must be between 0 and 1");
        }

        this.loadFactor = loadFactor;
        keys = (K[]) new Object[DEFAULT_SIZE];
        values = (V[]) new Object[DEFAULT_SIZE];
    }

    //pre: key != null
    //post: returns related value according to key (null if key not found)
    public V get(K key) {
        //checking precon
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        //calculating index
        int index = Math.abs(key.hashCode()) % keys.length;
        
        //while we have not found the key and have not hit null yet, keep looking
        boolean nullHit = false;
        while(!nullHit && !key.equals(keys[index])) {
            if (keys[index] == null) {
                nullHit = true;
            }
            
            //handling wrapping to front of array
            index++;
            if (index == keys.length) {
                index = 0;
            }
        }

        //return null only if null value was hit (meaning key was not present)
        return (nullHit) ? null : values[index];
    }

    //pre: key != null
    //post: (key, value) mapping is added to this table
    public void put(K key, V value) {
        //checking precon
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        //checking load limit
        if ((double) size / keys.length >= loadFactor) {
            resize();
        }

        //calculating index
        int index = Math.abs(key.hashCode()) % keys.length;
        boolean found = false;
        while(!found && keys[index] != null) {
            //this key is already present in this hash table
            if (keys[index].equals(key)) {
                found = true;
                values[index] = value;
            }
            index++;

            if (index == keys.length) {
                index = 0;
            }
        }
        
        //sets the key and value pair in parallel arrays if not found
        if (!found) {
            keys[index] = key;
            values[index] = value;
            size++;
        }
    }

    //post: returns number of elements in this hash table
    public int size() {
        return size;
    }

    //pre: key != null
    //post: removes associated key, returns null if not found or the value associated
    public V remove(K key) {
        //checking precon
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = Math.abs(key.hashCode()) % keys.length;
        boolean nullHit = false;
        while (!nullHit && !key.equals(keys[index])) {
            if (keys[index] == null) {
                //means element was never in the table
                nullHit = true;
            }

            index++;
            if (index == keys.length) {
                index = 0;
            }
        }

        V removedVal = null;
        if (!nullHit) {
            //found our target!
            keys[index] = null;
            removedVal = values[index];
            values[index] = null;
            size--;
        }

        //returns null if not found or value related to key removed
        return removedVal;
    }

    //post: keys and values have been resized and hashes recalculated
    private void resize() {
        keys = Arrays.copyOf(keys, keys.length * 2 + 1);
        values = Arrays.copyOf(values, values.length * 2 + 1);

        //recalculating hashes for each item
        int oldSize = (keys.length - 1) / 2;
        for (int i = 0; i < oldSize; i++) {
            K key  = keys[i];
            V value = values[i];
            if (key != null) {
                //null out references and reuse put method
                keys[i] = null;
                values[i] = null;
                put(key, value);
            }
        }
    }
}