package com.odukabdulbasit.bitcointicker.listsearch

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.odukabdulbasit.bitcointicker.api.CoinApi
import com.odukabdulbasit.bitcointicker.detail.DetailModel
import kotlinx.coroutines.launch

class ListSearchViewModel(val application: Application) : ViewModel(){

    private val _coinsIdenties = MutableLiveData<List<ListSearchModel>>()
    val coinsIdenties: LiveData<List<ListSearchModel>>
        get() = _coinsIdenties

    private val _searchedCoinsId = MutableLiveData<ListSearchModel?>()
    val searchedCoinsId: LiveData<ListSearchModel?>
        get() = _searchedCoinsId

    private val _coinsDetail = MutableLiveData<DetailModel>()
    val coinsDetail: LiveData<DetailModel>
        get() = _coinsDetail


    init {
        getCoinList()
    }

    private fun getCoinList() {
        viewModelScope.launch {
            try {
                _coinsIdenties.value =  CoinApi.retrofitCoinService.getCoinList()
                //var listResult = CoinApi.retrofitCoinService.getCoinList()
                //Log.i("ListSearchViewModel", "${listResult[1].name}")
                //_response.value = "Success: ${listResult.size} Coins retrieved"
            } catch (e: Exception) {
               // _response.value = "Failure: ${e.message}"
                Log.i("ListSearchViewModel", "${e.message}")
            }
        }
    }

    private fun getCoinDetail(ids : String, vs_currency : String = "usd") {
        viewModelScope.launch {
            try {
                _coinsDetail.value =  CoinApi.retrofitCoinService.getCoinDetail(vs_currency, ids)[0]
                //var listResult = CoinApi.retrofitCoinService.getCoinList()
                Log.i("ListSearchViewModel", "succesful")
                //_response.value = "Success: ${listResult.size} Coins retrieved"
            } catch (e: Exception) {
                // _response.value = "Failure: ${e.message}"
                Log.i("ListSearchViewModel", "${e.message}")
            }
        }
    }


    fun searchingText(textToSearch: String){

        val mainList = _coinsIdenties.value
        for (i in 0..mainList!!.size.minus(1)){
            if (mainList[i].name!!.contains(textToSearch) || mainList[i].symbol!!.contains(textToSearch) ){
                _searchedCoinsId.value = mainList[i]
                getCoinDetail(mainList[i].id!!)
            }else{
                Toast.makeText(application, "Undefined value!", Toast.LENGTH_LONG).show()
            }
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListSearchViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ListSearchViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}