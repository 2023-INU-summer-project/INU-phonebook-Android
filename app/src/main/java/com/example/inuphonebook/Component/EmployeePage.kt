package com.example.inuphonebook.Component

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.inuphonebook.LocalDB.Employee
import com.example.inuphonebook.R
import com.example.inuphonebook.ui.theme.BlueGray
import com.example.inuphonebook.ui.theme.Gray2
import com.example.inuphonebook.ui.theme.Gray3
import com.example.inuphonebook.ui.theme.White
import java.nio.charset.Charset

@OptIn(ExperimentalCoilApi::class)
@Composable
fun EmployeePage(
    employee : Employee,
    context : Context
){
    val TAG = "EmployeePage"

    //전화 걸기 계약
    val dialLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()){}

    val imageBackground = if(isSystemInDarkTheme()) Gray3 else BlueGray
    val textColor = if(isSystemInDarkTheme()) Gray2 else Gray3
    val highlightColor = if(isSystemInDarkTheme()) White else Black

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        if (employee.photo == null || employee.photo == ""){
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = CircleShape)
                    .background(color = imageBackground, shape = CircleShape),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    modifier = Modifier
                        .height(81.dp)
                        .width(62.dp),
                    painter = painterResource(R.drawable.main_logo),
                    contentDescription = "No Image",
                    tint = Color.Unspecified
                )
            }
        } else {

            val decodedByteArray = Base64.decode(employee.photo, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
            val bitmapImage = bitmap.asImageBitmap()

            Image(
                modifier = Modifier
                    .size(120.dp)
                    .background(color = Transparent, shape = CircleShape)
                    .clip(shape = CircleShape),
                bitmap = bitmapImage,
                contentDescription = "Image",
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 55.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = Modifier.weight(3f),
                    horizontalArrangement = Arrangement.Center,
                ){
                    Text(
                        text = employee.name,
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = highlightColor,
                        letterSpacing = 1.sp
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = Modifier.weight(3f),
                    horizontalArrangement = Arrangement.Center,
                ){
                    Text(
                        text = employee.college_name,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = textColor,
                        letterSpacing = 0.5.sp
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = Modifier.weight(3f),
                    horizontalArrangement = Arrangement.Center,
                ){
                    Text(
                        text = employee.department_name,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = textColor,
                        letterSpacing = 0.5.sp
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = Modifier.weight(3f),
                    horizontalArrangement = Arrangement.Center,
                ){
                    Text(
                        text = employee.role ?: "-",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = textColor,
                        letterSpacing = 0.5.sp
                    )
                }
            }
            Spacer(Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = Modifier.weight(3f),
                    horizontalArrangement = Arrangement.Center,
                ){
                    SelectionContainer {
                        Text(
                            modifier = Modifier.clickable(
                                enabled = employee.phoneNumber != "-" && employee.phoneNumber != ""
                            ){
                                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${employee.phoneNumber}"))
                                dialLauncher.launch(dialIntent)
                            },
                            text = employee.phoneNumber,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            color = highlightColor,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = Modifier.weight(3f),
                    horizontalArrangement = Arrangement.Center,
                ){
                    SelectionContainer{
                        Text(
                            modifier = Modifier.clickable{
                                if (employee.email != "-"){
                                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply{
                                        data = Uri.parse("mailto:${employee.email}")
                                    }
                                    context.startActivity(Intent.createChooser(emailIntent,"이메일 보내기"))
                                } else {
                                    Toast.makeText(context,"등록된 email이 없습니다.",Toast.LENGTH_SHORT).show()
                                }
                            },
                            text = employee.email ?: "-",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            color = highlightColor,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }
    }
}