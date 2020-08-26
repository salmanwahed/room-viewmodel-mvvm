package com.apptronium.observercheck.data

import android.content.Context
import androidx.lifecycle.LiveData

class ContactRepository(context: Context) {
    private var dao = AppDB.getInstance(context).contactDao()

    suspend fun insertContact(contact: Contact){
        dao.createContact(contact)
    }

    suspend fun deleteContact(contact: Contact){
        dao.deleteContact(contact)
    }

    fun getContactCount(): LiveData<Int>{
        return dao.getContactCount()
    }

    suspend fun deleteLast(){
        dao.deleteLast()
    }
}