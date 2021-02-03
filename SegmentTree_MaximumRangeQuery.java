//segment tree range maximum query

// lazy propagation implemented
public class SegmentTree_MaximumRangeQuery {
    static int[] segmentTree;
    static int[] lazy;
    public static void main(String[] args) {
        int[] arr = {-1,2,4,-3};
        int height = (int)(Math.log(arr.length)/Math.log(2)) + 1;
        int size = (int) Math.pow(2,height+1);
        segmentTree = new int[size];
        lazy = new int[size];
        buildTree(arr,0,0,arr.length-1);
        updateSegmentTreeRangeLazy(segmentTree,lazy,0,2,5,0,arr.length,0);
        for(int i : segmentTree){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i : lazy){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(rangeMaximumQueryLazy(segmentTree,lazy,0,2,0,arr.length,0));
    }
    public static void buildTree(int[] arr,int pos,int start,int end){
        if(start == end){
            segmentTree[pos] = arr[start];
        }
        else{
            int mid = (start + end) / 2;
            buildTree(arr,2*pos+1,start,mid);
            buildTree(arr,2 * pos + 2,mid+1,end);
            segmentTree[pos] = Math.max(segmentTree[2 * pos + 1],segmentTree[2 * pos + 2]);
        }
    }
    public static int query(int[] arr,int pos,int start,int end,int queryStart,int queryEnd){
        if(queryStart > end || queryEnd < start){
            return Integer.MIN_VALUE;
        }
        else if(queryStart <= start && queryEnd >= end){
            return segmentTree[pos];
        }
        int mid = (start + end) / 2;
        int p1 = query(arr,2 * pos + 1,start,mid,queryStart,queryEnd);
        int p2 = query(arr,2 * pos + 2,mid+1,end,queryStart,queryEnd);
        return Math.max(p1,p2);
    }
    public static void updateSegmentTreeRangeLazy(int[] segmentTree,int[] lazy,int startRange,int endRange,int updateValue,int start,int end,int pos){
        if(start > end){
            return;
        }
        if(lazy[pos] != 0){
            segmentTree[pos] += lazy[pos];
            if(start != end){ //not a lean node
                 lazy[2 * pos + 1] += lazy[pos];
                 lazy[2 * pos + 2] += lazy[pos];
            }
            lazy[pos] = 0;
        }
        if(startRange > end || endRange < start){
            return;
        }
        if(startRange <= start && endRange >= end){ // total overlap
            segmentTree[pos] += updateValue;
            if(start != end){ // not a leaf node
                lazy[2 * pos + 1] += updateValue;
                lazy[2 * pos + 2] += updateValue;
            }
            return;
        }
        // otherwise partial overlap so look both left and right
        int mid = (start + end) / 2;
        updateSegmentTreeRangeLazy(segmentTree,lazy,startRange,endRange,updateValue,start,mid,2 * pos + 1);
        updateSegmentTreeRangeLazy(segmentTree,lazy,startRange,endRange,updateValue,mid+1,end,2 * pos + 2);
        segmentTree[pos] = Math.max(segmentTree[2 * pos + 1],segmentTree[2 * pos + 2]);
    }

    public static int rangeMaximumQueryLazy(int[] segmentTree,int[] lazy,int queryStart,int queryEnd,int start,int end,int pos){
        if(start > end){
            return Integer.MIN_VALUE;
        }
        //make sure all propagation is done at pos. If not update tree
        //at pos and mark its children for lazy propagation.
        if(lazy[pos] != 0){
            segmentTree[pos] += lazy[pos];
            if(start != end){
                lazy[2 * pos + 1] += lazy[pos];
                lazy[2 * pos + 2] += lazy[pos];
            }
            lazy[pos] = 0;
        }
        // no overlap
        if(queryStart > end || queryEnd < start){
            return Integer.MIN_VALUE;
        }
        // total overlap
        if(queryStart <= start && queryEnd >= end){
            return segmentTree[pos];
        }
        // partial overlap

        int mid = (start + end) / 2;
        return Math.max(rangeMaximumQueryLazy(segmentTree,lazy,queryStart,queryEnd,start,mid,2 * pos + 1),rangeMaximumQueryLazy(segmentTree,lazy,queryStart,queryEnd,mid+1,end,2 * pos + 2));
    }
}
