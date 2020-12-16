import java.util.Arrays;

public class QuickSortClassic implements Sort {

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
    public static void sort(int [] A, int left, int right) {

        if((right - left) >=1) {

            int p = A[right];
            int i = left-1;
            int j= right;

            while ( j>i ) {

                // first increment i and then check
                    while (A[++i] < p) {
                        if(i==right) break;
                    }
                    // first decrement j and then check    
                    while (A[--j] > p) {
                        if(j==left) break;
                    }
                    // swap elements on on positions i and j when A[i] > p and A[j] < p
                    if (j > i ) {
                        swap(A,i,j);
                    }
            }
                 // swap with partitioning element
                   swap(A, i, right);
                  
                   sort(A, left, i-1);
                   sort (A, i+1, right);
                 
        }
    }

    public static void main(String[] args) {
        int [] a = {-50,6, 5, 4, 1, 2, 3};
        int [] b = {-50,6, 5, 4, 1, 2, 3};
        System.out.println(Arrays.equals(a,b));
        System.out.println(a.toString());
        sort(a, 1, a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
