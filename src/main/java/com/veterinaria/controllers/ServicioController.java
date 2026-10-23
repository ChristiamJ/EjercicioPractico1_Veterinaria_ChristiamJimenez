package com.veterinaria.controllers;

import com.veterinaria.domain.Servicio;
import com.veterinaria.service.ServicioService;
import com.veterinaria.service.TipoServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final TipoServicioService tipoServicioService;

    @GetMapping
    public String mostrarServicios(Model model) {
        model.addAttribute("servicios", servicioService.listarServicios());
        return "servicios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("tipos", tipoServicioService.listarTipos());
        return "servicios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("servicio", servicioService.buscarServicio(id).orElse(new Servicio()));
        model.addAttribute("tipos", tipoServicioService.listarTipos());
        return "servicios/formulario";
    }

    @PostMapping("/registrar")
    public String registrarServicio(@ModelAttribute Servicio servicio, RedirectAttributes atributos) {
        String accion = (servicio.getId() == null) ? "agregado" : "modificado";
        servicioService.guardarServicio(servicio);
        atributos.addFlashAttribute("mensajeExito", "El servicio fue " + accion + " correctamente.");
        return "redirect:/servicios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable("id") Integer id, RedirectAttributes atributos) {
        try {
            servicioService.eliminarServicio(id);
            atributos.addFlashAttribute("mensajeExito", "El servicio fue eliminado correctamente.");
        } catch (DataIntegrityViolationException e) {
            atributos.addFlashAttribute("mensajeError", "No se puede eliminar el servicio porque tiene citas asociadas.");
        }
        return "redirect:/servicios";
    }
}