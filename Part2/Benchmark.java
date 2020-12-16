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
     * @param inputPred     String with prediction data
     * @param n             integer of how many times the test will be ran
     * @param algo          a String array with all the algorithms that will be tested
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
     * @param inputArray    String with input data
     * @param inputPred     String with prediction data
     * @param n             integer of how many times the test will be ran
     * @param algo          a String array with all the algorithms that will be tested    
     * @param K             integer of how many buckets the tabular algorithm will store
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

                if (algorithm.equals("QuickSortClassic"))        {
                    t.play();
                    QuickSortClassic.sort(A, 0, A.length-1);
                    t.pause();
                } 
                else if (algorithm.equals("DualPivotQuickSort"))       {
                    t.play();
                    DualPivotQuickSort.sort(A, 0, A.length-1);
                    t.pause();
                }
                else if (algorithm.equals("DualPivotQuickSortWrong"))  {
                    t.play();
                    DualPivotQuickSortWrong.sort(A, 0, A.length-1);
                    t.pause();
                }
                else if (algorithm.equals("ThreePivotQuickSort"))      {
                    t.play();
                    ThreePivotQuickSort.sort(A, 0, A.length-1);
                    t.pause();
                }
                else if (algorithm.equals("ThreePivotQuickSortWrong")) {
                    t.play();
                    ThreePivotQuickSortWrong.sort(A, 0, A.length-1);
                    t.pause();
                }
                if(!correctnessTest(A)) return "Failure";
            }
            double time = t.check() / iterations;
            st  += time;
            sst += time * time;
        }
        mean = (st/n)/ 1000;
        sdev = (Math.sqrt((sst - mean * mean * n)/(n-1)))/1000;

        System.out.printf( "%12s  %6.1f mu  +/-  %6.3f %n", algorithm, mean, sdev);

        return "Success";
    }


    /**
     * searchObject
     * 
     * Instanciates an object of the algorithm that should be tested and stores them in a
     * Search array. 
     * To add another algorithm to the test write another if else statement below and to the
     * algorithms array in the Experiment class
     * 
     * @param algo      String of the algorithms that should be tested
     * @param input     a String with values to use for the array A in the search object
     * @param K         int of how many buckets Tabulation will use
     * @return          the searchObject
     */
    // public static Search searchObject(String algo, String input, int K){
    //     Search search;
    //     if   (algo.equals("BinarySearch")) search = new BinarySearch(input);
    //     else                               search = new Tabulation(input, K); 
    //     return search;
    // }

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
            if(!Arrays.equals(A, libraryArray)) {
                // System.out.println( algo[l] + Arrays.toString(A[l]));
                // System.out.println( "Library quickSort" + Arrays.toString(libraryArray));
                return false;
            }
        // System.out.println("SUCCESS");
        return true;
    }

    /**
     * addTime
     * 
     * adds the running time for each iteration together for the individual
     * algorithms.
     * 
     * @param a     int of how many algorithms are in the test
     * @param i     int of iterations
     * @param st    a double array with st for each algorithm
     * @param sst   a double array with sst for each algorithm
     */
    public static void addTime(int i, double st, double sst) {  
            double time = t.check() / i;
            st  += time;
            sst += time * time;
    }

    /**
     * 
     * @param a     int of how many algorithms are in the test
     * @param n     int of iterations
     * @param st    a double array with st for each algorithm
     * @param sst   a double array with sst for each algorithm
     */
    public static void calculateResult(int n, double st, double sst, String algorithm){
            mean = (st/n)/ 1000;
            sdev = (Math.sqrt((sst - mean * mean * n)/(n-1)))/1000;

            System.out.printf( "%12s  %6.1f mu  +/-  %6.3f %n", algorithm, mean, sdev);
    }

    /**
     * getMean
     * 
     * @return  an array with the means of the test for each algorithm
     */
    public static double getMean(){
        return mean;
    }

    /**
     * getSdev
     * 
     * @return  an array with the standard diviation of the test for each algorithm
     */
    public static double getSdev(){
        return sdev;
    }


}




