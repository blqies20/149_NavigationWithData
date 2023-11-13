package com.example.activity5_multipage.data

data class OrderUIState(
    val jumlah : Int = 0,
    val varian : String = "",
    val harga : String = "",
    var nama: String="",
    var alamat: String="",
    var tlp: String=""
)
