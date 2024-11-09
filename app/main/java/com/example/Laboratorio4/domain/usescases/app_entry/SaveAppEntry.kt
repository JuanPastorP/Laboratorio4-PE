package com.example.Laboratorio4.domain.usescases.app_entry

import com.example.Laboratorio4.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}