package com.example.activity10.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activity10.data.entity.Mahasiswa
import com.example.activity10.repository.RepositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel(private val repositoryMhs: RepositoryMhs) : ViewModel() {

    var uiState by mutableStateOf(MhsUIState())

    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh Kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh Kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh Kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh Kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh Kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh Kosong"
        )

        uiState = uiState.copy (isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.mahasiswaEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy (
                        snackBarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy (
                snackBarMessage = "Input Tidak Valid, Periksa Kembali Data Anda."
            )
        }
    }

    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class MhsUIState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
) {
    fun isValid(): Boolean {
        return nim == null && nama == null && jenisKelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}

//Menyimpan input form ke dalam entity
fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa (
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)

// data class variabel yang menyimpan data input form
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenisKelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)