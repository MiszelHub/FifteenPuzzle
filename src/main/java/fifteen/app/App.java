package fifteen.app;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException {
        String filePath = "";
        String algorithm ="";
        String strategy = "";

        try
        {
            filePath = args[0];
            algorithm = args[1];
            strategy = args[2];

        }catch (Exception e)
        {
            e.getMessage();
        }


        System.out.println( "FilePath : "+filePath);
        System.out.println( "FilePath : "+algorithm);
        System.out.println( "FilePath : "+strategy);

    }
}
