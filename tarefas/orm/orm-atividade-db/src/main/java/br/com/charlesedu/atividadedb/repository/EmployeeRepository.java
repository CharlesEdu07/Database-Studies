package br.com.charlesedu.atividadedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.charlesedu.atividadedb.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
