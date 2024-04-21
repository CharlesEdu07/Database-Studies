package br.com.atividadedb.model.dao;

import java.util.List;
import java.util.Map;

import br.com.atividadedb.model.entities.Activity;
import br.com.atividadedb.model.entities.Employee;
import br.com.atividadedb.model.entities.Project;

public interface ProjectDao {
    void insert(Project project);

    Project findById(Long id);

    void updateResponsible(Project project, Employee employee);

    Map<Project, List<Activity>> findAllWithActivities();
}
