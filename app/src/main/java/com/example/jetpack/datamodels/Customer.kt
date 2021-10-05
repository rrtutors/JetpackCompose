package com.example.jetpack.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String = "XYZ",
    @ColumnInfo(name = "gender")
    var gender: String? = "Male",
    @ColumnInfo(name = "email_id")
    val emailId: String? = "xyz@gmail.com"
)