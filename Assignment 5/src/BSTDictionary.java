public class BSTDictionary<T, K  extends Sortable> {
    private BSTNode<T,K> head = null;
    public void insert(K sortableString, T entry) {
        if(head == null){
            head = new BSTNode<>(sortableString, entry, null,null);
        }else{
            boolean c = true;
            BSTNode<T,K> node = head;
            while (c){
                if(node.getKey().compareTo(sortableString) < 0){
                    if (node.getLeft() == null){
                        node.setLeft(new BSTNode<>(sortableString, entry, null,null));
                        c = false;
                    }
                }else{
                    if (node.getRight() == null){
                        node.setRight(new BSTNode<>(sortableString, entry, null,null));
                        c = false;
                    }
                }
            }
        }
    }

    public void printTree() {

    }

    public K depth() {
        return null;
    }

    public void delete(K sortableString) {
        BSTNode<T,K> node = head;
        BSTNode<T,K> preNode = null;
        boolean isLeft = false;
        while (node != null){
            Sortable key = node.getKey();
            if(key.compareTo(sortableString) == 0){
                if(preNode == null){
                    if(head.getRight() == null){
                        head = head.getLeft();
                    }else{
                        BSTNode<T,K> nextNode;
                        if(node.getLeft() == null){
                            nextNode = node;
                            nextNode.setLeft(head.getLeft());
                            head = nextNode;
                        }else {
                            while (node.getLeft().getLeft() != null){
                                node = node.getLeft();
                            }
                            nextNode = node.getLeft();
                            node.setLeft(nextNode.getRight());
                            nextNode.setLeft(head.getLeft());
                            nextNode.setRight(head.getRight());
                            head = nextNode;
                        }
                    }
                } else {
                    if (isLeft){
                        preNode.setLeft(node.getRight());

                    }else{

                    }
                }
            }else if(key.compareTo(sortableString) < 0){
                preNode = node;
                node = node.getLeft();
                isLeft = true;
            }else if(key.compareTo(sortableString) > 0){
                preNode = node;
                node = node.getRight();
                isLeft = false;
            }
        }
        if(head.getKey().compareTo(sortableString) == 0){

        }
    }

    public BSTNode<T,K> search(K sortableString) {
        BSTNode<T,K> node = head;
        while (node != null){
            Sortable key = node.getKey();
            if(key.compareTo(sortableString) == 0){
                return node;
            }else if(key.compareTo(sortableString) < 0){
                node = node.getLeft();
            }else if(key.compareTo(sortableString) > 0){
                node = node.getRight();
            }
        }
        return null;
    }
}
