package de.kkottke.cooperative;

import de.kkottke.cooperative.api.EmployeesApi;
import de.kkottke.cooperative.api.model.EmployeeDto;
import de.kkottke.cooperative.modell.Employee;
import de.kkottke.cooperative.modell.Position;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class EmployeeResource implements EmployeesApi {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Inject
    public EmployeeResource(final EmployeeService employeeService, final EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> hello() {
        return CompletableFuture.supplyAsync(() -> "hello");
    }

    @Override
    public Response listEmployees(String position, SecurityContext securityContext) {
        return listEmployees(position);
    }

    private Response listEmployees(String position) {
        List<Employee> employees;
        if (StringUtils.isBlank(position)) {
            employees = employeeService.listEmployees();
        } else {
            employees = employeeService.findByPosition(Position.valueOf(position));
        }

        return Response.ok(employeeMapper.map(employees)).build();
    }

    @Override
    public Response getEmployee(Long id, SecurityContext securityContext) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return Response.ok(employee.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response addEmployee(EmployeeDto employeeDto, SecurityContext securityContext) {
        Employee employee = employeeMapper.map(employeeDto);
        employeeService.addEmployee(employee);
        URI uri = UriBuilder.fromResource(EmployeesApi.class)
                            .path(EmployeesApi.class, "getEmployee")
                            .build(employee.id);

        return Response.created(uri).build();
    }

    @Override
    public Response deleteEmployee(Long id, SecurityContext securityContext) {
        employeeService.deleteEmployee(id);

        return Response.noContent().build();
    }
}