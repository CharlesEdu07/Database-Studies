package br.com.charlesedu.atividadedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.charlesedu.atividadedb.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    
}
