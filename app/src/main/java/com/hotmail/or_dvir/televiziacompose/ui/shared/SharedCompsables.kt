package com.hotmail.or_dvir.televiziacompose.ui.shared

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hotmail.or_dvir.televiziacompose.R
import com.hotmail.or_dvir.televiziacompose.models.Movie
import kotlinx.coroutines.Dispatchers

private val CARD_WIDTH_DP = 130
private val CARD_HEIGHT_DP = CARD_WIDTH_DP * 1.5

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,//Modifier.size(CARD_WIDTH_DP.dp, CARD_HEIGHT_DP.dp),
        elevation = 10.dp,
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MoviePoster(
                modifier = Modifier.width(CARD_WIDTH_DP.dp),
//                modifier = Modifier.size(
//                    //todo test this height
//                    width = CARD_WIDTH_DP.dp,
//                    height = CARD_HEIGHT_DP.dp
//                ),
                posterUrl = movie.posterUrl
            )
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = movie.title
                )
                Text(movie.year.toString())
            }
        }
    }
}

@Composable
private fun MoviePoster(
    posterUrl: String,
    //todo is this needed?
    modifier: Modifier = Modifier
) {
    //if in preview mode
    if (LocalInspectionMode.current) {
        Image(
            modifier = modifier.padding(top = 8.dp),
            painter = painterResource(R.drawable.ic_error),
            contentDescription = null
        )
    } else {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(posterUrl)
                //todo how to tint the error image?
                .error(R.drawable.ic_error)
                .dispatcher(Dispatchers.IO)
                .build(),
            //todo
            // do i need to limit the width too?
            // test this height
            modifier = modifier,
            contentDescription = null
        )
    }
}

@Composable
private fun getCardRowCount(): Int {
    return when (getScreenWidthCategory()) {
        ScreenWidthCategory.SMALL -> 3
        ScreenWidthCategory.MEDIUM -> 5
        ScreenWidthCategory.LARGE -> 8
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "adaptive", showBackground = true)
@Composable
private fun AdaptiveGridPreview() {
    val imageWidth = 130
    val imageHeight = imageWidth * 1.5

    LazyVerticalGrid(
        cells = GridCells.Adaptive(imageWidth.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(15) {
            MovieCard(
                movie = Movie.dummy(),
                onClick = {}
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "small", showBackground = true, device = Devices.NEXUS_5)
@Preview(name = "medium", showBackground = true, device = Devices.NEXUS_7)
@Preview(name = "large", showBackground = true, device = Devices.NEXUS_10)
@Composable
private fun MovieCardPreview() {
    LazyVerticalGrid(

        // todo test this size
        cells = GridCells.Fixed(getCardRowCount()),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = 15,
        ) {
            MovieCard(
                movie = Movie.dummy(),
                onClick = {}
            )
        }
    }
}

@Composable
fun LoadingIndicatorFullScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                //do nothing. the user should not be able to change anything while loading
            },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun OutlinedTextFieldWithError(
    text: String,
    error: String,
    @StringRes hint: Int,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    onTextChanged: (String) -> Unit
) {
    val showError by remember(error) { mutableStateOf(error.isNotBlank()) }

    Column {
        OutlinedTextField(
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            value = text,
            isError = showError,
            modifier = modifier,
            singleLine = singleLine,
            onValueChange = { onTextChanged(it) },
            label = { Text(text = stringResource(id = hint)) },
            keyboardOptions = keyboardOptions,
            trailingIcon = trailingIcon
        )

        if (showError) {
            Text(
                modifier = Modifier.offset(16.dp, 0.dp),
                style = MaterialTheme.typography.caption,
                text = error,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Composable
fun PasswordTextField(
    text: String,
    error: String,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    @StringRes hint: Int = R.string.hint_password,
    onTextChanged: (String) -> Unit
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val passwordTransformation: VisualTransformation
    @DrawableRes val passwordIconId: Int
    @StringRes val passwordContentDescriptionId: Int

    if (isPasswordVisible) {
        passwordIconId = R.drawable.ic_visibility_off
        passwordContentDescriptionId = R.string.contentDescription_hidePassword
        passwordTransformation = VisualTransformation.None
    } else {
        //password is hidden
        passwordIconId = R.drawable.ic_visibility_on
        passwordContentDescriptionId = R.string.contentDescription_showPassword
        passwordTransformation = PasswordVisualTransformation()
    }

    OutlinedTextFieldWithError(
        text = text,
        error = error,
        hint = hint,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = keyboardActions,
        visualTransformation = passwordTransformation,
        modifier = modifier,
        onTextChanged = onTextChanged,
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible }
            ) {
                Icon(
                    painter = painterResource(passwordIconId),
                    contentDescription = stringResource(passwordContentDescriptionId)
                )
            }
        }
    )
}
