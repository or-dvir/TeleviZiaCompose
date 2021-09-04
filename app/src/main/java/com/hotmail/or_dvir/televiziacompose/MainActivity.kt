package com.hotmail.or_dvir.televiziacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hotmail.or_dvir.televiziacompose.ui.theme.TeleviZiaComposeTheme

class MainActivity : ComponentActivity()
{
    add this project to git!!!
    
    i created the basic skeleton for the database and it's accessors (repositories)
    should be time to start with actual screens... start with login!
    you probably should set up navigation first...

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            TeleviZiaComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String)
{
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview()
{
    TeleviZiaComposeTheme {
        Greeting("Android")
    }
}