package de.javagil.spreadop

// uses an immutable Map just to show how " by " works
class FullNameMC(val map: MutableMap<String, Any?>) {
    var first: String by map
    var last: String by map

    // optional if you want simple direct construction from values
    // but it makes the map-based-class more verbose
    constructor(first: String, last: String) :
            this(mutableMapOf("first" to first, "last" to last)) {
    }
}

class StreetAddressMC(val map: Map<String, Any?>) {
    val street: String by map
    val city: String by map
    val zipCode: String by map

    // optional if you want simple direct construction from values
    // but it makes the map-based-class more verbose
    constructor(street: String, city: String, zipCode: String) :
            this(mutableMapOf("street" to street, "city" to city, "zipCode" to zipCode)) {
    }
}

class FullAddressMC(val map: Map<String, Any?>) {
    val first: String by map
    val last: String by map
    val street: String by map
    val city: String by map
    val zipCode: String by map
    val planet: String by map

    // optional if you want simple direct construction from values
    // but it makes the map-based-class more verbose
    constructor(first: String, last: String, street: String, city: String, zipCode: String, planet: String) :
            this(mutableMapOf("first" to first, "last" to last, "street" to street, "city" to city, "zipCode" to zipCode, "planet" to planet)) {
    }
}
