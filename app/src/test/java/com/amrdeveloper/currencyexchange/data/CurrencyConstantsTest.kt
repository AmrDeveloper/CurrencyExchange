package com.amrdeveloper.currencyexchange.data

import com.amrdeveloper.currencyexchange.R
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class CurrencyConstantsTest {

    @Test
    fun currenyFullname_valieName_returnValidFullname() {
        val validName = "EUR"
        val fullname = CurrencyConstants.getCurrencyFullName(validName)
        assertThat(fullname).isEqualTo("Euro")
    }

    @Test
    fun currencyFullname_invalidName_returnEmptyString() {
        val validName = "NIL"
        val fullname = CurrencyConstants.getCurrencyFullName(validName)
        assertThat(fullname).isEqualTo("")
    }

    @Test
    fun currencyDrawableId_validName_returnValidId() {
        val validName = "EUR"
        val drawableId = CurrencyConstants.getCurrencyDrawableId(validName)
        assertThat(drawableId).isEqualTo(R.drawable.flag_eur)
    }

    @Test
    fun currencyDrawableId_invalidName_returnDefault() {
        val validName = "NIL"
        val drawableId = CurrencyConstants.getCurrencyDrawableId(validName)
        assertThat(drawableId).isEqualTo(R.drawable.ic_coin)
    }
}