import java.util.Arrays;

public class DualPivotQuickSort implements Sort {

    public static  void swap (int [] A, int i, int j ) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
    }
    public static void sort(int[] A, int left, int right) {
        
        //assume a sentinel A[0] = minus infinity
        if (right - left >= 1){
            int p = A [left];
            int q = A [right];
            
            if (p > q){           
                int temp = p;
                p = q;
                q = temp;
                A[left] = p;
                A[right] = q;
            }
            int l = left + 1;
            int g = right -1;
            int k = l;
            
            
            while (k <= g){
                // SWAP A[k] and A[l] and increment l
                if (A[k] < p) {  
                    swap(A, k, l);    
                    l = l +1;
                }
                else {
                    if (A[k] > q){
                        while ((A[g] > q) &&  (k <g)){
                            g = g-1;
                        }
                        // SWAP A[k] and A[g] and decrement g
                       swap(A, k, g);
                        g = g-1;
                        if (A[k] < p ){
                            //SWAP A[k] and A[l] and increment l
                            swap(A, k, l);
                            l = l+1;
                        }
                    }
                }
                k = k +1;
            }
            l = l-1;
            g = g+1;
            
            //2 Swaps
            swap(A, left, l);
            // int temp = A[left];
            // A[left] = A[l];
            // A[l] = temp;

            swap(A, right, g);
            // int dummy = A[right];
            // A[right] = A[g];
            // A[g] = dummy;
            
            sort(A, left, l-1);
            sort(A, l+1, g-1);
            sort(A, g+1, right);

        }
    }


    public static void main(String[] args) {
        int[] A = {130, 15, 13, 110, 12, -125, 126, 14};

        DualPivotQuickSort.sort(A, 0, A.length-1);
        System.out.println(Arrays.toString(A));
    }
    
}
