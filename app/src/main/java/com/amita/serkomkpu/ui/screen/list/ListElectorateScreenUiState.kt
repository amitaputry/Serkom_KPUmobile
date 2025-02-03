package com.amita.serkomkpu.ui.screen.list

import com.amita.serkomkpu.model.Electorate
import com.amita.serkomkpu.util.emptyString

data class ListElectorateScreenUiState(
    val searchQuery: String = emptyString(),
    val electorates: List<Electorate> = emptyList(),
    val selectedElectorate: Electorate? = null
)
