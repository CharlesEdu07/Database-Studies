package br.com.atividadedb.model.dao;

import br.com.atividadedb.model.entities.Activity;
import br.com.atividadedb.model.entities.Employee;
import br.com.atividadedb.model.entities.Project;

public interface ProjectDao {
    void insert(Project project);

    void addActivity(Activity activity);

    void updateResponsible(Employee employee);

    void findAllWithActivities();
}
