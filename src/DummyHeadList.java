public class DummyHeadList<T> implements List<T> {
    /**
     * Node class for singly linked list with dummy head
     * Each node has data and next pointer
     */
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

    /**
     * Adding element at the end of the list
     */
    @Override
    public boolean add(T element) {
        // iterating to the end (skipping the dummy head) and adding new node
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

    /**
     * Inserting element at specific index in the list
     */
    @Override
    public void add(int index, T element) throws Exception {
        if(index < 0 || index >= size){ // bounds check
            throw new Exception("List index out of bounds");
        }
        // else iterating to the position and setting the links
        Node node = new Node(element);
        Node prev = dummyHead.next;
        for(int i = 0; i < index-1; i++){
            prev = prev.next;
        }
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    /**
     * Removing element at specific index in the list
     */
    @Override
    public T remove(int index) throws Exception {
        if(index < 0 || index >= size){ // bounds check
            throw new Exception("List index out of bounds");
        }
        // iterating to the position, adjusting links, returning data
        Node prev = dummyHead.next;
        for(int i = 0; i < index-1; i++){
            prev = prev.next;
        }
        Node<T> node = prev.next;
        prev.next = node.next;
        size--;
        return node.data;
    }

    /**
     * Getting element at specific index in the list
     */
    @Override
    public T get(int index) throws Exception {
        if(index < 0 || index >= size){ // bounds check
            throw new Exception("List index out of bounds");
        }
        // else iterating to the index and returning data
        Node<T> current = dummyHead.next;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }

    /**
     * Getting size of the list
     */
    @Override
    public int size(){
        return size;
    }
}
