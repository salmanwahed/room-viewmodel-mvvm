package com.apptronium.observercheck.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.apptronium.observercheck.data.Contact
import com.apptronium.observercheck.data.ContactRepository
import kotlinx.coroutines.launch

class ContactViewmodel(contextArg: Context): ViewModel() {
    val repository: ContactRepository = ContactRepository(contextArg)


    fun getContactCount(): LiveData<Int> {
        return repository.getContactCount()
    }

    fun insertContact(contact: Contact){
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    fun deleteLastContact(){
        viewModelScope.launch {
            repository.deleteLast()
        }
    }
}