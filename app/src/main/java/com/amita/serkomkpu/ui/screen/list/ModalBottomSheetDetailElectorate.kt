package com.amita.serkomkpu.ui.screen.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.amita.serkomkpu.model.Electorate
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.amita.serkomkpu.ui.theme.poppinsFontFamily
import com.amita.serkomkpu.util.decodeStringBase64ToBitMap

@Composable
fun ModalBottomSheetDetailElectorate(
    modifier: Modifier = Modifier,
    onCloseButtonClicked: () -> Unit = {},
    electorate: Electorate
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(color = Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .padding(bottom = 15.dp)
        ) {
            IconButton(onClick = onCloseButtonClicked) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }
            Text(
                text = "Lihat Rincian Data ",
                color = Color(0xff333538),
                lineHeight = 6.5.em,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFontFamily
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 12.dp
                )
        ) {
            Image(
                bitmap = electorate.image.decodeStringBase64ToBitMap(),
                contentDescription = "Rectangle 926",
                modifier = Modifier
                    .size(size = 100.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = electorate.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF333538),
                    )
                )
                Text(
                    text = electorate.nik,
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 19.2.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFFAAAAAA),
                        letterSpacing = 0.04.sp,
                    )
                )
            }
        }
        Divider(
            color = Color(0xfff1f1f1),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Nama Lengkap",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 25.6.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333538),
                    letterSpacing = 0.05.sp,
                ),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ": ${electorate.name}",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 19.2.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF333538),
                    letterSpacing = 0.04.sp,
                ),
                modifier = Modifier.weight(1.5f)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = "NIK",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 25.6.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333538),
                    letterSpacing = 0.05.sp,
                ),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ": ${electorate.nik}",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 19.2.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF333538),
                    letterSpacing = 0.04.sp,
                ),
                modifier = Modifier.weight(1.5f)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Nomor Telepone",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 25.6.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333538),
                    letterSpacing = 0.05.sp,
                ),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ": ${electorate.phone}",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 19.2.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF333538),
                    letterSpacing = 0.04.sp,
                ),
                modifier = Modifier.weight(1.5f)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Jenis Kelamin",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 25.6.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333538),
                    letterSpacing = 0.05.sp,
                ),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ": ${electorate.gender}",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 19.2.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF333538),
                    letterSpacing = 0.04.sp,
                ),
                modifier = Modifier.weight(1.5f)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Tangal Pendataan",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 25.6.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333538),
                    letterSpacing = 0.05.sp,
                ),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ": ${electorate.dateCollectionDate}",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 19.2.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF333538),
                    letterSpacing = 0.04.sp,
                ),
                modifier = Modifier.weight(1.5f)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Alamat Rumah",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 25.6.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333538),
                    letterSpacing = 0.05.sp,
                ),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ": ${electorate.address}",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 19.2.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF333538),
                    letterSpacing = 0.04.sp,
                ),
                modifier = Modifier.weight(1.5f)
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
            val electorate = Electorate(
                nik = "201004000242002",
                name = "Ki Hadjar Dewantara",
                gender = "Pria",
                address = "Purwokerto Selatan",
            )
            ModalBottomSheetDetailElectorate(electorate = electorate)
        }
    }
}