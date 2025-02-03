package com.amita.serkomkpu.ui.component

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amita.serkomkpu.R
import com.amita.serkomkpu.ui.screen.home.CarouselItem
import com.amita.serkomkpu.ui.screen.home.Menu
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.amita.serkomkpu.ui.theme.poppinsFontFamily

@Composable
fun KpuMenu(
    modifier: Modifier = Modifier,
    menus: List<Menu> = emptyList(),
    onMenuClicked: (Menu) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(20.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(items = menus, key = { it.id }) { menu ->
            KpuMenuItem(menu = menu, onMenuClicked = onMenuClicked)
        }
    }
}

@Composable
fun KpuMenuItem(
    modifier: Modifier = Modifier,
    menu: Menu,
    onMenuClicked: (Menu) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(size = 8.dp))
            .fillMaxWidth()
            .background(color = Color(0xCBDEDEDE))
            .clickable { onMenuClicked(menu) }
            .padding(all = 8.dp)
            .padding(top = 16.dp)
    ) {
        Image(
            painter = painterResource(id = menu.image),
            contentDescription = menu.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(90.dp)
                .height(93.2517.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = menu.title,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            )
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewKpuMenu() {

    val carouselItems = listOf(
        CarouselItem(
            id = 1,
            image = R.drawable.img_tahapan_pemilu_1
        ),
        CarouselItem(
            id = 2,
            image = R.drawable.img_tahapan_pemilu_2
        )
    )

    val menus = listOf(
        Menu(id = 1, image = R.drawable.ic_menu_informasi, title = "Informasi KPU"),
        Menu(id = 2, image = R.drawable.ic_menu_entry_data_penduduk, title = "Entry Data Penduduk"),
        Menu(id = 3, image = R.drawable.ic_menu_lihat_data, title = "Lihat Data"),
        Menu(id = 4, image = R.drawable.ic_menu_lainnya, title = "Lainnya"),
    )

    SerkomKPUTheme {
        val context = LocalContext.current
        Surface(color = MaterialTheme.colorScheme.background) {
            Scaffold(
                topBar = { KpuHomeTopAppBar() }
            ) { paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    KpuCarousel(
                        items = carouselItems
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    KpuMenu(
                        menus = menus,
                        onMenuClicked = {
                            Toast.makeText(context, "${it.title}", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
