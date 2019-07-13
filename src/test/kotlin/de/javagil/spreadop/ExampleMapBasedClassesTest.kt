package de.javagil.spreadop

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class ExampleMapBasedClassesTest {

    @Test
    fun `demonstrate how the map is connected to the object`() {
        val map = mutableMapOf<String, Any?>("first" to "Henry", "last" to "O'Conner")

        val fullName = FullNameMC(map)
        // to demonstrate that the properties and the map are actually bound
        fullName.first = "Henriette"
        map["last"] = "Breathtaking Beauty" // also changes fullName

        assertThat(fullName.first).isEqualTo("Henriette")
        assertThat(fullName.last).isEqualTo("Breathtaking Beauty")
    }

    @Test
    fun `demonstrates how terse an ES6 object spread operator can be replaced`() {

        // ES6 code for comparision:
        // const fullName = { first: 'Henry', last: 'O\'Conner' }; // always needs property names
        // const streetAddress = { street: 'Im Hundekorb 1', city: 'Canine Town', zipCode: 'K9T' };
        // const fullAddress = { ...fullName, ...streetAddress, planet: 'Earth' };

        // in Kotlin also just 3 lines about same total number of characters, but type-safe
        val fullName = FullNameMC(first = "Henry", last = "O'Conner") // with property names ...
        val streetAddress = StreetAddressMC("Dog Basket", "Canine Town", "K9T") // ... or without
        val fullAddress = FullAddressMC(fullName() + streetAddress() + ("planet" to "Earth"))

        // alternatively a little bit more verbose:
        val fullAddress2 = FullAddressMC(fullName.asMap() + streetAddress.asMap() + ("planet" to "Earth"))

        // but here this map-based-class is more verbose as it can't be a data-class
        with(fullAddress) {
            assertThat(first).isEqualTo("Henry")
            assertThat(last).isEqualTo("O'Conner")
            assertThat(street).isEqualTo("Dog Basket")
            assertThat(city).isEqualTo("Canine Town")
            assertThat(zipCode).isEqualTo("K9T")
            assertThat(planet).isEqualTo("Earth")
        }
    }
}