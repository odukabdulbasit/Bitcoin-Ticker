package com.odukabdulbasit.bitcointicker.listsearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odukabdulbasit.bitcointicker.api.CoinApi
import kotlinx.coroutines.launch

class ListSearchViewModel : ViewModel(){

    init {
        getCoinList()
    }

    private fun getCoinList() {
        viewModelScope.launch {
            try {
                var listResult = CoinApi.retrofitCoinService.getCoinList()
                Log.i("ListSearchViewModel", "$listResult")
                //_response.value = "Success: ${listResult.size} Coins retrieved"
            } catch (e: Exception) {
               // _response.value = "Failure: ${e.message}"
                Log.i("ListSearchViewModel", "${e.message}")
            }
        }
    }

}