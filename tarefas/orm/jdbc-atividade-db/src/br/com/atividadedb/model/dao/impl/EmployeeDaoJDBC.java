package br.com.atividadedb.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.atividadedb.db.DB;
import br.com.atividadedb.db.DbException;
import br.com.atividadedb.model.dao.EmployeeDao;
import br.com.atividadedb.model.entities.Employee;

public class EmployeeDaoJDBC implements EmployeeDao {
    private Connection conn;

    public EmployeeDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Employee employee) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO employee (nome, sexo, dt_nasc, salario, supervisor, depto) VALUES (?, ?, ?, ?, ?, ?)");

            st.setString(1, employee.getName());
            st.setString(2, employee.getGender().toString());
            st.setDate(3, new Date(employee.getBirthDate().getTime()));
            st.setBigDecimal(4, employee.getSalary());
            st.setLong(5, employee.getSupervisor().getId());
            st.setLong(6, employee.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()) {
                    long id = rs.getLong(1);

                    employee.setId(id);
                }

                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
