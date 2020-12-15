import java.util.Arrays;

public class CorrectnessTest {
   private static String [] algo = {"QuickSortClassic","DualPivotQuickSort","ThreePivotQuickSort"};
   private static String [] mode = {"increasing", "decreasing", "same", "random", "equal", "semi-sorted"};
   private static int seed = 1234;
   private static int N = 20;
   

   public static void main(String[] args) {
       for (int i = 0; i < mode.length; i++) {
           int [] inputArray = Producer.generate(mode[i], N, seed);
           test(inputArray);
       }
       
   }
    public static String test (int [] inputArray) {
        System.out.println(Arrays.toString(inputArray));
        System.out.println();
         int [][] A = new int [algo.length][inputArray.length];
            for(int i = 0; i < algo.length; i++) {
                A[i] = inputArray.clone();
            }
            for(int j = 0; j < algo.length ; j++ ) {
                String algorithm = algo[j];
                // if(algorithm.equals("QuickSortSz")) {QuickSortSz.sort(A[j], 0, inputArray.length-1);}
                if(algorithm.equals("QuickSortClassic")) {QuickSortClassic.sort(A[j], 0, inputArray.length-1);}
                else if(algorithm.equals("DualPivotQuickSort")) {DualPivotQuickSort.sort(A[j], 0, inputArray.length-1);}
                else if(algorithm.equals("ThreePivotQuickSort")) {ThreePivotQuickSort.sort(A[j], 0, inputArray.length-1);}
            }

            if(!correctnessTest(A)) return "Failure";
        
        return "Success";
    } 


    /**
     * correctnessTest
     * 
     * Tests that all the output of the algorithms are the same. If they are not
     * that means that one or more algorithms are incorrect.
     * 
     * @param a         int of how many algorithms are in the test
     * @param dummy     an array with the output from the algortihms
     * @return          a boolean if the output are the same
     */
    public static Boolean correctnessTest(int [][] A){
        int [] libraryArray = A[0].clone();
        Arrays.sort(libraryArray);
        for(int l = 0 ; l < A.length; l++) {
            if(!Arrays.equals(A[l], libraryArray)) {
                System.out.println("Failed, " + algo[l]  + " is not correct");
                System.out.println( algo[l] + Arrays.toString(A[l]));
                System.out.println( "Library quickSort" + Arrays.toString(libraryArray));
                return false;
            }
        }
        return true;
    }



}
