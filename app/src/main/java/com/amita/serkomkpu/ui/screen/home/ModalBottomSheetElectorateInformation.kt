package com.amita.serkomkpu.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.amita.serkomkpu.R
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.amita.serkomkpu.ui.theme.poppinsFontFamily

@Composable
fun ModalBottomSheetElectorateInformation(
    modifier: Modifier = Modifier,
    onCloseButtonClicked: () -> Unit = {},
) {

    val generalElectionStages = remember {
        listOf(
            "Penyusunan Peraturan KPU",
            "Pemutakhiran data pemilih dan penyusunan pemilih",
            "Pendaftaran dan verifikasi peserta pemilu",
            "Penetapan peserta pemilu",
            "Masa kampanye pemilu",
            "Presiden dan wakil presiden",
            "Anggota DPR, DPRD Provinsi dan DPRD Kabupaten",
            "Anggota DPD",
            "Penetapan jumlah kursi dan penetapan dapil",
            "Masa tenggang",
            "Pemungutan Suara",
            "Perhitungan Suara",
            "Rekapitulasi hasil perhitungan suara"
        )
    }

    Column(
        modifier = modifier.fillMaxWidth()
            .background(color = Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .padding(horizontal = 4.dp)
        ) {
            IconButton(onClick = onCloseButtonClicked) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }
            Text(
                text = "Informasi KPU",
                color = Color(0xff333538),
                lineHeight = 6.5.em,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFontFamily
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.img_tahapan_pemilu),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.padding(horizontal = 20.dp)
                .height(335.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Tahapan Pemilihan Umum",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF333538),
            ),
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        generalElectionStages.forEachIndexed { index, stage ->
            Text(
                text = "${index + 1}. $stage",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF333538),
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        Spacer(modifier = Modifier.height(100.dp))
    }

}

@Preview
@Composable
private fun PreviewModalBottomSheetDetailElectorate() {
    SerkomKPUTheme {
        Surface {
            ModalBottomSheetElectorateInformation()
        }
    }
}