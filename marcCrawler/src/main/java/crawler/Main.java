package crawler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        
        // args[0] points to a file that contains a list of URLs
        // args[1] is the name of the bucket
        // args[2] is the properties file for bucket.
        
        ListCrawler myCrawler = new ListCrawler(args[1],args[2]);
  
        List<URL> urlList =  getURLListFromFile(args[0]);
        
        URLSource listSource = new URLSource(urlList);
        
        myCrawler.crawl(listSource, Paths.get(args[1]));
        
    }

    private static List<URL> getURLListFromFile(String path) throws IOException{
        
        File urlListFile = new File(path);
        
        Scanner fileScanner = new Scanner(urlListFile);
        
        LinkedList<URL> urlList = new LinkedList<URL>();
        
        while(fileScanner.hasNextLine()){
            urlList.add(new URL(fileScanner.nextLine()));
        }
        
        fileScanner.close();
        
        return urlList;
    }

}
