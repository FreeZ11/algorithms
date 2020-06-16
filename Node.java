
public class Node {
    String value;
    Node parent;
    Node left;
    Node right;

    Node(String value) {
        this.value = value;
        parent     = null;
        right      = null;
        left       = null;
    }
}
