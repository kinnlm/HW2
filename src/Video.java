import java.io.Serializable;
import java.util.Objects;

public class Video implements Serializable
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


    public boolean equals(Video vid)
    {
        if (this == vid)
        {
            return true;
        }
        if (vid == null || getClass() != vid.getClass())
        {
            return false;
        }
        return name.equals(vid.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
