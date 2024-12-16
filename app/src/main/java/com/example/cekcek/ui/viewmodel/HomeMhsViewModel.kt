package com.example.cekcek.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.activity10.repository.RepositoryMhs
import kotlinx.coroutines.flow.StateFlow

class HomeMhsViewModel(
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> = repositoryMhs.getAllMhs()
}
