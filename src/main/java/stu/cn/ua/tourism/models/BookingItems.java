package stu.cn.ua.tourism.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "booking_items")
public class BookingItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Bookings booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = false)
    private Tours tour;

    @Min(1)
    private Integer quantity;

    // Гетери та сетери
    public Integer getBookingItemId() {
        return bookingItemId;
    }
    public void setBookingItemId(Integer bookingItemId) {
        this.bookingItemId = bookingItemId;
    }
    public Bookings getBooking() {
        return booking;
    }
    public void setBooking(Bookings booking) {
        this.booking = booking;
    }
    public Tours getTour() {
        return tour;
    }
    public void setTour(Tours tour) {
        this.tour = tour;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
