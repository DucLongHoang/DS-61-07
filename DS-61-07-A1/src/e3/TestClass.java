package e3;

public class TestClass {
    public static void main(String[] args){

        Clock c1 = new Clock("12:00:00 FM");
        System.out.println(c1.getHours12());
        System.out.println(c1.getHours24());
        System.out.println(c1.getMinutes());
        System.out.println(c1.getSeconds());
        System.out.println(c1.getPeriod());
    }
}
