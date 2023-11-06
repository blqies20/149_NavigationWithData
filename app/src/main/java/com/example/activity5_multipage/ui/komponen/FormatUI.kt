package com.example.activity5_multipage.ui.komponen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.activity5_multipage.R

@Composable
fun FormatLabelHarga(subtotal: String, modifier: Modifier){
    Text(text = stringResource(R.string.subtotal_price, subtotal),
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
        )
}