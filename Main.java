
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String structure = args[1];

        if (structure.equals("bst")) {
            double start_time = System.nanoTime();
            BinaryTree bt = new BinaryTree();

            File file = new File(args[2]);
            Scanner scanner = new Scanner(file);

            int n = scanner.nextInt();
            String[] operations = new String[n];

            for (int i = 0; i < n; i++) {
                operations[i] = scanner.next();
            }
            for (String e : operations){
                if (e.split("_")[0].equals("insert")){
                    bt.insert(e.split("_")[1]);
                }
                else if (e.split("_")[0].equals("load")){
                    File file1 = new File(e.split("_")[1]);
                    bt.load(file1);
                }
                else if (e.split("_")[0].equals("delete")){
                    bt.delete(e.split("_")[1]);
                }
                else if (e.split("_")[0].equals("find")){
                    System.out.println(bt.find(e.split("_")[1]));
                }
                else if (e.split("_")[0].equals("min")){
                    System.out.println(bt.min(bt.root).value);
                }
                else if (e.split("_")[0].equals("max")){
                    if (bt.root==null) System.out.println(" ");
                    else System.out.println(bt.max(bt.root).value);
                }
                else if (e.split("_")[0].equals("successor")){
                    System.out.println(bt.treeSuccessor(bt.root,null,e.split("_")[1]).value);
                }
                else if (e.split("_")[0].equals("inorder")){
                    bt.inorder(bt.root);
                }
            }
            double endTime = System.nanoTime();
            double timeTaken = endTime - start_time;
            System.out.println("execution time in nanosecounds: "+ timeTaken);
            System.out.println("insert operations: "+bt.insert_counter);
            System.out.println("delete operations: "+bt.delete_counter);
            System.out.println("find operations: "+bt.find_counter);
            System.out.println("min operations: "+bt.min_counter);
            System.out.println("max operations: "+bt.max_counter);
            System.out.println("inorder operations: "+bt.inorder_counter);
            System.out.println("max size: "+ bt.max_size);
            System.out.println("curr size: "+ bt.size);

        }
        else if (structure.equals("rbt")){
            double start_time = System.nanoTime();
            RedBlackTree<String> rbt = new RedBlackTree<>();
            File file = new File(args[2]);
            Scanner scanner = new Scanner(file);

            int n = scanner.nextInt();
            String[] operations = new String[n];

            for (int i = 0; i < n; i++) {
                operations[i] = scanner.next();
            }
            for (String e : operations){
                if (e.split("_")[0].equals("insert")){
                    rbt.insert(e.split("_")[1]);
                }
                else if (e.split("_")[0].equals("load")){
                    File file1 = new File(e.split("_")[1]);
                    rbt.load(file1);
                }
                else if (e.split("_")[0].equals("delete")){
                    if (rbt.find(e.split("_")[1])==1) {
                        rbt.delete(rbt.getNode(e.split("_")[1]));
                    }
                    else{
                        System.out.println("  ");
                    }
                }
                else if (e.split("_")[0].equals("find")){
                    System.out.println(rbt.find(e.split("_")[1]));
                }
                else if (e.split("_")[0].equals("min")){
                    System.out.println(rbt.min(rbt.root).val);
                }
                else if (e.split("_")[0].equals("max")){
                    System.out.println(rbt.max(rbt.root).val);
                }
                else if (e.split("_")[0].equals("successor")){
                    System.out.println(rbt.treeSuccessor(rbt.getNode(e.split("_")[1])));
                }
                else if (e.split("_")[0].equals("inorder")){
                    rbt.inorder_counter++;
                    rbt.inorder(rbt.root);
                    System.out.println("");
                }
            }
            double endTime = System.nanoTime();
            double timeTaken = endTime - start_time;

            System.out.println("execution time in nanosecounds: "+ timeTaken);
            System.out.println("insert operations: "+rbt.insert_counter);
            System.out.println("delete operations: "+rbt.delete_counter);
            System.out.println("find operations: "+rbt.find_counter);
            System.out.println("min operations: "+rbt.min_counter);
            System.out.println("max operations: "+rbt.max_counter);
            System.out.println("inorder operations: "+rbt.inorder_counter);
            System.out.println("max size: "+ rbt.max_size);
            System.out.println("curr size: "+ rbt.size);
        }
        else if (structure.equals("hmap")){
            double start_time = System.nanoTime();
            int cntr = 0;
            HashTable<Integer,String> ht = new HashTable<>();
            File file = new File(args[2]);
            Scanner scanner = new Scanner(file);

            int n = scanner.nextInt();
            String[] operations = new String[n];

            for (int i = 0; i < n; i++) {
                operations[i] = scanner.next();
            }
            for (String e : operations){
                if (e.split("_")[0].equals("insert")){
                    ht.put(cntr,e.split("_")[1]);
                    cntr++;
                }
                else if (e.split("_")[0].equals("load")){
                    File file1 = new File(e.split("_")[1]);
                    cntr = ht.load(file1,cntr);
                }
                else if (e.split("_")[0].equals("delete")){
                    if (ht.find(e.split("_")[1])==1) {
                        ht.delete(e.split("_")[1]);
                    }
                    else{
                        System.out.println("  ");
                    }
                }
                else if (e.split("_")[0].equals("find")){
                    System.out.println(ht.find(e.split("_")[1]));
                }
                else if (e.split("_")[0].equals("min")){
                    System.out.println(" ");
                }
                else if (e.split("_")[0].equals("max")){
                    System.out.println(" ");
                }
                else if (e.split("_")[0].equals("successor")){
                    System.out.println(" ");
                }
                else if (e.split("_")[0].equals("inorder")){
                    System.out.println(" ");
                }
            }
            double endTime = System.nanoTime();
            double timeTaken = endTime - start_time;
            System.out.println("execution time in nanosecounds: "+ timeTaken);
            System.out.println("insert operations: "+ht.insert_counter);
            System.out.println("delete operations: "+ht.delete_counter);
            System.out.println("find operations: "+ht.find_counter);
            System.out.println("max size: "+ ht.max_size);
            System.out.println("curr size: "+ ht.size1);

        }


    }
}
