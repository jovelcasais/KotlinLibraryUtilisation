package com.jovelcasais.kotlinlibraryutilisation.utilities.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jovelcasais.kotlinlibraryutilisation.api.repo.ApiRepo
import com.jovelcasais.kotlinlibraryutilisation.api.state.ApiUIState
import com.jovelcasais.kotlinlibraryutilisation.db.models.NewsModel
import com.jovelcasais.kotlinlibraryutilisation.db.repo.NewsRepo
import com.jovelcasais.kotlinlibraryutilisation.ui.uievents.UIEvent
import com.jovelcasais.kotlinlibraryutilisation.utilities.helpers.DataHelper
import com.jovelcasais.kotlinlibraryutilisation.utilities.helpers.GraphHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _mainState = MutableLiveData<ApiUIState<NewsModel>>()
    val mainState: LiveData<ApiUIState<NewsModel>> = _mainState

    private val _newsItemState = MutableStateFlow<List<NewsModel?>>(emptyList());
    val newsItemState : StateFlow<List<NewsModel?>> = _newsItemState

    //var isDialogVisible = mutableStateOf(false)

    var isDialogVisible = mutableStateOf(false)

  /*  var isDialogVisible by mutableStateOf(false)
        private set*/

    private val apiRepo = ApiRepo()
    private val newsRepo : NewsRepo = GraphHelper.newsRepo

    init {
        getNewsItemsFromDB()
    }

    fun onEvent(event: UIEvent){
        when(event){
            is UIEvent.CallApi -> {
                callApi()
            }
        }
    }

    private fun callApi(){

        _mainState.value = ApiUIState.Loading
        apiRepo.getNews(DataHelper.getApiParam(), { items ->
            viewModelScope.launch {
                newsRepo.upsert(items)
                _mainState.value = ApiUIState.Success(NewsModel())
            }
        }) { error ->
            isDialogVisible.value = true
            _mainState.value = ApiUIState.Error(error)
        }
    }

     fun getNewsItemsFromDB(){
        viewModelScope.launch {
            newsRepo.getAllItems().collect{
                _newsItemState.value = it
            }
        }
    }

    fun showDialog() {
        //isDialogVisible = true
    }

    fun hideDialog() {
       // isDialogVisible = false
    }

}