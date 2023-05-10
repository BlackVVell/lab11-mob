package it_school.sumdu.edu.ua.lab11;


import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "documents")
public class Document {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String content;

    public Document(@Nullable String content) {
        this.content = content;
    }

    @Ignore
    public Document(int id, @Nullable String word) {
        this.id = id;
        this.content = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}