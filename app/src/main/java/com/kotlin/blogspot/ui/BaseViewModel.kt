package com.kotlin.blogspot.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<StateEvent, ViewState> : ViewModel()
{

    val TAG: String = "AppDebug"

    // Objects to be changed. That's why they are mutable
    protected val _stateEvent: MutableLiveData<StateEvent> = MutableLiveData()
    protected val _viewState: MutableLiveData<ViewState> = MutableLiveData()


    // This is the one that we will observe
    val viewState: LiveData<ViewState>
        get() = _viewState


    // In this method we used switchMap, which is used to trigger an event when the object it is looking at changed its value.
    // You can see here that we have the handleStateEvent which is going to be an abstract class because we cant the viewmodel extending it to handle whatever event their UI have.
    val dataState: LiveData<DataState<ViewState>> = Transformations
        .switchMap(_stateEvent){stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }


    // Used in our UI to change the value of our StateEvent, then triggers our handleStateEvent
    fun setStateEvent(event: StateEvent){
        _stateEvent.value = event
    }


    //
    fun getCurrentViewStateOrNew(): ViewState{
        val value = viewState.value?.let{
            it
        }?: initNewViewState()
        return value
    }


    abstract fun handleStateEvent(stateEvent: StateEvent): LiveData<DataState<ViewState>>

    abstract fun initNewViewState(): ViewState

}