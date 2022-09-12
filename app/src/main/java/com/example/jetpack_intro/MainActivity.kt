package com.example.jetpack_intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_intro.ui.theme.JetPack_introTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Android","Jetpack Compose"))

            /*JetPack_introTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Greeting("Android")
                    MessageCard(name = "Kotlin")
                }
            }*/

        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun MessageCard(msg: Message){
    Row{
        Image(painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture")
        Column {
            Text(text = msg.author)
            Text(text = msg.body)
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    /*JetPack_introTheme {
        // Text("Hello JP!")
        // Greeting("Android")
        // MessageCard(name = "Kotlin")
    }*/

    MessageCard(
        msg = Message("Colleague", "Hey, take a look a JPc")
    )
}

