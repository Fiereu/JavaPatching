import java.io.File;

public class Main {
    private static boolean CheckForPremain()
    {
        return false;
    }

    public static void main(String[] args) {
        if(CheckForPremain())
            System.out.println("Method did get Patched");
        else
            System.out.println("Method did not get Patched");

        // Do some stuff

    }
}
