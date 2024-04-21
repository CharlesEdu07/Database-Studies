package br.com.charlesedu.atividadedb.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.charlesedu.atividadedb.model.Activity;
import br.com.charlesedu.atividadedb.model.Project;
import br.com.charlesedu.atividadedb.repository.ActivityRepository;
import br.com.charlesedu.atividadedb.repository.DepartmentRepository;
import br.com.charlesedu.atividadedb.repository.EmployeeRepository;
import br.com.charlesedu.atividadedb.repository.ProjectRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;
    ProjectRepository projectRepository;
    ActivityRepository activityRepository;

    public TestConfig(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
            ProjectRepository projectRepository, ActivityRepository activityRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n==== Adicionar atividade a um projeto ====");

        Activity activity = new Activity(null, "Atividade 1", projectRepository.findById(1L).get(),
                LocalDate.parse("2021-01-01"), LocalDate.parse("2021-01-31"));

        activityRepository.save(activity);

        System.out.println("\nAtividade adicionada: " + activity);

        System.out.println("\n==== Atualizar responsável do projeto ====");

        Project project = projectRepository.findById(2L).get();
        
        project.setResponsible(employeeRepository.findById(2L).get());

        projectRepository.save(project);

        System.out.println("\nResponsável atualizado: " + projectRepository.findById(1L).get());

        System.out.println("\n==== Listar todos os projetos e suas respectivas atividades ====");

        projectRepository.findAll().forEach(System.out::println);
    }
}
