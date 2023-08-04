package com.example.inuphonebook.Component

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.inuphonebook.LocalDB.Employee

@OptIn(ExperimentalCoilApi::class)
@Composable
fun EmployeePage(
    employee : Employee,
    context : Context
){
    //전화 걸기 계약
    val dialLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK || it.resultCode == Activity.RESULT_CANCELED){
            showToast(context, "전화 종료")
        } else {
            showToast(context, "전화 연결 오류")
        }
    }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Icon(
            modifier = Modifier
                .size(100.dp)
                .clip(shape = CircleShape),
            painter = rememberImagePainter(data = employee.photo),
            contentDescription = "Icon",
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 25.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = Modifier.weight(2f)
                ){
                    Text(
                        text = "교수명",
                        fontSize = 24.sp
                    )
                }
                Row(
                    modifier = Modifier.weight(3f)
                ){
                    Text(
                        text = employee.name,
                        fontSize = 24.sp
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
                    modifier = Modifier.weight(2f)
                ){
                    Text(
                        text = "소속",
                        fontSize = 16.sp
                    )
                }
                Row(
                    modifier = Modifier.weight(3f)
                ){
                    Text(
                        text = employee.college_name,
                        fontSize = 16.sp
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
                    modifier = Modifier.weight(2f)
                ){
                    Text(
                        text = "세부 소속",
                        fontSize = 16.sp
                    )
                }
                Row(
                    modifier = Modifier.weight(3f)
                ){
                    Text(
                        text = employee.department_name,
                        fontSize = 16.sp
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
                    modifier = Modifier.weight(2f)
                ){
                    Text(
                        text = "직위",
                        fontSize = 16.sp
                    )
                }
                Row(
                    modifier = Modifier.weight(3f)
                ){
                    Text(
                        text = employee.role,
                        fontSize = 16.sp
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
                    modifier = Modifier.weight(2f)
                ){
                    Text(
                        text = "전화번호",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600)
                    )
                }
                Row(
                    modifier = Modifier.weight(3f)
                ){
                    Text(
                        modifier = Modifier.clickable{
                            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${employee.phoneNumber}"))
                            dialLauncher.launch(dialIntent)
                        },
                        text = employee.phoneNumber,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600)
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
                    modifier = Modifier.weight(2f)
                ){
                    Text(
                        text = "이메일",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600)
                    )
                }
                Row(
                    modifier = Modifier.weight(3f)
                ){
                    Text(
                        text = employee.email,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600)
                    )
                }
            }
        }
    }
}

private fun showToast(context : Context, msg : String,){
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}