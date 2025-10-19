package com.example.crudapp.Service;

import com.example.crudapp.dto.EmployeeDTO;

import java.util.ArrayList;

public interface EmployeeService {
    boolean saveEmployee(EmployeeDTO employeeDTO);
    boolean deleteEmployee(int id);
    EmployeeDTO searchEmployee(int id);
    boolean update(EmployeeDTO employeeDTO);
    ArrayList<EmployeeDTO> getAllEmployee();
}
