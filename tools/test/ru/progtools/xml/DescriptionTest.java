package ru.progtools.xml;

import ru.progtools.xml.description.Param;
import ru.progtools.xml.description.Description;
import ru.progtools.xml.description.Log;
import ru.progtools.xml.description.Field;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.Test;
/**
 *
 * @author deonisius
 */
public class DescriptionTest {

    public DescriptionTest() {
    }

    @Test
    public void testSomeMethod() {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(Field.class);
        xStream.processAnnotations(Param.class);
        xStream.processAnnotations(Log.class);
        xStream.processAnnotations(Description.class);
        Field filed = new Field("name", "type",  "title");
        Param param = new Param("name", "type", "title");
        Log log = new Log("when", "who", "changes");
        Description description = new Description("status", "tool", "name", "author", "dtCreate", "comment");
        description.addField(filed);
        description.addLog(log);
        description.addParam(param);
        String string = xStream.toXML(description);
        System.out.println(string);

        description = new Description("status", "tool", "name", "author", "dtCreate", "comment");
        string = xStream.toXML(description);
        System.out.println(string);
    }

}
