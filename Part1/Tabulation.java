import java.util.Arrays;
import java.util.Scanner;

/***********************************************************
 * TABULATION
 * 
 * @author Emelie Skörvald  emsk@itu.dk
 * @author Nikol Shakleva   nikv@itu.dk
 * @author Szilvia Gaspar   szga@itu.dk
 ***********************************************************/

public class Tabulation implements Search {

    int k = 10;
    int [] A;
    int[][] table;
    int size;
    int buckets;
    int halfBuckets;
    int max;
    int min;


    /**
     * 
     * @param input String input for creating the table
     * @param K int to create an array table with 2^k entries
     */
    public Tabulation(String input, int K){
        k = K;
        buckets = (int) Math.pow(2, k);
        halfBuckets = buckets/2;
        max = maxBit();
        min = minBit();
        makeTabulation(input);
        createTable();

    }

    /**
     * 
     * @param input String input for creating the table
     */
    public void makeTabulation(String input){

        String[] s = input.split(" ");
        size = Integer.parseInt(s[0]);
        A = new int[size];

        for (int i = 0; i < size; i++){
            A[i] = Integer.parseInt(s[i+1]);
        }
        Arrays.sort(A);
    }

	/**
     * Creates a Look-up table containing 2^k indexes each
     * containing entries for the smalles and biggest 
     * index for numbers in the specific range
     */
    public void createTable(){

        table = new int[buckets][2];                // Creates the the lookup table k^2 indexes containing 2 indexes
        
        int previousIndex=0;
        for(int i = 0; i < size ; i++) {
            int current = A[i];                             // Finding the value of current index of A
            int resultMin = min & current;                  // Finding the min value for the range of current
            int resultMax = max | current;                  // Finding the max value for the range of current
            int currentIndex = kthMostInteger(current);     // Finding the index for the current range

            if(A[table[currentIndex][0]] < resultMin || i < table[currentIndex][0]) table[currentIndex][0] = i;
            if(A[table[currentIndex][1]] > resultMax || i > table[currentIndex][1]) table[currentIndex][1] = i;

            // overwrite the default 0 value of the table by setting the right boundary to i
            if( currentIndex > previousIndex) fillTable(previousIndex, currentIndex, i);
            
            previousIndex = currentIndex;
            
            // overwrite the default 0 values in the table after the index A[size] with the index of A[size-1]
            if( i == size-1) fillTable(currentIndex, buckets, i);
        }
    }

    /**
     * Fills indexes of the lookup table that does not have any entries in A
     * @param left   first index
     * @param right  last index
     * @param i      index of a that should be filled with   
     */
    public void fillTable(int left, int right, int i){
        for(int j = left+1; j < right; j++) {
            table[j][0] = i;
            table[j][1] = i;
        }
    }


    /**
     * 
     * @return the smalles integer in the range of k
     */
    public int minBit(){
        int temp = 0;
        int shift = 32-k;
        for (int i = shift; i<32; i++ ){
            temp = 1 << i | temp;
        }
        return temp;
    }

    /**
     * 
     * @return the largest integer in the range of k
     */
    public int maxBit(){
        int temp = 0;
        int shift = 32-k;
        temp = (1 << shift)-1;
        return temp;
    }

    /**
     * 
     * @param x integer that should be added to the lookup table
     * @return an index in the lookup table
     */
    public int kthMostInteger(int x){
        int shift = 32 - k;
        int res = x >> shift;
        res = (res >= halfBuckets) ? res - halfBuckets : 
                                     res + halfBuckets;
        
        return  res;
    }
    
     /**
     * 
     * @param input a string with numbers to predict
     * @return a string with the result of the prediction
     */
    public String readingQuery(String input){
        StringBuilder sb = new StringBuilder();

        String[] amount = input.split(" ");
        for(int i = 0 ; i < amount.length ;i++){
            int x = Integer.parseInt(amount[i]);
            sb.append(pred(x));
        }
        return sb.toString();
    }


   
    /**@param x an int to call predict on
     * @return the string representation of the result of the prediction
     */
    public String pred(int x){

            int index = kthMostInteger(x);
            int left  = table[index][0];
            int right = table[index][1];

            if (left > 0) return(A[left] > x ? A[left-1] + " " : A[indexOf(x, left, right)] + " ");
            else          return(A[left] > x ? "None "         : A[indexOf(x, left, right)] + " ");       
    
    }

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param  a the array of integers, must be sorted in ascending order
     * @param  key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public int indexOf(int key, int left, int right) {
        int lo = left;
        int hi = right;
        int closest = -1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < A[mid]) hi = mid - 1;
            else if (key > A[mid]) {
                lo = mid + 1;
                if(closest < 0 || A[mid] > A[closest]) {
                    closest = mid;
                }
            }

            else return mid;
        }
        return closest;
    }

    public static void main(String[] args) {
        var b = new Tabulation("20 44 5433 2345 65 -654 4 -8765 3 -234 76543 22 -1 10 11 5 -10 20 -25 -30 30", 10);
        var a = new BinarySearch("20 44 5433 2345 65 -654 4 -8765 3 -234 76543 22 -1 10 11 5 -10 20 -25 -30 30");
        System.out.println(b.readingQuery("1 20 -5 -50 5432 5 654 -23456 66 432 6 4 -6 54 -76543 38695432 4 432 23456 -6543 54 -4"));
        System.out.println(a.readingQuery("1 20 -5 -50 5432 5 654 -23456 66 432 6 4 -6 54 -76543 38695432 4 432 23456 -6543 54 -4"));
        
        System.out.println("hi");

    }
}