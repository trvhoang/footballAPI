package application;

public class Calculation {
//    static: đã khởi tạo sẵn, chỉ cần gọi là dùng đc.
// Bỏ static thì phải khởi tạo instance: Calculation calc = new Calculation ()..
    public static int sum(int a, int b){
        return a+b;
    }

    public static int sub(int a, int b){
        return a - b;
    }

    public static int mul(int a, int b){
        return a * b;
    }
}
