package HW.HW2;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Iterator;
public class VideoLibrary
{
    // The ArrayList field to store videos
    private ArrayList<Video> videos;

    // Constructor
    public VideoLibrary(String filePath) throws IOException, ClassNotFoundException
    {
        // initialize the ArrayList
        videos = new ArrayList<>();

        // Create an ObjectInputStream
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));

        try
        {
            // Continuously read videos until an EOFException is thrown
            while (true)
            {
                Video video = (Video) in.readObject();
                videos.add(video);
            }
        } catch (EOFException e)
        {
            System.out.println("End of File Exception");
        }
        finally
        {
            // Close the input stream
            in.close();
        }
    }

    // Method to print all videos
    public void printVideos()
    {
        for (Video video : videos)
        {
            System.out.println(video);
        }
    }

    public void printUniqueVideos()
    {
        Set<Video> uniqueVideos = new HashSet<>(videos);
        for (Video video : uniqueVideos)
        {
            System.out.println(video);
        }
    }

    public int countVideos()
    {
        return videos.size();
    }

    public int countUniqueVideos()
    {
        int count = 0;
        Set<Video> uniqueVideos = new HashSet<>(videos);
        for (Video video : uniqueVideos)
        {
            count++;
        }
        return count;
    }

    public void printCategories()
    {
        Set<String> categories = new HashSet<>();
        for (Video video : videos)
        {
            categories.add(video.getCategory());
        }
        for (String category : categories)
        {
            System.out.println(category);
        }
    }
    // TODO Create a  TreeMap<String, TreeSet<String>> to contain the
    //  name and a set that contains all of the categories for each video.
    //  This must be done using an iterator over the video names set.
    //  Loop through this set and create a TreeSet<String> to hold the categories.
    //  You will need a loop to go through the list from the binary file.
    //  Add the categories to the set.  After the inner loop completes,
    //  put the name and set into a map.  Return the map.
    public TreeMap<String, TreeSet<String>> createVideoCategoriesMap()
    {
        // Create a TreeMap<String, TreeSet<String>> map.
        TreeMap<String, TreeSet<String>> setTreeMap = new TreeMap<>();

// Get the unique set of video names using an iterator
        Set<String> uniqueNames = new HashSet<String>();
        for (Video video : videos) {
            uniqueNames.add(video.getName());
        }

// For each unique video name…
        for (String name : uniqueNames) {
            TreeSet<String> categories = new TreeSet<String>();

            // Loop through all videos
            for (Video video : videos) {

                // If the video has the current unique name…
                if (video.getName().equals(name)) {

                    // …add its category to the TreeSet
                    categories.add(video.getCategory());
                }
            }

            // Put the name and categories into the map
            setTreeMap.put(name, categories);
        }

        //return the map
        return setTreeMap;
    }


    // The main method that uses the above
    public static void main(String[] args)
    {
        try
        {
            // Path to the binary file from VideoBuilder
            String filePath = "videos.dat";

            // Create a VideoLibrary object
            VideoLibrary videoLibrary = new VideoLibrary(filePath);

            // Print all videos
            videoLibrary.printVideos();







        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
