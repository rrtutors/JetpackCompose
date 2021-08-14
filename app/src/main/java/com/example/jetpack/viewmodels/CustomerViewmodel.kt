package com.example.jetpack.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack.datamodels.AppDatabase
import com.example.jetpack.datamodels.Customer
import com.example.jetpack.repository.CustomerRepository
import kotlinx.coroutines.launch

class CustomerViewmodel(appObj: Application) : AndroidViewModel(appObj) {

   private val customerRepository: CustomerRepository = CustomerRepository(appObj)
    fun fetchAllCustomer(): LiveData<List<Customer>> {
        return customerRepository.readAllCustomer
    }

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch {
            customerRepository.insertUser(customer = customer)
        }

    }

    fun deleteCustomerById(id: Int) {
        viewModelScope.launch {
            customerRepository.deleteCustomerById(id)
        }

    }
}