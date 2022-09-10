package service;

import com.example.homework28.exception.EmployeeAlreadyAddedException;
import com.example.homework28.exception.EmployeeNotFoundException;
import com.example.homework28.exception.EmployeeStorageIsFullException;
import com.example.homework28.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService{

    private static final int LIMIT = 10;
    private final Map<String, Employee> employees = new HashMap<>();
    private String key;

    private String getKey(String name, String secondName){return name + "|" + secondName;}

    public Employee addEmployee(String name,
                                String secondName
                                int department,
                                double salary) {
        Employee employee = new Employee(name, secondName, int department, double salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(key, employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee removeEmployee(String name,
                                   String secondName) {
        Employee employee = new Employee(name, secondName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String name,
                                 String secondName) {
        Employee employee = new Employee(name, secondName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }
    public List<Employee> getAll(){
        return Collections.unmodifiableList(employees);
    }


}