package com.example.jetmovieapp.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.jetmovieapp.model.Movie

@Composable
fun MovieRow(item : Movie, onItemClick : (movie:String)->Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp
    ),
        modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()
            .clickable { onItemClick(item.id) }) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier.padding(12.dp).size(100.dp),
                shape = RectangleShape
            ) {
                Image(painter= rememberImagePainter(data=item.images[0]),contentDescription = "Poster Image")
//                Icon(imageVector = Icons.Default.AccountBox,contentDescription = "Movie Image")
            }
        Column(modifier = Modifier.padding(4.dp)) {
            Text(
                text = item.title,
                textAlign = TextAlign.Center,
                color = Color.Black, // You can customize the color if needed
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Director : "+item.director,
                textAlign = TextAlign.Center,
                color = Color.Black, // You can customize the color if needed
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Released : "+item.year,
                textAlign = TextAlign.Center,
                color = Color.Black, // You can customize the color if needed
                style = MaterialTheme.typography.bodyMedium
            )

            AnimatedVisibility(visible = expanded){
                Column{
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color=Color.DarkGray, fontSize = 13.sp)){
                            append("Plot : ")
                        }
                        withStyle(style= SpanStyle(color=Color.DarkGray, fontSize = 13.sp,fontWeight = FontWeight.Normal)){
                            append(item.plot)
                        }
                    })
                    Divider()
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color=Color.DarkGray, fontSize = 13.sp)){
                            append("Rating : ")
                        }
                        withStyle(style= SpanStyle(color=Color.DarkGray, fontSize = 13.sp,fontWeight = FontWeight.Normal)){
                            append(item.rating)
                        }
                    }   )
                }
            }

            Icon(imageVector = if(expanded){Icons.Filled.KeyboardArrowUp}else{Icons.Filled.ArrowDropDown},contentDescription = "Down Arrow", modifier = Modifier.size(25.dp).clickable {
                    expanded = !expanded
            },tint = Color.DarkGray)
        }


        }

    }

}