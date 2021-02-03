public class Square_Root_Decomposition {
    static int[] arr = new int[100000];
    static int[] block = new int[100];
    static int blockSize;
    static void preProcess(int[] input,int size){
       int blockIndex = -1;
       blockSize =  (int)Math.ceil(Math.sqrt(size));
       for(int i = 0;i < size;i++){
           arr[i] = input[i];
           if(i % blockSize == 0){
               blockIndex++;
           }
           block[blockIndex] += arr[i];
       }
    }
    static void update(int index,int value){
        int blockNumber = index / blockSize;
        block[blockNumber] -= arr[index];
        block[blockNumber] += value;
        arr[index] = value;
    }
    static int query(int left,int right){
        int sum = 0;
        while(left < right && left % blockSize != 0 && left != 0){
            sum += arr[left];
            left++;
        }
        while(left + blockSize <= right){
            sum += block[left / blockSize];
            left += blockSize;
        }
        while (left <= right){
            sum += arr[left];
            left++;
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] input = {3,5,1,7,5,8,9,34,2};
        preProcess(input,input.length);
        System.out.println(query(3,6));
    }
}
