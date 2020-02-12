package net.me.dev.app.bdd

import net.me.dev.app.controllers.EmployeeController
import net.me.dev.app.facades.EmployeeFacade
import net.me.dev.app.repos.EmployeeRepository
import org.springframework.http.HttpStatus
import spock.lang.Shared
import spock.lang.Specification

class EmployeeControllerTest extends Specification{
    def expected_result
    def actual_result
    @Shared EmployeeRepository repository;
    @Shared EmployeeController controller;

    def setup() {
        repository = Mock(EmployeeRepository)
        controller = new EmployeeController(repository)
    }

    def "verify findAll"() {
        given: "expected result is set"
            expected_result = EmployeeFacade.buildEmployeeList()
            // >> its the mock operator it means when you call findAll return expected_result object
            repository.findAll() >> expected_result
        when: "actual result is retrieved"
            actual_result = controller.findAll()
        then: "actual result is compared to expected result"
            assert actual_result.statusCode == HttpStatus.OK
            assert actual_result.body == expected_result
    }

    def "verify findById"() {
        given: "expected result is set"
            expected_result = EmployeeFacade.buildEmployee()

            // >> its the mock operator it means when you call findAll return expected_result object
            repository.findById(expected_result.id) >> Optional.of(expected_result)
        when: "actual data is retrieved"
            actual_result = controller.findById(expected_result.id)
        then: "actual result is compared to expected result"
            assert actual_result.statusCode == HttpStatus.OK
            assert actual_result.body == expected_result
    }

    def "verify findByFirstName"() {
        given: "expected result is set"
            expected_result = EmployeeFacade.buildEmployeeList()

            // >> its the mock operator it means when you call findAll return expected_result object
            repository.findByFirstNameIgnoreCase(expected_result.first().firstName) >> expected_result
        when: "actual result is retrieved"
            actual_result = controller.findByFirstName(expected_result.first().firstName)
        then: "actual result is compared to expected result"
        verifyAll(actual_result) {
            statusCode == HttpStatus.OK
            body == expected_result
        }
    }

    def "verify findByStatus"() {
        given: "expected result is set"
            expected_result = EmployeeFacade.buildEmployeeList()

            // >> its the mock operator it means when you call findAll return expected_result object
            repository.findByStatus(expected_result.first().status) >> expected_result
        when: "actual result is retrieved"
            actual_result = controller.findByStatus(expected_result.first().status)
        then: "actual result is compared to expected result"
            verifyAll(actual_result) {
                statusCode == HttpStatus.OK
                body == expected_result
            }
    }

    def "verify createEntry"() {
        given: "expected result is set"
            expected_result = EmployeeFacade.buildEmployee()

            // >> its the mock operator it means when you call findAll return expected_result object
            repository.save(expected_result) >> expected_result
        when: "actual result is retrieved"
            actual_result = controller.createEntry(expected_result)
        then: "actual result is compared to expected result"
            verifyAll(actual_result) {
                statusCode == HttpStatus.OK
                body == expected_result
            }
    }

    def "verify modifyEntry"() {
        given: "expected result is set"
            expected_result = EmployeeFacade.buildEmployee()
            expected_result.firstName = "Test LastName Modified"

            //findById and save were mocked since modifyEntry is using both methods
            repository.findById(expected_result.id) >> Optional.of(expected_result)
            repository.save(expected_result) >> expected_result
        when: "actual result is retrieved"
            actual_result = controller.modifyEntry(expected_result.id, expected_result)
        then: "actual result is compared to expected result"
            verifyAll(actual_result) {
                statusCode == HttpStatus.OK
                body == expected_result
            }
    }

    def "verify deleteEntry"() {
        given: "expected result is set"
            expected_result = EmployeeFacade.buildEmployee()
            repository.findById(expected_result.id) >> Optional.of(expected_result)
        when: "actual result is retrieved"
            actual_result = controller.deleteEntry(expected_result.id)
        then: "actual result is compared to expected result"
            verifyAll(actual_result) {
                statusCode == HttpStatus.OK
                body == "Entry with id: ${expected_result.id} has been deleted"
            }
    }

}
