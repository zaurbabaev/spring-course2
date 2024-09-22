package az.babayev.springrest.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Schema(description = "Student Data Transfer Object")
public class StudentDTO {

    @Schema(description = "Student unique identified", example = "1")
    Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, message = "Name should be min 2 character")
    @Schema(description = "Student firstname", example = "Tonny")
    String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, message = "Surname should be min 2 character")
    @Schema(description = "Student lastname", example = "Stark")
    String surname;


    @Pattern(regexp = "\\+994(50|51|55|70|77)\\d{7}", message = "Phone number is not in correct number")
    @NotEmpty(message = "Phone number should not be empty")
    @Schema(description = "Student phone number", example = "+994702262052")
    String phoneNumber;

    @Email(message = "Please enter correct email: example@xyz.xy")
    @NotEmpty(message = "Email should not be empty")
    @Schema(description = "Student email", example = "example.xyz.xy")
    String email;

    @DateTimeFormat(pattern = "yyyy-dd-MM")
    @Past(message = "Date of birth must be in past tense")
    @Schema(description = "Student birthday", example = "1990-03-01")
    LocalDate birthday;
}
