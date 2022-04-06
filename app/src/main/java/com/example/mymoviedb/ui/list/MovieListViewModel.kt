package com.example.mymoviedb.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviedb.model.repository.RepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {
    private val movieDataState = MutableLiveData<AppViewState>()

    fun getLiveData(): LiveData<AppViewState> = movieDataState

    fun getData() {
        movieDataState.value = AppViewState.Loading
        repository.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onSuccess = {
                    movieDataState.postValue(AppViewState.Success(it.results, it.page))
                },
                onError = { e -> AppViewState.Error(Throwable(e.message!!)) }
            )
    }

    fun getDataByPage(page: Int) {
        movieDataState.value = AppViewState.Loading
        repository.getMovies((page + 1).toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onSuccess = {
                    movieDataState.postValue(AppViewState.Success(it.results, it.page))
                },
                onError = { e -> AppViewState.Error(Throwable(e.message!!)) }
            )
    }

    fun getFromLocal() {
        movieDataState
    }
}
