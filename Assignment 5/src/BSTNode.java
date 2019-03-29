// The "BSTNode" class.
public class BSTNode<E, K extends Sortable> {
    private K key;

    private E element;

    private BSTNode<E, K> left, right;

    BSTNode(K key, E element, BSTNode<E, K> left, BSTNode<E, K> right) {
        this.key = key;
        this.element = element;
        this.left = left;
        this.right = right;
    } // BSTNode constructor

    K getKey() {
        return key;
    } // getKey method

    E getElement() {
        return element;
    } // getElement method

    BSTNode<E, K> getLeft() {
        return left;
    } // getLeft method

    BSTNode<E, K> getRight() {
        return right;
    } // getRight method

    public void setElement(E element) {
        this.element = element;
    } // setElement method

    void setLeft(BSTNode<E, K> node) {
        left = node;
    } // setLeft method

    void setRight(BSTNode<E, K> node) {
        right = node;
    } // setRight method
} /* BSTNode class */
