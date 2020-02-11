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

    @Column(name = "FIRST_NAME", nullable = false)
    @JsonProperty(value = "first_name")
    private String firstName;

    @Column(name = "MIDDLE_INITIAL")
    @JsonProperty(value = "middle_initial")
    private String middleInitial;

    @Column(name = "LAST_NAME", nullable = false)
    @JsonProperty(value = "last_name")
    private String lastName;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    @JsonProperty(value = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "DATE_OF_EMPLOYMENT", nullable = false)
    @JsonProperty(value = "date_of_employment")
    private Date dateOfEmployment;

    @Column(name = "STATUS", nullable = false)
    private Status status;
}
