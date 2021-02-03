import java.util.Random;

public class RandomizedBinarySearch {
    public static void main(String[] args) {
        int[] arr = {56,76,87,98,123,453,566};
        int searchValue = 76;
        int res = randomizedBinarySearch(arr,0,arr.length-1,searchValue);
        if(res == -1){
            System.out.println("Not found");
        }
        else{
            System.out.println("Found " + searchValue + " at index " + res);
        }
    }
    static int randomizedBinarySearch(int[] arr,int left,int right,int searchValue){
        if(left <= right){
            int randomIndex = getRandomNumber(left,right);
            if(searchValue == arr[randomIndex]){
                return randomIndex;
            }
            else if(searchValue < arr[randomIndex]){
                return randomizedBinarySearch(arr,left,randomIndex-1,searchValue);
            }
            else if(searchValue > arr[randomIndex]){
                return randomizedBinarySearch(arr,randomIndex+1,right,searchValue);
            }
        }
        return -1;
    }
    static int getRandomNumber(int min,int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
