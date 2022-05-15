package com.k10tetry.kurrency.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = Kurrency.TABLE)
data class Kurrency(
    @PrimaryKey val id: String,
    @ColumnInfo val name: String?,
    @ColumnInfo val symbol: String?,
    @ColumnInfo val rank: Int = 0,
    @SerializedName("is_new") @ColumnInfo val isNew: Boolean = false,
    @SerializedName("is_active") @ColumnInfo val isActive: Boolean = false,
    @ColumnInfo val type: String?
) {
    companion object {
        const val TABLE = "kurrency_table"
    }
}
