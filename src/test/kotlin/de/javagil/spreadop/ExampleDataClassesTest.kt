package de.javagil.spreadop

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class ExampleDataClassesTest {

    @Test
    fun `demonstrate how the map is connected to the object`() {

        val map = mutableMapOf("first" to "Henry", "last" to "O'Conner")

        val fullName = FullNameDC::class.by(map)

        // show that the properties and the map are detached
        fullName.first = "Henriette"
        map["last"] = "Breathtaking Beauty" // won't be applied to fullName
        assertThat(fullName).isEqualTo(FullNameDC("Henriette", "O'Conner"))
    }

    @Test
    fun `demonstrate how the ES6 object spread operator can be replaced`() {

        // ES6 code for comparision:
        // const fullName = { first: 'Henry', last: 'O\'Conner' }; // always needs property names
        // const streetAddress = { street: 'Dog Basket', city: 'Canine Town', zipCode: 'K9T' };
        // const fullAddress = { ...fullName, ...streetAddress, planet: 'Earth' };

        // in Kotlin also just 3 lines about same total number of characters, but type-safe
        val fullName = FullNameDC(first = "Henry", last = "O'Conner") // with property names ...
        val streetAddress = StreetAddressDC("Dog Basket", "Canine Town", "K9T") // ... or without
        val fullAddress = FullAddressDC::class.by(fullName() + streetAddress() + ("planet" to "Earth"))

        assertThat(fullAddress).isEqualTo(
                FullAddressDC("Henry", "O'Conner", "Dog Basket", "Canine Town", "K9T", "Earth"))
    }
}