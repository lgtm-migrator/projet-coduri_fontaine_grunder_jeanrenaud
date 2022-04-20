package engine.moustache.templateTypes;

public class Main {
    private String author;
    private String title;
    private String subtitle;

    public Main(String author, String title, String subtitle) {
        this.author = author;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
