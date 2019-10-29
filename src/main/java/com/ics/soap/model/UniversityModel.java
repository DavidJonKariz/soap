package com.ics.soap.model;

import javax.persistence.*;

@Entity
@Table(name = "universities")
public class UniversityModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "yearFounded")
    private String yearFounded;

    private UniversityModel() {}

    public UniversityModel(String name, String location, String yearFounded) {
        this.name = name;
        this.location = location;
        this.yearFounded = yearFounded;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(String yearFounded) {
        this.yearFounded = yearFounded;
    }

    @Override
    public String toString() {
        return "University {" +
                "id=" + id +
                ", name=" + name +
                ", location=" + location +
                ", yearFounded=" + yearFounded +
                '}';
    }
}
