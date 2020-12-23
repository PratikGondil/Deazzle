package com.deazzle.roomdatabase.dao

import androidx.room.*
import com.deazzle.roomdatabase.entities.UsersData

/**
 * Created by pratikg on 2/8/18.
 */
@Dao
interface CustomerDao {
    @Insert
    fun insert(customerData: UsersData?)


    @Update
    fun update(customerData: UsersData?)

    @Delete
    fun delete(customerData: UsersData?)

    @Query("Select * FROM users")
    fun loadCustomerData(): List<UsersData?>

    @Query("DELETE FROM users")
    fun delete()
}