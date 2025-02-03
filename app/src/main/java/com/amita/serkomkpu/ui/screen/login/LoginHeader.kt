package com.amita.serkomkpu.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amita.serkomkpu.R
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.amita.serkomkpu.ui.theme.heading3
import com.amita.serkomkpu.ui.theme.regularTextStyle

@Composable
fun LoginHeader(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(81.46552.dp)
                .height(90.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "SELAMAT DATANG",
            style = MaterialTheme.typography.heading3
        )
        Text(
            text = "Silakan Login untuk melanjutkan",
            style = MaterialTheme.typography.regularTextStyle
        )
    }
}

@Preview
@Composable
fun PreviewLoginHeader() {
    SerkomKPUTheme {
        Surface {
            LoginHeader()
        }
    }
}
