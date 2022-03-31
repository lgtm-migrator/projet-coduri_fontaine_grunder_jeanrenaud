package commands;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import picocli.CommandLine;

import java.io.FileReader;
import java.io.IOException;

@CommandLine.Command(
        name = "version",
        aliases = {"v"}
)
public class VersionCommand implements Runnable {
    @Override
    public void run() {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = null;
        try {
            model = reader.read(new FileReader("pom.xml"));
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(model.getVersion());
    }
}
