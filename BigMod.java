import java.util.Scanner;

public class BigMod {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long a = input.nextLong();
        long b = input.nextLong();
        long m = input.nextLong();
        System.out.println(calculateBigMod(a,b,m));
    }
    static long calculateBigMod(long a,long b,long m){
        if(b == 0){
            return 1 % m;
        }
        long x = calculateBigMod(a,b/2,m);
        x = (x * x) % m;
        if(b%2 == 1){
            x = (x * a) % m;
        }
        return x;
    }
}
