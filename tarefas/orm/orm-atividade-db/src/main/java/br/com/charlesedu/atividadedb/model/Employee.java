package br.com.charlesedu.atividadedb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

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
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "sexo")
    private Character gender;

    @Column(name = "dt_nasc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(name = "salario")
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal salary;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "supervisor", referencedColumnName = "codigo")
    private Employee supervisor;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "depto", referencedColumnName = "codigo")
    private Department department;
}
