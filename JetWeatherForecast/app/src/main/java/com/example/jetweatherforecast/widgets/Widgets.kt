package com.example.jetweatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.jetweatherforecast.R
import com.example.jetweatherforecast.model.WeatherObject
import com.example.jetweatherforecast.utils.formatDate
import com.example.jetweatherforecast.utils.formatDateTime
import com.example.jetweatherforecast.utils.formatDecimals

@Composable
fun WeatherDetailRow(weather: WeatherObject) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    val celicus = ((formatDecimals(weather.temp.day).trim().toInt() - 32) * 5 )/9
    Surface(
        Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                formatDate(weather.dt)
                    .split(",")[0],
                modifier = Modifier.padding(start = 5.dp)
            )
            WeatherStateImage(imageUrl = imageUrl)
            Surface(modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = colorResource(R.color.skdjf)
            ) {
                Text(weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium)
            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold
                )
                ){
                    append("${celicus}º")

                }
                withStyle(
                    style = SpanStyle(
                        color = Color.LightGray)
                ){
                    append("${celicus}º")
                }
            })

        }


    }

}

@Composable
fun HumidityWindPressureRow(weather: WeatherObject, isImperial : Boolean) {
    Row(
        modifier = Modifier.padding(12.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.humidity}%", style = MaterialTheme.typography.bodyMedium
            )

        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.pressure} psi", style = MaterialTheme.typography.bodyMedium
            )

        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${formatDecimals(weather.speed)} "+ if (isImperial) "mph" else "m/s",
                style = MaterialTheme.typography.bodyMedium
            )

        }

    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {

    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "icon image",
        modifier = Modifier.size(80.dp)
    )

}

@Composable
fun SunsetSunRiseRow(weather: WeatherObject) {
    Row(
        modifier = Modifier.padding(12.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "pressure icon",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "${formatDateTime(weather.sunrise)} ",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 9.dp)
            )

        }

        Row {
            Text(
                text = "${formatDateTime(weather.sunset)} ",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 9.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "wind icon",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}