package com.example.path.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "endpoints")
public class EndpointEntity {
    @PrimaryKey int endpointIndex;
    @ColumnInfo(name = "location_text") String locationText;
    @ColumnInfo(name = "latitude") double latitude;
    @ColumnInfo(name = "longitude") double longitude;
}
