package com.deazzle.modules.userdatamodule.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.deazzle.R
import com.deazzle.databinding.UsersDataBinder
import com.deazzle.modules.apirepository.repositories.model.user.request.ObjUserData
import com.deazzle.modules.covidstatmodule.uiListner.IUserDataView
import com.deazzle.modules.userdatamodule.adapter.UsersAdapter
import com.deazzle.modules.userdatamodule.viewmodel.UserDataViewModel
import com.deazzle.modules.utils.BaseUtils
import com.deazzle.roomdatabase.database.AppDatabase
import com.deazzle.roomdatabase.database.DataOperations
import com.deazzle.roomdatabase.entities.ObjResult
import com.deazzle.roomdatabase.entities.UsersData
import com.facebook.drawee.backends.pipeline.Fresco
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod

class UserDataFragment : Fragment(), IUserDataView, CardStackListener {
    companion object {
        fun newInstance() =
            UserDataFragment()
    }

    private lateinit var userDataViewModel: UserDataViewModel
    lateinit var binder: UsersDataBinder
    lateinit var covidStatView :View
    var userData: List<UsersData> = ArrayList<UsersData>()
    var database: AppDatabase? = null
    private val adapter = UsersAdapter()
    private lateinit var layoutManager: CardStackLayoutManager
    var objResult=ObjResult()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Fresco.initialize(activity)
        userDataViewModel = ViewModelProviders.of(this).get(UserDataViewModel::class.java)
        binder =
            DataBindingUtil.inflate(
                inflater,
                R.layout.main_fragment,
                container,
                false
            )
        binder.setLifecycleOwner(this)
        binder.userViewModel = userDataViewModel
        userDataViewModel.IUserDataView = this
        covidStatView = binder.root
        initView()
        return covidStatView
    }

    private fun initView() {
        layoutManager = CardStackLayoutManager(activity, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }

        binder.stackView.layoutManager = layoutManager
        binder.stackView.adapter = adapter
        binder.stackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
        database = BaseUtils.getDatabase(context!!)
        callGetCurrentUsersApi()
    }



    override fun callGetCurrentUsersApi() {
        userData = database!!.customerDao()!!.loadCustomerData() as List<UsersData>
        if(userData !=null && userData.size>0)
        {
            adapter.setProfiles(userData)
        }else {
            BaseUtils.showProgress(activity!!)
            userDataViewModel.calluserDataAPI()
            observerCurrentUserAPI()
        }
   }

   fun observerCurrentUserAPI() {
        userDataViewModel.responseobserver.observe(
            viewLifecycleOwner,
            Observer { objStat ->
                BaseUtils.stopProgress()
                setData(objStat)


            })
    }

    private fun setData(objdata: ObjUserData) {
        for (result in objdata.results) {
            DataOperations.storeDataToDatabase(result, BaseUtils.getDatabase(context)!!)

        }
        userData = database!!.customerDao()!!.loadCustomerData() as List<UsersData>
        adapter.setProfiles(userData)

    }


    override fun onCardDisappeared(view: View?, position: Int) {
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardRewound() {
    }

}