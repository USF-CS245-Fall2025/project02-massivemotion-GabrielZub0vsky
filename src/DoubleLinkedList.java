public class DoubleLinkedList<T> implements List<T> {   
    /**
     * Node class for doubly linked list
     * Each node has data, next pointer and prev pointer
     */
    private class Node<V> {
        V data; 
        Node<V> next;
        Node<V> prev;

        public Node(V value){
            data = value;
            next = null;
            prev = null;
        }
    }

    int size;
    Node<T> head;
    public DoubleLinkedList(){
        size = 0;
        head = null;
    }

    /**
     * Adding element at the end of the list
     */
    @Override
    public boolean add(T element) {
         if (size == 0) { // empty list
            head = new Node(element);
            size++;
            return true;
        }
        // else iterate to the end and add the new node, adjusting pointers
        Node node = head;
        while (node.next != null){
            node = node.next;
        }
        Node newlast = new Node(element);
        newlast.next = null;
        node.next = newlast;
        newlast.prev = node; // connecting prev pointer
        size++;
        return true;
    }

    /**
     * Inserting element at specific index in the list
     */
    @Override
    public void add(int index, T element) throws Exception {
        Node node = new Node(element);
        if(index == 0){ // inserting at the head
           node.next = head;
           head = node; 
        }
        else { // iterating to the position and setting the links
            Node previous = head;
            for(int i = 0; i < index-1; i++){
                previous = previous.next;
            }
            node.next = previous.next;
            node.next.prev = node;
            node.prev = previous;
            previous.next = node;
        }
        size++;
    }

    /**
     * Getting element at specific index in the list
     */
    @Override
    public T get(int index) throws Exception {
        if(index < 0 || index > size){ // bounds check
            throw new Exception("List index out of bounds");
        }
        // iterating to the index and returning data
        Node<T> current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }

    /**
     *  Removing element at specific index in the list
     */ 
    @Override
    public T remove(int index) throws Exception {
        if(index < 0 || index >= size){ // bounds check
            throw new Exception("List index out of bounds");
        }
        
        if(index == 0){ // removing first element
            Node<T> node = head;
            head = head.next;
            head.prev = null;
            size--;
            return node.data;
        }
        // else iterating to the last position, adding node, adjusting links, returning data
        Node previous = head;
        for(int i = 0; i < index-1; i++){
            previous = previous.next;
        }
        Node<T> node = previous.next;
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        previous.next = node.next;    
        size--;
        return node.data;
    }

    /**
     * Getting size of the list
     */
    @Override
    public int size() {
        return size;
    }
}
