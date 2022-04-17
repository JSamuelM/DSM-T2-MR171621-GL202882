package sv.edu.udb.dsmt2mr171621gl202882.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sv.edu.udb.dsmt2mr171621gl202882.db.AppDatabase
import sv.edu.udb.dsmt2mr171621gl202882.db.ProductEntity

class HomeViewModel : ViewModel() {

    private val _productObservable: MutableLiveData<List<ProductEntity>> = MutableLiveData(mutableListOf())
    val productObservable: LiveData<List<ProductEntity>> get() = _productObservable

    fun getProductList(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(context)
            _productObservable.postValue(db.getDatabaseDao().getProducts())
        }
    }
}