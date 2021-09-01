package com.proway.diexample.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.diexample.model.User
import com.proway.diexample.repository.GithubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val repository: GithubUser) : ViewModel() {

    private val _repositories = MutableLiveData<List<User>>()
    val repositories: LiveData<List<User>> = _repositories

    private val _page = MutableLiveData(0)
    val page: LiveData<Int> = _page

    fun fetchUsers() {
        repository.fetchUsers() { response, _ ->
            response?.let { resp ->
                _repositories.value = resp
            }
        }
    }

    fun nextPage() {
        _page.value = _page.value!! + 1
    }

}