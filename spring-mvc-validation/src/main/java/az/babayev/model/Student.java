package az.babayev.model;

import az.babayev.enums.Sector;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    Integer id;
    @NotNull(message = "Mütləqdir")
    @NotEmpty(message = "boş qoymaq olmaz")
    @Size(min = 2, message = "Ad minimum 2 maksimum 30 simvol ola bilər")
    String name;

    @NotNull(message = "Mütləqdir")
    @NotEmpty(message = "boş qoymaq olmaz")
    @Size(min = 2, message = "Ad minimum 2 maksimum 30 simvol ola bilər")
    String surname;

    @Pattern(regexp = "\\+994(50|51|55|70|77)\\d{7}", message = "Telefon nömrəsi düzgün formatda deyil")
    @NotEmpty(message = "boş qoymaq olmaz")
    String phone;

    String address;

    @NotEmpty(message = "boş qoymaq olmaz")
    @Email(message = "Emaili düzgün daxil edin: example@xyz.xy")
    String email;

    @Past
    Date birthday;

    Sector sector;

    int counter;

}
