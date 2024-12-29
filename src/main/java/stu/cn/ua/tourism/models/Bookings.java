package stu.cn.ua.tourism.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "tourist_id", nullable = false)
    private Tourists tourist;

    @NotNull(message = "Booking date is required")
    @PastOrPresent(message = "Booking date cannot be in the future")
    private LocalDate bookingDate;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingItems> bookingItems = new ArrayList<>();

    // Гетери та сетери
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public Tourists getTourist() {
        return tourist;
    }
    public void setTourist(Tourists tourist) {
        this.tourist = tourist;
    }
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    public List<BookingItems> getBookingItems() {
        return bookingItems;
    }
    public void setBookingItems(List<BookingItems> bookingItems) {
        this.bookingItems.clear();
        if (bookingItems != null) {
            this.bookingItems.addAll(bookingItems);
        }
    }

    // Метод для додавання елемента до бронювання
    public void addBookingItem(BookingItems bookingItem) {
        bookingItems.add(bookingItem);
        bookingItem.setBooking(this);
    }

    // Метод для видалення елемента з бронювання
    public void removeBookingItem(BookingItems bookingItem) {
        bookingItems.remove(bookingItem);
        bookingItem.setBooking(null);
    }
}
