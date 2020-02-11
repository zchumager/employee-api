package net.me.dev.app.repos;

import net.me.dev.app.models.Employee;
import net.me.dev.app.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByFirstNameIgnoreCase(String lastName);
    List<Employee> findByStatus(Status status);
}
