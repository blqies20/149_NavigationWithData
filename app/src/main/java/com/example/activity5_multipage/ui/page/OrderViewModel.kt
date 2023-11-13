package com.example.activity5_multipage.ui.page

import android.icu.text.NumberFormat
import androidx.lifecycle.ViewModel
import com.example.activity5_multipage.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val HARGA_PER_CUP = 10000

class OrderViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(OrderUIState())
    val stateUI: StateFlow<OrderUIState> = _stateUI.asStateFlow()

    fun setContact(list: MutableList<String>){
        _stateUI.update {
                stateSaatIni-> stateSaatIni.copy(
            nama = list[0],
            alamat = list[1],
            tlp = list[2]
        )
        }
    }

    fun setJumlah(jmlThaiTea: Int){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmlThaiTea,
                harga = hitungHarga(jumlah = jmlThaiTea)
            )
        }
    }
    fun setVarian(varianPilihan: String){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(varian = varianPilihan)
        }
    }
    fun resetOrder(){
        _stateUI.value = OrderUIState()
    }
    private fun hitungHarga(
        jumlah: Int = _stateUI.value.jumlah,
    ): String{
        val kalkulasiHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getNumberInstance().format(kalkulasiHarga)
    }
}