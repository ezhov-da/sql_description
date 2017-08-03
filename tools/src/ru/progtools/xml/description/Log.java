package ru.progtools.xml.description;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Описание лога комментария
 * @author deonisius
 */
@XStreamAlias("log")
public class Log {

    @XStreamAsAttribute
    private String dtChange;
    @XStreamAsAttribute
    private String who;
    private String changes;

    public Log() {
    }

    public Log(String dtChange, String who, String changes) {
        this.dtChange = dtChange;
        this.who = who;
        this.changes = changes;
    }

    
    
    public String getDtChange() {
        return dtChange;
    }

    public void setDtChange(String dtChange) {
        this.dtChange = dtChange;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

}
