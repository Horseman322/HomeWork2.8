package service;

import com.example.homework28.exception.EmployeeNotFoundException;
import com.example.homework28.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


    public Employee findEmployeeWithMaxSalaryFromDepartment(int department){
       return employeeService.getAll().stream()
               .filter(employee -> employee.getDepartment()==department)
               .max(Comparator.comparingDouble(Employee::setSalary))
               .orElseThrow(() -> new EmployeeNotFoundException());
    }

    public Employee findEmployeeWithMinSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .min(Comparator.comparingDouble(Employee::setSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    public List<Employee> findEmployeesFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> findEmployees() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
