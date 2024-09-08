package az.babayev.springmvcjdbctemplate.model;


import az.babayev.springmvcjdbctemplate.annotation.MyAnnotation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Name should not be empty")
    private String surname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Pattern(regexp = "\\+994\\d{9}", message = "Phone should be +994000000000")
    private String phone;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 50, message = "Address should be min 3 max 50 characters")
    private String address;
    @Email
    @MyAnnotation(value = "xyz",message = "Should be start with xyz")
    private String email;
    private String sector;
}
