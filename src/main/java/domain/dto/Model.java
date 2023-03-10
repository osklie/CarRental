package domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.CarType;
import enums.FuelType;
import enums.GearBox;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private long modelId;

    @ManyToOne
    @JoinColumn(name = "mark")
    private Mark mark;

    @NotNull
    private String name;

    @NotNull
    private double engineSize;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @NotNull
    private int productionYear;

    @NotNull
    private String color;

    @NotNull
    private int seatsQuantity;

    @NotNull
    private int doorQuantity;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GearBox gearBox;

    @JsonIgnore
    @OneToMany(
            targetEntity = Car.class,
            mappedBy = "model",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Car> cars;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (Double.compare(model.engineSize, engineSize) != 0) return false;
        if (productionYear != model.productionYear) return false;
        if (seatsQuantity != model.seatsQuantity) return false;
        if (doorQuantity != model.doorQuantity) return false;
        if (!mark.equals(model.mark)) return false;
        if (!name.equals(model.name)) return false;
        if (carType != model.carType) return false;
        if (!color.equals(model.color)) return false;
        if (fuelType != model.fuelType) return false;
        return gearBox == model.gearBox;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = mark.hashCode();
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(engineSize);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + carType.hashCode();
        result = 31 * result + productionYear;
        result = 31 * result + color.hashCode();
        result = 31 * result + seatsQuantity;
        result = 31 * result + doorQuantity;
        result = 31 * result + fuelType.hashCode();
        result = 31 * result + gearBox.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return mark + " " + name;
    }


}