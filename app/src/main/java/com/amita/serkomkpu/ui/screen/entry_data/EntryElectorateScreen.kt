package com.amita.serkomkpu.ui.screen.entry_data

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amita.serkomkpu.R
import com.amita.serkomkpu.model.Electorate
import com.amita.serkomkpu.repository.ElectorateRepository
import com.amita.serkomkpu.ui.component.KpuAddressTextField
import com.amita.serkomkpu.ui.component.KpuButton
import com.amita.serkomkpu.ui.component.KpuDatePicker
import com.amita.serkomkpu.ui.component.KpuRadioButton
import com.amita.serkomkpu.ui.component.KpuTextField
import com.amita.serkomkpu.ui.component.KpuTopAppBar
import com.amita.serkomkpu.ui.component.KpuTopAppBarType
import com.amita.serkomkpu.ui.screen.destinations.ChooseLocationScreenDestination
import com.amita.serkomkpu.ui.screen.destinations.HomeScreenDestination
import com.amita.serkomkpu.ui.theme.SerkomKPUTheme
import com.amita.serkomkpu.util.decodeStringBase64ToBitMap
import com.amita.serkomkpu.util.uriToBase64
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultRecipient
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalPermissionsApi
@FlowPreview
@ExperimentalFoundationApi
@Destination
@ExperimentalMaterial3Api
@Composable
fun EntryElectorateScreen(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    resultChooseLocationReceipt: ResultRecipient<ChooseLocationScreenDestination, String> = EmptyResultRecipient(),
    entryElectorateViewModel: EntryElectorateViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val focusRequester = remember(::FocusRequester)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    val contentResolver = context.contentResolver

    val entryElectorateScreenUiState by entryElectorateViewModel.entryElectorateScreenUiState.collectAsState()
    val btnAddEnabled by entryElectorateViewModel.btnAddEnabled.collectAsState(initial = false)

    val selectedImage = entryElectorateScreenUiState.imageBitmap

    val saveEntryElectorateDialogState = rememberUseCaseState()
    val calendarDialogState = rememberUseCaseState()

    val navigateUp: () -> Unit = { navigator.navigateUp() }
    val navigateToChooseLocationScreen: () -> Unit = {
        navigator.navigate(ChooseLocationScreenDestination())
    }
    val navigateToHomeScreen: () -> Unit = {
        navigator.navigate(HomeScreenDestination()) {
            popUpTo(HomeScreenDestination.route) {
                inclusive = true
            }
        }
    }

    val options = remember { listOf("Laki-Laki", "Perempuan") }

    val openGalleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            scope.launch {
                withContext(Dispatchers.Default) {
                    val base64String = uriToBase64(contentResolver, uri)
                    val bitmapDecoded = base64String.decodeStringBase64ToBitMap()
                    entryElectorateViewModel.updateImage(base64String)
                    withContext(Dispatchers.Main) {
                        entryElectorateViewModel.updateImageBitmap(bitmapDecoded)
                    }
                }
            }

        }
    }

    val openGallery: () -> Unit = {
        openGalleryLauncher.launch("image/*")
    }
    val openCalendarDialog: () -> Unit = {
        calendarDialogState.show()
    }

    CoreDialog(
        state = saveEntryElectorateDialogState,
        selection = CoreSelection(
            withButtonView = true,
            negativeButton = SelectionButton(
                text = "Batal"
            ),
            positiveButton = SelectionButton(
                "Simpan"
            ),
            onPositiveClick = {
                entryElectorateViewModel.addEntryElectorate()
                navigateToHomeScreen()
            },
            onNegativeClick = { saveEntryElectorateDialogState.finish() }
        ),
        body = {
            Text(text = "Anda dapat melihat data pemilih yang baru ditambah, di halaman daftar data pemilih!")
        },
        header = Header.Default(
            title = "Apakah anda yakin ingin menyimpan data pemilih ini?",
            icon = IconSource(imageVector = Icons.Default.Warning)
        ),
    )

    CalendarDialog(
        state = calendarDialogState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date { newDate ->
            entryElectorateViewModel.updateDateCollectionDate(newDate)
            focusManager.clearFocus()
        }
    )
    
    resultChooseLocationReceipt.onNavResult { result ->
        when (result) {
            NavResult.Canceled -> {}
            is NavResult.Value -> entryElectorateViewModel.updateAddress(result.value)
        }
    }

    Scaffold(
        topBar = {
            KpuTopAppBar(
                title = "Entry Data Pemilih",
                onNavIconClicked = navigateUp,
                type = KpuTopAppBarType.Detail
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .animateContentSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(28.dp))
            KpuTextField(
                label = "NIK",
                text = entryElectorateScreenUiState.nik,
                onTextChanged = entryElectorateViewModel::updateNik,
                placeholder = "Masukan NIK disini",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuTextField(
                label = "Nama Lengkap",
                text = entryElectorateScreenUiState.name,
                onTextChanged = entryElectorateViewModel::updateName,
                placeholder = "Masukan nama lengkap disini",
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuTextField(
                label = "Nomor Telepon",
                text = entryElectorateScreenUiState.phone,
                onTextChanged = entryElectorateViewModel::updatePhone,
                placeholder = "Masukan no telepon disini",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(20.dp))
            KpuRadioButton(
                label = "Jenis Kelamin",
                selectedOption = entryElectorateScreenUiState.gender,
                options = options,
                onOptionChanged = entryElectorateViewModel::updateGender
            )
            KpuDatePicker(
                label = "Tanggal Pendataan",
                text = entryElectorateScreenUiState.dateCollectionDate.toString(),
                onTextChanged = {},
                placeholder = "Masukan tanggal pendataan",
                onClicked = openCalendarDialog,
                focusRequester = focusRequester,
            )
            Spacer(modifier = Modifier.height(12.dp))
            KpuAddressTextField(
                label = "Alamat Rumah",
                text = entryElectorateScreenUiState.address,
                placeholder = "Masukan alamat rumah",
                onTextChanged = entryElectorateViewModel::updateAddress,
                onChooseLocationClicked = navigateToChooseLocationScreen,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (selectedImage == null) {
                Image(
                    painter = painterResource(id = R.drawable.ic_placeholder_add_image),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .clickable { openGallery() }
                        .width(100.dp)
                        .height(83.dp)
                )
            }
            if (selectedImage != null) {
                Image(
                    bitmap = selectedImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(34.dp))
            KpuButton(
                text = "Submit",
                onButtonClicked = { saveEntryElectorateDialogState.show() },
                modifier = Modifier.fillMaxWidth(),
                enabled = btnAddEnabled
            )
        }
    }
}

@ExperimentalPermissionsApi
@FlowPreview
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEntryElectorateScreen() {
    val electorateRepository = object : ElectorateRepository {
        override fun getAllElectorate(): Flow<List<Electorate>> = flow {  }

        override fun searchElectorate(query: String): Flow<List<Electorate>> = flow {  }

        override suspend fun getElectorate(id: Int): Electorate? = null

        override fun getElectorateCount(): Flow<Int> = flow {  }

        override suspend fun deleteAllElectorate() {}

        override suspend fun deleteElectorate(id: Int) {}

        override suspend fun upsertElectorate(electorate: Electorate) {}
    }
    val entryElectorateViewModel = EntryElectorateViewModel(electorateRepository)
    SerkomKPUTheme {
        Surface {
            EntryElectorateScreen(
                entryElectorateViewModel = entryElectorateViewModel
            )
        }
    }
}
