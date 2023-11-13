package com.example.activity5_multipage.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.activity5_multipage.R
import com.example.activity5_multipage.ui.theme.Activity5_multipageTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactPage(onSubmitButtonClicked: (MutableList<String>) -> Unit){

    var namaTxt by remember{ mutableStateOf("") }
    var alamatTxt by remember{ mutableStateOf("") }
    var tlpTxt by remember{ mutableStateOf("") }

    var listData: MutableList<String> = mutableListOf(namaTxt,alamatTxt,tlpTxt)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = namaTxt,
            onValueChange = {namaTxt = it},
            label = { Text(text = stringResource(id = R.string.nama)
            )}
        )

        OutlinedTextField(
            value = alamatTxt,
            onValueChange = {alamatTxt = it},
            label = { Text(text = stringResource(id = R.string.alamat)
            )}
        )

        OutlinedTextField(
            value = tlpTxt,
            onValueChange = {tlpTxt = it},
            label = { Text(text = stringResource(id = R.string.tlp)
            )}
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = { onSubmitButtonClicked(listData) } ) {
            Text(text = stringResource(id = R.string.btn_submit))

        }
    }
}

@Preview (showBackground = true)
@Composable
fun preview(){
    Activity5_multipageTheme {
        ContactPage { }
    }
}
