package com.jetpack.codechallenge.models

class Schools : ArrayList<SchoolDetails>()

data class SchoolDetails(
    val dbn: String,
    val city:String,
    val zip:String,
    val primary_address_line_1: String,
    val school_name: String,
)
//val school_email: String = ""
