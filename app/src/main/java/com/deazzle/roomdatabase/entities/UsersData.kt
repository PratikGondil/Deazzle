package com.deazzle.roomdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by pratikg on 2/8/18.
 */
@Entity(tableName = "users",primaryKeys = ["name"])
class UsersData {
    @ColumnInfo(name = "name")
    lateinit var name: String

    @ColumnInfo(name = "location")
    var location: String = ""

    @ColumnInfo(name = "image")
    lateinit var picture: String


    @ColumnInfo(name = "accept")
     var accept: Boolean = false

}