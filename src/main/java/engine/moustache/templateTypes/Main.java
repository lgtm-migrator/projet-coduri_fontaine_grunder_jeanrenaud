package engine.moustache.templateTypes;

public class Main {
    private String author;
    private String title;
    private String subtitle;

    public Main(final String author, final String title, final String subtitle) {
        this.author = author;
        this.title = title;
        this.subtitle = subtitle;
    }

    /**
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(final String author) {
        this.author = author;
    }

    /**
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     *
     * @return the subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     *
     * @param subtitle
     */
    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }
}
