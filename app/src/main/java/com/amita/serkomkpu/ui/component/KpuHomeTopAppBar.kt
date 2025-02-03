package com.amita.serkomkpu.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amita.serkomkpu.R
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.amita.serkomkpu.ui.theme.heading3

@ExperimentalMaterial3Api
@Composable
fun KpuHomeTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "KPU MOBILE APPS",
    onLogoutActionClicked: () -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier,
        shape = RectangleShape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        )
    ) {
        TopAppBar(
            navigationIcon = {
                 Row {
                     Spacer(modifier = Modifier.width(12.dp))
                     Image(
                         painter = painterResource(id = R.drawable.img_logo),
                         contentDescription = "image description",
                         contentScale = ContentScale.FillBounds,
                         modifier = Modifier
                             .width(35.dp)
                             .height(38.dp)
                     )
                     Spacer(modifier = Modifier.width(8.dp))
                 }
            },
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.heading3,
                    modifier = Modifier.offset(y = 2.dp),
                    color = Color(0xFF333538)
                )
            },
            actions = {
                  IconButton(onClick = onLogoutActionClicked) {
                      Icon(
                          imageVector = Icons.Default.Logout,
                          contentDescription = "Keluar dari aplikasi",
                          tint = Color(0xFF333538)
                      )
                  }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.White
            )
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewKpuHomeTopAppBar() {
    SerkomKPUTheme {
        Surface {
            Scaffold(
                topBar = { KpuHomeTopAppBar() }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}
