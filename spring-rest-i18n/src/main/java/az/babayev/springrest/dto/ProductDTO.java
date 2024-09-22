package az.babayev.springrest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Product Data Transfer Object")
public class ProductDTO {

    @Schema(description = "Product unique identified", example = "1")
    Long id;

    @Size(min = 3, max = 30, message = "Name should be min 3 max 30 characters")
    @NotEmpty(message = "Name should be not empty")
    @Schema(description = "Product name", example = "Tomato")
    String name;


    @NotNull(message = "Price should not be empty")
    @Min(value = 0, message = "min value of price is 0")
    @Schema(description = "Product price", example = "2.5")
    BigDecimal price;

    @NotNull(message = "Quantity should not be empty")
    @Min(value = 1, message = "The minimum quantity is 1")
    @Schema(description = "Product quantity", example = "3")
    Integer quantity;

    @Schema(description = "About product", example = "This product is delicious")
    String description;
}
