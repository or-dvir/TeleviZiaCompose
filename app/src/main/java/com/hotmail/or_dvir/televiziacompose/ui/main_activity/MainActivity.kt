package com.hotmail.or_dvir.televiziacompose.ui.main_activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.hotmail.or_dvir.televiziacompose.ui.all_movies.NavGraphs
import com.hotmail.or_dvir.televiziacompose.ui.theme.TeleviZiaComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeleviZiaComposeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
