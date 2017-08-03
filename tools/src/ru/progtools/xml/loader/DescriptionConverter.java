package ru.progtools.xml.loader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import ru.progtools.xml.description.Description;
import ru.progtools.xml.description.Field;
import ru.progtools.xml.description.Log;
import ru.progtools.xml.description.Param;

/**
 *
 * @author deonisius
 */
public class DescriptionConverter {

    private final XStream xStreamXml;
    private final XStream xStreamJson;

    public DescriptionConverter() {
        xStreamXml = new XStream(new DomDriver());
        xStreamXml.processAnnotations(Field.class);
        xStreamXml.processAnnotations(Param.class);
        xStreamXml.processAnnotations(Log.class);
        xStreamXml.processAnnotations(Description.class);
        //xStreamJson = new XStream(new JettisonMappedXmlDriver());
        xStreamJson = new XStream(new JsonHierarchicalStreamDriver());
        
        
        xStreamJson.processAnnotations(Field.class);
        xStreamJson.processAnnotations(Param.class);
        xStreamJson.processAnnotations(Log.class);
        xStreamJson.processAnnotations(Description.class);
    }

    public Description getDescription(String string) {
        Description description = (Description) getObject(string);
        return description;
    }

    public Description getObject(String string) {
        Description description = (Description) xStreamXml.fromXML(string);
        return description;
    }

    public String getJsonDescription(String string) {
        Description description = getObject(string);
        String json = xStreamJson.toXML(description);
        return json;
    }
}
