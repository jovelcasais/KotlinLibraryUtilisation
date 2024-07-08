package com.jovelcasais.kotlinlibraryutilisation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ButtonComponent(value: String, onButtonClicked : () -> Unit, isEnabled : Boolean){
    Button(
        onClick = { onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled
    ) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .heightIn(50.dp),
           /* .background(
               // brush = Brush.horizontalGradient(listOf(background, PRIMARY_BUTTON_BG_COLOR)),
                shape = RoundedCornerShape(50.dp)
            ),*/
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 15.sp,
                // fontWeight = FontWeight.Medium,
                //fontFamily = TextSnippetsHelper.getFontMedium(),
                color = Color.White)
        }
    }
}

@Composable
fun LoadingViewComponent(onDismiss:() -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Loading.. Please wait..",
                    Modifier
                        .padding(8.dp), textAlign = TextAlign.Center
                )
                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun DialogComponent(
    message:String, positiveClickString:String, negativeClickString:String, onConfirm: (Boolean) -> Unit, onDismiss: (Boolean) -> Unit){

    AlertDialog(
        onDismissRequest = {
            onDismiss(false)
        },
        title = {
            Text(text = "Message")
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(false) }) {
                Text(positiveClickString)
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss(false) }) {
                Text(negativeClickString)
            }
        }
    )

}