import java.util.Random;

public class L3z3 {

    static int compares = 0;
    public static void main(String[] args) {

        Random r = new Random();
        int n = Integer.parseInt(args[0]);
        int v = Integer.parseInt(args[1]);
        int[] test;

        if(args.length>=3) {

            for (int k = 1000; k <= 100000; k += 1000) {
                test = new int[k];
                for (int i = 0; i < k; i++) {
                    test[i] = i;
                }
                v = (int)(Math.random()*100000+1);
                double startTime = System.nanoTime();

                int result = binarySearch(0, test.length - 1, v, test);

                double endTime = System.nanoTime();
                double timeTaken = endTime - startTime;
                if (result == 1) {
                    System.out.println("Dana liczba "+v+" jest w tablicy " + "Liczba porównań wynosi " + compares+" czas działania "+timeTaken);
                } else {
                    System.out.println("Danej liczby "+v+" nie ma w tablicy " + "Liczba porównań wynosi " + compares+" czas działania "+timeTaken);
                }
                compares = 0;
            }
        }
        else{
            test = new int[n];
            for (int i = 0; i < n; i++) {
                test[i] = i;
            }

            double startTime = System.nanoTime();

            int result = binarySearch(0, test.length - 1, v, test);

            double endTime = System.nanoTime();
            double timeTaken = endTime - startTime;
            if (result == 1) {
                System.out.println("Dana liczba "+v+" jest w tablicy " + "Liczba porównań wynosi " + compares+" czas działania "+timeTaken);
            } else {
                System.out.println("Danej liczby "+v+" nie ma w tablicy " + "Liczba porównań wynosi " + compares+" czas działania "+timeTaken);
            }
        }
    }

    static int binarySearch(int l, int p, int number,int[] tab ){
        if(l > p) return 0;
        compares++;

        int mid = (l+p)/2;
        compares++;
        if(number == tab[mid]){
            return 1;
        }
        compares++;
        if(number < tab[mid]){
            return binarySearch(l, mid-1, number, tab);
        }
        else{
            return binarySearch(mid+1,p,number,tab);
        }
    }

}
