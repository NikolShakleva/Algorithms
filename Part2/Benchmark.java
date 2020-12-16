import java.util.Arrays;

/***********************************************************
 * BENCHMARK
 * 
 * @author Emelie Skörvald emsk@itu.dk
 * @author Nikol Shakleva nikv@itu.dk
 * @author Szilvia Gaspar szga@itu.dk
 * 
 ***********************************************************/
public class Benchmark {

    private static double sdev;
    private static double mean;
    private static Timer t;
    
    /**
     * Warmup
     * 
     * Warming up the algorithms that will be tested before executing the real test
     * During the test there will also be a correctness test to make sure that they
     * are correct.
     * 
     * @param inputArray    String with input data
     * @param n             integer of how many times the test will be ran
     * @param algorithm     a String with the algorithm name
     * @return              a string Failure or Success
     */
    public static String warmUp (int[] inputArray, int n, String algorithm) {

        for(int i = 0; i < n; i++ ) {
            int[] A = inputArray.clone();
            if      (algorithm.equals("QuickSortClassic"))         {QuickSortClassic.sort(A, 0, A.length-1);}
            else if (algorithm.equals("DualPivotQuickSort"))       {DualPivotQuickSort.sort(A, 0, A.length-1);}
            else if (algorithm.equals("DualPivotQuickSortWrong"))  {DualPivotQuickSortWrong.sort(A, 0, A.length-1);}
            else if (algorithm.equals("ThreePivotQuickSort"))      {ThreePivotQuickSort.sort(A, 0, A.length-1);}
            else if (algorithm.equals("ThreePivotQuickSortWrong")) {ThreePivotQuickSortWrong.sort(A, 0, A.length-1);}
            else if (algorithm.equals("Standard"))                 {Arrays.sort(A);}
            else if (algorithm.equals("InsertionSort"))            {InsertionSort.sort(A);}
    
            if(!correctnessTest(A)) return "FAILURE";
        }
        return "Success";
    } 

    /**
     * run
     * 
     * Running the benchmark for the algorithms, all algorithms run after eachother and
     * the output is sent to a correctness test to make sure that they are correct.
     * 
     * @param inputArray    int array with input data
     * @param iteration     number of iterations
     * @param n             integer of how many times the test will be run
     * @param algorithm     a String with the algorithm that will be tested    
     * @return              a string Failure or Success  
     */
    public static String run(int[] inputArray, int iterations, int n, String algorithm) {

        double st    = 0.0;
        double sst   = 0.0;
        sdev         = 0.0;
        mean         = 0.0;
        t            = new Timer();
        

        for(int i=0; i < n; i++) {
            for(int k = 0; k < iterations ; k++) {
                int[] A = inputArray.clone();

                switch(algorithm) {
                    case "QuickSortClassic":
                        t.play();
                        QuickSortClassic.sort(A, 0, A.length-1);
                        t.pause();
                        break;

                    case "DualPivotQuickSort":
                        t.play();
                        DualPivotQuickSort.sort(A, 0, A.length-1);
                        t.pause();
                        break;

                    case "DualPivotQuickSortWrong":
                        t.play();
                        DualPivotQuickSortWrong.sort(A, 0, A.length-1);
                        t.pause();
                        break;

                    case "ThreePivotQuickSort":
                        t.play();
                        ThreePivotQuickSort.sort(A, 0, A.length-1);
                        t.pause();
                        break;

                    case "ThreePivotQuickSortWrong":
                        t.play();
                        ThreePivotQuickSortWrong.sort(A, 0, A.length-1);
                        t.pause();
                        break;

                    case "Standard":
                        t.play();
                        Arrays.sort(A);
                        t.pause();
                        break;

                    case "InsertionSort":
                        t.play();
                        InsertionSort.sort(A);
                        t.pause();
                        break;
                    
                    default:
                    System.out.println("No algorithm to measure");
                }
                if(!correctnessTest(A)) return "Failure";
            }
            double time = t.check() / iterations;
            st  += time;
            sst += time * time;
        }
        mean = (st/n);
        sdev = (Math.sqrt((sst - mean * mean * n)/(n-1)));

        System.out.printf( "%12s  %6.1f mu  +/-  %6.3f %n", algorithm, mean, sdev);

        return "Success";
    }


     /**
     * correctnessTest
     * 
     * Checks that an array sorted by Java's library sort implementation 
     * and a given array are the same. 
     * 
     * @param A  the int array to be tested that it was correctly sorted
     * @return  a boolean true if the two arrays are the same
     */
    public static Boolean correctnessTest(int[] A){
        int[] libraryArray = A.clone();
        Arrays.sort(libraryArray);

        if(!Arrays.equals(A, libraryArray)) return false;
    
        return true;
    }


    /**
     * getMean
     * 
     * @return  the mean of the test
     */
    public static double getMean(){
        return mean;
    }

    /**
     * getSdev
     * 
     * @return  the standard diviation of the test
     */
    public static double getSdev(){
        return sdev;
    }


}




