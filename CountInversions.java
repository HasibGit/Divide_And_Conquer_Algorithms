public class CountInversions {
    public static void main(String[] args) {
        int[] arr = {5,12,7,3,2};
        sort(arr,0,arr.length-1);
    }
    public static void sort(int[] arr,int left,int right){
        if(left < right){
            int mid = (left + right) / 2;
            sort(arr,left,mid);
            sort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }
    public static void merge(int[] arr,int left,int mid,int right){
        int size1 = mid - left + 1;
        int size2 = right - mid;
        int[] arr1 = new int[size1];
        int[] arr2 = new int[size2];
        for(int i = 0;i < size1;i++){
            arr1[i] = arr[left + i];
        }
        for(int j = 0;j < size2;j++){
            arr2[j] = arr[mid + 1 + j];
        }
        int i = 0,j = 0,k = left;

    }
}
