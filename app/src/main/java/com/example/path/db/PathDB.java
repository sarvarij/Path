package com.example.path.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EndpointEntity.class}, version = 1)
public abstract class PathDB extends RoomDatabase {
    public abstract EndpointDAO getEndpointDAO();
}
