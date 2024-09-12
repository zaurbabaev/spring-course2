package az.babayev.entity;

import az.babayev.enums.Sector;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotEmpty(message = "Should not be empty")
    @Size(min = 2, message = "Name should be min 2 max 30 characters")
    String name;

    @NotEmpty(message = "Should not be empty")
    @Size(min = 2, message = "Name should be min 2 character")
    String surname;


    @Pattern(regexp = "\\+994(50|51|55|70|77)\\d{7}", message = "Phone number is not in correct format")
    @NotEmpty(message = "Should not be empty")
    String phone;

    String address;

    @NotEmpty(message = "Should not be empty")
    @Email(message = "Please enter email correct: example@xyz.xy")
    String email;

    @Past
    @DateTimeFormat(pattern = "yyyy-dd-MM")
    LocalDate birthday;

    @Enumerated(EnumType.STRING)
    Sector sector;

    @Transient
    int counter;

}
