package engine.moustache;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import engine.moustache.templateTypes.Main;
import engine.moustache.templateTypes.MainTemplate;

public class TemplateEngine {
    public TemplateEngine(){
        TemplateLoader loader = new FileTemplateLoader("templates", ".html");
        Handlebars handlebars = new Handlebars(loader);
        try {
            Template template = handlebars.compile("template");
            MainTemplate mainTemplate = template.as(MainTemplate.class);
            System.out.println(template.apply(new Main("Luca Coduri", "My website", "This my beautiful website")));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TemplateEngine();
    }
}
