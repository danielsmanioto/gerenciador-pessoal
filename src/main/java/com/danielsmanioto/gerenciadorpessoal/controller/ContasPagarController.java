package com.danielsmanioto.gerenciadorpessoal.controller;

import com.danielsmanioto.gerenciadorpessoal.model.ContasPagar;
import com.danielsmanioto.gerenciadorpessoal.service.CentroCustoService;
import com.danielsmanioto.gerenciadorpessoal.service.ContasPagarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contas-pagar")
public class ContasPagarController {

    private final ContasPagarService contasPagarService;
    private final CentroCustoService centroCustoService;

    public ContasPagarController(ContasPagarService contasPagarService, CentroCustoService centroCustoService) {
        this.contasPagarService = contasPagarService;
        this.centroCustoService = centroCustoService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("contas", contasPagarService.findAll());
        return "contas-pagar/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("contasPagar", new ContasPagar());
        model.addAttribute("centrosCusto", centroCustoService.findAll());
        model.addAttribute("statusList", ContasPagar.StatusConta.values());
        return "contas-pagar/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("contasPagar", contasPagarService.findById(id));
        model.addAttribute("centrosCusto", centroCustoService.findAll());
        model.addAttribute("statusList", ContasPagar.StatusConta.values());
        return "contas-pagar/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute ContasPagar contasPagar, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("centrosCusto", centroCustoService.findAll());
            model.addAttribute("statusList", ContasPagar.StatusConta.values());
            return "contas-pagar/form";
        }
        contasPagarService.save(contasPagar);
        return "redirect:/contas-pagar";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        contasPagarService.deleteById(id);
        return "redirect:/contas-pagar";
    }
}
