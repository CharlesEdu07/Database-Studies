package br.com.atividadedb.model.dao;

import br.com.atividadedb.model.entities.Activity;
import br.com.atividadedb.model.entities.Employee;

public interface ProjectDao {
    void addActivity(Activity activity);

    void updateResponsible(Employee employee);

    void findAllWithActivities();
}
