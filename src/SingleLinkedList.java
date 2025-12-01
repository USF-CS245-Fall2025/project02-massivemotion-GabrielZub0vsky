public class SingleLinkedList<T> implements List<T> {
    /**
     * Node class for singly linked list
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
    Node<T> head;
    public SingleLinkedList() {
        size = 0;
        head = null;
    }

    /**
     * Adding element at the end of the list
     */
    @Override
    public boolean add(T element) {
        if (size == 0) { // adding to empty list
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
        else {
            // else iterating to the position, adding node, setting the links, increasing size
            Node prev = head;
            for(int i = 0; i < index-1; i++){
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
        }
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
        // if removing at head
        if(index == 0) {
            Node<T> node = head;
            head = head.next;
            size--;
            return node.data;
        }
        // else iterating to the position, adjusting links, returning data
        Node prev = head;
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
     * Getting size of the list
     */
    @Override
    public int size(){
        return size;
    }
}
