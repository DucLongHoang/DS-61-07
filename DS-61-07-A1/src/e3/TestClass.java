package e3;

public class TestClass {
    public static void main(String[] args){

        Clock clock1 = new Clock(16, 30, 29);
        Clock clock2 = new Clock(4, 29, 29, Period.PM);
        Clock clock3 = new Clock("16:30:28");
        Clock clock4 = new Clock("04:30:29 PM");
        System.out.println(clock1.equals(clock2));
        System.out.println(clock3.equals(clock4));
        System.out.println(clock2.equals(clock3));
        System.out.println(clock1.equals(clock4));
        System.out.println(clock1.hashCode() + " " + clock2.hashCode() + " " + clock3.hashCode() + " " + clock4.hashCode());
    }
}
