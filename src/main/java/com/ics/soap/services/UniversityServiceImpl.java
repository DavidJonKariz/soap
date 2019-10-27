package com.ics.soap.services;

import com.ics.soap.Repository.UniversityRepo;
import com.ics.soap.model.UniversityModel;

import java.util.List;

public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepo universityRepo;

    public UniversityServiceImpl(UniversityRepo universityRepo) {
        this.universityRepo = universityRepo;
    }

    @Override
    public List<UniversityModel> getAllUniversities() {
        return universityRepo.findAll();
    }

    @Override
    public UniversityModel getUniversityById(Long id) throws Exception {
        return universityRepo.findById(id).orElseThrow(() ->
                new Exception("No University with id: " + id + " found."));
    }

    @Override
    public synchronized boolean addUniversity(UniversityModel university) {
        List<UniversityModel> list = universityRepo.findAll();
        if (list.size() > 0) {
            return false;
        } else {
            university = universityRepo.save(university);
            return true;
        }
    }

    @Override
    public void updateUniversity(UniversityModel university) {
        universityRepo.save(university);
    }

    @Override
    public void deleteUniversity(UniversityModel university) {
        universityRepo.delete(university);
    }
}
