package de.kkottke.cooperative.modell;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
@Data
@RegisterForReflection
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Position position;
}
