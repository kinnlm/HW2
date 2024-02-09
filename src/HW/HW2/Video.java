package HW.HW2;

public class Video {
    private String name;
    private int minutes;
    private String category;

    public Video() {
    }

    public Video(String name, int minutes, String category) {
        this.name = name;
        this.minutes = minutes;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Video: name: " + name + ", minutes: " + minutes + ", category: " + category;
    }
}
