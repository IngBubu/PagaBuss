package com.example.PagaBus

import java.util.regex.Pattern
import java.util.regex.Matcher

class ValidateEmail {
    companion object{

        var pat: Pattern?= null
        var mat: Matcher?= null

        fun isEmail(email: String): Boolean {
            pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-ZÑa-zñ0-9-]+\\.)+[A-ZÑa-zñ]{2,4}$")
            mat = pat!!.matcher(email)
            return mat!!.matches()
        }
    }

}