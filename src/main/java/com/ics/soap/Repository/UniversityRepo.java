package com.ics.soap.Repository;

import com.ics.soap.model.UniversityModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepo extends JpaRepository<UniversityModel, Long> {
    UniversityModel findByNameEquals(String name);
    UniversityModel findByNameEqualsAndLocationEqualsAndYearFoundedEquals(String name, String location, String yearFounded);
}
