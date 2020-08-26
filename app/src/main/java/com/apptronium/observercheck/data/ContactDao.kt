package com.apptronium.observercheck.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {
    @Insert
    suspend fun createContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT COUNT(*) FROM contact")
    fun getContactCount(): LiveData<Int>

    @Query("DELETE FROM contact WHERE id=(SELECT MIN(id) FROM contact)")
    suspend fun deleteLast()

}