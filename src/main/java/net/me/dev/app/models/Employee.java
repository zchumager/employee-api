package net.me.dev.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name", nullable = false)
    @JsonProperty(value = "first_name")
    private String firstName;

    @Column(name = "middle_initial")
    @JsonProperty(value = "middle_initial")
    private String middleInitial;

    @Column(name = "last_name", nullable = false)
    @JsonProperty(value = "last_name")
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    @JsonProperty(value = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "date_of_employment", nullable = false)
    @JsonProperty(value = "date_of_employment")
    private Date dateOfEmployment;

    @Column(name = "status", nullable = false)
    private Status status;
}
