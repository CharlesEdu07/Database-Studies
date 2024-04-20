package br.com.atividadedb.model.dao;

import br.com.atividadedb.model.entities.Department;

public interface DepartmentDao {
    void insert(Department department);

    void findById(Long id);
}
