package com.hotmail.or_dvir.televiziacompose.ui.all_movies

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun AllMoviesScreen() {
    Text("all movies")

    //todo
    //  paging (endless scroll???)
}

@Composable
private fun Movie() {

    //todo
    //  add preview
}
