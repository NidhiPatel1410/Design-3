// In this problem, using a hashmap for O(1) lookup and doubly linked list for the order to maintain the least recently used near
// the tail and most recently used near the head.

// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class LRUCache {
    // Declare node class
    class Node {
        // It will have key value pair
        int key, val;
        // And next and prev pointers since it is doubly linkedlist
        Node next, prev;

        // Constructor
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // Head and tail will be dummy node so that we dont have to modify our logic in
    // case of any operation on first or last node
    Node head, tail;
    // Hashmap will store key and node address
    HashMap<Integer, Node> map;
    // Declare capacity globally so that we can use everywhere
    int capacity;

    // Constructor
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        // Dummy nodes
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        // For initial empty doubly linked list
        head.next = tail;
        tail.prev = head;
    }

    // Write the logic for adding most recently used node towards the head
    private void addToHead(Node node) {
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        node.prev = head;
    }

    // Write logic for the removal of least recently used node from the tail
    private void removeFromTail(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int get(int key) {
        // Check if key not present in map, return -1
        if (!map.containsKey(key)) {
            return -1;
        }
        // Else, get the node address
        Node curr = map.get(key);
        // Remove it from tail and put it towards head since it will be most recently
        // used now
        removeFromTail(curr);
        addToHead(curr);
        // Return it's value
        return curr.val;
    }

    public void put(int key, int value) {
        // Check if key already present in map, that means we have to update it's value
        if (map.containsKey(key)) {
            // Get the node address
            Node curr = map.get(key);
            // Remove from end and put at the start since it will most recently used now
            removeFromTail(curr);
            addToHead(curr);
            // Change it's value
            curr.val = value;
            // And return
            return;
        }
        // Else, create a new node with key value
        Node curr = new Node(key, value);
        // Check if the map is already full
        if (map.size() == capacity) {
            Remove LRU node from tail
            Node tailprev = tail.prev;
            removeFromTail(tailprev);
            // Also remove from map
            map.remove(tailprev.key);
        }
        // Now we have space, so add this new node towards the head
        addToHead(curr);
        // Also put in map
        map.put(key, curr);

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */