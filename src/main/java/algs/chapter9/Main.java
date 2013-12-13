package algs.chapter9;

import java.io.File;

/**
 * Load up information from file and show line segment intersections.
 *
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        // locate in resources
        String locDirect = "D:\\Pulpit\\JAVA applications\\ZamiatanieProstaTesty\\src\\main\\resources\\algs\\chapter9";
        //przepraszam, ale ta relatywna sciezka w kodzie mnie wkurza, nie moge  :(

//        String loc = "resources" + java.io.File.separatorChar +
//                "algs\\chapter9" + java.io.File.separatorChar;
        File f = new File(locDirect, "data1.txt");

        Debug d = new Debug();
        d.executeFile(f);

        System.out.println("-----------------------");

        f = new File(locDirect, "data2.txt");
        d.executeFile(f);
    }
}
