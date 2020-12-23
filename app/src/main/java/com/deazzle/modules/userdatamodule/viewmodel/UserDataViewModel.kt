package com.deazzle.modules.userdatamodule.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deazzle.modules.apirepository.repositories.apicallbacks.user.response.IGetUserDataResponse
import com.deazzle.modules.apirepository.repositories.frameworkrequest.stats.GetUsersDataRepository
import com.deazzle.modules.apirepository.repositories.model.user.request.ObjUserData
import com.deazzle.modules.covidstatmodule.uiListner.IUserDataHandler
import com.deazzle.modules.covidstatmodule.uiListner.IUserDataView

class UserDataViewModel : ViewModel(), IUserDataHandler,IGetUserDataResponse {
    lateinit var IUserDataView : IUserDataView
    var responseobserver: MutableLiveData<ObjUserData> = MutableLiveData()


    override fun calluserDataAPI() {
        GetUsersDataRepository.apiUserDataReq(this)
    }

    override fun onSuccessResponse(response: ObjUserData?, isError: Boolean) {
     responseobserver.value =response
    }

    override fun onFailureResponse() {


    }


}