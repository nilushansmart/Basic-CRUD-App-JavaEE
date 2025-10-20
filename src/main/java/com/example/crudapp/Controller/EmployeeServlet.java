package com.example.crudapp.Controller;

import com.example.crudapp.Service.EmployeeService;
import com.example.crudapp.Service.impl.EmployeeServiceImpl;
import com.example.crudapp.dto.EmployeeDTO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();

        EmployeeService service = new EmployeeServiceImpl();
        ArrayList<EmployeeDTO> employeeDTOS = service.getAllEmployee();

        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(employeeDTOS));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();

        EmployeeDTO employeeDTO = gson.fromJson(new BufferedReader(new InputStreamReader(req.getInputStream())), EmployeeDTO.class);

        EmployeeService service =  new EmployeeServiceImpl();
        boolean status = service.saveEmployee(employeeDTO);

        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(status));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();

        EmployeeDTO employeeDTO = gson.fromJson(new BufferedReader(new InputStreamReader(req.getInputStream())), EmployeeDTO.class);

        EmployeeService service =  new EmployeeServiceImpl();
        boolean status = service.update(employeeDTO);

        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(status));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();

//        int idNo =  Integer.parseInt(req.getParameter("idNo"));

        String id = String.valueOf(req.getReader());

        System.out.println("delete employee idNo:"+Integer.parseInt(id));

        EmployeeService service =  new EmployeeServiceImpl();
        boolean status = service.deleteEmployee(Integer.parseInt(id));

        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(status));
    }


}
