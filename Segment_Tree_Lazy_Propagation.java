public class Segment_Tree_Lazy_Propagation {
    static final int max = 1000;
    static int[] segmenttree = new int[max];
    static int[] lazy = new int[max];

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        build(arr,0,arr.length-1,0);
        System.out.println(rangeSumQuery(0,arr.length-1,1,3,0));
        update(0,0,arr.length-1,1,5,10);
        System.out.println(rangeSumQuery(0,arr.length-1,1,3,0));
    }

    public static void build(int[] arr,int start,int end,int pos){
        if(start > end){
            return;
        }

        if(start == end){
            segmenttree[pos] = arr[start]; // leaf node
            return;
        }

        int mid = (start + end) / 2;

        build(arr,start,mid,2 * pos + 1);
        build(arr,mid+1,end,2 * pos + 2);

        segmenttree[pos] = segmenttree[2 * pos + 1] + segmenttree[2 * pos + 2];
    }

    public static void update(int pos,int start,int end,int qstart,int qend,int val){
        if(lazy[pos] != 0){
            segmenttree[pos] += (end - start + 1) * lazy[pos];

            if(start != end){ // not leaf
                lazy[2 * pos + 1] += lazy[pos];
                lazy[2 * pos + 2] += lazy[pos];
            }
            lazy[pos] = 0;
        }

        if(start > end || start > qend || end < qstart){
            return;
        }

        // in range
        if(start >= qstart && end <= qend){
            segmenttree[pos] += (end - start + 1) * val;
            if(start != end){
                lazy[2 * pos + 1] += val;
                lazy[2 * pos + 2] += val;
            }
            return;
        }

        // partial overlap
        int mid = (start + end) / 2;
        update(2 * pos + 1,start,mid,qstart,qend,val);
        update(2 * pos + 2,mid + 1,end,qstart,qend,val);
        segmenttree[pos] += segmenttree[2 * pos + 1] + segmenttree[2 * pos + 2];
    }

    public static int rangeSumQuery(int start,int end,int qstart,int qend, int pos){
        if(lazy[pos] != 0){
            segmenttree[pos] += (end - start + 1) * lazy[pos];
            if(start != end){
                lazy[2 * pos + 1] += lazy[pos];
                lazy[2 * pos + 2] += lazy[pos];
            }
            lazy[pos] = 0;
        }
        // out of range
        if(start > end || start > qend || end < qstart){
            return 0;
        }
        // in range
        if(start >= qstart && end <= qend){
            return segmenttree[pos];
        }
        // partial overlap

        int mid = (start + end) / 2;
        int p1 = rangeSumQuery(start,mid,qstart,qend,2 * pos + 1);
        int p2 = rangeSumQuery(mid + 1,end,qstart,qend,2 * pos + 2);
        return p1 + p2;
    }

}
