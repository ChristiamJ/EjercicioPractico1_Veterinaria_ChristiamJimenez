package com.veterinaria.controllers;

import com.veterinaria.domain.TipoServicio;
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
@RequestMapping("/tipos")
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    @GetMapping
    public String mostrarTipos(Model model) {
        model.addAttribute("tipos", tipoServicioService.listarTipos());
        return "tipos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tipoServicio", new TipoServicio());
        return "tipos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("tipoServicio", tipoServicioService.buscarTipo(id).orElse(new TipoServicio()));
        return "tipos/formulario";
    }

    @PostMapping("/registrar")
    public String registrarTipo(@ModelAttribute TipoServicio tipoServicio, RedirectAttributes atributos) {
        String accion = (tipoServicio.getId() == null) ? "agregado" : "modificado";
        tipoServicioService.guardarTipo(tipoServicio);
        atributos.addFlashAttribute("mensajeExito", "El tipo de servicio fue " + accion + " correctamente.");
        return "redirect:/tipos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarTipo(@PathVariable("id") Integer id, RedirectAttributes atributos) {
        try {
            tipoServicioService.eliminarTipo(id);
            atributos.addFlashAttribute("mensajeExito", "El tipo de servicio fue eliminado correctamente.");
        } catch (DataIntegrityViolationException e) {
            atributos.addFlashAttribute("mensajeError", "No se puede eliminar el tipo porque tiene servicios asociados.");
        }
        return "redirect:/tipos";
    }
}