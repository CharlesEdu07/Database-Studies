package br.com.atividadedb.model.dao;

import br.com.atividadedb.model.entities.Employee;
import br.com.atividadedb.model.entities.Project;

public interface ProjectDao {
    void insert(Project project);

    Project findById(Long id);

    void updateResponsible(Project project, Employee employee);

    void findAllWithActivities();
}
