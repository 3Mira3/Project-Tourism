package stu.cn.ua.tourism.controllers;

import stu.cn.ua.tourism.models.Tours;
import stu.cn.ua.tourism.service.TourService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping
    public String listTours(Model model) {
        model.addAttribute("tours", tourService.getAllTours());
        return "tours";
    }

    @GetMapping("/add")
    public String addTourForm(Model model) {
        model.addAttribute("tour", new Tours());
        return "add-tour";
    }

    @PostMapping("/add")
    public String addTour(@Valid @ModelAttribute("tour") Tours tour, BindingResult result) {
        if (result.hasErrors()) {
            return "add-tour";
        }
        tourService.saveTour(tour);
        return "redirect:/tours";
    }

    @GetMapping("/edit/{id}")
    public String editTourForm(@PathVariable("id") Integer id, Model model) {
        Tours tour = tourService.findTourById(id).orElse(null);
        if (tour != null) {
            model.addAttribute("tour", tour);
            return "edit-tour";
        } else {
            return "redirect:/tours";
        }
    }

    @PostMapping("/update/{id}")
    public String updateTour(@PathVariable("id") Integer id, @Valid @ModelAttribute("tour") Tours tour, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tour", tour);
            return "edit-tour";
        }
        tour.setTourId(id);
        tourService.updateTour(tour);
        return "redirect:/tours";
    }

    @GetMapping("/delete/{id}")
    public String deleteTour(@PathVariable("id") Integer id) {
        tourService.deleteTourById(id);
        return "redirect:/tours";
    }
}
