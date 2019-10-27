package com.ics.soap;

import com.ics.soap.Repository.UniversityRepo;
import com.ics.soap.model.UniversityModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyData implements CommandLineRunner {
    private final UniversityRepo universityRepo;

    public DummyData(UniversityRepo universityRepo) {
        this.universityRepo = universityRepo;
    }


    @Override
    public void run(String... args) throws Exception {
        UniversityModel strath = new UniversityModel("Strathmore University", "Madaraka", "2002");
        UniversityModel jkuat = new UniversityModel("Jomo Kenyatta University of Agriculture and Technology","Juja", "1994");
        universityRepo.save(strath);
        universityRepo.save(jkuat);
    }
}
