package de.kkottke.cooperative;

import de.kkottke.cooperative.modell.Employee;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private final EmployeeService employeeService;

    @Inject
    public EmployeeResource(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    public Response listEmployees() {
        List<Employee> employees = employeeService.listEmployees();

        return Response.ok(employees).build();
    }

    @POST
    public Response addEmployee(final Employee employee) {
        employeeService.addEmployee(employee);
        URI uri = UriBuilder.fromResource(EmployeeResource.class).build(employee.getId());

        return Response.created(uri).build();
    }
}