package stu.cn.ua.tourism.controllers;

import stu.cn.ua.tourism.models.BookingItems;
import stu.cn.ua.tourism.models.Bookings;
import stu.cn.ua.tourism.models.Tours;
import stu.cn.ua.tourism.service.BookingItemsService;
import stu.cn.ua.tourism.service.BookingService;
import stu.cn.ua.tourism.service.TourService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/booking-items")
public class BookingItemsController {
    @Autowired
    private BookingItemsService bookingItemsService;
    @Autowired
    private BookingService bookingsService;
    @Autowired
    private TourService toursService;

    @GetMapping
    public String listBookingItems(Model model) {
        model.addAttribute("bookingItems", bookingItemsService.getAllBookingItems());
        return "booking-items";
    }

    @GetMapping("/add")
    public String addBookingItemForm(Model model) {
        model.addAttribute("bookingItem", new BookingItems());
        model.addAttribute("bookings", bookingsService.getAllBookings());
        model.addAttribute("tours", toursService.getAllTours());
        return "add-booking-item";
    }

    @PostMapping("/add")
    public String addBookingItem(@Valid @ModelAttribute BookingItems bookingItem, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookingItem", bookingItem);
            model.addAttribute("bookings", bookingsService.getAllBookings());
            model.addAttribute("tours", toursService.getAllTours());
            return "add-booking-item";
        }
        bookingItemsService.saveBookingItem(bookingItem);
        return "redirect:/booking-items";
    }

    @GetMapping("/edit/{id}")
    public String editBookingItemForm(@PathVariable("id") Integer id, Model model) {
        Optional<BookingItems> bookingItemOpt = bookingItemsService.findBookingItemById(id);
        if (bookingItemOpt.isPresent()) {
            model.addAttribute("bookingItem", bookingItemOpt.get());
            model.addAttribute("bookings", bookingsService.getAllBookings());
            model.addAttribute("tours", toursService.getAllTours());
            return "edit-booking-item";
        } else {
            return "redirect:/booking-items";
        }
    }

    @PostMapping("/update/{id}")
    public String updateBookingItem(
            @PathVariable("id") Integer id,
            @Valid @ModelAttribute BookingItems bookingItem,
            BindingResult bindingResult,
            @RequestParam("booking.bookingId") Integer bookingId,
            @RequestParam("tour.tourId") Integer tourId,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("bookingItem", bookingItem);
            model.addAttribute("bookings", bookingsService.getAllBookings());
            model.addAttribute("tours", toursService.getAllTours());
            return "edit-booking-item";
        }

        Optional<Bookings> booking = bookingsService.findBookingById(bookingId);
        Optional<Tours> tour = toursService.findTourById(tourId);

        if (booking.isPresent() && tour.isPresent()) {
            bookingItem.setBookingItemId(id);
            bookingItem.setBooking(booking.get());
            bookingItem.setTour(tour.get());
            bookingItemsService.updateBookingItem(bookingItem);
        }

        return "redirect:/booking-items";
    }

    @GetMapping("/delete/{id}")
    public String deleteBookingItem(@PathVariable("id") Integer id) {
        bookingItemsService.deleteBookingItemById(id);
        return "redirect:/booking-items";
    }
}
