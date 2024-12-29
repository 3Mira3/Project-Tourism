package stu.cn.ua.tourism.service;

import stu.cn.ua.tourism.models.BookingItems;
import stu.cn.ua.tourism.repository.BookingItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingItemsService {

    @Autowired
    private BookingItemsRepository bookingItemsRepository;

    public List<BookingItems> getAllBookingItems() {
        return bookingItemsRepository.findAll();
    }

    public Optional<BookingItems> findBookingItemById(Integer id) {
        return bookingItemsRepository.findById(id);
    }

    public void saveBookingItem(BookingItems bookingItem) {
        bookingItemsRepository.save(bookingItem);
    }

    public void updateBookingItem(BookingItems updatedBookingItem) {
        Optional<BookingItems> existingBookingItemOpt = bookingItemsRepository.findById(updatedBookingItem.getBookingItemId());

        if (existingBookingItemOpt.isPresent()) {
            BookingItems existingBookingItem = existingBookingItemOpt.get();
            existingBookingItem.setBooking(updatedBookingItem.getBooking());
            existingBookingItem.setTour(updatedBookingItem.getTour());
            existingBookingItem.setQuantity(updatedBookingItem.getQuantity());
            bookingItemsRepository.save(existingBookingItem);
        }
    }

    public void deleteBookingItemById(Integer id) {
        bookingItemsRepository.deleteById(id);
    }
}
