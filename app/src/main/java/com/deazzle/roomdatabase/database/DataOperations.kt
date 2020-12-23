package com.deazzle.roomdatabase.database

import com.deazzle.modules.apirepository.repositories.model.user.request.Result
import com.deazzle.roomdatabase.entities.ObjResult
import com.deazzle.roomdatabase.entities.UsersData


class DataOperations {

    fun addCustomerData(objResult: ObjResult) {
        if (dataBase == null) return
        var customerData = UsersData()
        if (objResult != null) {
            customerData = customerDataInstance(objResult)
        }

        dataBase!!.customerDao()!!.insert(customerData)
        dataBase!!.customerDao()!!.loadCustomerData()


    }

    private fun customerDataInstance(objResult: ObjResult): UsersData {
        val customerData = UsersData()
        customerData.name = objResult.name!!
        customerData.location = objResult.location
        customerData.picture = objResult.picture!!
        customerData.accept =false
        return customerData
    }

    companion object {
        private var instance: DataOperations? = null
        private var dataBase: AppDatabase? = null
        fun with(appDataBase: AppDatabase?): DataOperations? {
            if (dataBase == null) dataBase =
                    appDataBase
            if (instance == null) instance =
                    DataOperations()
            return instance
        }

        fun storeDataToDatabase(
                result: Result,
                database: AppDatabase
        ) {
            val objResult = ObjResult()

            objResult.name = result.email
            objResult.location = result.location.city
            objResult.picture = result.picture.large
            objResult.isAccept = false



            with(database)!!.addCustomerData(objResult)
        }
    }
}