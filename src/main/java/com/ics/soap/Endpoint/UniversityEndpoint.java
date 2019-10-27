package com.ics.soap.Endpoint;

import com.ics.soap.Repository.UniversityRepository;
import com.ics.soap.model.UniversityModel;
import com.ics.soap.services.UniversityService;
import localhost._7991.universities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class UniversityEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:7991/universities";
    private UniversityService universityService;
//    private UniversityRepository universityRepository;


    @Autowired
    public UniversityEndpoint(UniversityRepository universityRepository) {
//        this.universityRepository = universityRepository;
    }

//    One University with name
//    @ResponsePayload
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUniversityRequest")
//    public GetUniversityResponse getUniversity(@RequestPayload GetUniversityRequest request) {
//        GetUniversityResponse response = new GetUniversityResponse();
//        response.setUniversity(universityRepository.getUniversityByName(request.getName()));
//
//        return response;
//    }

//    Get University with ID
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUniversityRequest")
    public GetUniversityByIdResponse getUniversity(@RequestPayload GetUniversityByIdRequest request) throws Exception {
        GetUniversityByIdResponse response = new GetUniversityByIdResponse();
        University university = new University();
        BeanUtils.copyProperties(universityService.getUniversityById(request.getUniversityId()), university);
        response.setUniversity(university);
        return response;
    }

//    All Universities
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUniversitiesRequest")
    public GetAllUniversitiesResponse getAllUniversities(@RequestPayload GetAllUniversitiesRequest request) {
        GetAllUniversitiesResponse response = new GetAllUniversitiesResponse();
        List<University> universities = new ArrayList<University>();
        List<UniversityModel> universityModels = universityService.getAllUniversities();
        for (UniversityModel universityModel : universityModels) {
            University ob = new University();
            BeanUtils.copyProperties(universityModel, ob);
            universities.add(ob);
        }
        response.getUniversity().addAll(universities);
        return response;
    }

//    Add University
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUniversityRequest")
    public AddUniversityResponse addUniversityResponse(@RequestPayload AddUniversityRequest request) {
        AddUniversityResponse response = new AddUniversityResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        UniversityModel universityModel = new UniversityModel(request.getName(), request.getLocation(), request.getYearFounded());
        boolean flag = universityService.addUniversity(universityModel);
        if (!flag) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");
            response.setServiceStatus(serviceStatus);
        } else {
            University university = new University();
            BeanUtils.copyProperties(universityModel, university);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
            response.setServiceStatus(serviceStatus);
        }
        return response;
    }

//    Update Unversity
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUniversityRequest")
    public UpdateUniversityResponse getAllUniversities(@RequestPayload UpdateUniversityRequest request) {
        UpdateUniversityResponse response = new UpdateUniversityResponse();
        UniversityModel universityModel = new UniversityModel(request.getUniversity().getName(), request.getUniversity().getLocation(), request.getUniversity().getYearFounded());
        BeanUtils.copyProperties(request.getUniversity(), universityModel);
        universityService.updateUniversity(universityModel);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Content Updated Successfully");
        response.setServiceStatus(serviceStatus);
        return response;
    }

//    Delete University
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUniversityRequest")
    public DeleteUniversityResponse getAllUniversities(@RequestPayload DeleteUniversityRequest request) throws Exception {
        DeleteUniversityResponse response = new DeleteUniversityResponse();
        UniversityModel universityModel = universityService.getUniversityById(request.getUniversityId());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (universityModel == null) {
            serviceStatus.setStatusCode("Fail");
            serviceStatus.setMessage("Content Not Available");
        } else {
            universityService.deleteUniversity(universityModel);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }


}
