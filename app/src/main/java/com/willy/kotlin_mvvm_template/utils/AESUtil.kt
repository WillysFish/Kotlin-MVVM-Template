package com.willy.kotlin_mvvm_template.utils

import android.content.Context
import android.util.Base64

import com.willy.kotlin_mvvm_template.R
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * Created by Willy on 2019/10/30.
 */

object AESUtil {

    internal var seed = ""

    fun init(context: Context) {
        seed = context.getString(R.string.aes_seed)
    }

    @Throws(Exception::class)
    fun encrypt(cleartext: String): String {
        var encryptedText: ByteArray?
        try {
            val keyData = seed.toByteArray()
            val ks = SecretKeySpec(keyData, "AES")
            val c = Cipher.getInstance("AES")
            c.init(Cipher.ENCRYPT_MODE, ks)
            encryptedText = c.doFinal(cleartext.toByteArray(charset("UTF-8")))
            return Base64.encodeToString(encryptedText, Base64.DEFAULT)
        } catch (e: Exception) {
            Log.e(e,"AESUtil.encrypt")
            return ""
        }

    }

    @Throws(Exception::class)
    fun decrypt(encrypted: String): String {
        var clearText: ByteArray?
        return try {
            val keyData = seed.toByteArray()
            val ks = SecretKeySpec(keyData, "AES")
            val c = Cipher.getInstance("AES")
            c.init(Cipher.DECRYPT_MODE, ks)
            clearText = c.doFinal(Base64.decode(encrypted, Base64.DEFAULT))
            clearText.toString(Charsets.UTF_8)
        } catch (e: Exception) {
            ""
        }

    }
}
