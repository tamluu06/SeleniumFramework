package helpers;

import java.io.File;

public class SystemsHelper {

    //Function to project directory
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;

    }

}
