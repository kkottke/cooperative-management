package de.kkottke.cooperative;

import de.kkottke.cooperative.modell.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class EmployeeService {

    private final EntityManager em;

    @Inject
    public EmployeeService(final EntityManager em) {
        this.em = em;
    }

    public List<Employee> listEmployees() {
        CriteriaQuery<Employee> query = em.getCriteriaBuilder().createQuery(Employee.class);
        Root<Employee> rootEntry = query.from(Employee.class);
        CriteriaQuery<Employee> allQuery = query.select(rootEntry);

        return em.createQuery(allQuery).getResultList();
    }

    @Transactional
    public void addEmployee(final Employee employee) {
        em.persist(employee);
    }
}
