package az.babayev.springrest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Translate Data Transfer Object")
public class TranslateDTO {

    @Schema(description = "Translate unique identified", example = "1")
    Long id;

    @NotEmpty(message = "Keyword should not be empty")
    @Size(min = 5, message = "Surname should be min 5 character")
    @Schema(description = "Keyword", example = "about")
    String keyword;

    @NotEmpty(message = "Language should not be empty")
    @Size(min = 2, message = "Language should be min 2 character")
    @Schema(description = "Language", example = "az")
    String language;

    @NotEmpty(message = "Translate should not be empty")
    @Size(min = 3, message = "Translate should be min 3 character")
    @Schema(description = "Translate", example = "Haqqimizda")
    String translate;
}

