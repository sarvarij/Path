package com.example.path.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EndpointDAO {
    @Query("SELECT * FROM endpoints")
    List<EndpointEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EndpointEntity... endpointEntities);
}
