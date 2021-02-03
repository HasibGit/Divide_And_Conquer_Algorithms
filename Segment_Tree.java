// Segment tree for sum of range query
public class Segment_Tree {
    static int[] segmentTree;
    public static void main(String[] args) {
        int[] arr = {5,3,8,12};
        int height = (int) (Math.log(arr.length) / Math.log(2)) + 1;
        int size = (int) Math.pow(2,height + 1);
        segmentTree = new int[size];
        buildTree(arr,0,0,arr.length-1);
        for(int i : segmentTree){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(query(0,0,arr.length-1,0,3));
    }
    public static void buildTree(int[] arr,int pos,int start,int end){
        if(start == end){
            segmentTree[pos] = arr[start];
        }
        else{
            int mid = (start + end) / 2;
            buildTree(arr,2*pos+1,start,mid);
            buildTree(arr,2*pos + 2,mid+1,end);
            segmentTree[pos] = segmentTree[2 * pos + 1] + segmentTree[2 * pos + 2];
        }
    }
    public static int query(int pos,int start,int end,int queryStart,int queryEnd){
        if(queryStart > end || queryEnd < start){ // totally outta range
            return 0; // ai range e sum pabo na.so,function return
        }
        else if(queryStart <= start && queryEnd >= end){
            return segmentTree[pos];     // completely overlap
        }
        int mid =(int) (start + end) / 2;
        int p1 = query(2 * pos + 1,start,mid,queryStart,queryEnd); // [artial overlap
        int p2 = query(2 * pos + 2,mid + 1,end,queryStart,queryEnd);
        return p1 + p2;
    }
    public static void update(int[] arr,int pos,int index,int value,int start,int end){
        if(start == end){
            segmentTree[pos] += value;
            arr[start] += value;
        }
        else{
            int mid = (start + end) / 2;
            if(index >= start && index <= mid){
                update(arr,2 * pos + 1,index,value,start,mid);
            }
            else
                update(arr,2 * pos + 2,index,value,start,mid);
            segmentTree[pos] = segmentTree[2 * pos + 1] + segmentTree[2 * pos + 2];
        }
    }
}
