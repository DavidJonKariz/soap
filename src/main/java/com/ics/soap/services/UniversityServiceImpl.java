package com.ics.soap.services;

import com.ics.soap.Repository.UniversityRepo;
import com.ics.soap.model.UniversityModel;
import localhost._7991.universities.ServiceStatus;
import localhost._7991.universities.University;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public UniversityModel findByName(String name) {
        return universityRepo.findByNameEquals(name);
    }

    @Override
    public UniversityModel findUniversity(String name, String location, String yearFounded) {
        return universityRepo.findByNameEqualsAndLocationEqualsAndYearFoundedEquals(name, location, yearFounded);
    }

    @Override
    public UniversityModel findById(Long id) throws Exception {
        return universityRepo.findById(id).orElseThrow(() ->
                new Exception("No University with id: " + id + " found."));
    }

    @Override
    public ServiceStatus addUniversity(University university, ServiceStatus serviceStatus) throws Exception {
        UniversityModel universityModel = findByName(university.getName());
        if (universityModel == null) {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("University Added Successfully");
            universityModel = new UniversityModel(university.getName(), university.getLocation(), university.getYearFounded());
            universityRepo.save(universityModel);
            return serviceStatus;
        }
        serviceStatus.setStatusCode("CONFLICT");
        serviceStatus.setMessage("University Already Exists");
        return serviceStatus;
    }

    @Override
    public ServiceStatus updateUniversity(Long universityId, UniversityModel updateUni, ServiceStatus serviceStatus) throws Exception {
        UniversityModel universityModel = findById(universityId);
        if (universityModel != null) {
            universityModel.setName(updateUni.getName());
            universityModel.setLocation(updateUni.getLocation());
            universityModel.setYearFounded(updateUni.getYearFounded());
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("University Updated Successfully");
            universityRepo.save(universityModel);
            return serviceStatus;
        }
        serviceStatus.setStatusCode("FAILURE");
        serviceStatus.setMessage("University Updated Unsuccessfully");
        throw new Exception("University does not exist.");
    }

    @Override
    public ServiceStatus deleteUniversity(UniversityModel university, ServiceStatus serviceStatus) throws Exception {
        if (findById(university.getId()) != null) {
            universityRepo.delete(university);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
            return serviceStatus;
        }
        serviceStatus.setStatusCode("FAILURE");
        serviceStatus.setMessage("University Does not Exist");
        throw new Exception("University does not exist.");
    }
}
