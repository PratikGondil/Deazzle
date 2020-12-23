package com.deazzle.modules.apirepository
import com.deazzle.modules.apirepository.repositories.apicallbacks.user.response.IGetUserDataResponse


/*
* Temporary Callback
 */
interface IUserDataRepository {

    fun apiUserDataReq(
        userDataResponseListener: IGetUserDataResponse
    )



}