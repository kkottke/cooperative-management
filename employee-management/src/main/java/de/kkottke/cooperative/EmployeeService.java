package de.kkottke.cooperative;

import de.kkottke.cooperative.modell.Employee;
import de.kkottke.cooperative.modell.Position;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeeService {

    private final EmployeeRepository repository;

    @Inject
    public EmployeeService(final EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> listEmployees() {
        return repository.listAll();
    }

    public Optional<Employee> findById(long id) {
        return Optional.ofNullable(repository.findById(id));
    }

    public List<Employee> findByPosition(Position position) {
        return repository.findByPosition(position);
    }

    @Transactional
    public void addEmployee(final Employee employee) {
        repository.persist(employee);
    }

    @Transactional
    public void deleteEmployee(long id) {
        repository.delete("id", id);
    }
}
