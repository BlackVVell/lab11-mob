package it_school.sumdu.edu.ua.lab11;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DocDao {
    @Insert
    void insert(Document document);

    @Update
    void update(Document... document);

    @Query("SELECT * from documents LIMIT 1")
    Document[] getAnyDocument();

    @Query("SELECT * FROM documents WHERE id = 1")
    LiveData<Document> getDocument();
}