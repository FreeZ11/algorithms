import java.util.*;

public class L3z2 {

    static int random_select_swaps = 0;
    static int random_select_comp = 0;
    static int select_swaps = 0;
    static int select_comp = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        int k = scanner.nextInt();
        int[] array_copy;

        if(args[0].equals("r")){
            for(int i = 0; i<n;i++){
                array[i]  = randomValue(randomValue(100));
            }
        }
        if(args[0].equals("p")){
            List<Integer> helper_array = new ArrayList<Integer>();
            for(int i = 1; i<=n ; i++){
                helper_array.add(i);
            }
            for(int i = 0;i<n;i++){
                Random rand = new Random();
                int r= helper_array.get(rand.nextInt(helper_array.size()));
                array[i] =r;
                helper_array.remove(Integer.valueOf(r));
            }
        }
        array_copy = array.clone();
        System.out.println("-----------------------------------------Random select-----------------------------------------");
        int result = randomized_selection(array,0,array.length-1,k-1);
        for(int elem : array){
            if(elem == result){
                System.out.print("["+elem+"] ");
            }
            else {
                System.out.print(elem+" ");
            }
        }
        System.out.print("\nTablica startowa: ");
        for(int a : array_copy) System.out.print(a+" ");
        System.out.println("\nAby znaleźć k-ty najmniejszy element wykonano: "+random_select_comp+" porównań i "+random_select_swaps+" przestawien \n");

        System.out.println("-----------------------------------------Select-----------------------------------------");
        array = array_copy.clone();
        result = select(array,k-1);
        for(int elem : array){
            if(elem == result){
                System.out.print("["+elem+"] ");
            }
            else {
                System.out.print(elem+" ");
            }
        }
        System.out.print("\nTablica startowa: ");
        for(int a : array_copy) System.out.print(a+" ");
        System.out.println("\nAby znaleźć k-ty najmniejszy element wykonano: "+select_comp+" porównań i "+select_swaps+" przestawien \n");

    }

    static int randomValue(int i){
        double randomDouble = Math.random();
        randomDouble = randomDouble * i + 1;
        int randomInt = (int) randomDouble;
        return randomInt;
    }

    static int select(int[] arr, int k){

        List<List<Integer>> sublists = new ArrayList<>();
        List<Integer> arr_to_list = new ArrayList<>();
        int pivot;
        for(int i : arr){
            arr_to_list.add(i);
        }

        for(int i = 0;i<arr.length;i+=5){
            if((arr.length-i)%5!=0 && i/5 >= arr_to_list.size()/5){

                sublists.add(arr_to_list.subList(i,arr.length));
            }
            else {
                sublists.add(arr_to_list.subList(i, i + 5));
            }
        }

        ArrayList<Integer>medians = new ArrayList<>();
        for(List<Integer> s: sublists){

            Collections.sort(s);
            medians.add((int) s.get(s.size()/2));
        }

        if(medians.size() <= 5) {
            Collections.sort(medians);
            pivot = medians.get(medians.size() / 2);
            System.out.println("Nowy pivot: "+pivot);
        }
        else {
            Integer[] a = medians.stream().toArray(Integer[]::new);
            int[] intA = Arrays.stream(a).mapToInt(Integer::intValue).toArray();

            pivot = select(intA, medians.size() / 2);
            System.out.println("Nowy pivot: "+pivot);
        }

        ArrayList<Integer>low = new ArrayList<>();
        ArrayList<Integer>high = new ArrayList<>();
        for(int a: arr){
            System.out.println("Compare: "+a+" z "+pivot);
            select_comp++;
            if(a < pivot){
                System.out.println("Swap: "+pivot+" z "+a);
                select_swaps++;
                low.add(a);

            }
            if(a > pivot){
                System.out.println("Swap: "+a+" z "+pivot);
                select_swaps++;
                high.add(a);
            }
        }
        System.out.print("Tablica mniejszych: ");
        for(int i : low){
            System.out.print(i+" ");
        }
        System.out.print("\nTablica większych: ");
        for(int i : high){
            System.out.print(i+" ");
        }
        System.out.println("");
        int i = low.size();
        if(k < i){
            Integer[] a = low.stream().toArray(Integer[]::new);
            int[] intA = Arrays.stream(a).mapToInt(Integer::intValue).toArray();
            return select(intA,k);
        }
        else if(k > i){
            Integer[] a = high.stream().toArray(Integer[]::new);
            int[] intA = Arrays.stream(a).mapToInt(Integer::intValue).toArray();
            return select(intA,k-i-1);
        }
        else{
            return pivot;
        }
    }

    static int randomized_selection(int[] array, int left, int right, int n) {
        if (left == right) {
            return array[left];
        }


        int pivotIndex = randomPivot(left, right);
        pivotIndex = partition(array, left, right, pivotIndex);

        if (n == pivotIndex) {
            return array[n];
        } else if (n < pivotIndex) {
            return randomized_selection(array, left, pivotIndex - 1, n);
        } else {
            return randomized_selection(array, pivotIndex + 1, right, n);
        }
    }

    static int partition(int[] array, int start, int end, int pivotIndex) {

        int pivot = array[pivotIndex];
        System.out.println("Nowy pivot: "+pivot);

        System.out.println("Swap: "+array[pivotIndex]+" z "+array[end]);
        random_select_swaps++;
        swap(array, pivotIndex, end);
        for(int a : array) System.out.print(a+" ");
        System.out.println("");
        int storeIndex = start;
        for(int i = start; i < end; i++) {
            System.out.println("Compare: "+array[i]+" z "+pivot);
            random_select_comp++;
            if(array[i] < pivot) {
                System.out.println("Swap: "+array[storeIndex]+" z "+array[i]);
                random_select_swaps++;
                swap(array, storeIndex, i);
                for(int a : array) System.out.print(a+" ");
                System.out.println("");
                storeIndex++;
            }
        }
        System.out.println("Swap: "+array[end]+" z "+array[storeIndex]);
        random_select_swaps++;
        swap(array, end, storeIndex);
        return storeIndex;
    }

    private static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    private static int randomPivot(int left, int right) {
        return left + (int) Math.floor(Math.random() * (right - left + 1));
    }
}
