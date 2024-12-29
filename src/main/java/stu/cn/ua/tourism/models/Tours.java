package stu.cn.ua.tourism.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tours")
public class Tours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tourId;

    @NotBlank(message = "Tour name is required")
    @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Tour name can only contain letters, numbers, and spaces")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "Category is required")
    @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Category can only contain letters, numbers, and spaces")
    private String category;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingItems> bookingItems = new ArrayList<>();

    // Гетери та сетери
    public Integer getTourId() {
        return tourId;
    }
    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public List<BookingItems> getBookingItems() {
        return bookingItems;
    }
    public void setBookingItems(List<BookingItems> bookingItems) {
        this.bookingItems = bookingItems;
    }
}
