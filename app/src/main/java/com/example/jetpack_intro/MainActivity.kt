package com.example.jetpack_intro

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_intro.ui.theme.JetPack_introTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            JetPack_introTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // MessageCard(Message("Android", "Jetpack Compose"))
                    Conversation(SampleData.conversationSample)
                // Greeting("Android")
                // MessageCard(name = "Kotlin")
                }
            }

        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun MessageCard(msg: Message) {
    // Add padding
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                //Set image size
                .size(60.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        // Add a horizontal space btw the image and the column
        Spacer(modifier = Modifier.width(9.dp))
        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember {
            mutableStateOf(false)
        }

        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )
        // We toggle the isExpanded variable when we click on this Column

        Column (modifier = Modifier.clickable { isExpanded=!isExpanded }){
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            // Add a vertical space btw the author and msg texts
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)

            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2

                )
            }
        }
    }

}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn{items(messages) { message ->
        MessageCard(message)
    }
    }
}


@Preview
@Composable
fun PreviewConversation() {
    JetPack_introTheme {
        Conversation(SampleData.conversationSample)
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard(){
    JetPack_introTheme {
        Surface{
            MessageCard(msg = Message("Colleague", "Hey, take a look a JPc") )
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

