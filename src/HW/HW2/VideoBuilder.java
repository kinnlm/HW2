package HW.HW2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VideoBuilder
{
    ArrayList<Video> movies = new ArrayList<>();

    public static void main(String[] args)
    {
        VideoBuilder vb = new VideoBuilder();
        vb.readFile();
        vb.writeBinaryFile();
    }

    private void readFile()
    {
        File videoFile = new File("videos.txt");
        try
        {
            Scanner inFile = new Scanner(videoFile);
            while (inFile.hasNext())
            {
                String line = inFile.nextLine();
                String[] parts = line.split(",");
                Video video = new Video(parts[0].trim(), Integer.parseInt(parts[1].trim()), parts[2].trim());
                movies.add(video);
            }
            inFile.close();
        } catch (FileNotFoundException e)
        {
            System.err.println("Please check your path. The videos.txt file was not found.");
        }
    }

    private void writeBinaryFile()
    {
        ObjectOutputStream output = null;
        try
        {
            output = new ObjectOutputStream(new FileOutputStream("videos.dat"));
            for (int index = 0; index < movies.size(); index++)
            {
                output.writeObject(movies.get(index));
            }
            output.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
