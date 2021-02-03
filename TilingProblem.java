import java.util.Scanner;

public class TilingProblem {
   public static class Point{
        int x;
        int y;
        Point(int p,int q){
            this.x = p;
            this.y = q;
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[][] tile = new int[n][n];
        int x = input.nextInt();
        int y = input.nextInt();
        tile[x-1][y-1] = 1;
        Point p = new Point(x-1,y-1);
        fillTile(tile,p);
        for(int i = 0;i < tile.length;i++){
            for(int j = 0;j < tile[0].length;j++){
                System.out.print(tile[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void fillTile(int[][] tile,Point p){
        Point mid = new Point(tile.length/2-1,tile.length/2-1);
        Point a = new Point(mid.x,mid.x);
        Point b = new Point(mid.x,mid.x + 1);
        Point c = new Point(mid.x+1,mid.x);
        Point d = new Point(mid.x+1,mid.x+1);
        if(a.x > mid.x || a.y > mid.y){
            tile[a.x][a.y] = 5;
        }
        if(b.x > mid.x || b.y > mid.y){
            tile[b.x][b.y] = 5;
        }
        if(c.x > mid.x || c.y > mid.y){
            tile[c.x][c.y] = 5;
        }
        if(d.x > mid.x || d.y > mid.y){
            tile[d.x][d.y] = 5;
        }

    }
}

