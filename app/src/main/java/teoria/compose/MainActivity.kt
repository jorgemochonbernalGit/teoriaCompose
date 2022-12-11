package teoria.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import teoria.compose.ui.theme.TeoriaTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeoriaTheme {
                //var selected by remember { mutableStateOf("Ejemplo 1") }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //var counterMas by rememberSaveable { mutableStateOf(value = 0) }
                    //var counterMenos by rememberSaveable { mutableStateOf(value = 0) }
                    //val getOptions = getOptions(titles = listOf("Coco", "Mailo"))
                    Column() {
                        //MyEstado1(counterMas) { counterMas = it }
                        //MyEstado2(counterMenos) { counterMenos = it }
                        //getOptions.forEach {
                        //  MyCheckBoxWithText1(it)
                        //}
                        //MyListRadioButton(selected) { selected = it }
                        MyRangeSlider()
                    }
                }
            }
        }
    }
}

@Composable
fun MyDropDownMenu() {
    var selectedText by remember { mutableStateOf("") }
    var expaned by remember { mutableStateOf(false) }
    var deserts = listOf("Vainilla", "Cafe", "Fruta")
    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expaned = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expaned,
            onDismissRequest = { expaned = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            deserts.forEach { d ->
                DropdownMenuItem(onClick = {
                    expaned = false
                    selectedText = d
                }) {
                    Text(text = d)
                }
            }
        }
    }
}

@Composable
fun LineaVerticalHorizontal() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), color = Color.Red
    )
}

@Composable
fun MyBadyBox() {
    BadgedBox(
        modifier = Modifier.padding(20.dp),
        badge = {
            Badge(
                content = {
                    Text(modifier = Modifier.padding(2.dp), text = "10")
                },
                backgroundColor = Color.Black,
                contentColor = Color.White,
                modifier = Modifier.padding(2.dp)
            )
        },
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Default.Star,
            contentDescription = "l",
            tint = Color.Blue
        )
    }
}

@Composable
fun Card1() {
    Card(
        elevation = 12.dp,
        shape = MaterialTheme.shapes.small,
        contentColor = Color.Yellow,
        backgroundColor = Color.Red,
        border = BorderStroke(5.dp, Color.Black),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Coco")
            Text(text = "Mailo")
            Text(text = "Tara")
        }
    }
}

@Composable
fun MyRadioButton1() {
    Row(modifier = Modifier.fillMaxSize()) {
        RadioButton(
            selected = true, enabled = false, onClick = { }, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Cyan,
                disabledColor = Color.Green
            )
        )
        Text(text = "Coco")
    }
}

@Composable
fun MyListRadioButton(name: String, onItemSelected: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 1", onClick = { onItemSelected("Ejemplo 1") })
            Text(text = "Ejemplo 1")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 2", onClick = { onItemSelected("Ejemplo 2") })
            Text(text = "Ejemplo 2")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 3", onClick = { onItemSelected("Ejemplo 3") })
            Text(text = "Ejemplo 3")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 4", onClick = { onItemSelected("Ejemplo 4") })
            Text(text = "Ejemplo 4")
        }
    }
}

@Composable
fun MyTrisStatusCheckBox() {
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> {
                ToggleableState.Off
            }
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = "Coco",
            selected = status,
            onCheckedChange = { status = it })
    }
}

@Composable
fun MyCheckBoxWithText1(checkInfo: CheckInfo) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Text(text = checkInfo.title)
    }
}

@Composable
fun MyCheckBoxWithText2() {
    var state by rememberSaveable { mutableStateOf(false) }
    Row(modifier = Modifier.padding(7.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Text(text = "Ejemplo1")
    }
}

@Composable
fun MyCheckBox1() {
    var state by rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = state, onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Cyan,
            checkmarkColor = Color.Yellow
        )
    )
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

@Composable
fun textField1() {
    var text2 by remember { mutableStateOf("") }
    TextField(
        value = text2,
        onValueChange = {
            text2 = if (it.contains("coco")) {
                it.replace("coco", "Mailo")
            } else {
                it
            }
        },
        label = { Text(text = "Introduce tu nombre") })
}

@Composable
fun button1() {
    var enabled by rememberSaveable { mutableStateOf(true) }
    Column() {
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    }
    Button(
        onClick = { enabled = false },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Magenta,
            contentColor = Color.Blue
        ),
        border = BorderStroke(5.dp, Color.Red)
    ) {
        Text(text = "Boton")
    }

    OutlinedButton(
        onClick = { enabled = false },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Magenta,
            contentColor = Color.Blue
        )
    ) {
        Text(text = "Boton")
    }
}

@Composable
fun textFieldOutlined1() {
    var text2 by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text2,
        onValueChange = { text2 = it },
        label = { Text(text = "Hola") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Blue
        )
    )
}

@Composable
fun imagen1() {
    Image(
        painter = painterResource(id = R.drawable.coco),
        contentDescription = "coco",
        alpha = 0.5f,
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)
        //Modifier.clip(RoundedCornerShape(25.dp))
    )
}

//https://fonts.google.com/icons
@Composable
fun icon() {
    Icon(
        imageVector = Icons.Rounded.Star,
        contentDescription = "Estrella",
        tint = Color.Red
    )
}

@Composable
fun ProgressBar1() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Column() {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 10.dp)
            LinearProgressIndicator(
                modifier = Modifier.padding(10.dp),
                color = Color.Red,
                backgroundColor = Color.Yellow
            )
        }

        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "pulsa")
        }
    }
}

@Composable
fun ProgressBar2() {
    var progressStatus by rememberSaveable { mutableStateOf(0f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progressStatus)
        Row(modifier = Modifier.fillMaxSize()) {
            Button(onClick = { progressStatus += 0.1f }) {
                Text(text = "Incrementar")
            }
            Button(onClick = { progressStatus -= 0.1f }) {
                Text(text = "Decrementar")
            }
        }
    }
}

@Composable
fun Switch() {
    var state by rememberSaveable { mutableStateOf(true) }
    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = SwitchDefaults.colors(
            //true
            uncheckedThumbColor = Color.Red,
            uncheckedTrackColor = Color.Magenta,
            uncheckedTrackAlpha = 0.3f,
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Cyan,
            checkedTrackAlpha = 0.5f,

            //false
            disabledCheckedThumbColor = Color.Yellow,
            disabledUncheckedThumbColor = Color.Yellow
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TeoriaTheme {

    }
}