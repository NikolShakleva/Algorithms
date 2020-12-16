import java.util.Arrays;

/**
 * 
 *  run with a larger stack
 *  java -Xss2g CorrectnessTest
 */

public class CorrectnessTest {

   private static int   N       = 200_000;
   private static int[] seed    = Seed.createSeed(1234);
  
    /**
     * 
     * @param inputArray
     * @param algorithm
     * @return
     */
    public static String test (int[] inputArray, String algorithm) {

        if      (algorithm.equals("QuickSortClassic"))         {QuickSortClassic.sort(inputArray, 0, inputArray.length-1);}
        else if (algorithm.equals("DualPivotQuickSort"))       {DualPivotQuickSort.sort(inputArray, 0, inputArray.length-1);}
        else if (algorithm.equals("DualPivotQuickSortWrong"))  {DualPivotQuickSortWrong.sort(inputArray, 0, inputArray.length-1);}
        else if (algorithm.equals("ThreePivotQuickSort"))      {ThreePivotQuickSort.sort(inputArray, 0, inputArray.length-1);}
        else if (algorithm.equals("ThreePivotQuickSortWrong")) {ThreePivotQuickSortWrong.sort(inputArray, 0, inputArray.length-1);}

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
    public static Boolean correctnessTest(int[] A){
        int[] libraryArray = A.clone();
        Arrays.sort(libraryArray);

        if(!Arrays.equals(A, libraryArray)) return false;

        return true;
    }

    /**
     * 
     * @param algo
     * @param mode
     */
    public static void runTest(String[] algo, String[] mode) {
        for (int i = 0; i < mode.length; i++) {
            for(int  l = 0; l< seed.length; l++) {
                for(int j = 0; j < algo.length; j++) {
                    int [] inputArray = Producer.generate(mode[i], N, seed[l]);

                    String value =  test(inputArray, algo[j]);
                    if(value.equals("FAILURE")) {
                        System.out.println("FAILURE. " + algo[j]  + " is not correct for mode: " + mode[i] + " for seed: " + seed[l]);
                        System.out.println("--------------------------------------------------------------------------------------------");
                        System.out.println();
                    }
                }
            }
        }   
    }
}
