package de.kkottke.cooperative;

import de.kkottke.cooperative.api.model.EmployeeDto;
import de.kkottke.cooperative.modell.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface  EmployeeMapper {

    Employee map(EmployeeDto dto);

    List<EmployeeDto> map(List<Employee> employees);
}
