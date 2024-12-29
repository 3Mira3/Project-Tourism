package stu.cn.ua.tourism.service;

import stu.cn.ua.tourism.models.Bookings;
import stu.cn.ua.tourism.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingsRepository bookingRepository;

    public List<Bookings> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Bookings> findBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    public void saveBooking(Bookings booking) {
        bookingRepository.save(booking);
    }

    public void updateBooking(Bookings updatedBooking) {
        Optional<Bookings> existingBookingOpt = bookingRepository.findById(updatedBooking.getBookingId());

        if (existingBookingOpt.isPresent()) {
            Bookings existingBooking = existingBookingOpt.get();
            existingBooking.setTourist(updatedBooking.getTourist());
            existingBooking.setBookingDate(updatedBooking.getBookingDate());
            bookingRepository.save(existingBooking);
        }
    }

    public void deleteBookingById(Integer id) {
        bookingRepository.deleteById(id);
    }
}
