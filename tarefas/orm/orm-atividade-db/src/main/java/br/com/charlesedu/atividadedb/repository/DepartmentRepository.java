package br.com.charlesedu.atividadedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.charlesedu.atividadedb.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
}
