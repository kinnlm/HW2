package HW.HW2;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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
            // End of file reached
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

//    TODO printUniqueVideos(), countVideos(), printCategories()

    // The main method that uses the above
    public static void main(String[] args)
    {
        try
        {
            // Path to the binary file from VideoBuilder
            String filePath = "path/to/the/file";

            // Create a VideoLibrary object
            VideoLibrary myLibrary = new VideoLibrary(filePath);

            // Print all videos
            myLibrary.printVideos();

            // Additional operations as per the assignment...
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
