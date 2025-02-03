package com.amita.serkomkpu.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.amita.serkomkpu.ui.theme.seed
import com.amita.serkomkpu.ui.theme.textButtonStyle

@Composable
fun KpuButton(
    modifier: Modifier = Modifier,
    text: String,
    onButtonClicked: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        disabledContainerColor = Color(0xFFDEDEDE),
        contentColor = Color(0xFFAAAAAA),
        containerColor = seed
    ),
    loadingState: Boolean = false,
    enabled: Boolean = true
) {
    val textColor = if (enabled) Color.White else Color(0xFFAAAAAA)
    val textButton = if (loadingState) "Loading..." else text
    Button(
        onClick = onButtonClicked,
        modifier = modifier,
        shape = RoundedCornerShape(size = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 9.dp),
        colors= colors,
        enabled = enabled && !loadingState
    ) {
        Text(
            text = textButton,
            style = MaterialTheme.typography.textButtonStyle.copy(
                color = textColor
            )
        )
    }
}

@Preview
@Composable
fun PreviewKpuButton() {
    SerkomKPUTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            KpuButton(
                text = "Daftar",
                onButtonClicked = {},
                modifier = Modifier.fillMaxWidth(),
                loadingState = true
            )
        }
    }
}
