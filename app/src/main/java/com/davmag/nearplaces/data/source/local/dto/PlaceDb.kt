package com.davmag.nearplaces.data.source.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate

@Entity
data class PlaceDb (
    @PrimaryKey
    @ColumnInfo(name = "_show_id")
    val id : String,
    val name : String
    //TODO database fields here
)