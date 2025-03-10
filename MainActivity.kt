package com.example.myapplication

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unit_converter()
                }
            }
        }
    }
}


@Composable
fun unit_converter(){

    var inputValue by remember{mutableStateOf(value = "")}
    var outputvalue by remember{mutableStateOf(value = "")}
    var inputUnit by remember {mutableStateOf(value = "")}
    var outputUnit by remember{mutableStateOf(value = "")}
    var iExpandable by remember{mutableStateOf(value = false)}
    var oExpandable by remember{mutableStateOf(value = false)}
    var conversionFactor by remember{mutableStateOf(value = 1.000)}
    var oconversionFactor by remember{mutableStateOf(value = 1.000)}
    fun convertible(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.000
        val result = (inputValueDouble * conversionFactor * 100.000 / oconversionFactor).roundToInt()/100.000
        outputvalue = result.toString()
    }


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)

    {
        Text("Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {inputValue = it}, label = {Text("Enter Here")})
        Spacer(modifier = Modifier.height(16.dp))

        Row{
            Box {
                Button(onClick = {iExpandable = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Down Arrow")
                }
                DropdownMenu(expanded = iExpandable, onDismissRequest = {iExpandable = false}) {
                    DropdownMenuItem(text = { Text("cm") },
                        onClick = { iExpandable = false
                            inputUnit = "cm"
                            conversionFactor = 0.01
                            convertible()})

                    DropdownMenuItem(text = { Text("m") },
                        onClick = {iExpandable = false
                            inputUnit = "m"
                            conversionFactor = 1.000
                            convertible()})

                    DropdownMenuItem(text = {Text("Feet")},
                        onClick = {iExpandable = false
                            inputUnit = "Feet"
                            conversionFactor = 0.3048
                            convertible()})

                    DropdownMenuItem(text = {Text("Millimeters")},
                        onClick = {iExpandable = false
                            inputUnit = "Millimeter"
                            conversionFactor = 0.001
                            convertible()})
                }
            }

            Spacer(modifier = Modifier.width(32.dp))

            Box{
                Button(onClick = {oExpandable = true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Down Arrow")
                }
                DropdownMenu(expanded = oExpandable, onDismissRequest = {oExpandable = false}) {
                    DropdownMenuItem(text = { Text("cm") },
                        onClick = {oExpandable = false
                                    outputUnit = "cm"
                                    oconversionFactor = 0.01
                                    convertible()
                                    })

                    DropdownMenuItem(text = { Text("m") },
                        onClick = { oExpandable = false
                            outputUnit = "m"
                            oconversionFactor = 1.000
                            convertible()})

                    DropdownMenuItem(text = {Text("Feet")},
                        onClick = {oExpandable = false
                            outputUnit = "Feet"
                            oconversionFactor = 0.3048
                            convertible()})

                    DropdownMenuItem(text = {Text("Millimeters")},
                        onClick = {oExpandable = false
                            outputUnit = "Millimeter"
                            oconversionFactor = 0.001
                            convertible()})
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: " + outputvalue)
    }
}