package domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.CarType;
import enums.FuelType;
import enums.GearBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelDto {

    private long modelId;
    private Mark mark;
    private String name;
    private double engineSize;
    private CarType carType;
    private int productionYear;
    private String color;
    private int seatsQuantity;
    private int doorQuantity;
    private FuelType fuelType;
    private GearBox gearbox;

    @JsonIgnore
    private Set<Car> cars;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelDto modelDto = (ModelDto) o;

        if (Double.compare(modelDto.engineSize, engineSize) != 0) return false;
        if (productionYear != modelDto.productionYear) return false;
        if (seatsQuantity != modelDto.seatsQuantity) return false;
        if (doorQuantity != modelDto.doorQuantity) return false;
        if (!mark.equals(modelDto.mark)) return false;
        if (!name.equals(modelDto.name)) return false;
        if (carType != modelDto.carType) return false;
        if (!color.equals(modelDto.color)) return false;
        if (fuelType != modelDto.fuelType) return false;
        return gearbox == modelDto.gearbox;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = 31 * name.hashCode();
        temp = Double.doubleToLongBits(engineSize);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + carType.hashCode();
        result = 31 * result + productionYear;
        result = 31 * result + color.hashCode();
        result = 31 * result + seatsQuantity;
        result = 31 * result + doorQuantity;
        result = 31 * result + fuelType.hashCode();
        result = 31 * result + gearbox.hashCode();
        return result;
    }
}