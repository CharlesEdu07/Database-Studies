package br.com.charlesedu.atividadedb.model;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projeto")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @ManyToOne
    @JoinColumn(name = "responsavel", referencedColumnName = "codigo")
    private Employee responsible;

    @ManyToOne
    @JoinColumn(name = "depto", referencedColumnName = "codigo")
    private Department department;

    @Column(name = "data_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "data_fim")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
