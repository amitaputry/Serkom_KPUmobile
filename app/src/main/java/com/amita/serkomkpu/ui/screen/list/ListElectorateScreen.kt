package com.amita.serkomkpu.ui.screen.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amita.serkomkpu.model.Electorate
import com.amita.serkomkpu.ui.component.KpuOutlinedTextField
import com.amita.serkomkpu.ui.component.KpuTopAppBar
import com.amita.serkomkpu.ui.component.KpuTopAppBarType
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.amita.serkomkpu.ui.theme.greyTextFill
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@FlowPreview
@Destination
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ListElectorateScreen(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    listElectorateViewModel: ListElectorateViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val deleteElectorateDialogState = rememberUseCaseState()
    val detailElectorateModalBottomSheetState = rememberModalBottomSheetState()

    val navigateUp: () -> Unit = { navigator.navigateUp() }

    val listElectorateScreenUiState by listElectorateViewModel.listElectorateScreenUiState.collectAsState()

    val searchQuery = listElectorateScreenUiState.searchQuery
    val electorates = listElectorateScreenUiState.electorates
    val selectedElectorate = listElectorateScreenUiState.selectedElectorate

    var tempDeletedElectorateId by remember {
        mutableIntStateOf(0)
    }

    val showDetailElectorateBottomSheet: (Electorate) -> Unit = {
        listElectorateViewModel.updateSelectedElectorate(it)
    }

    val hideElectorateModalBottomSheet: () -> Unit = {
        scope.launch {
            detailElectorateModalBottomSheetState.hide()
        }.invokeOnCompletion {
            if (!detailElectorateModalBottomSheetState.isVisible) {
                listElectorateViewModel.updateSelectedElectorate(null)
            }
        }
    }


    LaunchedEffect(key1 = Unit) {
        listElectorateViewModel.getAllElectorate()
    }

    LaunchedEffect(key1 = searchQuery) {
        snapshotFlow { searchQuery }
            .debounce(100)
            .distinctUntilChanged()
            .collectLatest { query ->
                if (query.isNotEmpty()) {
                    listElectorateViewModel.searchElectorate(query)
                } else {
                    listElectorateViewModel.getAllElectorate()
                }
            }
    }

    CoreDialog(
        state = deleteElectorateDialogState,
        header = Header.Default(
            title = "Apakah anda yakin ingin menghapus data ini?",
            icon = IconSource(imageVector = Icons.Default.Warning)
        ),
        body = {
            Text(text = "Anda tidak dapat mengembalikan data pemilih yang sudah dihapus, apakah anda yakin?")
        },
        selection = CoreSelection(
            withButtonView = true,
            negativeButton = SelectionButton(
                text = "Batal"
            ),
            positiveButton = SelectionButton(
                "Hapus"
            ),
            onPositiveClick = { listElectorateViewModel.deleteElectorate(tempDeletedElectorateId) },
            onNegativeClick = { deleteElectorateDialogState.finish() }
        ),
    )

    if (selectedElectorate != null) {
        ModalBottomSheet(
            sheetState = detailElectorateModalBottomSheetState,
            onDismissRequest = hideElectorateModalBottomSheet,
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            ModalBottomSheetDetailElectorate(
                electorate = selectedElectorate,
                onCloseButtonClicked = hideElectorateModalBottomSheet
            )
        }
    }

    Scaffold(
        topBar = {
            KpuTopAppBar(
                title = "Daftar Data Pemilih",
                type = KpuTopAppBarType.Detail,
                onNavIconClicked = navigateUp
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            KpuOutlinedTextField(
                text = searchQuery,
                onTextChanged = listElectorateViewModel::updateSearchQuery,
                placeholder = "Cari data penduduk",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = greyTextFill
                    )
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .padding(top = 26.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(all = 4.dp),
            ) {
                items(items = electorates, key = { it.id }) { electorate ->
                    KpuElectorateItem(
                        electorate = electorate,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 16.dp)
                            .animateItemPlacement(),
                        onCardClicked = showDetailElectorateBottomSheet,
                        onDeleteIconClicked = {
                            tempDeletedElectorateId = it
                            deleteElectorateDialogState.show()
                        }
                    )
                }
            }
        }
    }
}

@FlowPreview
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewListElectorateScreen() {
    SerkomKPUTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ListElectorateScreen()
        }
    }
}
