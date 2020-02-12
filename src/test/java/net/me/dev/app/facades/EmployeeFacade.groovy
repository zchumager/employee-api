package net.me.dev.app.facades

import net.me.dev.app.models.Employee
import net.me.dev.app.models.Status

class EmployeeFacade {
    static Employee buildEmployee(id = 1111) {
       def employee = new Employee();
        employee.id = id
        employee.firstName = "Test FirstName";
        employee.middleInitial = "T";
        employee.lastName = "Test Last Name";
        employee.dateOfBirth = new Date(1987, 11, 6)
        employee.dateOfEmployment = new Date(2019, 5, 4);
        employee.status = Status.ACTIVE

        return employee;
    }

    static List<Employee> buildEmployeeList(ids = [2222, 3333]) {
        def employees = [];
        ids.each {
            employees.add(buildEmployee(it))
        }

        return employees;
    }
}
