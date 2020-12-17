import java.util.Arrays;

/**
 * ThreePivotQuickSort
 */
public class ThreePivotQuickSort implements Sort {

    /**
     * 
     * @param A int array the swap has to be called on
     * @param i int to swap
     * @param j int to swap
     */    
    public static  void swap (int [] A, int i, int j ) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
 
    /**
     * 
     * @param A int array to sort
     * @param left the left index of where the array starts
     * @param right the right index of where the array ends
     */
    public static void sort(int[] A, int left, int right){

        if (right - left >= 1){

            int a = left + 2;
            int b = left + 2;
            int c = right -1;
            int d = right -1;

            int p = A[left];
            int q = A[left +1];
            int r = A[right];

                if (p > r){
                    int temp = p;
                    p = r;
                    r = temp;
                    A[left] = p;
                    A [right] = r;
                }

                if (p > q){             
                    int temp = p;
                    p = q;
                    q = temp;
                    A[left] = p;
                    A[left + 1] = q;
                }

                if (q > r){
                    int temp = q; 
                    q = r;
                    r = temp;
                    A[left + 1] = q;
                    A[right] = r;
                }
        
            while (b <= c){
                while (A[b] < q && b<=c){
                    if(A[b]< p){
                        //SWAP (A[a], A[b])
                        swap(A,a,b);
                        a = a + 1;
                    }
                    b = b + 1;
                }

                while (A[c] > q && b<= c){
                    if (A[c] > r){
                        //SWAP (A[c], A[d])
                        swap(A, c, d);
                        d = d - 1;
                    }
                    c = c - 1;
                }
                if (b <= c){
                    if (A[b] > r){
                        if (A[c] < p){
                            // swap (A[b],A[a]), swap (A[a],A[c])
                            swap(A, b, a);
                            swap(A, a, c);
                            a = a +1;
                        }
                        else {
                            //swap(A[b],A[c])
                            swap(A, b, c);
                        }
                        //swap(A[c],A[d])
                        swap(A, c, d);
                        b = b +1;
                        c = c-1;
                        d = d-1;
                    }
                    else {
                        if (A[c] < p){
                            //swap(A[b],A[a]), swap(A[a],A[c])
                            swap(A, b, a);
                            swap(A, a, c);
                            a = a +1;
                        }
                        else {
                            //swap(A[b],A[c])
                            swap(A, b, c);
                        }
                        b = b + 1;
                        c = c - 1;
                    }
                }
            }
            a = a - 1;
            b = b - 1;
            c = c + 1; 
            d = d + 1;
            //swap(A[left + 1],A[a]), swap(A[a],A[b])
            swap(A, left+1, a);
            swap(A, a, b);
            a = a - 1;

            //swap(A[left],A[a]), swap(A[right],A[d])
            swap(A, left, a);
            swap(A, right, d);

            sort(A, left, a-1);
            sort(A, a+1, b-1);
            sort(A, b+1, d-1);
            sort(A, d+1, right);
        }
    }

    public static void main(String[] args) {
        // int[] A = {130, 15, 13, 110, 12, -125, 126, 14};
        // int[] A = {-130, -15, 13, 110, 12, -125, 126, 14, 345, -23, 5, 7, 10, 43};
        int[] A = {-130, -15, 13, 110, 12, -125, 126, 14, 345, -23, 5, 7, 10, 43, 345, -14535, 45,234, 8674,1,-5, 11, 75, 999, 2096, 56, -18, 12, 0, 675, 999, 78, -19, 555, -333, 2};

        sort(A, 0, A.length-1);
        System.out.println(Arrays.toString(A));
    }
}