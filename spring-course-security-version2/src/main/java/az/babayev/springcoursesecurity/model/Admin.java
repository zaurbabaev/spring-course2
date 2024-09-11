package az.babayev.springcoursesecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @NotEmpty(message = "Should not be empty")
    @Size(min = 2,max = 30, message = "Should be min 2 max 30 characters")
    private String name;
    @NotEmpty(message = "Should not be empty")
    @Size(min=2, max = 50, message = "Should be min 4 max 50 characters")
    private String username;
    @NotEmpty(message = "Should not be empty")
    private String password;

}
