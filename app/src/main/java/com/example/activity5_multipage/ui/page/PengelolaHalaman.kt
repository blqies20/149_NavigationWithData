package com.example.activity5_multipage.ui.page

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.activity5_multipage.R
import com.example.activity5_multipage.data.SumberData.varian

enum class PengelolaHalaman {
    Home,
    Varian,
    Summary
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThaiTeaAppBar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier,
        navigationIcon = {
            if (bisaNavigasiBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, 
                    contentDescription = stringResource(id = R.string.back_button)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThaiTeaApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold(
        topBar = {
            ThaiTeaAppBar(bisaNavigasiBack = false, navigasiUp = { /*TODO: implement back navigation*/ })
        }
    ) { innerPadding ->
        val uiState by viewModel.stateUI.collectAsState()
        NavHost(
            navController = navController, 
            startDestination = PengelolaHalaman.Home.name, 
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = PengelolaHalaman.Home.name){
                HomePage (
                    onNextButtonClicked = {navController.navigate(PengelolaHalaman.Varian.name) }
                )
            }
            composable(route = PengelolaHalaman.Varian.name){
                val context = LocalContext.current
                VarianPage(
                    pilihanRasa = varian.map {id->context.resources.getString(id)},
                    onSelectionChanged = {viewModel.setVarian(it)},
                    onConfirmButtonClicked = {viewModel.setJumlah(it)},
                    onNextButtonClicked = { navController.navigate(PengelolaHalaman.Summary.name)},
                    onCancelButtonClicked = { cancelOrderAndNavigateToHome(viewModel, navController)}
                )
            }
            composable(route = PengelolaHalaman.Summary.name){
                SummaryPage(orderUIState = uiState, onCancelButtonClicked = { cancelOrderAndNavigateToVarian(navController)}
                    //onSendButtonClicked = {subject: String, summary: String)
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToHome(
    viewModel: OrderViewModel,
    navController: NavHostController
){
    viewModel.resetOrder()
    navController.popBackStack(PengelolaHalaman.Home.name, inclusive = false)
}

private fun cancelOrderAndNavigateToVarian(
    navController: NavHostController
){
    navController.popBackStack(PengelolaHalaman.Varian.name, inclusive = false)
}