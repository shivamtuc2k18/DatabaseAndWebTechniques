package com.dbconnection.dbconnection.Feed;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name ="rssfeed")
public class Feed {

    @Id
    @Column(name = "id",unique = true)
    private Integer id;
    @Column(name = "title",unique = true)
    private String title;
    @Column(name = "link")
    private String link;
    @Column(name = "date")
    private Date date;
    @Column(name="provider")
    private String provider;
    @Column(name="inserteddate")
    private LocalDateTime inserteddate;

    /**
     * Constructor
     * */

    public Feed(Integer id, String title, String link, Date date, String provider, LocalDateTime inserteddate) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.date = date;
        this.provider = provider;
        this.inserteddate = inserteddate;
    }

    public Feed() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public LocalDateTime getInserteddate() {
        return inserteddate;
    }
    public void setInserteddate(LocalDateTime inserteddate) {
        this.inserteddate = inserteddate;
    }
}
