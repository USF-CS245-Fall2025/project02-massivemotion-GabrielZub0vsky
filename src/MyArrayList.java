public class MyArrayList<T> implements List<T> {
    T[] arr;
    int size;
    public MyArrayList(){
        size = 0;
        arr = (T[]) new Object[10];
    }

    /**
     * Growing the internal array when full
     */
    protected void grow_array() {
        T[] new_arr = (T[]) new Object[arr.length * 2];
        System.arraycopy(arr, 0, new_arr, 0, arr.length);
        arr = new_arr;
    }

    /**
     * Adding element at the end of the list
     */
    @Override
    public boolean add(T element){
        // if array full, grow it
        if(size == arr.length-1){
            grow_array();
        } 
        arr[size++] = element;
        return true;
    }

    /**
     * Inserting element at specific index in the list
     */
    @Override
    public void add(int index, T element) throws Exception {
        if(index < 0 || index > size){ // bounds check
            throw new Exception("List index out of bounds");
        }
        // if array full, grow it
        if(size == arr.length - 1){ 
            grow_array();
        }
        // iterating backwards to desired index
        for(int i = size; i > index; i--) { 
            arr[i] = arr[i-1];
        }
        // inserting element at index and increasing size
        arr[index] = element;
        size++;
    }

    /**
     * Getting element at specific index in the list
     */
    @Override 
    public T get(int index) throws Exception {
        if(index < 0 || index >= size){ // bounds check
            throw new Exception("List index out of bounds");
        }
        return arr[index];
    }

    /**
     * Removing element at specific index in the list
     */
    @Override
    public T remove(int index) throws Exception {
        if(index < 0 || index >= size){ // bounds check
            throw new Exception("List index out of bounds");
        }
        // shifting elements to the left and returning removed element
        T copy = arr[index];
        for(int i = index; i < size-1; i++){
            arr[i] = arr[i+1];
        }
        size--;
        return copy;
    }

    /**
     * Getting size of the list
     */
    @Override
    public int size(){
        return size;
    }
}
