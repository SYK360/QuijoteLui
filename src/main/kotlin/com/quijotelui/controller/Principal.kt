package com.quijotelui.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class Principal{

    @RequestMapping("/")
    @ResponseBody
    fun index():String{
        return "Bienvenido a <h3>Quijote Lui :/</h3>"
    }

}