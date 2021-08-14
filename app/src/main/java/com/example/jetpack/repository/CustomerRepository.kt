package com.example.jetpack.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.jetpack.dao.CustomerDao
import com.example.jetpack.datamodels.AppDatabase
import com.example.jetpack.datamodels.Customer

class CustomerRepository(application: Application) {
    private var customerDao: CustomerDao

    init {
        val database = AppDatabase.getDatabase(application)
        customerDao = database.customerDao()
    }

    val readAllCustomer: LiveData<List<Customer>> = customerDao.fetchAllCustomer()
    suspend fun insertUser(customer: Customer) {
        customerDao.insertCustomer(customer)
    }

    suspend fun deleteCustomerById(id: Int) {
        customerDao.deleteCustomerById(id)
    }

    init {
        val database = AppDatabase.getDatabase(application)
        customerDao = database.customerDao()
    }
}