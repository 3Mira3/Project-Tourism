package stu.cn.ua.tourism.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tourists")
public class Tourists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer touristId;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Name can only contain letters")
    private String name;

    @NotBlank(message = "Surname is required")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Surname can only contain letters")
    private String surname;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\+?[0-9]+$", message = "Phone must be a valid number")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @OneToMany(mappedBy = "tourist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookings> bookings = new ArrayList<>();

    // Гетери та сетери
    public Integer getTouristId() {
        return touristId;
    }
    public void setTouristId(Integer touristId) {
        this.touristId = touristId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Bookings> getBookings() {
        return bookings;
    }
    public void setBookings(List<Bookings> bookings) {
        this.bookings.clear();
        if (bookings != null) {
            this.bookings.addAll(bookings);
        }
    }
}
