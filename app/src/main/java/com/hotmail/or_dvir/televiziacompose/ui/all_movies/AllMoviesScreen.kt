package com.hotmail.or_dvir.televiziacompose.ui.all_movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hotmail.or_dvir.televiziacompose.R
import com.hotmail.or_dvir.televiziacompose.database.Database
import com.hotmail.or_dvir.televiziacompose.database.toMovieModels
import com.hotmail.or_dvir.televiziacompose.models.MovieModel
import com.hotmail.or_dvir.televiziacompose.ui.shared.LoadingIndicator
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel
import java.util.UUID

typealias AllMoviesEvent = (AllMoviesUiEvent) -> Unit

@Destination(start = true)
@Composable
fun AllMoviesScreen() {
    val vm = getViewModel<AllMoviesViewModel>()
    val scaffoldState = rememberScaffoldState()
    val uiState = vm.uiState.collectAsState().value

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.screenTitle_allMovies)) }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)) {
                when(uiState.screenState) {
                    AllMoviesScreenState.Loading -> LoadingIndicator()
                    AllMoviesScreenState.Normal -> MovieList(
                        movies = uiState.allMovies,
                        onEvent = vm::onUiEvent
                    )
                }
            }
        }
    )

    stopped here
    //todo
    //  paging (endless scroll???)
}

@Composable
private fun MovieList(
    movies: List<MovieModel>,
    onEvent: AllMoviesEvent
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(movies) { index, it ->
            Movie(
                movie = it,
                onEvent = onEvent
            )

            if(index != movies.lastIndex) {
                Divider()
            }
        }
    }
}

@Composable
private fun Movie(
    movie: MovieModel,
    onEvent: AllMoviesEvent
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable {
                onEvent(AllMoviesUiEvent.OnMovieClicked(movie.id))
            }
            .padding(vertical = 8.dp, horizontal = 16.dp)
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
        onEvent = { },
        movie = MovieModel(
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
        onEvent = { },
        movie = MovieModel(
            id = UUID.randomUUID(),
            title = "movie title movie title movie title movie title movie title movie title movie title movie title",
            plotSummary = "plot summary plot summary plot summary plot summary plot summary plot summary plot summary plot summary plot summary plot summary"
        )
    )
}


@Preview(showBackground = true)
@Composable
private fun AllMoviesPreview() {
    MovieList(
        movies = Database.allMovies.toMovieModels(),
        onEvent = { }
    )
}