package com.ics.soap.Endpoint;

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
    private final UniversityService universityService;


    @Autowired
    public UniversityEndpoint(UniversityService universityService) {
        this.universityService = universityService;
    }

//    TODO: One University with name ***
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUniversityRequest")
    public GetUniversityResponse getUniversity(@RequestPayload GetUniversityRequest request) {
        GetUniversityResponse response = new GetUniversityResponse();
//        UniversityModel universityModel = universityService.findByName(request.getName());
        University university = new University();
        BeanUtils.copyProperties(universityService.findByName(request.getName()), university);
        response.setUniversity(university);
        return response;
    }

//    TODO: Get University with ID ***
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUniversityByIdRequest")
    public GetUniversityByIdResponse getUniversityById(@RequestPayload GetUniversityByIdRequest request) throws Exception {
        GetUniversityByIdResponse response = new GetUniversityByIdResponse();
        University university = new University();
        BeanUtils.copyProperties(universityService.findById(request.getUniversityId()), university);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("University With ID: " + request.getUniversityId() + " Found");
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

//    Add University ***
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUniversityRequest")
    public AddUniversityResponse addUniversity(@RequestPayload AddUniversityRequest request) throws Exception {
        System.out.println("Name: " + request.getName());
        System.out.println("Location: " + request.getLocation());
        System.out.println("Year Founded: " + request.getYearFounded());
        AddUniversityResponse response = new AddUniversityResponse();
        University university = new University();
        university.setName(request.getName());
        university.setLocation(request.getLocation());
        university.setYearFounded(request.getYearFounded());
        ServiceStatus serviceStatus = universityService.addUniversity(university, new ServiceStatus());
        response.setServiceStatus(serviceStatus);
        return response;
    }

//    Update University (Works)
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUniversityRequest")
    public UpdateUniversityResponse updateUniversity(@RequestPayload UpdateUniversityRequest request) throws Exception {
        UpdateUniversityResponse response = new UpdateUniversityResponse();
        UniversityModel updateUni = new UniversityModel(request.getName(), request.getLocation(), request.getYearFounded());
        ServiceStatus serviceStatus = universityService.updateUniversity(request.getUniversityId(), updateUni, new ServiceStatus());
        response.setServiceStatus(serviceStatus);
        return response;
    }

//    Delete University (Works)
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUniversityRequest")
    public DeleteUniversityResponse deleteUniversity(@RequestPayload DeleteUniversityRequest request) throws Exception {
        DeleteUniversityResponse response = new DeleteUniversityResponse();
        UniversityModel universityModel = universityService.findById(request.getUniversityId());
        ServiceStatus serviceStatus = universityService.deleteUniversity(universityModel, new ServiceStatus());
        response.setServiceStatus(serviceStatus);
        return response;
    }


}
