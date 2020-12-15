import java.util.*;


/***********************************************************
 * PRODUCER
 * 
 * @author  Emelie Skörvald emsk@itu.dk
 * @author  Nikol Shakleva  nikv@itu.dk
 * @author  Szilvia Gaspar  szga@itu.dk
 * 
 ***********************************************************/

public class Producer {
    

    public static int [] generate (String mode, int N, int seed) {;
        final Random R = new Random();
        R.setSeed(seed + N);


        if( N <= 1) {
			System.err.println("The list is alredy sorted "+ N);
			System.exit(1);
		}

		final int [] vals = new int [N];


		switch (mode) {
            case "increasing": 
                vals[0]= R.nextInt();
                for(int i =1; i<N; i++) {
                   vals[i] = vals[i-1] + 1;
                }
            break;
            
            case "decreasing":
                vals[0]= R.nextInt();
                for(int i =1; i<N; i++) {
                    vals[i] = vals[i-1] - 1;
             }
			break;
            case "same":
                vals[0]= R.nextInt();
                for(int i = 1; i<N; i++) {
                    vals[i] = vals[0];
                }
            break;

            case "random": 
                for(int i = 0; i<N; i++) {
                    vals[i] = R.nextInt();
                }
            break;

            case "semi-sorted":
            // check how many filled items are in the array
            int count =0;
                for(int i =0; i+3<N; i+=3) {
                    vals[i]=R.nextInt();
                    vals[i+1] = vals[i]+1;
                    vals[i+2] = vals[i]+2;
                }
        //  if the count is smaller then N we need to fill the rest of the array
            if(count!=N) {
                for(int i = count; i<N; i++) {
                    vals[i] = R.nextInt();
                }
            }
            break;
                
        case "equal":
            // the index of the last inserted element in the array 
            int index = 0;
            for(int i = 0; i<N; i=i +N/10) {
                int num = R.nextInt();
                for(int j = 0; j< N/10; j++ ) {
                    vals[index] = num;
                    index++;
                } 
            }
        break;

		default:
			System.err.println("Unknown mode: " + mode);
		}

        
        return vals;
        }
    }