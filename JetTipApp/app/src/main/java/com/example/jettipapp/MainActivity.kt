package com.example.jettipapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jettipapp.components.InputField
import com.example.jettipapp.ui.theme.JetTipAppTheme
import com.example.jettipapp.util.calculateTipAmount
import com.example.jettipapp.util.calculateTotalAmount
import com.example.jettipapp.widget.RoundIconButton
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTipAppTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }


}


@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.padding(10.dp),
        color = MaterialTheme.colorScheme.background
    ) {

        Column {
            MainContent()
        }

    }
}


@Composable
fun TopHeader(totalPerPerson: Double) {
    Surface(
        modifier = Modifier.padding(top = 20.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        color = Color(0xFFE9D7F7)
    ) {
        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Total Per Person", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "$" + String.format("%.2f", totalPerPerson),
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Preview
@Composable
fun MainContent() {
    BillForm { billAmt ->
        Log.d("AMT", "MainContent: ${billAmt.toInt()}")
    }
}


@Composable
fun BillForm(onValueChange: (String) -> Unit = {}) {
    val totalBillState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val splitCounter = remember {
        mutableIntStateOf(1)
    }
    val tipAmountState = remember {
        mutableDoubleStateOf(0.0)
    }
    val sliderPosition = remember { mutableFloatStateOf(0f) }

    val totalPerPersonState = remember { mutableDoubleStateOf(0.0) }

    TopHeader(totalPerPersonState.doubleValue)

    Spacer(modifier = Modifier.height(30.dp))
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        shape = RoundedCornerShape(CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {

        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValueChange(totalBillState.value.trim())
                    keyboardController?.hide()
                }
            )
            if (validState) {
                Row(
                    modifier = Modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        "Split", modifier = Modifier.padding(start = 16.dp)
                            .align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(120.dp))
                    Row(
                        modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {

                        RoundIconButton(imageVector = Icons.Default.Remove,
                            onClick = {
                                if (splitCounter.intValue > 1) {
                                    splitCounter.intValue -= 1
                                    totalPerPersonState.doubleValue = calculateTotalAmount(
                                        totalBillState.value.toDouble(),
                                        tipAmountState.doubleValue,
                                        splitCounter.intValue
                                    )
                                }
                            })
                        Text(
                            text = splitCounter.intValue.toString(),
                            modifier = Modifier.align(Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp)
                        )
                        RoundIconButton(imageVector = Icons.Default.Add,
                            onClick = {
                                splitCounter.intValue += 1
                                totalPerPersonState.doubleValue = calculateTotalAmount(
                                    totalBillState.value.toDouble(),
                                    tipAmountState.doubleValue,
                                    splitCounter.intValue
                                )
                            })
                    }
                }

                Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                    Text(
                        text = "Tip",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(165.dp))
                    Text(
                        text = "$ ${tipAmountState.doubleValue}",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                }

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = (sliderPosition.floatValue).roundToInt().toString() + "%")
                    Spacer(modifier = Modifier.height(12.dp))
                    Slider(
                        value = sliderPosition.floatValue,
                        onValueChange = {
                            sliderPosition.floatValue = it
                            tipAmountState.doubleValue = calculateTipAmount(
                                totalBillState.value.toDouble(),
                                sliderPosition.floatValue.roundToInt()
                            )
                            totalPerPersonState.doubleValue = calculateTotalAmount(
                                totalBillState.value.toDouble(),
                                tipAmountState.doubleValue,
                                splitCounter.intValue
                            )
                        },
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.secondary,
                            activeTrackColor = MaterialTheme.colorScheme.secondary,
                            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        steps = 5,
                        valueRange = 0f..100f
                    )

                }

            } else {
                Box {}
            }
        }

    }
}





