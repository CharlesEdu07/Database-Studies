package br.com.atividadedb.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.atividadedb.db.DB;
import br.com.atividadedb.db.DbException;
import br.com.atividadedb.model.dao.ProjectDao;
import br.com.atividadedb.model.entities.Project;
import br.com.atividadedb.model.entities.Activity;
import br.com.atividadedb.model.entities.Employee;

public class ProjectDaoJDBC implements ProjectDao {
    private Connection conn;

    public ProjectDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Project project) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO projeto (nome, descricao, responsavel, depto, data_inicio, data_fim) VALUES (?, ?, ?, ?, ?, ?) RETURNING codigo");

            st.setString(1, project.getName());
            st.setString(2, project.getDescription());
            st.setLong(3, project.getResponsible().getId());
            st.setLong(4, project.getDepartment().getId());
            st.setDate(5, new Date(project.getStartDate().getTime()));
            st.setDate(6, new Date(project.getEndDate().getTime()));

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Long id = rs.getLong(1);

                project.setId(id);
            }

            DB.closeResultSet(rs);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void addActivity(Activity activity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addActivity'");
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
