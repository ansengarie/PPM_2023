package id.ac.unpas.helloworld

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.unpas.helloworld.ui.theme.HelloComposeTheme
import id.ac.unpas.helloworld.ui.theme.Purple700
import id.ac.unpas.helloworld.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FormLogin()
                }
            }
        }
    }
}

@Composable
fun FormLogin() {
    val context = LocalContext.current
    val username = remember { mutableStateOf(TextFieldValue("")) }
    val password = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Username", modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )

        Text(
            text = "Password", modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )
        TextField(
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )

        val loginButtonColor = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )

        val resetButtonColor = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )

        Divider(
            modifier = Modifier
                .weight(1f)
                .width(1.dp)
        )

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier
                    .weight(8f),
                onClick = {
                    if (username.value != TextFieldValue("") && password.value != TextFieldValue("")) {
                        //buat toast menampilkan username
                        Toast.makeText(
                            context,
                            "Halo ${username.value.text} !",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Kolom tidak tidak boleh ada yang kosong!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                colors = loginButtonColor
            ) {
                Text(
                    text = "Login",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier.padding(8.dp)
                )
            }

            Button(
                modifier = Modifier
                    .weight(8f),
                onClick = {
                    username.value = TextFieldValue("")
                    password.value = TextFieldValue("")
                }, colors = resetButtonColor
            ) {
                Text(
                    text = "Reset",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloComposeTheme {
        FormLogin()
    }
}