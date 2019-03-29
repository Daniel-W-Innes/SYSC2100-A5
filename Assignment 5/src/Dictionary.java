interface Dictionary<E, K extends Sortable> {
    // search for an entry with key KEY and return the object
    E search(K key);

    // insert a key-value pair into the dictionary
    void insert(K key, E element);

    // delete an entry with key KEY
    void delete(K key);

    // print the Dictionary in sorted order (as determined by the keys)
    void printTree();

    // return the depth of the underlying tree
    int depth();
}
