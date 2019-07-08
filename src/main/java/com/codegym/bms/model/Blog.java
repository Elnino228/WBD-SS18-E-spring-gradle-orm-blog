package com.codegym.bms.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Date time;



    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Blog(){}

    public Blog(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.time=new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Category getCategory() {
        return category;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
