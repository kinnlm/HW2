package HW.HW2;
/**
 * Class: VideoLibrary.JAVA
 *
 * @Author Logan Kinnaird
 * @Version Course ITEC 3150-3 Spring 2024
 * Written: Feb 2, 2024
 * <p>
 * The VideoLibrary class represents a library of videos.
 * For each video, it stores it in a list and provides
 * methods for printing, and counting the videos.
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Map;


public class VideoLibrary
{
    // The ArrayList field to store videos
    private ArrayList<Video> videos;

    /**
     * Constructs a VideoLibrary by reading Video objects from
     * the specified file path.
     *
     * @param filePath the path to the file
     * @throws IOException            if an input or output exception occurred
     * @throws ClassNotFoundException if the Video class is not found
     */
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

    /**
     * Prints all videos in the library.
     */
    public void printVideos()
    {
        for (Video video : videos)
        {
            System.out.println(video);
        }
    }

    /**
     * Prints all videos with unique names in the library.
     */
    public void printUniqueVideos()
    {
        Set<Video> uniqueVideos = new HashSet<>(videos);
        for (Video video : uniqueVideos)
        {
            System.out.println(video);
        }
    }

    /**
     * Counts all videos in the library.
     *
     * @return the amount videos
     */
    public int countVideos()
    {
        return videos.size();
    }

    /**
     * Counts all unique videos in the library.
     *
     * @return the amount unique videos
     */
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

    /**
     * Prints all unique categories of videos in the library.
     */
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

    /**
     * Creates a map of Videos sorted by categories.
     *
     * @return a map with Video name as key, and categories as value
     */
    public TreeMap<String, TreeSet<String>> createVideoCategoriesMap()
    {
        // Create a TreeMap<String, TreeSet<String>> map.
        TreeMap<String, TreeSet<String>> setTreeMap = new TreeMap<>();

// Get the unique set of video names using an iterator
        Set<String> uniqueNames = new HashSet<String>();
        for (Video video : videos)
        {
            uniqueNames.add(video.getName());
        }

// For each unique video name…
        for (String name : uniqueNames)
        {
            TreeSet<String> categories = new TreeSet<String>();

            // Loop through all videos
            for (Video video : videos)
            {

                // If the video has the current unique name…
                if (video.getName().equals(name))
                {

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

            System.out.println("-".repeat(50));

            videoLibrary.printUniqueVideos();

            System.out.println("-".repeat(50));

            System.out.println("Total videos: " + videoLibrary.countVideos());

            System.out.println("-".repeat(50));

            System.out.println("Total unique videos: " + videoLibrary.countUniqueVideos());

            System.out.println("-".repeat(50));

            videoLibrary.printCategories();

            System.out.println("-".repeat(50));

            TreeMap<String, TreeSet<String>> videoCategoriesMap = videoLibrary.createVideoCategoriesMap();
            for (Map.Entry<String, TreeSet<String>> entry : videoCategoriesMap.entrySet())
            {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            System.out.println("-".repeat(50));

        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }


    }
}
