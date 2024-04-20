package br.com.atividadedb.model.dao.impl;

import java.sql.Connection;

import br.com.atividadedb.model.dao.ProjectDao;
import br.com.atividadedb.model.entities.Activity;
import br.com.atividadedb.model.entities.Employee;

public class ProjectDaoJDBC implements ProjectDao {
    private Connection conn;

    public ProjectDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertActivity(Activity activity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertActivity'");
    }

    @Override
    public void updateResponsible(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateResponsible'");
    }

    @Override
    public void findAllWithActivities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllWithActivities'");
    }
}
