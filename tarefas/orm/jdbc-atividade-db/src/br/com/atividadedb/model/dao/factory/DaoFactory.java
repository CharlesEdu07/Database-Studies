package br.com.atividadedb.model.dao.factory;

import br.com.atividadedb.db.DB;
import br.com.atividadedb.model.dao.ActivityDao;
import br.com.atividadedb.model.dao.DepartmentDao;
import br.com.atividadedb.model.dao.EmployeeDao;
import br.com.atividadedb.model.dao.ProjectDao;
import br.com.atividadedb.model.dao.impl.ActivityDaoJDBC;
import br.com.atividadedb.model.dao.impl.DepartmentDaoJDBC;
import br.com.atividadedb.model.dao.impl.EmployeeDaoJDBC;
import br.com.atividadedb.model.dao.impl.ProjectDaoJDBC;

public class DaoFactory {
    public static ProjectDao createProjectDao() {
        return new ProjectDaoJDBC(DB.getConnection());
    }

    public static EmployeeDao createEmployeeDao() {
        return new EmployeeDaoJDBC(DB.getConnection());
    }

    public static ActivityDao createActivityDao() {
        return new ActivityDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
