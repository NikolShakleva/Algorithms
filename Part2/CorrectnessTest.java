import java.util.Arrays;

public class CorrectnessTest {
   private static String [] algo = {"QuickSortClassic","DualPivotQuickSort","ThreePivotQuickSort"};
   private static String [] twoPivot = {"DualPivotQuickSort",  "DualPivotQuickSortWrong","ThreePivotQuickSort", "ThreePivotQuickSortWrong"};
   private static String [] mode = {"increasing", "decreasing", "same", "random", "equal", "semi-sorted"};
   private static int [] seed = Seed.createSeed(1234);
   private static int N = 10_000;
   

    public static String test (int [] inputArray, String algorithm) {

        //  if(algorithm.equals("QuickSortClassic")) {QuickSortClassic.sort(inputArray, 0, inputArray.length-1);}
          if(algorithm.equals("DualPivotQuickSort")) {DualPivotQuickSort.sort(inputArray, 0, inputArray.length-1);}
         else if(algorithm.equals("DualPivotQuickSortWrong")) {DualPivotQuickSortWrong.sort(inputArray, 0, inputArray.length-1);}
         else if(algorithm.equals("ThreePivotQuickSort")) {ThreePivotQuickSort.sort(inputArray, 0, inputArray.length-1);}
         else if(algorithm.equals("ThreePivotQuickSortWrong")) {ThreePivotQuickSortWrong.sort(inputArray, 0, inputArray.length-1);}

            if(!correctnessTest(inputArray)) return "FAILURE";
        
        return "SUCCESS";
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
    public static Boolean correctnessTest(int [] A){
        int [] libraryArray = A.clone();
        Arrays.sort(libraryArray);
            if(!Arrays.equals(A, libraryArray)) {
                // System.out.println( algo[l] + Arrays.toString(A[l]));
                // System.out.println( "Library quickSort" + Arrays.toString(libraryArray));
                return false;
            }
        // System.out.println("SUCCESS");
        return true;
    }

    
    public static void main(String[] args) {
        for (int i = 0; i < mode.length; i++) {
            for(int  l = 0; l< seed.length; l++) {
                 for(int j = 0; j < twoPivot.length; j++) {
                     int [] inputArray = Producer.generate(mode[i], N, seed[l]);
                    //  System.out.println("Correctness test for: " + twoPivot[j] + " mode " + mode[i] + " seed: " + seed[l]);
                     String value =  test(inputArray, twoPivot[j]);
                     if(value.equals("FAILURE")) {
                        System.out.println("FAILURE. " + twoPivot[j]  + " is not correct for mode: " + mode[i] + " for seed: " + seed[l]);
                        System.out.println("--------------------------------------------------------------------------------------------");
                        System.out.println();
                     }
                    //  System.out.println();
                 }
             }
        }
        
    }


}
