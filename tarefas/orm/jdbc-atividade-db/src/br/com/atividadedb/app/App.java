package br.com.atividadedb.app;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.atividadedb.model.dao.ActivityDao;
import br.com.atividadedb.model.dao.DepartmentDao;
import br.com.atividadedb.model.dao.EmployeeDao;
import br.com.atividadedb.model.dao.ProjectDao;
import br.com.atividadedb.model.dao.factory.DaoFactory;
import br.com.atividadedb.model.entities.Activity;
import br.com.atividadedb.model.entities.Department;
import br.com.atividadedb.model.entities.Employee;
import br.com.atividadedb.model.entities.Project;

public class App {
        public static void main(String[] args) throws Exception {
                Locale.setDefault(Locale.US);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
                DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
                ProjectDao projectDao = DaoFactory.createProjectDao();
                ActivityDao activityDao = DaoFactory.createActivityDao();

                // =========== Insert Employee ===========

                System.out.println("\n=== TEST 1: Insert Employee ===");

                Employee existingEmployee = employeeDao.findById(1L);

                Department existingDepartment = departmentDao.findById(1L);

                Employee employee1 = new Employee(null, "Carlos", 'M', new Date(sdf.parse("01/01/1990").getTime()),
                                new BigDecimal(3000.00), existingEmployee, existingDepartment);

                employeeDao.insert(employee1);

                System.out.println("Inserted employee! ID: " + employee1.getId());

                // =========== Insert Project ===========

                System.out.println("\n=== TEST 2: Insert Project ===");

                Project project1 = new Project(null, "Projeto de BD", "Description of the new project", employee1,
                                existingDepartment, new Date(sdf.parse("01/01/2021").getTime()),
                                new Date(sdf.parse("01/01/2022").getTime()));

                projectDao.insert(project1);

                System.out.println("\nInserted project! ID: " + project1.getId());

                // =========== Insert Activity ===========

                System.out.println("\n=== TEST 3: Insert an Activity ===");

                Project existingProject = projectDao.findById(1L);

                System.out.println("\nProject: " + existingProject);

                Activity activity1 = new Activity(null, "Atividade de teste", existingProject,
                                new Date(sdf.parse("01/01/2021").getTime()),
                                new Date(sdf.parse("01/01/2022").getTime()));

                activityDao.insert(activity1);

                System.out.println("\nInserted activity! ID: " + activity1.getId());

                // =========== Update Responsible ===========

                System.out.println("\n=== TEST 4: Update Responsible ===");

                Employee employee2 = employeeDao.findById(3L);

                projectDao.updateResponsible(project1, employee2);

                System.out.println("\nUpdated responsible!");

                // =========== Find All Projects and respective activities ===========

                System.out.println("\n=== TEST 5: Find All Projects and respective activities ===");

                for (Project project : projectDao.findAllWithActivities().keySet()) {
                        System.out.println("\n" + project);

                        for (Activity activity : projectDao.findAllWithActivities().get(project)) {
                                System.out.println(activity);
                        }
                }
        }
}
