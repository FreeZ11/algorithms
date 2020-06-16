
class RedBlackNode<T extends Comparable<T>> {

    public static final int BLACK = 0;
    public static final int RED = 1;
    public T val;

    RedBlackNode<T> parent;
    RedBlackNode<T> left;
    RedBlackNode<T> right;


    // the number of elements to the left of each node
    public int numLeft = 0;
    // the number of elements to the right of each node
    public int numRight = 0;

    public int color;

    RedBlackNode(){
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }

    RedBlackNode(T val){
        this();
        this.val = val;
    }
}
