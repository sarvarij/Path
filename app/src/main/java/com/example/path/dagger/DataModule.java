package com.example.path.dagger;

import android.content.Context;

import androidx.room.Room;

import com.example.path.db.EndpointDAO;
import com.example.path.db.PathDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    private Context applicationContext;

    public DataModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Singleton
    @Provides
    PathDB providePathDB() {
        return Room.databaseBuilder(applicationContext, PathDB.class, "PATH_DB").build();
    }

    @Provides
    EndpointDAO provideEndpointDAO(PathDB pathDB) {
        return pathDB.getEndpointDAO();
    }
}
