public class Part2 {

    private static String[] allAlgorithms       = {"QuickSortClassic","DualPivotQuickSort","ThreePivotQuickSort"};
    private static String[] boundaryCheck       = {"DualPivotQuickSort",  "DualPivotQuickSortWrong","ThreePivotQuickSort", "ThreePivotQuickSortWrong"};

    private static String[] quickestAndLibrary  = {"ThreePivotQuickSort", "Standard"};
    private static String[] insertionSortTest   = {"QuickSortClassic","InsertionSort", "DualPivotQuickSort","ThreePivotQuickSort"};

    private static String[] optimized           = {"QuickSortClassicI","DualPivotQuickSortI","ThreePivotQuickSortI"};

    private static final String[] modeRandom    = {"random"};
    private static String[] allModes            = {"increasing", "decreasing", "same", "random", "equal", "semi-sorted"};

    private static final int[] NLarge           = {20_000, 100_000, 500_000};
    private static final int[] NInsertion       = {10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};
    
    public static String[] warmUpModes          = {"increasing", "random"};


    public static void main(String[] args) {
        
    // Taks 4, testing correctness of all algorithms
    // CorrectnessTest.runTest(allAlgorithms, allModes);
    // CorrectnessTest.runTest(optimized, allModes);


    // // Task 4, test removing boundary checks
    CorrectnessTest.runTest(boundaryCheck, allModes);

    // // Task 5, performance test, average runningtime
    // Experiment.experiment(allAlgorithms, allModes, warmUpModes NLarge);

    // // Task 5, performance test, average runningtime Library vs Quickest
    // Experiment.experiment(quickestAndLibrary, allModes, NLarge);

    // // Task 5, test all algorithms vs Insertion sort to find optimal array sizes
    // ExperimentInsertion.experiment(insertionSortTest, modeRandom, NInsertion);

    // // Task 5, performance test, average runningtime for new implementation with Insertion sort
    // Experiment.experiment(optimized, allModes, NLarge);





    }
}
