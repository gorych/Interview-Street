package by.gstu.interviewstreet.service;

import by.gstu.interviewstreet.domain.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployeesBySubdivision(int subdivisionId);

}
