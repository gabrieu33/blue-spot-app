package com.ifam.pdm.blueSpotApp.core.entities

import com.ifam.pdm.blueSpotApp.core.enums.CivilState

class Landlord(
    id: String?,
    name: String,
    email: String,
    password: String,
    phone: String,
    nationality: String,
    civilState: CivilState,
    occupation: String,
    rg: String,
    cpf: String,
    address: String,
    val properties: MutableList<Property> = ArrayList<Property>()
) : User(id, name, email, password, phone, nationality, civilState, occupation, rg, cpf, address) {

}