// segment tree for range minimum query
public class SegmentTree_MinimumRangeQuery {
    static int[] segmentTree;
    public static void main(String[] args) {
        int[] arr = {5,3,1,7,9,12};
        int height = (int)(Math.log(arr.length) / Math.log(2)) + 1;
        int size = (int) Math.pow(2,height + 1);
        segmentTree = new int[size];
        buildTree(arr,0,0,arr.length-1);
        System.out.println(query(arr,0,0,arr.length-1,3,5));
    }
    public static void buildTree(int[] arr,int pos,int start,int end){
        if(start == end){
            segmentTree[pos] = arr[start];
        }
        else{
            int mid = (start + end) / 2;
            buildTree(arr,2*pos+1,start,mid);
            buildTree(arr,2*pos+2,mid+1,end);
            segmentTree[pos] = Math.min(segmentTree[2*pos + 1],segmentTree[2 * pos + 2]);
        }
    }
    public static int query(int[] arr,int pos,int start,int end,int queryStart,int queryEnd){
        if(queryStart > end || queryEnd < start){
            return Integer.MAX_VALUE;
        }
        else if(queryStart <= start && queryEnd >= end){
            return segmentTree[pos];
        }
        int mid = (start + end) / 2;
        int p1 = query(arr,2*pos+1,start,mid,queryStart,queryEnd);
        int p2 = query(arr,2*pos + 2,mid+1,end,queryStart,queryEnd);
        return Math.min(p1,p2);
    }
}
