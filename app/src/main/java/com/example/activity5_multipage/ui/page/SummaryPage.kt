package com.example.activity5_multipage.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.activity5_multipage.R
import com.example.activity5_multipage.data.OrderUIState
import com.example.activity5_multipage.ui.komponen.FormatLabelHarga

@Composable
fun SummaryPage(
    OrderUIState: OrderUIState,
    onCancelButtonClicked: ()-> Unit,
    //onSendButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier
){
    val items = listOf(
        Pair(stringResource(R.string.quantity), OrderUIState.jumlah),
        Pair(stringResource(R.string.varian), OrderUIState.varian)
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = stringResource(R.string.nama))
        Text(text = OrderUIState.nama)
        Divider()
        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = stringResource(id = R.string.alamat))
        Text(text = OrderUIState.alamat)
        Divider()
        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = stringResource(id = R.string.tlp))
        Text(text = OrderUIState.tlp)
        Divider()
        Spacer(modifier = Modifier.padding(16.dp))

        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
        ) {
            items.forEach { item ->
                Column {
                    Text(item.first.uppercase())
                    Text(text = item.second.toString(), fontWeight = FontWeight.Bold)
                }
                Divider(thickness = dimensionResource(id = R.dimen.thickness_divider))
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            FormatLabelHarga(subtotal = OrderUIState.harga , modifier = Modifier.align(Alignment.Start))
        }
        Row(
            modifier = Modifier
                .weight(1f, false)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {  }
                ) {
                    Text(text = stringResource(id = R.string.send))
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onCancelButtonClicked
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewSummaryPage() {
    // Create a sample OrderUIState for preview
    val OrderUIState = OrderUIState(
        jumlah = 3,
        varian = "Macha",
        harga = "15.90"
    )

    SummaryPage(
            OrderUIState = OrderUIState,
            onCancelButtonClicked = { /* Handle cancel button click */ }
    )

}
