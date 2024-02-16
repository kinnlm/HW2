package HW.HW2;

import java.io.Serializable;
import java.util.Objects;

public class Video implements Serializable, Comparable<Video>
{
    private String name;
    private int minutes;
    private String category;

    public Video()
    {
    }

    public Video(String name, int minutes, String category)
    {
        this.name = name;
        this.minutes = minutes;
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return "Video: name: " + name + ", minutes: " + minutes + ", category: " + category;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Video video = (Video) o;
        return Objects.equals(name, video.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }


    @Override
    public int compareTo(Video otherVideo)
    {
        return this.name.compareTo(otherVideo.getName());
    }
}
