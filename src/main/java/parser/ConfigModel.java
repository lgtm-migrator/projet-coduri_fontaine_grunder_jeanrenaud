package parser;

public class ConfigModel {
    private String title;
    private String description;

    @Override
    public String toString() {
        return "ConfigModel{"
               + "title='" + title + '\''
               + ", description='" + description + '\''
               + '}';
    }

    /**
     * The title of the config.
     * @return the title.
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
     * The description of the config.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }
}
