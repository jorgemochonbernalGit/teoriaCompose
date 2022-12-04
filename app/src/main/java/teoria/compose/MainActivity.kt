package teoria.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import teoria.compose.ui.theme.TeoriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeoriaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var counterMas by rememberSaveable { mutableStateOf(value = 0) }
                    var counterMenos by rememberSaveable { mutableStateOf(value = 0) }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MyEstado1(counterMas) { counterMas = it }
                        MyEstado2(counterMenos) { counterMenos = it }
                    }

                }
            }
        }
    }
}

@Composable
fun MySpacer(height: Int, width: Int) {
    Spacer(
        modifier = Modifier
            .width(height.dp)
            .height(width.dp)
    )
}

//ocupa todo el ancho y todo el alto modifier = Modifier.fillMaxSize()
//para centear contentAlignment = Alignment.Center
//verticalScroll nos permite dar scroll
@Composable
fun MyBox() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Cyan)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Text(text = "asdfvg asdfghgrtgfrtghijhogiyfutydrfyguhijokplñlpkjhgfdrftghj,k.ojhgfcd")
        }
    }
}

//wieght coge el 100% de la pantalla y lo va diviendo en funcion del valor 1f
@Composable
fun MyColumn1() {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Ejemplo 1",
            Modifier
                .background(Color.Red)
                .weight(1f)
        )
        Text(
            text = "Ejemplo 2",
            Modifier
                .background(Color.Black)
                .weight(1f)
        )
        Text(
            text = "Ejemplo 3",
            Modifier
                .background(Color.Blue)
                .weight(1f)
        )
        Text(
            text = "Ejemplo 4",
            Modifier
                .background(Color.Cyan)
                .weight(1f)
        )
    }
}

//verticalArrangement Arrangement defininos la seperacion de los columns
@Composable
fun MyColumn2() {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Ejemplo 1", Modifier.background(Color.Red))
        Text(text = "Ejemplo 2", Modifier.background(Color.Black))
        Text(text = "Ejemplo 3", Modifier.background(Color.Blue))
        Text(text = "Ejemplo 4", Modifier.background(Color.Cyan))
    }
}

//horizontalArrangement Arrangement defininos la seperacion de los columns
@Composable
fun MyRow1() {
    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Ejemplo1")
        Text(text = "Ejemplo2")
        Text(text = "Ejemplo2")
    }
}

@Composable
fun BoxColumnRow() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color.Red)
        ) {}
        MySpacer(40, 0)
        Row(
            Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Yellow)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.White), contentAlignment = Alignment.Center
            ) {
                Text(text = "Coco", modifier = Modifier.background(Color.Red))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color.Cyan)
        )
    }
}

@Composable
fun MyEstado1(n: Int, onValueChange: (Int) -> Unit) {
    //var counter = remember { mutableStateOf(value = 0) }
    Button(onClick = { onValueChange(n + 1) }) {
        Text(text = "Coco")
    }
    Text(text = "Pulsado: ${n}")
}

@Composable
fun MyEstado2(n: Int, onValueChange: (Int) -> Unit) {
    //var counter = remember { mutableStateOf(value = 0) }
    Button(onClick = { onValueChange(n - 1) }) {
        Text(text = "Mailo")
    }
    Text(text = "Pulsado: ${n}")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TeoriaTheme {

    }
}