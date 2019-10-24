//package com.ics.soap.Endpoint;
//
//import com.ics.soap.Repository.CountryRepository;
//import localhost._7000.soap_server.GetCountryRequest;
//import localhost._7000.soap_server.GetCountryResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//@Endpoint
//public class CountryEndpoint {
//    private static final String NAMESPACE_URI = "http://localhost:7000/soap-server";
//
//
//    private CountryRepository countryRepository;
//
//
//    @Autowired
//    public CountryEndpoint(CountryRepository countryRepository) {
//        this.countryRepository = countryRepository;
//    }
//
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
//    @ResponsePayload
//    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
//        GetCountryResponse response = new GetCountryResponse();
//        response.setCountry(countryRepository.findCountry(request.getName()));
//        return response;
//    }
//
//}
