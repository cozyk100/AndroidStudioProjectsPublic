package com.example.mockk

import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * バージョン1.9.1から、モックを階層にチェーンすることができる
 */
class HierarchicalMockingTest {
    interface AddressBook {
        val contacts: List<Contact>
    }

    interface Contact {
        val name: String
        val telephone: String
        val address: Address
    }

    interface Address {
        val city: String
        val zip: String
    }

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun hierarchicalMocking() {
        val addressBook = mockk<AddressBook> {
            every { contacts } returns listOf(
                mockk {
                    every { name } returns "John"
                    every { telephone } returns "123-456-789"
                    every { address.city } returns "New-York"
                    every { address.zip } returns "123-45"
                },
                mockk {
                    every { name } returns "Alex"
                    every { telephone } returns "789-456-123"
                    every { address } returns mockk {
                        every { city } returns "Wroclaw"
                        every { zip } returns "543-21"
                    }
                }
            )
        }

        assertEquals("John", addressBook.contacts.get(0).name)
        assertEquals("543-21", addressBook.contacts.get(1).address.zip)
    }
}