package de.kkottke.cooperative;

import de.kkottke.cooperative.modell.Employee;
import de.kkottke.cooperative.modell.Position;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    public List<Employee> findByPosition(final Position position) {
        return find("position", position).list();
    }
}
