class BSTDictionary<T, K extends Sortable> {
    private BSTNode<T, K> head = null;

    void insert(K sortableString, T entry) {
        if (head == null) {
            head = new BSTNode<>(sortableString, entry, null, null);
        } else {
            boolean c = true;
            BSTNode<T, K> node = head;
            while (c) {
                if (node.getKey().compareTo(sortableString) < 0) {
                    if (node.getLeft() == null) {
                        node.setLeft(new BSTNode<>(sortableString, entry, null, null));
                        c = false;
                    }
                } else {
                    if (node.getRight() == null) {
                        node.setRight(new BSTNode<>(sortableString, entry, null, null));
                        c = false;
                    }
                }
            }
        }
    }

    void printTree() {

    }

    K depth() {
        return null;
    }

    void delete(K sortableString) {
        BSTNode<T, K> node = head;
        BSTNode<T, K> preNode = null;
        boolean isLeft = false;
        while (node != null) {
            Sortable key = node.getKey();
            if (key.compareTo(sortableString) == 0) {
                if (node.getLeft() == null && node.getRight() == null) {
                    if (isLeft) {
                        preNode.setLeft(null);
                    } else if (preNode != null) {
                        preNode.setRight(null);
                    } else {
                        head = null;
                    }
                } else if (node.getLeft() == null && node.getRight() != null) {
                    if (isLeft) {
                        preNode.setLeft(node.getRight());
                    } else if (preNode != null) {
                        preNode.setRight(node.getRight());
                    } else {
                        head = node.getRight();
                    }
                } else if (node.getRight() == null && node.getLeft() != null) {
                    if (isLeft) {
                        preNode.setLeft(node.getLeft());
                    } else if (preNode != null) {
                        preNode.setRight(node.getLeft());
                    } else {
                        head = node.getLeft();
                    }
                } else {
                    BSTNode<T, K> successorNode = node.getRight();
                    BSTNode<T, K> preSuccessorNode = node;
                    if (successorNode.getLeft() == null) {
                        successorNode.setLeft(node.getLeft());
                        if (isLeft) {
                            preNode.setLeft(successorNode);
                        } else if (preNode != null) {
                            preNode.setRight(successorNode);
                        } else {
                            head = successorNode;
                        }
                    } else {
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
                    }
                }
            } else if (key.compareTo(sortableString) < 0) {
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

    BSTNode<T, K> search(K sortableString) {
        BSTNode<T, K> node = head;
        while (node != null) {
            Sortable key = node.getKey();
            if (key.compareTo(sortableString) == 0) {
                return node;
            } else if (key.compareTo(sortableString) < 0) {
                node = node.getLeft();
            } else if (key.compareTo(sortableString) > 0) {
                node = node.getRight();
            }
        }
        return null;
    }
}
