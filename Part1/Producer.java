import java.util.*;
import java.util.stream.IntStream;

/***********************************************************
 * PRODUCER
 * 
 * @author  Emelie Skörvald emsk@itu.dk
 * @author  Nikol Shakleva  nikv@itu.dk
 * @author  Szilvia Gaspar  szga@itu.dk
 * 
 ***********************************************************/

public class Producer {
        
    /**
     * 
     * @param mode  String to define the different input types 
     * @param N     integer of how big  the generated input should be
     * @param seed  an integer used as a seed to generate the input from
     * @return      String of the generated input
     */
    public static String generate (String mode, int N, int seed) {

    final Random R = new Random();
    R.setSeed(seed);
    StringBuilder sbvals = new StringBuilder();
    
    final List<Integer> vals = new ArrayList<>();

    switch (mode) {
        // Input type to make sure that the pred does not exist in the vals 
        //and the algorithm will go through empty buckets before returning None
        case "non-existent": 
            sbvals.append(N + " ");
            // Making N random large numbers in range from 47_483_647 to 2_147_483_647
            IntStream largeNumbers = R.ints(N, 47_483_647, 2_147_483_647);
            largeNumbers.forEach(e -> vals.add(e));
            break;

        case "non-existent pred": 
            // Making N random smaller numbers than in vals (used for set S) in range 
            //from 0 to 47_483_647
            IntStream smallNumbers = R.ints(2*N, 0, 47_483_646);
            smallNumbers.forEach(e -> vals.add(e));
        break;

        case "random": 
            sbvals.append(N + " ");
            IntStream randN = R.ints(N);
            randN.forEach(e -> vals.add(e));
        break;

        case "random pred": 
            IntStream randNp = R.ints(2*N);
            randNp.forEach(e -> vals.add(e));
        break;

        // creating input vals (set S) so that it contains small numbers
        case "big pred, small ints": 
            sbvals.append(N + " ");
            IntStream randP = R.ints(N, 0, 1_000);
            randP.forEach(e -> vals.add(e));
        break;
        // creating input vals (prediction query) such that the numbers are large
        case "big pred, small ints pred": 
            IntStream randPp = R.ints(2*N, 500, Integer.MAX_VALUE);
            randPp.forEach(e -> vals.add(e));
        break;

        // creating input vals in a small range so that many of the numbers 
        //would fall in the same bucket
        case "same bucket": 
            sbvals.append(N + " ");
            IntStream numbers = R.ints(N, 0, 1_000);
            numbers.forEach(e -> vals.add(e));
        break;

        case "same bucket pred": 
            IntStream numbersP = R.ints(2*N);
            numbersP.forEach(e -> vals.add(e));
        break;
        

        default:
            System.err.println("Unknown mode: " + mode);
        }

        for (int i = 0; i < N; ++i) sbvals.append(vals.get(i) + " ");

        return sbvals.toString();
    }

}