package stu.cn.ua.tourism.controllers;

import stu.cn.ua.tourism.models.Tourists;
import stu.cn.ua.tourism.service.TouristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tourists")
public class TouristController {
    @Autowired
    private TouristService touristService;

    @GetMapping
    public String listTourists(Model model) {
        model.addAttribute("tourists", touristService.getAllTourists());
        return "tourists"; // tourists.html
    }

    @GetMapping("/add")
    public String addTouristForm(Model model) {
        model.addAttribute("tourist", new Tourists());
        return "add-tourist";
    }

    @PostMapping("/add")
    public String addTourist(@Valid @ModelAttribute("tourist") Tourists tourist, BindingResult result) {
        if (result.hasErrors()) {
            return "add-tourist";
        }
        touristService.addTourist(tourist);
        return "redirect:/tourists";
    }

    @GetMapping("/edit/{id}")
    public String editTouristForm(@PathVariable("id") int id, Model model) {
        Tourists tourist = touristService.findById(id).orElse(null);
        if (tourist != null) {
            model.addAttribute("tourist", tourist);
            return "edit-tourist";
        } else {
            return "redirect:/tourists";
        }
    }

    @PostMapping("/update/{id}")
    public String updateTourist(@PathVariable("id") int id, @Valid @ModelAttribute("tourist") Tourists tourist, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-tourist";
        }
        tourist.setTouristId(id);
        touristService.updateTourist(tourist);
        return "redirect:/tourists";
    }

    @GetMapping("/delete/{id}")
    public String deleteTourist(@PathVariable("id") int id) {
        touristService.deleteTourist(id);
        return "redirect:/tourists";
    }
}
