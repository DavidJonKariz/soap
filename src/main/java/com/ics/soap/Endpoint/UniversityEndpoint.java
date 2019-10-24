//package com.ics.soap.Endpoint;
//
//import com.ics.soap.Repository.UniversityRepository;
//import localhost._7991.soap_server.GetUniversityRequest;
//import localhost._7991.soap_server.GetUniversityResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//@Endpoint
//public class UniversityEndpoint {
//    private static final String NAMESPACE_URI = "http://localhost:7000/soap-server";
//
//
//    private UniversityRepository universityRepository;
//
//
//    @Autowired
//    public UniversityRepository(UniversityRepository universityRepository) {
//        this.universityRepository = universityRepository;
//    }
//
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUniversityRequest")
//    @ResponsePayload
//    public GetUniversityResponse getUniversity(@RequestPayload GetUniversityRequest request) {
//        GetUniversityResponse response = new GetUniversityResponse();
//        response.setUniversity(universityRepository.findUniversity(request.getName()));
//
//        return response;
//    }
//
//}
