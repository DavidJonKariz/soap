package com.ics.soap.Repository;

import localhost._7991.universities.University;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UniversityRepository {
    private Map<String, University> universities = new HashMap<>();
//    private Map<Long, University> universitiesId = new HashMap<>();


    @PostConstruct
    private void loadData() {
//        Strathmore
        University strathmore = new University();
        strathmore.setName("Strathmore");
        strathmore.setLocation("Madaraka");
        strathmore.setYearFounded("1961");
        universities.put(strathmore.getName(), strathmore);

//        JKUAT
        University jkuat = new University();
        jkuat.setName("Jomo Kenyatta University");
        jkuat.setLocation("Juja");
        jkuat.setYearFounded("1994");
        universities.put(jkuat.getName(), jkuat);

//        KU
        University ku = new University();
        ku.setName("Kenyatta University");
        ku.setLocation("Thika");
        ku.setYearFounded("1985");
        universities.put(ku.getName(), ku);
    }

    public University getUniversityByName(String name) {
        return universities.get(name);
    }

//    public University getUniversityById(Long id) {
//        return universitiesId.get(id);
//    }

//    public List<University> getAll() {
//        return (List<University>) universities.values();
//    }
}
