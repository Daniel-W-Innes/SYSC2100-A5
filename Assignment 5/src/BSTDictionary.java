class BSTDictionary<T, K extends Sortable> implements Dictionary<T, K> {
    private BSTNode<T, K> head = null;

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
                } else {
                    if (node.getRight() == null) {
                        node.setRight(new BSTNode<>(sortableString, entry, null, null));
                        c = false;
                    } else {
                        node = node.getRight();
                    }
                }
            }
        }
    }

    public void printTree() {
        if (head == null) {
            System.out.println("The tree is empty");
        } else {
            printTreeR(head);
        }
    }

    private void printTreeR(BSTNode<T, K> node) {
        if (node != null) {
            printTreeR(node.getLeft());
            System.out.println("(Key = " + node.getKey().toString() + ", Element = " + node.getElement().toString() + ")");
            printTreeR(node.getRight());
        }
    }

    public int depth() {
        if (head == null) {
            return 0;
        } else {
            return depthR(head);
        }
    }

    private int depthR(BSTNode<T, K> node) {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = 1 + depthR(node.getLeft());
            int rightDepth = 1 + depthR(node.getRight());
            return (leftDepth < rightDepth) ? rightDepth : leftDepth;
        }
    }

    public void delete(K sortableString) {
        BSTNode<T, K> node = head;
        BSTNode<T, K> preNode = null;
        boolean isLeft = false;
        while (node != null) {
            Sortable key = node.getKey();
            if (key.compareTo(sortableString) == 0) {
                if (node.getLeft() == null && node.getRight() == null) {
                    if (isLeft) {
                        preNode.setLeft(null);
                        return;
                    } else if (preNode != null) {
                        preNode.setRight(null);
                        return;
                    } else {
                        head = null;
                        return;
                    }
                } else if (node.getLeft() == null && node.getRight() != null) {
                    if (isLeft) {
                        preNode.setLeft(node.getRight());
                        return;
                    } else if (preNode != null) {
                        preNode.setRight(node.getRight());
                        return;
                    } else {
                        head = node.getRight();
                        return;
                    }
                } else if (node.getRight() == null && node.getLeft() != null) {
                    if (isLeft) {
                        preNode.setLeft(node.getLeft());
                        return;
                    } else if (preNode != null) {
                        preNode.setRight(node.getLeft());
                        return;
                    } else {
                        head = node.getLeft();
                        return;
                    }
                } else {
                    BSTNode<T, K> successorNode = node.getRight();
                    BSTNode<T, K> preSuccessorNode = node;
                    if (successorNode.getLeft() == null) {
                        successorNode.setLeft(node.getLeft());
                        if (isLeft) {
                            preNode.setLeft(successorNode);
                            return;
                        } else if (preNode != null) {
                            preNode.setRight(successorNode);
                            return;
                        } else {
                            head = successorNode;
                            return;
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
                            return;
                        } else if (preNode != null) {
                            preNode.setRight(successorNode);
                            return;
                        } else {
                            head = successorNode;
                            return;
                        }
                    }
                }
            } else if (key.compareTo(sortableString) > 0) {
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
