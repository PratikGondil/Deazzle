package com.deazzle.modules.apirepository.repositories.frameworkrequest.stats
import com.deazzle.modules.apirepository.IUserDataRepository
import com.deazzle.modules.apirepository.repositories.apicallbacks.user.response.IGetUserDataResponse
import com.deazzle.modules.apirepository.repositories.model.user.request.ObjUserData
import com.google.gson.Gson
import kotlincodes.com.retrofitwithkotlin.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object GetUsersDataRepository : IUserDataRepository {

    lateinit var iGetUserDataResponse: IGetUserDataResponse
    private fun getStatData() {
        val call: Call<ObjUserData> = ApiClient.getClient.getUserData()
        call.enqueue(object : Callback<ObjUserData> {

            override fun onResponse(
                call: Call<ObjUserData>,
                response: Response<ObjUserData>
            ) {
                var objUserResponse:ObjUserData = response.body()!!
                iGetUserDataResponse.onSuccessResponse(objUserResponse,false)
            }

            override fun onFailure(call: Call<ObjUserData>?, t: Throwable?) {
                iGetUserDataResponse.onFailureResponse()

            }

        })
    }

    override fun apiUserDataReq(
        userDataResponseListener: IGetUserDataResponse
    ) {

        this.iGetUserDataResponse = userDataResponseListener
        getStatData()

    }


    fun getDeserializeResponse(response: String): ObjUserData? {
        return Gson().fromJson(
            response,
            ObjUserData::class.java
        )
    }

}