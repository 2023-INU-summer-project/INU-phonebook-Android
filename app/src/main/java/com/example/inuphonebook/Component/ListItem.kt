package com.example.inuphonebook.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inuphonebook.LocalDB.Employee
import com.example.inuphonebook.R
import com.example.inuphonebook.ui.theme.DividerLineColor
import com.example.inuphonebook.ui.theme.FIllFavoriteColor
import com.example.inuphonebook.ui.theme.FillNotFavoriteColor
import com.example.inuphonebook.ui.theme.INUPhoneBookTheme

@Composable
fun ListItem(
    employee : Employee,
    onClick : () -> Unit,
    onFavoriteClick : () -> Unit,
){
    Column(
        Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                onClick()
            }
            .background(color = White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            Modifier
                .padding(start = 15.dp, end = 15.dp, top = 8.dp)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column{
                Spacer(Modifier.weight(1f))

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = employee.name,
                        fontSize = 20.sp
                    )

                    Spacer(Modifier.width(11.dp))

                    Text(
                        text = employee.college_name,
                        fontSize = 16.sp
                    )
                }

                Spacer(Modifier.weight(1f))

                Text(
                    text = employee.phoneNumber,
                    fontSize = 20.sp,
                    letterSpacing = 1.sp
                )

                Spacer(Modifier.weight(1f))
            }

            Spacer(Modifier.weight(1f))

            IconButton(
                onClick = onFavoriteClick
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(R.drawable.tmp_favorite_not),
                    contentDescription = "Is Favorite",
                    tint = if(employee.isFavorite) FIllFavoriteColor else FillNotFavoriteColor
                )
            }
        }
        Spacer(Modifier.height(8.dp))

        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DividerLineColor
        )
    }
}

@Composable
@Preview
fun TestListItem(){
    INUPhoneBookTheme {
        Box(
            Modifier.fillMaxSize().background(color = White)
        ){
            val dummyItem = Employee(
                photo = R.drawable.ic_launcher_foreground,
                name = "서호준",
                role = "연구생",
                phoneNumber = "010-6472-3783",
                isFavorite = true,
                position = "",
                id = 0,
                college_name = "정보통신대학",
                department_name = "컴퓨터공학부",
                email = "seohojon@naver.com",
                category = "기본"
            )
            ListItem(
                employee = dummyItem,
                onClick = {},
                onFavoriteClick = {},
            )
        }
    }
}