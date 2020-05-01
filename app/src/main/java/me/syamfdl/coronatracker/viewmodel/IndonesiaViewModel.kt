package me.syamfdl.coronatracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.syamfdl.coronatracker.data.*
import me.syamfdl.coronatracker.services.ApiCorona

class IndonesiaViewModel: ViewModel() {

    private val _data = MutableLiveData<DataIndonesia>()
    val data : LiveData<DataIndonesia>
        get() = _data

    private val _dataPositif = MutableLiveData<DataPositifGlobal>()
    val dataPositif : LiveData<DataPositifGlobal>
    get() = _dataPositif

    private val _dataSembuh = MutableLiveData<DataSembuhGlobal>()
    val dataSembuh : LiveData<DataSembuhGlobal>
        get() = _dataSembuh

    private val _dataMeninggal = MutableLiveData<DataMeninggalGlobal>()
    val dataMeninggal : LiveData<DataMeninggalGlobal>
        get() = _dataMeninggal

    private val _dataProvinsi = MutableLiveData<List<DataProvinsi>>()
    val dataProvinsi: LiveData<List<DataProvinsi>>
    get() = _dataProvinsi

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private var job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    init {
        _response.value = ""
        initData()
        dataPositif()
        dataSembuh()
        dataMeninggal()
        dataProvinsi()
    }

    fun initData() {
            uiScope.launch {
                try {
                    val result = ApiCorona.retrofitService.showData()
                    result.map {
                        if (result.isNotEmpty()){
                            _data.value = it
                        } else {
                            _response.value = "Data Kosong"
                        }
                    }
                    _response.value = "Berhasil fetch data!"
                } catch (t: Throwable) {
                    _response.value = "Tidak ada koneksi internet!"
                }
            }
    }

    fun dataPositif() {
        uiScope.launch {
            try {
                val resultp = ApiCorona.retrofitService.dataPositif()
                _dataPositif.value = resultp

                _response.value = "Berhasil fetch data!"
            } catch (t: Throwable) {
                _response.value = "Tidak ada koneksi internet!"
            }
        }
    }

    fun dataSembuh() {
        uiScope.launch {
            try {
                val results = ApiCorona.retrofitService.dataSembuh()
                _dataSembuh.value = results

                _response.value = "Berhasil fetch data!"
            } catch (t: Throwable) {
                _response.value = "Tidak ada koneksi internet!"
            }
        }
    }

    fun dataMeninggal() {
        uiScope.launch {
            try {
                val resultm = ApiCorona.retrofitService.dataMeninggal()
                _dataMeninggal.value = resultm

                _response.value = "Berhasil fetch data!"
            } catch (t: Throwable) {
                _response.value = "Tidak ada koneksi internet!"
            }
        }
    }

    fun dataProvinsi() {
        uiScope.launch {
            try {
                val result = ApiCorona.retrofitService.dataProvinsi()
                _dataProvinsi.value = result
                _response.value = "Berhasil fetch data!"
            } catch (t: Throwable) {
                _response.value = "Tidak ada koneksi internet!"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}