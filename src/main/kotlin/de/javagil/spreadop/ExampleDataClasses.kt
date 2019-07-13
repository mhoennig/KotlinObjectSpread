package de.javagil.spreadop

data class FullNameDC(var first: String, var last: String) {}

data class StreetAddressDC(val street: String, val city: String, val zipCode: String) {}

data class FullAddressDC(val first: String, val last: String, val street: String, val city: String, val zipCode: String, val planet: String)
