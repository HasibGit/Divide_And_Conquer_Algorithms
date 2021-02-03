public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {45,46,65,87,99,657};
        int searchValue = 99;
        int res = binarySearch(arr,0,arr.length-1,searchValue);
        if(res == -1)
            System.out.println("Not found");
        else{
            System.out.println("Found " + searchValue + " at " + res);
        }
    }
    static int binarySearch(int[] arr,int left,int right,int searchValue){
        if(right >= left){
            int midIndex = (left + right) / 2;
            if(arr[midIndex] == searchValue){
                return midIndex;
            }
            else if(searchValue < arr[midIndex]){
                return binarySearch(arr,left,midIndex-1,searchValue);
            }
            else if(searchValue > arr[midIndex]){
                return binarySearch(arr,midIndex+1,right,searchValue);
            }
        }
        return -1;
    }

}
