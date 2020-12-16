import java.util.Arrays;

public class QuickSortClassicI implements Sort {
    
    public static void sort(int [] A, int left, int right) {
        int size = A.length;
        if (size < 30){
            InsertionSort.sort(A);
        }
        else{
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
                        int temp = A[i];
                        A[i] = A[j];
                        A[j] = temp;
                        
                    }
                    }
                    // swap with partitioning element
                        int temp = A[i];
                        A[i] = A[right];
                        A[right] = temp;
                    sort(A, left, i-1);
                    sort (A, i+1, right);
                    
            }
        }
    }

    public static void main(String[] args) {
        int [] a = {-50,6, 5, 4, 1, 2, 3};
        int [] b = {-50,6, 5, 4, 1, 2, 3};
        // System.out.println(Arrays.equals(a,b));
        // System.out.println(a.toString());
        sort(a, 1, a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
