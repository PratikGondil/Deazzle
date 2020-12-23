package kotlincodes.com.retrofitwithkotlin.retrofit

import com.deazzle.modules.apirepository.repositories.model.user.request.ObjUserData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("?results=10")
    fun getUserData(): Call<ObjUserData>

}