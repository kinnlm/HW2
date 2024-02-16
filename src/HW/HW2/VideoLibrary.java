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
import java.util.Map;
import java.util.Collections;

@SuppressWarnings("ClassHasNoToStringMethod")
public class VideoLibrary implements AutoCloseable
{
    private static final int PAGE_BREAK_LENGTH = 70;
    // The ArrayList field to store videos
    private final ArrayList<Video> videos;

    /**
     * Constructs a VideoLibrary by reading Video objects from
     * the specified file path.
     *
     * @param filePath the path to the file
     *
     * @throws IOException            if an input or output exception occurred
     * @throws ClassNotFoundException if the Video class is not found
     */
    // Constructor
/*    public VideoLibrary(String filePath) throws IOException, ClassNotFoundException
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
            System.out.println("End of File");
        }
        finally
        {
            // Close the input stream
            in.close();
        }
    }*/
    public VideoLibrary(String filePath) throws IOException, ClassNotFoundException
    {
        this.videos = new ArrayList<>();

        // Use try-with-resources to ensure the input stream is closed
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath)))
        {

            // Continuously read videos until an EOFException is thrown
            while (true)
            {
                try
                {
                    Video video = (Video) in.readObject();
                    videos.add(video);

                } catch (EOFException e)
                {
                    // End of file reached
                    break;
                }
            }
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
        Set<Video> uniqueVideos = new HashSet<Video>(videos);
        ArrayList<Video> uniqueVideosList = new ArrayList<Video>(uniqueVideos);

        Collections.sort(uniqueVideosList);
        for (Video video : uniqueVideosList)
        {
            System.out.println(video.getName());
        }
    }
/* without using a set to find unique videos.

    public void printUniqueVideos() {
        videos.stream()
                .distinct()
                .sorted()
                .forEach(video -> System.out.println(video.getName()));
    }*/

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
/*    public int countUniqueVideos()
    {
        int count = 0;
        Set<Video> uniqueVideos = new HashSet<>(videos);
        for (Video video : uniqueVideos)
        {
            count++;
        }
        return count;
    }*/
    public int countUniqueVideos()
    {
        Set<Video> uniqueVideos = new HashSet<>(videos);
        return uniqueVideos.size();
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
     * @return a map with Video name as key, and categories as value.
     */
    public TreeMap<String, TreeSet<String>> createVideoCategoriesMap()
    {
        // Create a TreeMap<String, TreeSet<String>> map.
        TreeMap<String, TreeSet<String>> setTreeMap = new TreeMap<String, TreeSet<String>>();

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

    /**
     * Counts the frequency of each video name in the library.
     *
     * @return a Map where keys are video names and values are the frequencies of these names.
     */
    public Map<String, Integer> countVideoNames()
    {
        TreeMap<String, Integer> nameCounts = new TreeMap<String, Integer>();
        for (Video video : videos)
        {
            String name = video.getName();
            if (nameCounts.containsKey(name))
            {
                nameCounts.put(name, nameCounts.get(name) + 1);
            }
            else
            {
                nameCounts.put(name, 1);
            }
        }
        return nameCounts;
    }

    /**
     * Iterates over the entries in the Video Categories map generated by the createVideoCategoriesMap() method.
     * For each entry, prints the key (representing the video name) and value (representing the set of categories associated with that video) on the console.
     */
    public void printVideoCategoriesMap()
    {
        TreeMap<String, TreeSet<String>> videoCategoriesMap = createVideoCategoriesMap();
        for (Map.Entry<String, TreeSet<String>> entry : videoCategoriesMap.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
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

            System.out.println("-".repeat(PAGE_BREAK_LENGTH));

            videoLibrary.printUniqueVideos();

            System.out.println("-".repeat(PAGE_BREAK_LENGTH));

            System.out.println("Total videos: " + videoLibrary.countVideos());

            System.out.println("-".repeat(PAGE_BREAK_LENGTH));

            System.out.println("Total unique videos: " + videoLibrary.countUniqueVideos());

            System.out.println("-".repeat(PAGE_BREAK_LENGTH));

            Map<String, Integer> videoCounts = videoLibrary.countVideoNames();
            System.out.println("\tVideo Counts");
            for (Map.Entry<String, Integer> entry : videoCounts.entrySet())
            {
                String videoName = entry.getKey();
                Integer count = entry.getValue();
                System.out.println(videoName + "\t" + count);
            }

            System.out.println("-".repeat(PAGE_BREAK_LENGTH));

            videoLibrary.printCategories();

            System.out.println("-".repeat(PAGE_BREAK_LENGTH));

            videoLibrary.printVideoCategoriesMap();

            System.out.println("-".repeat(PAGE_BREAK_LENGTH));

        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void close() throws Exception
    {
        // Shouldn't need to handle an exception if AutoClose does its thing.
    }

}
