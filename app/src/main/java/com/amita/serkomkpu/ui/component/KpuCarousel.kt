package com.amita.serkomkpu.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amita.serkomkpu.R
import com.amita.serkomkpu.ui.screen.home.CarouselItem
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme

@ExperimentalFoundationApi
@Composable
fun KpuCarousel(
    modifier: Modifier = Modifier,
    items: List<CarouselItem> = emptyList(),
    onItemClicked: (CarouselItem) -> Unit = {}
) {
    val pagerState = rememberPagerState {
        items.size
    }
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 20.dp),
        key = { it },
        modifier = modifier,
        pageSize = PageSize.Fixed(280.dp),
        pageSpacing = 20.dp
    ) {
        KpuCarouselItem(
            item = items[it],
            onItemClicked = onItemClicked
        )
    }
}

@Composable
fun KpuCarouselItem(
    modifier: Modifier = Modifier,
    item: CarouselItem,
    onItemClicked: (CarouselItem) -> Unit = {}
) {
    Image(
        painter = painterResource(id = item.image),
        contentDescription = "image description",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .width(280.dp)
            .height(150.dp)
            .clickable { onItemClicked(item) }
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewKpuCarousel() {
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
    SerkomKPUTheme {
        Surface {
            Scaffold(
                topBar = { KpuHomeTopAppBar() }
            ) { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    Spacer(modifier = Modifier.height(24.dp))
                    KpuCarousel(
                        items = carouselItems
                    )
                }
            }
        }
    }
}
