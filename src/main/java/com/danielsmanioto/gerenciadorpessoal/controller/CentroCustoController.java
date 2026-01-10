package com.danielsmanioto.gerenciadorpessoal.controller;

import com.danielsmanioto.gerenciadorpessoal.model.CentroCusto;
import com.danielsmanioto.gerenciadorpessoal.service.CentroCustoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/centro-custo")
public class CentroCustoController {

    private final CentroCustoService centroCustoService;

    public CentroCustoController(CentroCustoService centroCustoService) {
        this.centroCustoService = centroCustoService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("centros", centroCustoService.findAll());
        return "centro-custo/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("centroCusto", new CentroCusto());
        return "centro-custo/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("centroCusto", centroCustoService.findById(id));
        return "centro-custo/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute CentroCusto centroCusto, BindingResult result) {
        if (result.hasErrors()) {
            return "centro-custo/form";
        }
        centroCustoService.save(centroCusto);
        return "redirect:/centro-custo";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        centroCustoService.deleteById(id);
        return "redirect:/centro-custo";
    }
}
