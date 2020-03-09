package de.kkottke.cooperative;

import de.kkottke.cooperative.modell.Employee;
import de.kkottke.cooperative.modell.Position;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public Response listEmployees(@QueryParam("position") Position position) {
        List<Employee> employees;
        if (position == null) {
            employees = employeeService.listEmployees();
        } else {
            employees = employeeService.findByPosition(position);
        }

        return Response.ok(employees).build();
    }

    @GET
    @Path("{id}")
    public Response getEmployee(@PathParam("id") long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return Response.ok(employee.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response listEmployeesWithPosition(@QueryParam("position") Position position) {
        List<Employee> employees = employeeService.findByPosition(position);

        return Response.ok(employees).build();
    }

    @POST
    public Response addEmployee(final Employee employee) {
        employeeService.addEmployee(employee);
        URI uri = UriBuilder.fromResource(EmployeeResource.class)
                            .path(EmployeeResource.class, "getEmployee")
                            .build(employee.id);

        return Response.created(uri).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") long id) {
        employeeService.deleteEmployee(id);

        return Response.accepted().build();
    }
}