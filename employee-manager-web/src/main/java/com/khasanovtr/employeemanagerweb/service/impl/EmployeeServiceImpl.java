package com.khasanovtr.employeemanagerweb.service.impl;

import com.khasanovtr.employeemanagerweb.exceptions.EmployeeAlreadyAddedException;
import com.khasanovtr.employeemanagerweb.exceptions.EmployeeNotFoundException;
import com.khasanovtr.employeemanagerweb.exceptions.EmployeeStorageIsFullException;
import com.khasanovtr.employeemanagerweb.model.Employee;
import com.khasanovtr.employeemanagerweb.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int MAX_AMOUNT = 5;
    private List<Employee> employees = new ArrayList<>();
    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() > MAX_AMOUNT) {
            throw new EmployeeStorageIsFullException("Вы достигли максимального количества сотрудников в списке");
        }
        Employee emp = new Employee(firstName, lastName);
        if (employees.contains(emp)) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем и фамилией" + firstName + " " + lastName +
                    " уже существует");
        }

        employees.add(emp);
        return emp;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);
        if (!employees.contains(emp)) {
            throw new EmployeeNotFoundException("Сотрудник с именем и фамилией" + firstName + " " + lastName +
                    " не существует");
        }
        return emp;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);
        if (!employees.contains(emp)) {
            throw new EmployeeNotFoundException("Сотрудник с именем и фамилией" + firstName + " " + lastName +
                    " не существует");
        }
        employees.remove(emp);
        return emp;
    }

    @Override
    public Collection<Employee> findAll() {

        return employees;
    }
}
