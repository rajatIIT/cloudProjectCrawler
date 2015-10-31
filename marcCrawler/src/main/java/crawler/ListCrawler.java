package crawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;

public class ListCrawler implements Crawler {

    BucketWriter myWriter;
    
    public ListCrawler(String bucketName,String credentialsFilePath ) {
        myWriter = new BucketWriter(bucketName, credentialsFilePath);
    }

    
    public void crawl(URLSource source, Path OutputDirectory)   {

        while (source.hasNext()) {

            URL nextURL = source.nextURL();
            
            System.out.println("Write URL to Bucket : " + nextURL.toString());

            // crawl this URL
/*
            String pageSource = getSource(nextURL);

            String hostDirectory = OutputDirectory.toString() + File.separator + nextURL.getHost();

            if ((new File(hostDirectory)).exists())
                (new File(hostDirectory)).mkdir();
  */          
            String newFileName = System.currentTimeMillis() + "-" + nextURL.hashCode();
            
            
            try {
                myWriter.write(newFileName,nextURL.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
            /*
            File newPage = new File(newFileName);
            
            try {
                
                // should be write to Bucket
                writeToFile(pageSource,newPage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
            */
        }
    }


    private String getSource(URL next) {
        return null;
    }

}
