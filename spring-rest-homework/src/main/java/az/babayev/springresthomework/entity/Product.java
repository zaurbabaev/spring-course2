package az.babayev.springresthomework.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 20, message = "Name should be min 3 max 20 characters")
    String name;
    @Min(value = 0, message = "Min value of price is 0")
    @NotNull(message = "Price should not be null")
    BigDecimal price;
    String description;
    @NotNull(message = "Quantity should not be null")
    @Min(value = 1, message = "The minimum quantity is 1")
    Integer quantity;
}
