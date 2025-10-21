public class MyArrayList<T> implements List<T> {
    
    protected class ListIterator<U> implements Iterator<U> {
        protected int pos = -1;

        @Override
        public boolean hasNext() {
            return (pos < size-1);
        }
        
        @Override
        public U next() {
            return (U) arr[++pos];
        }
    }
    
    T[] arr;
    int size;

    public MyArrayList(){
        size = 0;
        arr = (T[]) new Object[10];
    }

    protected void grow_array() {
        T[] new_arr = (T[]) new Object[arr.length * 2];
        System.arraycopy(arr, 0, new_arr, 0, arr.length);
        arr = new_arr;
    }

    @Override
    public boolean add(T element){
        if(size == arr.length-1){
            grow_array();
        } 

        arr[size++] = element;
        return true;
    }

    @Override
    public void add(int index, T element) throws Exception{
        if(index < 0 || index > size){
            throw new Exception("List index out of bounds");
        }

        if(size == arr.length - 1){
            grow_array();
        }

        for(int i = size; i > index; i--) {
            arr[i] = arr[i-1];
        }

        arr[index] = element;
        size++;
    }

    @Override 
    public T get(int index) throws Exception {
        if(index < 0 || index >= size){
            throw new Exception("List index out of bounds");
        }
        return arr[index];
    }

    @Override
    public T remove(int index) throws Exception {
        if(index < 0 || index >= size){
            throw new Exception("List index out of bounds");
        }
        T copy = arr[index];
        for(int i = index; i < size-1; i++){
            arr[i] = arr[i+1];
        }
        size--;
        return copy;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public Iterator<T> iterator(){
        return new ListIterator<>();
    }
}
