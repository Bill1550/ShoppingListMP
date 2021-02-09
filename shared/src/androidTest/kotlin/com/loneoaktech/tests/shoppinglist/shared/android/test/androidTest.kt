package com.loneoaktech.tests.shoppinglist.shared.android.test

import com.loneoaktech.tests.shoppinglist.shared.Greeting
import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greeting().contains("Android"))
    }
}