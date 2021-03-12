package com.davmag.nearplaces.data.source.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.davmag.nearplaces.data.source.local.dto.PlaceDb

@Dao
interface PlacesDao : BaseDao<PlaceDb> {

    @Query("SELECT * FROM PlaceDb")
    fun get() : DataSource.Factory<Int, PlaceDb>

    @Transaction
    fun cache(vararg places: PlaceDb) {
        removeAll()
        insertSync(*places)
    }

    @Query("DELETE FROM PlaceDb")
    fun removeAll()
}