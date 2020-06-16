
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class BinaryTree {
    Node root;
    int insert_counter = 0;
    int delete_counter = 0;
    int find_counter   = 0;
    int min_counter    = 0;
    int max_counter    = 0;
    int inorder_counter= 0;
    int size = 0;
    int max_size = size;

    private Node insertRecursive(Node current, String value) {

        if (current == null) {
            return new Node(value);
        }

        if (value.compareTo(current.value) < 0) {
            current.left = insertRecursive(current.left, value);
        } else if (value.compareTo(current.value) > 0) {
            current.right = insertRecursive(current.right, value);
        } else {
            return current;
        }

        return current;
    }

    public void insert(String value){
        String helper = "";
        char[] chars = value.toCharArray();
        for (char c : chars){
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
            helper+=c;
        }
        root = insertRecursive(root, helper);
        insert_counter++;
        size++;
        if (size > max_size){
            max_size = size;
        }
    }

    private int containsNodeRecursive(Node current, String value) {
        if (current == null) {
            return 0;
        }
        if (value.equals(current.value)) {
            return 1;
        }
        return value.compareTo(current.value) < 0
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public int find(String value) {
        find_counter++;
        return containsNodeRecursive(root, value);
    }

    private Node deleteRecursive(Node current, String value) {
        if (current == null) {
            return null;
        }

        if (value.equals(current.value)) {
            if (current.left == null && current.right == null) {
                return null;
            }
            else if (current.right == null) {
                return current.left;
            }
            else if (current.left == null) {
                return current.right;
            }
            else{
                String smallestValue = min(current.right).value;
                current.value = smallestValue;
                current.right = deleteRecursive(current.right, smallestValue);
                return current;
            }

        }
        if (value.compareTo(current.value) < 0) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    public void delete(String value) {
        delete_counter++;
        size--;
        root = deleteRecursive(root, value);
    }

    public Node min(Node root) {
        min_counter++;
        return root.left == null ? root : min(root.left);
    }

    public Node max(Node root){
        max_counter++;
        return root.right == null ? root : max(root.right);
    }

    private void inorderRecursive(Node node){
        if(node != null){
            inorderRecursive(node.left);
            System.out.print(node.value + " ");
            inorderRecursive(node.right);
        }
    }

    public void inorder(Node root){
        inorder_counter++;
        inorderRecursive(root);
        System.out.println("");
    }

    public Node treeSuccessor(Node root, Node succ, String value){

        if ( root == null){
            return null;
        }

        if (root.value.equals(value)){
            if (root.right != null){
                return min(root.right);
            }
        }else if (value.compareToIgnoreCase(root.value) < 0){
            succ = root;
            return treeSuccessor(root.left, succ, value);
        }else{
            return treeSuccessor(root.right, succ, value);
        }

        return succ;
    }

    public void load(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> a = new ArrayList<>();
        while (scanner.hasNext()){
            a.add(scanner.next());
        }
        for (String s : a){
            insert(s);
        }
    }
}
