package com.jmruiz.NoteApp.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "notes")
public class NoteEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;
    private boolean favorite;
    @ColumnInfo(name = "created_at")
    private Date createdAt;
    @ColumnInfo(name = "image_path")
    private String imagePath;

    public NoteEntity(){}

    public NoteEntity(String title, String content, boolean favorite, String imagePath) {
        this.title = title;
        this.content = content;
        this.favorite = favorite;
        this.imagePath = imagePath;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
