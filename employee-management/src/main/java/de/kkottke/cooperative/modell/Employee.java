package de.kkottke.cooperative.modell;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
@RegisterForReflection
public class Employee extends PanacheEntityBase {

    @Id
    @GeneratedValue
    public long id;

    @NotNull
    public String firstName;

    @NotNull
    public String lastName;

    @NotNull
    public Position position;
}
