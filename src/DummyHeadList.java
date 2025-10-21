public class DummyHeadList<T> implements List<T> {
    // iterator for Dummy Head List
    protected class ListIterator<U> implements Iterator<U> {
        private Node node = dummyHead.next;
        
        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public U next () { // Return data and advance
            Node prev = node;
            node = node.next;
            return (U) prev.data;
        }
    }
    
    private class Node<V> {
        V data; 
        Node<V> next;

        public Node(V value) {
            data = value;
            next = null;
        }
    }

    int size;
    Node<T> dummyHead;   

    public DummyHeadList() {
        size = 0;
        dummyHead = new Node<>(null);   
        // dummy head (null value)
        // real first element is dummyHead.next
    }

    @Override
    public boolean add(T element) {
        Node node = dummyHead.next;
        while (node.next != null){
            node = node.next;
        }
        Node newlast = new Node(element);
        newlast.next = null;
        node.next = newlast;
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) throws Exception {
        if(index < 0 || index >= size){
            throw new Exception("List index out of bounds");
        }

        Node node = new Node(element);
        Node prev = dummyHead.next;
        for(int i = 0; i < index-1; i++){
            prev = prev.next;
        }
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    @Override
    public T remove(int index) throws Exception {
        if(index < 0 || index >= size){
            throw new Exception("List index out of bounds");
        }

        Node prev = dummyHead.next;
        for(int i = 0; i < index-1; i++){
            prev = prev.next;
        }
        Node<T> node = prev.next;
        prev.next = node.next;
        size--;
        return node.data;
    }

    @Override
    public T get(int index) throws Exception {
        if(index < 0 || index >= size){
            throw new Exception("List index out of bounds");
        }
        
        Node<T> current = dummyHead.next;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
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
