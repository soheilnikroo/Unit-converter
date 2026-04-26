package com.soheilnikroo.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soheilnikroo.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter() {
    var inputValue by remember {
        mutableStateOf("")
    }
    remember { mutableIntStateOf(0) }

    var isInputDropDownExpanded by remember { mutableStateOf(false) }
    var isOutputDropDownExpanded by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableFloatStateOf(0.0F) }

    var conversionfactor by remember { mutableStateOf(0.01) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Here all the ui elements stack on each other
        Text("Unit Convertor")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
            })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = {
                    isInputDropDownExpanded = true
                }) {
                    Text("Select")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = isInputDropDownExpanded,
                    onDismissRequest = {
                        isInputDropDownExpanded = false
                    }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            conversionfactor
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text("Meters")
                        },
                        onClick = {}
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {}
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {}
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {
                    isOutputDropDownExpanded = true
                }) {
                    Text("Select")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = isOutputDropDownExpanded,
                    onDismissRequest = {
                        isOutputDropDownExpanded = false
                    }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {})
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {})
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {})
                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {})
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result:")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Value: ${(sliderValue * 100).toInt()}%",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(32.dp))

            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Composable
fun FullScreenImageSlider() {
    val images = listOf(
        "https://picsum.photos/800/1200?random=1",
        "https://picsum.photos/800/1200?random=2",
        "https://picsum.photos/800/1200?random=3"
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = images.size,
            modifier = Modifier.fillMaxSize(),
            key = { images[it] }
        ) { page ->
            AsyncImage(
                model = images[page],
                contentDescription = "Full screen image",
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        // Parallax effect
                        val offset = (page - selectedIndex).toFloat()
                        translationX = offset * 100f
                    },
                contentScale = ContentScale.Crop
            )
        }

        // Close button
        IconButton(
            onClick = { /* Handle close */ },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .background(
                    Color.Black.copy(alpha = 0.5f),
                    CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.White
            )
        }

        // Bottom indicator
        Text(
            text = "${selectedIndex + 1} / ${images.size}",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
                .background(
                    Color.Black.copy(alpha = 0.5f),
                    RoundedCornerShape(16.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}