import java.io.*;
import java.util.*;

public class LocalIO{
    public static void main (String args[]){}

    static void consoleToFile(String path) throws IOException{
        DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
        Scanner scanner = new Scanner(System.in);

        int n;

        while (true){
            try{
                n = scanner.nextInt();
            }catch (InputMismatchException ex){
                break;
            }

            out.writeInt(n);
        }
        scanner.close();
        out.close();
    }

    static void filetoConsole(String path) throws IOException{
        DataInputStream in  = new DataInputStream(new FileInputStream(path));
        
        int n; 
        while (in.available() > 0){
            n = in.readInt();
        }
    }

}