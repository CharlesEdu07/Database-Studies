package br.com.charlesedu.atividadedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.charlesedu.atividadedb.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
