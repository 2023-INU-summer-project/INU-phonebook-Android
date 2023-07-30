package com.example.inuphonebook.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.inuphonebook.ui.theme.INUPhoneBookTheme

@Composable
fun CustomAddCategoryDialog(
    modifier : Modifier = Modifier,
    onDismissRequest : () -> Unit,
    properties : DialogProperties = DialogProperties(),
    title : String = "title",
    cancelMsg : String = "cancelMsg",
    okMsg : String = "okMsg",
    onOkClick : () -> Unit = {},
    value : String = "",
    onChangeValue : (String) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ){
        Column(
            modifier = modifier.background(color = Color.White)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(Modifier.height(10.dp))

            CustomEditText(
                fontSize = 16.sp,
                textColor = Color.Black,
                trailingIcon = null,
                onTrailingClick = {},
                onKeyboardDone = {},
                shape = RoundedCornerShape(size = 20.dp),
                placeholder = title,
                value = value,
                onValueChange = onChangeValue
            )

            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier.weight(1f)
                        .clickable{
                            onDismissRequest()
                        },
                    textAlign = TextAlign.Center,
                    text = cancelMsg,
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier.weight(1f)
                        .clickable{
                            onOkClick()
                            onDismissRequest()
                        },
                    textAlign = TextAlign.Center,
                    text = okMsg,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun TestAddDialog(){
    INUPhoneBookTheme {
        Box(
            modifier = Modifier.wrapContentSize()
        ){
            CustomAddCategoryDialog(
                onDismissRequest = {},
                properties = DialogProperties(),
                onChangeValue = {}
            )
        }
    }
}