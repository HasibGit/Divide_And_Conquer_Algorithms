public class Sparse_Table_RMQ {
    static int[][] sparseTable;
    public static void main(String[] args) {
        int[] arr = {4,1,1,3,2,2};
        buildSparseTable(arr,arr.length);
        System.out.println(rangeMinimumQuery(arr,3,5));
    }
    static void buildSparseTable(int[] arr, int n){
        sparseTable = new int[n][(int)(Math.log(n)/Math.log(2)) + 1];
        for(int i = 0;i < n;i++){
            sparseTable[i][0] = i;
        }
        /*
        starting from j,we are looking for next 2^j elements
        2^0 = 1
        so previously amra first column e minimum index i e hobe
        karon minimum search hocce 1 ta element er moddhe
         */
        for(int j = 1;1 << j <= n;j++){
            for(int i = 0;i + (1 << j) - 1 < n;i++){
                if(arr[sparseTable[i][j-1]] < arr[sparseTable[i + (1 << j-1)][j-1]]){
                    sparseTable[i][j] = sparseTable[i][j-1];
                }
                else{
                    sparseTable[i][j] = sparseTable[i + (1 << j-1)][j-1];
                }
            }
        }
    }
    public static int rangeMinimumQuery(int[]arr,int low, int high) {
        int l = high - low + 1; // number of elements in range
        int k =(int)(Math.log(l) / Math.log(2));
        /*
        first starting from low, we are looking for next 2^k elements,which is alreay there in table

         */
        int num1 = arr[sparseTable[low][k]];
        int left = l - (1 << k);// jotogulo baki ase oi range er 1st index e move
        int num2 = arr[sparseTable[low + left][k]];
        if (num1 <= num2) {
            return num1;
        } else {
            return num2;
        }
    }
}
