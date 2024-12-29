package stu.cn.ua.tourism.service;

import stu.cn.ua.tourism.models.Tourists;
import stu.cn.ua.tourism.repository.TouristsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TouristService {

    @Autowired
    private TouristsRepository touristRepository;

    public List<Tourists> getAllTourists() {
        return touristRepository.findAll();
    }

    public Optional<Tourists> findById(int id) {
        return touristRepository.findById(id);
    }

    public void addTourist(Tourists tourist) {
        touristRepository.save(tourist);
    }

    public void updateTourist(Tourists updatedTourist) {
        Tourists existingTourist = touristRepository.findById(updatedTourist.getTouristId())
                .orElseThrow(() -> new IllegalArgumentException("Tourist not found"));

        existingTourist.setName(updatedTourist.getName());
        existingTourist.setSurname(updatedTourist.getSurname());
        existingTourist.setPhone(updatedTourist.getPhone());
        existingTourist.setEmail(updatedTourist.getEmail());
        existingTourist.setBookings(updatedTourist.getBookings());

        touristRepository.save(existingTourist);
    }

    public void deleteTourist(int id) {
        touristRepository.deleteById(id);
    }
}
