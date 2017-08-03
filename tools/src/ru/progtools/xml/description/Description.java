package ru.progtools.xml.description;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 * Описание
 *
 * @author deonisius
 */
@XStreamAlias("description")
public class Description {

    @XStreamAsAttribute
    private String status;
    @XStreamAsAttribute
    private String tool;
    private String name;
    private String author;
    private String dtCreate;
    private String comment;
    private List<Param> params;
    private List<Field> fields;
    private List<Log> logs;

    public Description() {
        params = new ArrayList<>();
        fields = new ArrayList<>();
        logs = new ArrayList<>();
    }

    public Description(String status, String tool, String name, String author, String dtCreate, String comment) {
        this();
        this.status = status;
        this.tool = tool;
        this.name = name;
        this.author = author;
        this.dtCreate = dtCreate;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(String dtCreate) {
        this.dtCreate = dtCreate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Param> getParams() {
        return params;
    }

    public void addParam(Param param) {
        params.add(param);
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void addLog(Log log) {
        logs.add(log);
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

}
