package com.amita.serkomkpu.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amita.serkomkpu.R
import com.amita.serkomkpu.ui.component.KpuCarousel
import com.amita.serkomkpu.ui.component.KpuHomeTopAppBar
import com.amita.serkomkpu.ui.component.KpuMenu
import com.amita.serkomkpu.ui.screen.destinations.EntryElectorateScreenDestination
import com.amita.serkomkpu.ui.screen.destinations.HomeScreenDestination
import com.amita.serkomkpu.ui.screen.destinations.ListElectorateScreenDestination
import com.amita.serkomkpu.ui.screen.destinations.LoginScreenDestination
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalPermissionsApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Destination
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
) {

    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    var showBottomSheet by remember { mutableStateOf(false) }
    val logoutDialogState = rememberUseCaseState()
    val electorateModalBottomSheetState = rememberModalBottomSheetState()

    val navigateToLoginScreen: () -> Unit = {
        navigator.navigate(LoginScreenDestination()) {
            popUpTo(HomeScreenDestination.route) {
                inclusive = true
            }
        }
    }

    val showElectorateModalBottomSheet: () -> Unit = {
        scope.launch {
            showBottomSheet = true
        }
    }

    val hideElectorateModalBottomSheet: () -> Unit = {
        scope.launch {
            electorateModalBottomSheetState.hide()
        }.invokeOnCompletion {
            if (!electorateModalBottomSheetState.isVisible) {
                showBottomSheet = false
            }
        }
    }

    val navigateToEntryElectorate: () -> Unit = {
        navigator.navigate(EntryElectorateScreenDestination())
    }

    val navigateToListElectorate: () -> Unit = {
        navigator.navigate(ListElectorateScreenDestination())
    }

    val navigateToOtherScreen: () -> Unit = {
        scope.launch {
            snackBarState.showSnackbar("Halaman other masih dalam pengerjaan!")
        }
    }

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
        Menu(
            id = 1,
            image = R.drawable.ic_menu_informasi,
            title = "Informasi KPU"
        ),
        Menu(
            id = 2,
            image = R.drawable.ic_menu_entry_data_penduduk,
            title = "Entry Data Penduduk"
        ),
        Menu(
            id = 3,
            image = R.drawable.ic_menu_lihat_data,
            title = "Lihat Data"
        ),
        Menu(
            id = 4,
            image = R.drawable.ic_menu_lainnya,
            title = "Lainnya"
        ),
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = electorateModalBottomSheetState,
            onDismissRequest = hideElectorateModalBottomSheet,
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            ModalBottomSheetElectorateInformation(
                onCloseButtonClicked = hideElectorateModalBottomSheet
            )
        }
    }

    CoreDialog(
        state = logoutDialogState,
        selection = CoreSelection(
            withButtonView = true,
            negativeButton = SelectionButton(
                text = "Batal"
            ),
            positiveButton = SelectionButton(
                "Logout"
            ),
            onPositiveClick = navigateToLoginScreen,
            onNegativeClick = { logoutDialogState.finish() }
        ),
        body = {
            Text(text = "Ketika anda melakukan logout,anda harus login kembali melalui halaman login!")
        },
        header = Header.Default(
            title = "Apakah anda yakin ingin logout?",
            icon = IconSource(imageVector = Icons.Default.Warning)
        ),
    )

    Scaffold(
        topBar = { KpuHomeTopAppBar(onLogoutActionClicked = { logoutDialogState.show() }) },
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackBarState) }
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
                    when (it.id) {
                        1 -> showElectorateModalBottomSheet()
                        2 -> navigateToEntryElectorate()
                        3 -> navigateToListElectorate()
                        4 -> navigateToOtherScreen()
                    }
                }
            )
        }
    }
}

@FlowPreview
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewHomeScreen() {
    SerkomKPUTheme {
        Surface {
            HomeScreen()
        }
    }
}
