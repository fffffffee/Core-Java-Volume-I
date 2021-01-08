package Welcome;

public class Welcome {
    public static void main(String[] args)
    {
        String[] greeting = new String[3];
        greeting[0] = "Welcome to Core Java";
        greeting[1] = "by Li DongXing";
        greeting[2] = "and DB";

        for (String g : greeting)
            System.out.println(g);
    }
}
