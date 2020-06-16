
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RedBlackTree<T extends Comparable<T>> {

    int insert_counter = 0;
    int delete_counter = 0;
    int find_counter   = 0;
    int min_counter    = 0;
    int max_counter    = 0;
    int inorder_counter= 0;
    int size =0;
    int max_size = size;

    // Root initialized to nil.
    private RedBlackNode<T> nil = new RedBlackNode<T>();
    public RedBlackNode<T> root = nil;

    public RedBlackTree() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

    public void insert(T value) {
        String helper = "";
        char[] chars = value.toString().toCharArray();
        for (char c : chars){
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                helper+=c;
        }
        insert(new RedBlackNode<T>((T) helper));
        insert_counter++;
        size++;
        if (size > max_size){
            max_size=size;
        }
    }

    private void insert(RedBlackNode<T> z){
        RedBlackNode<T> y = nil;
        RedBlackNode<T> x = root;

        while (!isNil(x)){
            y = x;

            if (z.val.compareTo(x.val) < 0){
                x.numLeft++;
                x = x.left;
            }
            else{
                x.numRight++;
                x = x.right;
            }
        }

        z.parent = y;

        if (isNil(y)){
            root = z;
        }
        else if (z.val.compareTo(y.val) < 0){
            y.left = z;
        }
        else{
            y.right = z;
        }

        z.left = nil;
        z.right = nil;
        z.color = RedBlackNode.RED;

        FixInsert(z);
    }

    private void FixInsert(RedBlackNode<T> n){
       RedBlackNode<T> y = nil;

       while (n.parent.color == RedBlackNode.RED){

           if (n.parent == n.parent.parent.left){

               y = n.parent.parent.right;

               if (y.color == RedBlackNode.RED){
                   n.parent.color = RedBlackNode.BLACK;
                   y.color = RedBlackNode.BLACK;
                   n.parent.parent.color = RedBlackNode.RED;
                   n = n.parent.parent;
               }
               else if (n ==n.parent.right){
                   n = n.parent;
                   leftRotate(n);
               }

               else{
                   n.parent.color = RedBlackNode.BLACK;
                   n.parent.parent.color = RedBlackNode.RED;
                   rightRotate(n.parent.parent);
               }
           }
           else{

               y = n.parent.parent.left;


               if (y.color == RedBlackNode.RED){
                   n.parent.color = RedBlackNode.BLACK;
                   y.color = RedBlackNode.BLACK;
                   n.parent.parent.color = RedBlackNode.RED;
                   n = n.parent.parent;
               }

               // Case 2: if y is black and z is a left child
               else if (n == n.parent.left){
                   // rightRotate around z's parent
                   n = n.parent;
                   rightRotate(n);
               }
               // Case 3: if y  is black and z is a right child
               else{
                   // recolor and rotate around z's grandpa
                   n.parent.color = RedBlackNode.BLACK;
                   n.parent.parent.color = RedBlackNode.RED;
                   leftRotate(n.parent.parent);
               }
           }
       }
       root.color = RedBlackNode.BLACK;
    }

    private boolean isNil(RedBlackNode node){
        return node == nil;
    }

    private void leftRotate(RedBlackNode<T> x){

        // leftRotateFixup() updates numLeft and numRigth
        leftRotateFixup(x);

        RedBlackNode<T> y;
        y = x.right;
        x.right = y.left;


        if (!isNil(y.left))
            y.left.parent = x;
        y.parent = x.parent;


        if (isNil(x.parent))
            root = y;

        else if (x.parent.left == x)
            x.parent.left = y;


        else
            x.parent.right = y;


        y.left = x;
        x.parent = y;
    }

    private void leftRotateFixup(RedBlackNode x){

        // Case 1: Only x, x.right and x.right.right always are not nil.
        if (isNil(x.left) && isNil(x.right.left)){
            x.numLeft = 0;
            x.numRight = 0;
            x.right.numLeft = 1;
        }

        // Case 2: x.right.left also exists in addition to Case 1
        else if (isNil(x.left) && !isNil(x.right.left)){
            x.numLeft = 0;
            x.numRight = 1 + x.right.left.numLeft +
                    x.right.left.numRight;
            x.right.numLeft = 2 + x.right.left.numLeft +
                    x.right.left.numRight;
        }

        // Case 3: x.left also exists in addition to Case 1
        else if (!isNil(x.left) && isNil(x.right.left)){
            x.numRight = 0;
            x.right.numLeft = 2 + x.left.numLeft + x.left.numRight;

        }
        // Case 4: x.left and x.right.left both exist in addtion to Case 1
        else{
            x.numRight = 1 + x.right.left.numLeft +
                    x.right.left.numRight;
            x.right.numLeft = 3 + x.left.numLeft + x.left.numRight +
                    x.right.left.numLeft + x.right.left.numRight;
        }

    }

    private void rightRotate(RedBlackNode<T> y){


        rightRotateFixup(y);

        RedBlackNode<T> x = y.left;
        y.left = x.right;

        if (!isNil(x.right)) {
            x.right.parent = y;
        }
        x.parent = y.parent;

        if (isNil(y.parent)) {
            root = x;
        }

        else if (y.parent.right == y) {
            y.parent.right = x;
        }

        else {
            y.parent.left = x;
        }
        x.right = y;

        y.parent = x;

    }

    private void rightRotateFixup(RedBlackNode y){

        // Case 1: Only y, y.left and y.left.left exists.
        if (isNil(y.right) && isNil(y.left.right)){
            y.numRight = 0;
            y.numLeft = 0;
            y.left.numRight = 1;
        }

        // Case 2: y.left.right also exists in addition to Case 1
        else if (isNil(y.right) && !isNil(y.left.right)){
            y.numRight = 0;
            y.numLeft = 1 + y.left.right.numRight +
                    y.left.right.numLeft;
            y.left.numRight = 2 + y.left.right.numRight +
                    y.left.right.numLeft;
        }

        // Case 3: y.right also exists in addition to Case 1
        else if (!isNil(y.right) && isNil(y.left.right)){
            y.numLeft = 0;
            y.left.numRight = 2 + y.right.numRight +y.right.numLeft;

        }

        // Case 4: y.right & y.left.right exist in addition to Case 1
        else{
            y.numLeft = 1 + y.left.right.numRight +
                    y.left.right.numLeft;
            y.left.numRight = 3 + y.right.numRight +
                    y.right.numLeft +
                    y.left.right.numRight + y.left.right.numLeft;
        }

    }

    private int containsNodeRecursive(RedBlackNode<T> current, T value) {
        if (current == nil) {
            return 0;
        }
        if (value.equals(current.val)) {
            return 1;
        }
        return value.compareTo(current.val) < 0
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public int find(T value){
        find_counter++;
        return containsNodeRecursive(root,value);
    }

    public RedBlackNode<T> getNode(T value){

        RedBlackNode<T> current = root;

        // While we haven't reached the end of the tree
        while (!isNil(current)){

            // If we have found a node with a key equal to key
            if (current.val.equals(value))
                // return that node and exit getNode()
                return current;

                // go left or right based on value
            else if (current.val.compareTo(value) < 0)
                current = current.right;

            else
                current = current.left;
        }
        // If we cannot find node with our value return null
        return null;
    }

    public RedBlackNode<T> min(RedBlackNode<T> node){
        min_counter++;
        while (!isNil(node.left))
            node = node.left;
        return node;
    }

    public RedBlackNode<T> max(RedBlackNode<T> node){
        max_counter++;
        while (!isNil(node.right)){
            node = node.right;
        }
        return node;
    }

    public void inorder(RedBlackNode<T> node){
        if (!isNil(node)){
            inorder(node.left);
            System.out.print(node.val+" ");
            inorder(node.right);
        }
    }

    public void load(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> a = new ArrayList<>();
        while (scanner.hasNext()){
            a.add(scanner.next());
        }
        for (String s : a){
            insert((T) s);
        }
    }

    public T treeSuccessor(RedBlackNode<T> x){

        if (x==null){
            T s = (T) " ";
            return s;
        }

        if (!isNil(x.left)) {
            if (min(x.right).val == null){
                T s = (T) " ";
                return s;
            }
            return min(x.right).val;
        }

        RedBlackNode<T> succ = x.parent;


        while (!isNil(succ) && x == succ.right){

            x = succ;
            succ = succ.parent;
        }

        return succ.val;
    }

    public void delete(RedBlackNode<T> to_remove){

        delete_counter++;
        size--;
        RedBlackNode<T> z = getNode(to_remove.val);


        RedBlackNode<T> x = nil;
        RedBlackNode<T> y = nil;

        // if either one of z's children is nil, then we must remove z
        if (isNil(z.left) || isNil(z.right))
            y = z;

            // else we must remove the successor of z
        else y = getNode(treeSuccessor(to_remove));


        if (!isNil(y.left))
            x = y.left;
        else
            x = y.right;


        x.parent = y.parent;


        if (isNil(y.parent))
            root = x;

        else if (!isNil(y.parent.left) && y.parent.left == y)
            y.parent.left = x;

        else if (!isNil(y.parent.right) && y.parent.right == y)
            y.parent.right = x;

        // if y != z, trasfer y's satellite data into z.
        if (y != z){
            z.val = y.val;
        }

        // Update the numLeft and numRight numbers which might need
        // updating due to the deletion of z.val
        fixNodeData(x,y);

        // If y's color is black, it is a violation of the
        // RedBlackTree properties so call removeFixup()
        if (y.color == RedBlackNode.BLACK)
            removeFixup(x);
    }

    private void fixNodeData(RedBlackNode<T> x, RedBlackNode<T> y){

        // Initialize two variables which will help us traverse the tree
        RedBlackNode<T> current = nil;
        RedBlackNode<T> track = nil;


        // if x is nil, then we will start updating at y.parent
        // Set track to y, y.parent's child
        if (isNil(x)){
            current = y.parent;
            track = y;
        }

        else{
            current = x.parent;
            track = x;
        }

        while (!isNil(current)){
            // if the node we deleted has a different value than
            // the current node
            if (y.val != current.val) {

                // if the node we deleted is greater than
                // current.val then decrement current.numRight
                if (y.val.compareTo(current.val) > 0)
                    current.numRight--;

                // if the node we deleted is less than
                // current.val then decrement current.numLeft
                if (y.val.compareTo(current.val) < 0)
                    current.numLeft--;
            }

            else{

                if (isNil(current.left))
                    current.numLeft--;
                else if (isNil(current.right))
                    current.numRight--;

                else if (track == current.right)
                    current.numRight--;
                else if (track == current.left)
                    current.numLeft--;
            }

            // update track and current
            track = current;
            current = current.parent;

        }

    }

    private void removeFixup(RedBlackNode<T> x){

        RedBlackNode<T> uncle;

        while (x != root && x.color == RedBlackNode.BLACK){

            if (x == x.parent.left){

                uncle = x.parent.right;

                // Case 1, uncle's color is red.
                if (uncle.color == RedBlackNode.RED){
                    uncle.color = RedBlackNode.BLACK;
                    x.parent.color = RedBlackNode.RED;
                    leftRotate(x.parent);
                    uncle = x.parent.right;
                }

                // Case 2, both of uncle's children are black
                if (uncle.left.color == RedBlackNode.BLACK &&
                        uncle.right.color == RedBlackNode.BLACK){
                    uncle.color = RedBlackNode.RED;
                    x = x.parent;
                }
                // Case 3 / Case 4
                else{
                    // Case 3, uncle's right child is black
                    if (uncle.right.color == RedBlackNode.BLACK){
                        uncle.left.color = RedBlackNode.BLACK;
                        uncle.color = RedBlackNode.RED;
                        rightRotate(uncle);
                        uncle = x.parent.right;
                    }
                    // Case 4, uncle = black, uncle.right = red
                    uncle.color = x.parent.color;
                    x.parent.color = RedBlackNode.BLACK;
                    uncle.right.color = RedBlackNode.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            }

            else{

                uncle = x.parent.left;

                if (uncle.color == RedBlackNode.RED){
                    uncle.color = RedBlackNode.BLACK;
                    x.parent.color = RedBlackNode.RED;
                    rightRotate(x.parent);
                    uncle = x.parent.left;
                }

                if (uncle.right.color == RedBlackNode.BLACK &&
                        uncle.left.color == RedBlackNode.BLACK){
                    uncle.color = RedBlackNode.RED;
                    x = x.parent;
                }

                else{

                    if (uncle.left.color == RedBlackNode.BLACK){
                        uncle.right.color = RedBlackNode.BLACK;
                        uncle.color = RedBlackNode.RED;
                        leftRotate(uncle);
                        uncle = x.parent.left;
                    }

                    uncle.color = x.parent.color;
                    x.parent.color = RedBlackNode.BLACK;
                    uncle.left.color = RedBlackNode.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }

        // set x to black to ensure there is no violation of
        // RedBlack tree Properties
        x.color = RedBlackNode.BLACK;
    }
}
