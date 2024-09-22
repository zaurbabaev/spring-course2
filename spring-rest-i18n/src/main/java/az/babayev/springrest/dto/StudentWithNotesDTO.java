package az.babayev.springrest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Student with notes Data Transfer Object")
public class StudentWithNotesDTO {

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

    @NotEmpty(message = "Note should not be empty")
    @Size(min = 10, message = "Surname should be min 2 character")
    @Schema(description = "Student notes", example = "This student is good")
    String note;

}
