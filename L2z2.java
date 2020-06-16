import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L2z2 {

    static int numbers[];
    static int n = 0;
    static int merge_swap_counter=0;
    static int merge_comp_counter=0;
    static int quick_swap_counter= 0;
    static int quick_comp_counter=0;
    static int insertion_swaps = 0;
    static int insertion_compares = 0;

    public static void main(String[] args) {

        if(args.length>4){
            for(int n =100; n<=10000;n+=100){
                numbers = new int[n];
                int k = Integer.parseInt(args[6]);
                for(int i = 0 ; i<k ; i++) {

                    for (int j = 0; j < n; j++) {
                        numbers[j] = randomValue(n);
                    }

                    if (args[1].equals("merge")) {
                        double startTime = System.nanoTime();

                        MergeSort(numbers, 0, numbers.length - 1, args[3]);

                        double endTime = System.nanoTime();
                        double timeTaken = endTime - startTime;
                        try {
                            writeResult(n, merge_comp_counter, merge_swap_counter, timeTaken, args[5]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        merge_swap_counter = 0;
                        merge_comp_counter = 0;

                    }
                    if (args[1].equals("insertion")) {
                        double startTime = System.nanoTime();

                        InsertionSort(numbers, args[3]);

                        double endTime = System.nanoTime();
                        double timeTaken = endTime - startTime;
                        try {
                            writeResult(n, insertion_compares, insertion_swaps, timeTaken, args[5]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        insertion_swaps = 0;
                        insertion_compares = 0;
                    }
                    if (args[1].equals("quick")) {
                        double startTime = System.nanoTime();

                        QuickSort(numbers, 0, numbers.length - 1, args[3]);

                        double endTime = System.nanoTime();
                        double timeTaken = endTime - startTime;
                        try {
                            writeResult(n, quick_comp_counter, quick_swap_counter, timeTaken, args[5]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        quick_comp_counter = 0;
                        quick_swap_counter = 0;
                    }
                }
                numbers = null;
            }
            return;
        }
        Scanner scanner = new Scanner(System.in);

        n= scanner.nextInt();
        numbers = new int[n];

        for(int i=0; i<n;i++) {
            numbers[i] = scanner.nextInt();
        }

        if(args[1].equals("merge")) {
            double startTime = System.nanoTime();

            MergeSort(numbers, 0, numbers.length - 1,args[3]);

            double endTime = System.nanoTime();
            double timeTaken = endTime - startTime;

            System.out.println("liczba porównań: "+ merge_comp_counter +" liczba przestawień: "+merge_swap_counter);
            System.out.println("Sortowanie trwało: "+timeTaken+" nanosekund");
        }
        if(args[1].equals("insertion")) {
            double startTime = System.nanoTime();

            InsertionSort(numbers,args[3]);

            double endTime = System.nanoTime();
            double timeTaken = endTime - startTime;

            System.out.println("liczba porównań: "+ insertion_compares +" liczba przestawień: "+insertion_swaps);
            System.out.println("Sortowanie trwało: "+timeTaken+" nanosekund");
        }
        if(args[1].equals("quick")) {
            double startTime = System.nanoTime();

            QuickSort(numbers, 0, numbers.length - 1,args[3]);

            double endTime = System.nanoTime();
            double timeTaken = endTime - startTime;

            System.out.println("liczba porównań: "+ quick_comp_counter +" liczba przestawień: "+quick_swap_counter);
            System.out.println("Sortowanie trwało: "+timeTaken+" nanosekund");
        }
        for( int l : numbers){
            System.out.print(l+" ");
        }
    }

    static void writeResult(int data_size, int compares, int swaps, double time, String where ) throws IOException {
        FileWriter fileWriter = new FileWriter(where,true);
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.println(data_size+";"+compares+";"+swaps+";"+time);
        writer.close();

    }

    static int randomValue(int i){
        double randomDouble = Math.random();
        randomDouble = randomDouble * i + 1;
        int randomInt = (int) randomDouble;
        return randomInt;
    }

    static void InsertionSort(int[] numbers, String sign){


        int n = numbers.length;

        for (int i = 1; i < n; ++i) {
            int key = numbers[i];
            int j = i - 1;

            if(sign.equals(">=")) {
                while (j >= 0 && numbers[j] < key) { // ta linijka odpowiada za kolejność sortowania
                    System.out.println("Porównuje: " + numbers[j] + " z " + key);
                    insertion_compares++;
                    System.out.println("Przesawiam: " + key + " z " + numbers[j]);
                    insertion_swaps++;
                    numbers[j + 1] = numbers[j];
                    j = j - 1;
                }
                System.out.println("Porównuje: " + numbers[j+1] + " z " + key);
                insertion_compares++;
            }
            if(sign.equals("<=")){
                while (j >= 0 && numbers[j] > key) { // ta linijka odpowiada za kolejność sortowania
                    System.out.println("Porównuje: " + numbers[j] + " z " + key);
                    insertion_compares++;
                    System.out.println("Przesawiam: " + key + " z " + numbers[j]);
                    insertion_swaps++;
                    numbers[j + 1] = numbers[j];
                    j = j - 1;
                }
                System.out.println("Porównuje: " + numbers[j+1] + " z " + key);
                insertion_compares++;
            }
            numbers[j + 1] = key;
        }
    }

    static void merge(int arr[], int l, int m, int r, String sign) {

        // wyznaczyamy rozmiar dwóch "mniejszych" tablic
        int first_half_size = m - l + 1;
        int second_half_size = r - m;

        //tymczasowe tablice
        int first_half[] = new int [first_half_size];
        int second_half[] = new int [second_half_size];

        for (int i=0; i<first_half_size; ++i)
            first_half[i] = arr[l + i];
        for (int j=0; j<second_half_size; ++j)
            second_half[j] = arr[m + 1+ j];

        /* łączenie tymczasowych tablic w jedna */

        // inicjacja indexów tablic
        int i = 0, j = 0;

        // index połaczonczej tablicy
        int k = l;
        while (i < first_half_size && j < second_half_size) {

            System.out.println("Porównuje: "+first_half[i]+" z "+second_half[j]);
            merge_comp_counter++;
            if(sign.equals("<=")) {
                if (first_half[i] <= second_half[j]) { // ta linijka odpowiada za kolejność sortowania
                    System.out.println("Przestawiam: " + arr[k] + " z " + first_half[i]);
                    arr[k] = first_half[i];
                    i++;
                    merge_swap_counter++;
                } else {
                    System.out.println("Przestawiam: " + arr[k] + " z " + second_half[j]);
                    arr[k] = second_half[j];
                    j++;
                    merge_swap_counter++;
                }
            }
            if(sign.equals(">=")) {
                if (first_half[i] >= second_half[j]) { // ta linijka odpowiada za kolejność sortowania
                    System.out.println("Przestawiam: " + arr[k] + " z " + first_half[i]);
                    arr[k] = first_half[i];
                    i++;
                    merge_swap_counter++;
                } else {
                    System.out.println("Przestawiam: " + arr[k] + " z " + second_half[j]);
                    arr[k] = second_half[j];
                    j++;
                    merge_swap_counter++;
                }
            }
            k++;
        }

        while (i < first_half_size) {
            System.out.println("Przestawiam: " + arr[k] + " z " + first_half[i]);
            merge_swap_counter++;
            arr[k] = first_half[i];
            i++;
            k++;
        }

        while (j < second_half_size) {
            System.out.println("Przestawiam: " + arr[k] + " z " + second_half[j]);
            merge_swap_counter++;
            arr[k] = second_half[j];
            j++;
            k++;
        }
    }

    static void MergeSort(int numbers[], int l, int r,String sign) {

        if (l < r) {
            // znajdowanie środka
            int m = (l+r)/2;

            // sortowanie obu połówek
            MergeSort(numbers, l, m,sign);
            MergeSort(numbers , m+1, r,sign);

            // łączenie posortowanych połówek w jedną
            merge(numbers, l, m, r,sign);
        }

    }


    static int partition(int numbers[], int low, int high,String sign) {
        int theChosen = numbers[high];

        int i = (low-1);

        for (int j=low; j<high; j++) {

            if(sign.equals("<=")) {
                System.out.println("Porównuje: " + numbers[j] + " z " + theChosen);
                quick_comp_counter++;
                if (numbers[j] < theChosen)// ta linijka odpowiada za koljenosc sortowania
                {
                    i++;
                    System.out.println("Przestawiam: " + numbers[j] + " z " + theChosen);
                    quick_swap_counter++;

                    // zamiana miejscami za pomocą zmiennej pomocniczej
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
            if(sign.equals(">=")){
                System.out.println("Porównuje: " + numbers[j] + " z " + theChosen);
                quick_comp_counter++;
                if (numbers[j] > theChosen)// ta linijka odpowiada za koljenosc sortowania
                {
                    i++;
                    System.out.println("Przestawiam: " + numbers[j] + " z " + theChosen);
                    quick_swap_counter++;

                    // zamiana miejscami za pomocą zmiennej pomocniczej
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        // to samo co powyzej
        int temp = numbers[i+1];
        numbers[i+1] = numbers[high];
        numbers[high] = temp;

        return i+1;
    }



    static void QuickSort(int numbers[], int low, int high,String sign) {

        if (low < high) {

            //pi oznacza partitioning index
            int pi = partition(numbers, low, high,sign);

            // sortowanie obu części tablicy
            QuickSort(numbers, low, pi-1,sign);
            QuickSort(numbers, pi+1, high,sign);
        }
    }
}
