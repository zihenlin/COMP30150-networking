import java.io.*;
import java.util.*;

public class LocalIO{
    public static void main (String args[]){}

    static void consoleToFile(String path, int[][]data) throws IOException{
        DataOutputStream out = new DataOutputStream(new FileOutputStream(path));

        int[][] sketch = data;

        for(int i = 0; i < sketch.length; i++){
            for(int j = 0; j < sketch[i].length; j++){
                out.writeInt(sketch[i][j]);
            }
        }
        out.flush();

        // while (true){
        //     try{
        //         n = scanner.nextInt();
        //     }catch (InputMismatchException ex){
        //         break;
        //     }

        //     out.writeInt(n);
        // }
        // scanner.close();
        out.close();
    }

    static int[][] filetoConsole(String path) throws IOException{
        DataInputStream in  = new DataInputStream(new FileInputStream(path));
        
        int n; 
        while (in.available() > 0){
            n = in.readInt();
        }
    }

}