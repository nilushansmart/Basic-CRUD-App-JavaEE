package com.example.crudapp.Service.impl;

import com.example.crudapp.DB.DbConnection;
import com.example.crudapp.Service.EmployeeService;
import com.example.crudapp.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;


public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) {
        try {
            Connection conn = DbConnection.getDbcon().getConnection();

            PreparedStatement statement = conn.prepareStatement("insert into employee (id_no, name, address, position) values (?, ?, ?, ?)");

            statement.setObject(1,employeeDTO.getIdNo());
            statement.setObject(2,employeeDTO.getName());
            statement.setObject(3,employeeDTO.getAddress());
            statement.setObject(4,employeeDTO.getPosition());

            //excute query
            int status = statement.executeUpdate();

            if (status>0){
                return true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        try {
            Connection conn = DbConnection.getDbcon().getConnection();

            PreparedStatement statement = conn.prepareStatement("Delete from employee where id_no = ? ");
            statement.setObject(1, id);

            int status = statement.executeUpdate();

            if (status>0){
                return true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public EmployeeDTO searchEmployee(int id) {
        try {
            Connection conn = DbConnection.getDbcon().getConnection();

            PreparedStatement statement = conn.prepareStatement("select * from employee where id_no = ?");

            statement.setObject(1, id);

            //Excute the queary
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return new EmployeeDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) {
        try {
            Connection conn = DbConnection.getDbcon().getConnection();
            PreparedStatement statement = conn.prepareStatement("update employee set name = ?, address = ?, position = ? where id_no = ? ");

            statement.setObject(1, employeeDTO.getName());
            statement.setObject(2,employeeDTO.getAddress());
            statement.setObject(3,employeeDTO.getPosition());
            statement.setObject(4, employeeDTO.getIdNo());

            int status = statement.executeUpdate();

            if (status>0){
                return true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() {
        ArrayList<EmployeeDTO> employee = new ArrayList<>();
        try {
            Connection conn = DbConnection.getDbcon().getConnection();

            PreparedStatement stm = conn.prepareStatement("select * from employee");

            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()){
                employee.add(new EmployeeDTO(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }
}
