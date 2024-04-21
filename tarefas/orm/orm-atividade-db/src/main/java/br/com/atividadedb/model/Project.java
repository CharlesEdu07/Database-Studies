package br.com.atividadedb.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Project implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Employee responsible;
    private Department department;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Activity> activities;

    public Project() {
    }

    public Project(Long id, String name, String description, Employee responsible, Department department,
            LocalDate startDate,
            LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.responsible = responsible;
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getResponsible() {
        return responsible;
    }

    public void setResponsible(Employee responsible) {
        this.responsible = responsible;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getstartDate() {
        return startDate;
    }

    public void setstartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getendDate() {
        return endDate;
    }

    public void setendDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", name=" + name + ", description=" + description + ", responsible=" + responsible
                + ", department=" + department + ", startDate=" + startDate + ", endDate=" + endDate + ", activities="
                + activities + "]";
    }
}
