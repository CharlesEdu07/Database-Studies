package br.com.atividadedb.model.dao;

import br.com.atividadedb.model.entities.Department;

public interface DepartmentDao {
    void insert(Department department);

    Department findById(Long id);
}
