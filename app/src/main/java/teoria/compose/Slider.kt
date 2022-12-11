package teoria.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

@Composable
fun BasicSlider() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var sliderPosition by remember { mutableStateOf(0f) }
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
        Text(text = sliderPosition.toString())
    }
}

@Composable
fun SliderAvanzado() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var sliderPosition by remember { mutableStateOf(0f) }
        var fin by remember { mutableStateOf("") }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = { fin = sliderPosition.toString() },
            valueRange = 0f..10f,
            steps = 9
        )
        Text(text = fin)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyRangeSlider() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var currentPage by remember { mutableStateOf(0f..10f) }
        RangeSlider(
            values = currentPage,
            onValueChange = { currentPage = it },
            valueRange = 0f..10f,
            steps = 9
        )
        Text(text = "Valor inferior ${currentPage.start}")
        Text(text = "Valor superior ${currentPage.endInclusive}")
    }
}