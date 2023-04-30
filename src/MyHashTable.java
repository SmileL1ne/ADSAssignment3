public class MyHashTable<K, V> {

    // public modifier needed in testing class when
    // creating 'current' node to iterate through all nodes in every bucket
    public class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }

        public HashNode<K, V> getNext() { // needed in testing class
            return next;
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    public MyHashTable(int M) {
        chainArray = new HashNode[M];
        size = 0;
    }

    private int hash(K key) { // getting bucket number based on a hash of the key and formula
        int keyHash = key.hashCode();
        int result = 1;
        result = (37 * result + keyHash) % M;
        return Math.abs(result);
    }

    public void put(K key, V value) {
        int bucket = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        // check if bucket is empty
        if (chainArray[bucket] != null) { // add newNode at the start of the bucket
            newNode.next = chainArray[bucket];
        }
        chainArray[bucket] = newNode; // if empty - assign initial element to the newNode
        size++;
    }

    public V get(K key) {
        int bucket = hash(key);
        HashNode<K, V> currentNode = chainArray[bucket];
        while (currentNode != null) { // search till finding desired node
            if (currentNode.key == key) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public V remove(K key) {
        int bucket = hash(key);
        HashNode<K, V> currentNode = chainArray[bucket];
        HashNode<K, V> previousNode = null;
        while (currentNode != null) { // search till finding desired node
            if (currentNode.key == key) { // delete and return found node
                if (previousNode == null) {
                    chainArray[bucket] = currentNode.next;
                } else {
                    previousNode.next = currentNode.next;
                }
                size--;
                return currentNode.value;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    public boolean contains(V value) { // guess it's O(N) because of checking every bucket
        HashNode<K, V> currentNode;
        for (int i = 0; i < chainArray.length; i++) { // check every bucket for the value
            currentNode = chainArray[i];
            while (currentNode != null) { // check within a bucket
                if (currentNode.value.equals(value)) {
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    public K getKey(V value) { // pretty same as 'contains' method but returning a key instead of boolean
        HashNode<K, V> currentNode;
        for (int i = 0; i < chainArray.length; i++) {
            currentNode = chainArray[i++];
            while (currentNode != null) {
                if (currentNode.value.equals(value)) {
                    return currentNode.key;
                }
                currentNode = currentNode.next;
            }
        }
        return null;
    }

    public HashNode<K, V>[] getChainArray() { // needed in testing class (don't know how to do it another way)
        return chainArray;
    }

    public int getSize() {
        return size;
    }
}
