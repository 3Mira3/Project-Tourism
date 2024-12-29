package stu.cn.ua.tourism.controllers;

import stu.cn.ua.tourism.models.Bookings;
import stu.cn.ua.tourism.service.TouristService;
import stu.cn.ua.tourism.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TouristService touristService;

    @GetMapping
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings"; // bookings.html
    }

    @GetMapping("/add")
    public String addBookingForm(Model model) {
        model.addAttribute("booking", new Bookings());
        model.addAttribute("tourists", touristService.getAllTourists());
        return "add-booking";
    }

    @PostMapping("/add")
    public String addBooking(@Valid @ModelAttribute("booking") Bookings booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tourists", touristService.getAllTourists());
            return "add-booking";
        }
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/edit/{id}")
    public String editBookingForm(@PathVariable("id") Integer id, Model model) {
        Optional<Bookings> bookingOpt = bookingService.findBookingById(id);
        if (bookingOpt.isPresent()) {
            model.addAttribute("booking", bookingOpt.get());
            model.addAttribute("tourists", touristService.getAllTourists());
            return "edit-booking";
        }
        return "redirect:/bookings";
    }

    @PostMapping("/update/{id}")
    public String updateBooking(@PathVariable("id") Integer id, @Valid @ModelAttribute("booking") Bookings booking,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tourists", touristService.getAllTourists());
            return "edit-booking";
        }
        bookingService.updateBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Integer id) {
        bookingService.deleteBookingById(id);
        return "redirect:/bookings";
    }
}
