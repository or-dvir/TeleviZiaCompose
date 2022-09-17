package com.hotmail.or_dvir.televiziacompose.ui.all_movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hotmail.or_dvir.televiziacompose.models.Movie
import com.ramcosta.composedestinations.annotation.Destination
import java.util.UUID

@Destination(start = true)
@Composable
fun AllMoviesScreen() {

    stopped here

    Text("all movies")



    //todo
    //  paging (endless scroll???)
}

@Composable
private fun Movie(
    movie: Movie,
    onClick: (id: UUID) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable { onClick(movie.id) }
    ) {
        movie.apply {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = MaterialTheme.typography.h6
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = plotSummary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NormalMoviePreview() {
    Movie(
        onClick = { },
        movie = Movie(
            id = UUID.randomUUID(),
            title = "movie title",
            plotSummary = "plot summary"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun LongMoviePreview() {
    Movie(
        onClick = { },
        movie = Movie(
            id = UUID.randomUUID(),
            title = "movie title movie title movie title movie title movie title movie title movie title movie title",
            plotSummary = "plot summary plot summary plot summary plot summary plot summary plot summary plot summary plot summary plot summary plot summary"
        )
    )
}
