package br.com.atividadedb.model.dao.impl;

import java.math.BigDecimal;
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
                    "INSERT INTO funcionario (nome, sexo, dt_nasc, salario, supervisor, depto) VALUES (?, ?, ?, ?, ?, ?)");

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

    @Override
    public Employee findById(Long id) {
        PreparedStatement st = null;

        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM funcionario WHERE codigo = ?");

            st.setLong(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Employee dep = instantiateEmployee(rs);

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

    private Employee instantiateEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();

        emp.setId(rs.getLong("codigo"));
        emp.setName(rs.getString("nome"));
        emp.setGender(rs.getString("sexo").charAt(0));
        emp.setBirthDate(rs.getDate("dt_nasc"));

        String salaryString = rs.getString("salario").replaceAll("[^0-9.]", "");

        BigDecimal salary = new BigDecimal(salaryString);

        emp.setSalary(salary);

        return emp;
    }
}
