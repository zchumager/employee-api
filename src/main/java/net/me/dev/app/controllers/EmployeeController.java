package net.me.dev.app.controllers;

import net.me.dev.app.models.Employee;
import net.me.dev.app.models.Status;
import net.me.dev.app.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private EmployeeRepository repository;

    @PostMapping("/")
    public ResponseEntity<Employee> createEntry(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok().body(repository.save(employee));
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id))));
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<Employee>> findByFirstName(@PathVariable("firstName") String firstName) {
        return ResponseEntity.ok().body(repository.findByFirstNameIgnoreCase(firstName));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Employee>> findByStatus(@PathVariable("status") Status status) {
        return ResponseEntity.ok().body(repository.findByStatus(status));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Employee> modifyEntry(@PathVariable("id") Integer id, @Valid @RequestBody Employee details) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));

        employee.setFirstName(details.getFirstName());
        employee.setMiddleInitial(details.getMiddleInitial());
        employee.setLastName(details.getLastName());
        employee.setDateOfBirth(details.getDateOfBirth());
        employee.setDateOfEmployment(employee.getDateOfEmployment());
        employee.setStatus(employee.getStatus());

        return ResponseEntity.ok().body(repository.save(employee));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable("id") Integer id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        repository.delete(employee);

        return ResponseEntity.ok().body("Entry with id: " + id + " has been deleted");
    }
}
