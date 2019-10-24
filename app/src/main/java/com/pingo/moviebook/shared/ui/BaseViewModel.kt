package com.pingo.moviebook.shared.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pingo.moviebook.shared.utils.ErrorHandler
import okhttp3.ResponseBody

/**
 * Base View model
 * @property showProgress MutableLiveData<BannerUIModel>
 * @property connectionError MutableLiveData<Boolean>
 * @constructor
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * controls loading/progress
     */
    val showProgress = MutableLiveData<Boolean>()

    /**
     * Show/Hide connection error dialog
     */
    val connectionError = MutableLiveData<Boolean>()

    /**
     * Show/Hide connection error dialog
     */
    val errorMessage = MutableLiveData<String>()


    /**
     * Parse error if needed
     * @param error Throwable?
     */
    protected open fun onBaseError(error: Throwable?) {
        showProgress.postValue(false)
        when (val errorStr = ErrorHandler.getError(error)) {
            ErrorHandler.INTERNET_ERROR -> connectionError.postValue(true)
            else -> errorMessage.postValue(errorStr)
        }
    }

    /**
     * Parse error
     * @param error ResponseBody?
     */
    protected open fun onBaseError(error: ResponseBody?) {
        showProgress.postValue( false)
        val errorStr = ErrorHandler.getError(error?.string())
        errorMessage.postValue(errorStr)
    }

}