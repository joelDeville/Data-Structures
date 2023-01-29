//author: Joel Deville
//variable size list of elements using floating indices

import java.util.Arrays;

public class VariableList<T> {
    //container of elements
    private T[] con;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public VariableList() {
        this(DEFAULT_SIZE);
    }

    //pre: initialCap > 0
    @SuppressWarnings("unchecked")
    public VariableList(int initialCap) {
        //checking precon
        if (initialCap <= 0) {
            throw new IllegalArgumentException("Invalid initial capacity");
        }

        con = (T[]) new Object[initialCap];
    }

    //pre: 0 <= ind < size
    //post: returns the value at the index
    public T get(int ind) {
        //checking precon
        if (ind < 0 || ind >= size) {
            throw new IllegalArgumentException("Invalid index for retrieval");
        }

        return con[ind];
    }

    //pre: val != null
    //post: val is added at the end of this list
    public void add(T val) {
        //checking precon
        if (val == null) {
            throw new IllegalArgumentException("Invalid null argument");
        }

        //checking for need to resize
        if (size == con.length) {
            resize();
        }

        con[size] = val;
        size++;
    }

    //pre: 0 <= index < size
    //post: removes element at specified index, returns value
    public T remove(int index) {
        //checking precon
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index");
        }

        T val = con[index];
        con[index] = null;
        size--;

        for (int i = index; i < size; i++) {
            con[i] = con[i + 1];
        }

        return val;
    }

    //pre: tgt != null
    //post: remove element if found in list, false if not found
    public boolean remove(T tgt) {
        //checking precon
        if (tgt == null) {
            throw new IllegalArgumentException("Invalid null argument");
        }

        boolean found = false;
        int ind = 0;
        //while element is not found and have not reached end of list
        while (!found && ind < size) {
            if (tgt.equals(con[ind])) {
                found = true;
                con[ind] = null;
                size--;
            } else {
                ind++;
            }
        }

        //shift elements down since it was removed
        if (found) {
            for (int i = ind; i < size; i++) {
                con[i] = con[i + 1];
            }
        }

        return found;
    }

    //post: returns the size of this list
    public int size() {
        return size;
    }

    //post: array is resized to accommodate more elements
    private void resize() {
        con = Arrays.copyOf(con, con.length * 2 + 1);
    }
}