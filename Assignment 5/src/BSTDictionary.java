/**
 * A implementation of a BST using BSTNode.
 * @author Daniel Innes 101067175
 * @param <T> The data type of the element
 * @param <K>The data type of the key
 * @see BSTNode
 */

class BSTDictionary<T, K extends Sortable> implements Dictionary<T, K> {
    private BSTNode<T, K> head = null;

    /**
     * Insert a new node into the dictionary, will override if keys are not distinct
     * @param sortableString The key for the new node
     * @param entry The element for the new node
     */
    public void insert(K sortableString, T entry) {
        if (head == null) {
            head = new BSTNode<>(sortableString, entry, null, null);
        } else {
            boolean c = true;
            BSTNode<T, K> node = head;
            while (c) {
                if (node.getKey().compareTo(sortableString) > 0) {
                    if (node.getLeft() == null) {
                        node.setLeft(new BSTNode<>(sortableString, entry, null, null));
                        c = false;
                    } else {
                        node = node.getLeft();
                    }
                } else if (node.getKey().compareTo(sortableString) < 0){
                    if (node.getRight() == null) {
                        node.setRight(new BSTNode<>(sortableString, entry, null, null));
                        c = false;
                    } else {
                        node = node.getRight();
                    }
                } else {
                    node = new BSTNode<>(sortableString, entry, null, null);
                    c = false;
                }
            }
        }
    }


    /**
     * Print the entire tree as a inorder list of nodes
     */
    public void printTree() {
        if (head == null) {
            System.out.println("The tree is empty");
        } else {
            printSudTree(head);
        }
    }

    /**
     * Print the tree from node as a inorder list of nodes
     * @param node The starting node
     */
    private void printSudTree(BSTNode<T, K> node) {
        if (node != null) {
            printSudTree(node.getLeft());
            System.out.println("(Key = " + node.getKey().toString() + ", Element = " + node.getElement().toString() + ")");
            printSudTree(node.getRight());
        }
    }

    /**
     * Find the depth of the BST
     * @return The depth
     */
    public int depth() {
        if (head == null) {
            return 0;
        } else {
            return subDepth(head);
        }
    }

    /**
     * Find the depth of the sub BST starting from node
     * @param node The starting node
     * @return The depth
     */
    private int subDepth(BSTNode<T, K> node) {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = 1 + subDepth(node.getLeft());
            int rightDepth = 1 + subDepth(node.getRight());
            return (leftDepth < rightDepth) ? rightDepth : leftDepth;
        }
    }

    /**
     * Delete the node with the key sortableString if it exists
     * @param sortableString The key of the node to delete
     */
    public void delete(K sortableString) {
        BSTNode<T, K> node = head;
        BSTNode<T, K> preNode = null;
        boolean isLeft = false;
        while (node != null) {
            if (node.getKey().compareTo(sortableString) == 0) {
                if (node.getLeft() == null && node.getRight() == null) {
                    if (isLeft) {
                        preNode.setLeft(null);
                    } else if (preNode != null) {
                        preNode.setRight(null);
                    } else {
                        head = null;
                    }
                    return;
                } else if (node.getLeft() == null && node.getRight() != null) {
                    if (isLeft) {
                        preNode.setLeft(node.getRight());
                    } else if (preNode != null) {
                        preNode.setRight(node.getRight());
                    } else {
                        head = node.getRight();
                    }
                    return;
                } else if (node.getRight() == null && node.getLeft() != null) {
                    if (isLeft) {
                        preNode.setLeft(node.getLeft());
                    } else if (preNode != null) {
                        preNode.setRight(node.getLeft());
                    } else {
                        head = node.getLeft();
                    }
                    return;
                } else {
                    BSTNode<T, K> successorNode = node.getRight();
                    if (successorNode.getLeft() == null) {
                        successorNode.setLeft(node.getLeft());
                        if (isLeft) {
                            preNode.setLeft(successorNode);
                        } else if (preNode != null) {
                            preNode.setRight(successorNode);
                        } else {
                            head = successorNode;
                        }
                        return;
                    } else {
                        BSTNode<T, K> preSuccessorNode = node;
                        while (successorNode.getLeft() != null) {
                            preSuccessorNode = successorNode;
                            successorNode = successorNode.getLeft();
                        }
                        preSuccessorNode.setLeft(successorNode.getRight());
                        successorNode.setLeft(node.getLeft());
                        successorNode.setRight(node.getRight());
                        if (isLeft) {
                            preNode.setLeft(successorNode);
                        } else if (preNode != null) {
                            preNode.setRight(successorNode);
                        } else {
                            head = successorNode;
                        }
                        return;
                    }
                }
            } else if (node.getKey().compareTo(sortableString) > 0) {
                preNode = node;
                node = node.getLeft();
                isLeft = true;
            } else {
                preNode = node;
                node = node.getRight();
                isLeft = false;
            }
        }
    }

    /**
     * Get the element for the given key if it exists
     * @param sortableString The key to find
     * @return The element of the node, null if the node dose not exist
     */
    public T search(K sortableString) {
        BSTNode<T, K> node = head;
        while (node != null) {
            if (node.getKey().compareTo(sortableString) == 0) {
                return node.getElement();
            } else if (node.getKey().compareTo(sortableString) > 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return null;
    }
}
