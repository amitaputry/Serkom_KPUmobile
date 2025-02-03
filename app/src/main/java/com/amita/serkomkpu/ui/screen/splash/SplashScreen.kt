package com.amita.serkomkpu.ui.screen.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amita.serkomkpu.R
import com.amita.serkomkpu.ui.screen.destinations.LoginScreenDestination
import com.amita.serkomkpu.ui.screen.destinations.SplashScreenDestination
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
@FlowPreview
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            delay(2000L)
            navigator.navigate(LoginScreenDestination()) {
                popUpTo(SplashScreenDestination.route) {
                    inclusive = true
                }
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center, 
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(173.dp)
                .height(191.dp)
        )
    }
}

@FlowPreview
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewSplashScreen() {
    SerkomKPUTheme {
        Surface {
            SplashScreen()
        }
    }
}