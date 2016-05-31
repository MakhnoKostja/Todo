package ua.kmakhno.iam.listofnotes.ua.kmakhno.iam.listofnotes.todoprojectojects;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by I am on 22.04.2016.
 */
public class TodoDocument implements Serializable {

    private String name;
    private String content;
    private Date createDate;
    private int number = -1;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TodoDocument)) return false;
        TodoDocument document = (TodoDocument) o;
        return number == document.getNumber();
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public TodoDocument() {
    }

    public TodoDocument(String name, String content, Date createDate, int number) {
        this.name = name;
        this.content = content;
        this.createDate = createDate;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return name;
    }
}
