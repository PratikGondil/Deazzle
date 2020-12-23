package com.deazzle.modules.apirepository.repositories.apicallbacks.user.response
import com.deazzle.modules.apirepository.repositories.model.user.request.ObjUserData


interface IGetUserDataResponse {
    fun onSuccessResponse(
        response: ObjUserData?,
        isError: Boolean
    )
    fun onFailureResponse()
}