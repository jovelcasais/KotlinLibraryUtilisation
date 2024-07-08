package com.jovelcasais.kotlinlibraryutilisation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jovelcasais.kotlinlibraryutilisation.api.state.ApiUIState
import com.jovelcasais.kotlinlibraryutilisation.ui.components.DialogComponent
import com.jovelcasais.kotlinlibraryutilisation.ui.components.LoadingViewComponent
import com.jovelcasais.kotlinlibraryutilisation.ui.uievents.UIEvent
import com.jovelcasais.kotlinlibraryutilisation.utilities.viewmodels.MainViewModel

@Composable
fun MainScreen(vm: MainViewModel = viewModel()){

    val lifecycleOwner = LocalLifecycleOwner.current
    val state by vm.mainState.observeAsState()
    val items by vm.newsItemState.collectAsState()
    val context = LocalContext.current

    var showLoader by remember {
        mutableStateOf(true)
    }

    var showItems by remember {
        mutableStateOf(false)
    }

    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("general", "sports", "entertainment")
    val filteredItems = if (selectedCategory == "All") {
        items
    } else {
        items.filter { (it?.categories?.contains(selectedCategory) ?: "") == true }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                vm.onEvent(UIEvent.CallApi)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column {

        categories.forEach { category ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = category)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if(showItems){
            LazyColumn (
                userScrollEnabled = true
            ) {

                itemsIndexed(filteredItems) { _, item ->
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (item != null) {
                                    Toast
                                        .makeText(context, item.title, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                    ) {

                        Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            if (item != null) {

                                Text(
                                    text = item.title,
                                    modifier = Modifier
                                        .padding(20.dp),
                                    color = Color.Black,
                                   // textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Normal,
                                )
                            }
                        }
                    }
                }
            }
        }

        when(state){

            is ApiUIState.Error ->{
                showLoader = false
                showItems = true
                vm.showDialog()
            }

            is ApiUIState.Loading -> {
                if(showLoader){
                    LoadingViewComponent(onDismiss = {

                    }) }

            }
            is ApiUIState.Success -> LaunchedEffect(Unit) {
                showLoader = false
                showItems = true
            }
            else -> {}
        }

        if(vm.isDialogVisible.value){
            DialogComponent((state as ApiUIState.Error).error,
                "Okay", "Cancel",
                onConfirm = {
                    vm.isDialogVisible.value = false
                }, onDismiss = {
                    vm.isDialogVisible.value = false
                })
        }
    }
}