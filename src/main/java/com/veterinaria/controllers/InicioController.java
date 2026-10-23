package com.veterinaria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InicioController {

    @GetMapping("/")
    public String mostrarInicio() {
        return "inicio";
    }

    @PostMapping("/consulta")
    public String recibirConsulta(@RequestParam String nombre, RedirectAttributes atributos) {
        atributos.addFlashAttribute("agradecimiento",
                "Gracias por escribirnos, " + nombre + ". Pronto responderemos tu consulta.");
        return "redirect:/";
    }
}