package com.davmag.nearplaces.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davmag.nearplaces.data.source.local.dao.PlacesDao
import com.davmag.nearplaces.data.source.local.dto.PlaceDb
import com.davmag.nearplaces.data.source.local.util.RoomConverters

@Database(
    entities = [
        PlaceDb::class,
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getPlacesDao() : PlacesDao
}