package com.itwill.transaction.spring;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = RuntimeException.class)
public interface EmployeeService {
	public abstract void registerEmployee(Employee emp);
	@Transactional(propagation = Propagation.MANDATORY)
	public abstract void deleteEmployee(int id);
	public abstract void udpateEmployee(Employee emp);
	public abstract List<Employee> getEmps();
	public abstract void increaseSalaryForAllUnCheckedException();
	@Transactional(rollbackFor = Exception.class)
	public abstract void increaseSalaryForAllCheckedException() 
			throws Exception;
}