public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {45,76,34,12,77,88,48,63,22};
        quickSort(arr,0,arr.length-1);
        for(int i : arr){
            System.out.print(i + " ");
        }
    }
    static void quickSort(int[] arr,int start,int end){
        if(start < end){
            int pos = partition(arr,start,end);
            quickSort(arr,start,pos-1);
            quickSort(arr,pos+1,end);
        }
    }
    static int partition(int[] arr,int start,int end){
        int i = start - 1;
        int j = start;
        int val = arr[end];
        while(j < end){
            if(arr[j] > val){
                j++;
            }
            else{
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
        for(int t = end-1;t > i;t--){
            arr[t+1] = arr[t];
        }
        arr[i+1] = val;
        return i+1;
    }
}
