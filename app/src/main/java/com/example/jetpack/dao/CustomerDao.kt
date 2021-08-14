package com.example.jetpack.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jetpack.datamodels.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customer")
    fun fetchAllCustomer(): LiveData<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer)

    @Query("DELETE FROM Customer where id = :id")
    suspend fun deleteCustomerById(id: Int)
}