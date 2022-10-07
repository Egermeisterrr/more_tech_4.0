package com.example.data.repository

import android.content.Context
import com.example.data.utils.DeCryptor
import com.example.data.utils.EnCryptor
import com.example.domain.repository.RefreshTokenRepository

class RefreshTokenRepositoryImpl(context: Context): RefreshTokenRepository {

    private var deCryptor: DeCryptor = DeCryptor()
    private var enCryptor: EnCryptor = EnCryptor(context)

    override fun encryptToken(token: String) {
        enCryptor.encryptText(SAMPLE_ALIAS, token)
    }

    override fun decryptToken(): String {
        return deCryptor.decryptData(SAMPLE_ALIAS, enCryptor.getEncryption(), enCryptor.getIv())
    }

    override fun deleteToken() {
        deCryptor.deleteKeyStore(SAMPLE_ALIAS)
    }

    companion object {
        private const val SAMPLE_ALIAS = "MYALIAS"
    }
}