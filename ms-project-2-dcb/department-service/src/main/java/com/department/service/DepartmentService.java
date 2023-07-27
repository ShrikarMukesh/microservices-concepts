package com.department.service;

import com.department.entity.Department;
import com.department.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		System.out.println("Inside saveDepartment of DepartmentService");
		return departmentRepository.save(department);
	}

	public Department findDepartmentById(Long departmentId) {
		System.out.println("Inside saveDepartment of DepartmentService");
		return departmentRepository.findByDepartmentId(departmentId);
	}
}
