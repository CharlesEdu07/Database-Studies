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
    public Project findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM projeto WHERE codigo = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Project project = instantiateProject(rs);

                return project;
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Project instantiateProject(ResultSet rs) throws SQLException {
        Project project = new Project();

        project.setId(rs.getLong("codigo"));
        project.setName(rs.getString("nome"));
        project.setDescription(rs.getString("descricao"));
        project.setStartDate(rs.getDate("data_inicio"));
        project.setEndDate(rs.getDate("data_fim"));

        return project;
    }

    @Override
    public void updateResponsible(Project project, Employee employee) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE projeto SET responsavel = ? WHERE codigo = ?");

            st.setLong(1, employee.getId());
            st.setLong(2, project.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void findAllWithActivities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllWithActivities'");
    }
}
