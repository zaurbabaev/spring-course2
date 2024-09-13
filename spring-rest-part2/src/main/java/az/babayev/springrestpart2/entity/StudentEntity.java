package az.babayev.springrestpart2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "students")
@Entity
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, message = "Name should be min 2 character")
    String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, message = "Surname should be min 2 character")
    String surname;


    @Pattern(regexp = "\\+994(50|51|55|70|77)\\d{7}", message = "Phone number is not in correct number")
    @NotEmpty(message = "Phone number should not be empty")
    String phoneNumber;

    @Email(message = "Please enter correct email: example@xyz.xy")
    @NotEmpty(message = "Email should not be empty")
    String email;

    @DateTimeFormat(pattern = "yyyy-dd-MM")
    @Past(message = "Date of birth must be in past tense")
    LocalDate birthday;


}
