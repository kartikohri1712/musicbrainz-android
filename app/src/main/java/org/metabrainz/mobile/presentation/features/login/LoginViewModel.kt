package org.metabrainz.mobile.presentation.features.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.metabrainz.mobile.data.repository.LoginRepository
import org.metabrainz.mobile.data.sources.api.entities.AccessToken
import org.metabrainz.mobile.data.sources.api.entities.userdata.UserInfo

class LoginViewModel @ViewModelInject constructor(private var repository: LoginRepository?) : ViewModel() {
    var accessTokenLiveData: MutableLiveData<AccessToken?>? = null
        get() {
            if (field == null) field = repository!!.accessTokenLiveData
            return field
        }
        private set
    var userInfoLiveData: MutableLiveData<UserInfo?>? = null
        get() {
            if (field == null) field = repository!!.userInfoLiveData
            return field
        }
        private set

    fun fetchAccessToken(code: String?) {
        repository!!.fetchAccessToken(code)
    }

    fun fetchUserInfo() {
        repository!!.fetchUserInfo()
    }

    override fun onCleared() {
        super.onCleared()
        repository = null
    }
}