/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.progtools.xml.description;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Описание поля комментария
 *
 * @author deonisius
 */
@XStreamAlias("field")
public class Field {

    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String type;
    @XStreamAsAttribute
    private String title;

    public Field() {
    }

    public Field(String name, String type, String title) {
        this.name = name;
        this.type = type;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }
}
