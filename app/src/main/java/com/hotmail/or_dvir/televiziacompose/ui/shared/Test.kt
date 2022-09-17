package com.hotmail.or_dvir.televiziacompose.ui.shared

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hotmail.or_dvir.televiziacompose.models.Movie

@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "custom grid", showBackground = true)
@Composable
private fun TestTest() {
    val numColumns = getCellCount()

    //todo we DONT want to use the entire screens width, but the CONTAINER'S
    // width!!! maybe box with constraints???
    val screenWidthDp = LocalConfiguration.current.screenWidthDp

    val oneLine = "bla bla bla bla bla bla"
    val oneAndHalfLine = "bla bla bla bla bla bla bla bla bla"

    val movies = listOf(
        Movie.dummy().copy(title = "bla"),
        Movie.dummy().copy(title = "bla bla"),
        Movie.dummy().copy(title = oneLine + " bla"),

        Movie.dummy().copy(title = oneAndHalfLine),
        Movie.dummy().copy(title = "bla bla bla"),
        Movie.dummy().copy(title = "bla"),

        Movie.dummy().copy(title = "bla bla bla bla"),
    )

    val contentPadding = 16.dp
    val arrangement = Arrangement.SpaceEvenly
//    val arrangement = Arrangement.spacedBy(8.dp)

    val sidePaddingDp = contentPadding / 2
    val contentPaddingInner = contentPadding / (numColumns - 1)
    val totalPaddingDp = sidePaddingDp + contentPaddingInner

    val cellWidthDp = (screenWidthDp.dp - totalPaddingDp) / numColumns

    LazyColumn(
        contentPadding = PaddingValues(contentPadding)
    ) {
        items(movies.chunked(numColumns)) { line ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .border(1.dp, Color.Blue),
                horizontalArrangement = arrangement
            ) {
                line.forEach { movie ->
                    Cell(
                        text = movie.title,
                        modifier = Modifier.weight(1f)
                                //todo what about padding???
//                            .width(cellWidthDp)
                            .fillMaxHeight()
                    )
                }
            }
        }
    }
}

@Composable
private fun Cell(
    text: String,
    modifier: Modifier = Modifier
) {
    val numWords = (1..7).random()
    val b = StringBuilder()
    for (w in 1..numWords) {
        b.append("bla ")
    }

    Text(
        modifier = modifier
            .padding(1.dp)
            .border(1.dp, Color.Red),
        text = text,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun getCellCount(): Int {
    return when (getScreenWidthCategory()) {
        ScreenWidthCategory.SMALL -> 3
        ScreenWidthCategory.MEDIUM -> 5
        ScreenWidthCategory.LARGE -> 8
    }
}