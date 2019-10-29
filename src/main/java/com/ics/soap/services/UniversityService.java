package com.ics.soap.services;

import com.ics.soap.model.UniversityModel;
import localhost._7991.universities.ServiceStatus;

import java.util.List;

public interface UniversityService {
    List<UniversityModel> getAllUniversities();
    UniversityModel findByName(String name);
    UniversityModel findUniversity(String name, String location, String yearFounded);
    UniversityModel findById(Long id) throws Exception;
    ServiceStatus addUniversity(UniversityModel university, ServiceStatus serviceStatus) throws Exception;
    ServiceStatus updateUniversity(Long id, UniversityModel university, ServiceStatus serviceStatus) throws Exception;
    ServiceStatus deleteUniversity(UniversityModel university, ServiceStatus serviceStatus) throws Exception;
}
