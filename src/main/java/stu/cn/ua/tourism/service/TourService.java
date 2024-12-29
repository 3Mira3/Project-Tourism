package stu.cn.ua.tourism.service;

import stu.cn.ua.tourism.models.Tours;
import stu.cn.ua.tourism.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    public List<Tours> getAllTours() {
        return tourRepository.findAll();
    }

    public Optional<Tours> findTourById(Integer id) {
        return tourRepository.findById(id);
    }

    public void saveTour(Tours tour) {
        tourRepository.save(tour);
    }

    public void updateTour(Tours updatedTour) {
        Optional<Tours> existingTourOpt = tourRepository.findById(updatedTour.getTourId());

        if (existingTourOpt.isPresent()) {
            Tours existingTour = existingTourOpt.get();
            existingTour.setName(updatedTour.getName());
            existingTour.setPrice(updatedTour.getPrice());
            existingTour.setCategory(updatedTour.getCategory());
            tourRepository.save(existingTour);
        }
    }

    public void deleteTourById(Integer id) {
        tourRepository.deleteById(id);
    }
}
