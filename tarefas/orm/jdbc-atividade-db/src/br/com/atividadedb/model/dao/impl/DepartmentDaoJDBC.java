package br.com.atividadedb.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.atividadedb.db.DB;
import br.com.atividadedb.db.DbException;
import br.com.atividadedb.model.dao.DepartmentDao;
import br.com.atividadedb.model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO department (nome, sigla, descricao, gerente) VALUES (?, ?, ?, ?) RETURNING codigo");

            st.setString(1, department.getName());
            st.setString(2, department.getAcronym());
            st.setString(3, department.getDescription());
            st.setLong(4, department.getManager().getId());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                long id = rs.getLong(1);

                department.setId(id);
            }

            DB.closeResultSet(rs);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Long id) {
        PreparedStatement st = null;

        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM departamento WHERE codigo = ?");

            st.setLong(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = instantiateDepartment(rs);

                return dep;
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();

        dep.setId(rs.getLong("codigo"));
        dep.setName(rs.getString("nome"));
        dep.setAcronym(rs.getString("sigla"));
        dep.setDescription(rs.getString("descricao"));

        return dep;
    }
}
