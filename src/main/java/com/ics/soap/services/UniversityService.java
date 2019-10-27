package com.ics.soap.services;

import com.ics.soap.model.UniversityModel;

import java.util.List;

public interface UniversityService {
    List<UniversityModel> getAllUniversities();
    UniversityModel getUniversityById(Long id) throws Exception;
    boolean addUniversity(UniversityModel university);
    void updateUniversity(UniversityModel university);
    void deleteUniversity(UniversityModel university);
}
