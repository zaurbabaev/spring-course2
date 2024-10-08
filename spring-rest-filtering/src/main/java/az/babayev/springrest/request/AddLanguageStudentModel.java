package az.babayev.springrest.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddLanguageStudentModel {
    Long studentId;
    List<Long> languageIds;
}
