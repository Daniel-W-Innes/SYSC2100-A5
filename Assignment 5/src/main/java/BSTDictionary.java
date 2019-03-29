import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A implementation of a BST using BSTNode.
 *
 * @param <T>    The data type of the element
 * @param <K>The data type of the key
 * @author Daniel Innes 101067175
 * @see BSTNode
 */

class BSTDictionary<T, K extends Sortable> implements Dictionary<T, K> {
    private BSTNode<T, K> head = null;
    private Gson gson = new Gson();

    /**
     * Insert a new node into the dictionary, will override if keys are not distinct
     *
     * @param sortableString The key for the new node
     * @param entry          The element for the new node
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
                } else if (node.getKey().compareTo(sortableString) < 0) {
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
     * Print the entire tree as a JSON
     */
    void printJSON() {
        System.out.println(gson.toJson(head));
    }

    /**
     * save the entire tree as a JSON
     */
    void save() {
        try {
            FileWriter fileWriter = new FileWriter("saves/save.json");
            fileWriter.write(gson.toJson(head));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * load the entire tree as a JSON
     */
    void load() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("saves/save.json")));
            Type type = new TypeToken<BSTNode<T, K >>() {}.getType();
            head = gson.fromJson(json, type);
        } catch (IOException | com.google.gson.JsonSyntaxException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void clear() {
        head = null;
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
     *
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
     *
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
     *
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
     *
     * @param sortableString The key of the node to delete
     */
    public void delete(K sortableString) {
        BSTNode<T, K> node = head;
        BSTNode<T, K> preNode = null;
        boolean isLeft = false;
        while (node != null) {
            if (node.getKey().compareTo(sortableString) == 0) {
                if (node.getLeft() == null && node.getRight() == null) {
                    deleteNote(isLeft, preNode, null);
                } else if (node.getLeft() == null && node.getRight() != null) {
                    deleteNote(isLeft, preNode, node.getRight());
                } else if (node.getRight() == null && node.getLeft() != null) {
                    deleteNote(isLeft, preNode, node.getLeft());
                } else {
                    BSTNode<T, K> successorNode = node.getRight();
                    if (successorNode.getLeft() == null) {
                        successorNode.setLeft(node.getLeft());
                        deleteNote(isLeft, preNode, successorNode);
                    } else {
                        BSTNode<T, K> preSuccessorNode = node;
                        while (successorNode.getLeft() != null) {
                            preSuccessorNode = successorNode;
                            successorNode = successorNode.getLeft();
                        }
                        preSuccessorNode.setLeft(successorNode.getRight());
                        successorNode.setLeft(node.getLeft());
                        successorNode.setRight(node.getRight());
                        deleteNote(isLeft, preNode, successorNode);
                    }
                }
                return;
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

    private void deleteNote(boolean isLeft, BSTNode<T, K> Parent, BSTNode<T, K> child) {
        if (isLeft) {
            Parent.setLeft(child);
        } else if (Parent != null) {
            Parent.setRight(child);
        } else {
            head = child;
        }
    }

    /**
     * Get the element for the given key if it exists
     *
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
