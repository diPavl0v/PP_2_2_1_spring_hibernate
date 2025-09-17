package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(
        name = "cars",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_car_model_series",
                columnNames = {"model","series"}
        )
)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int series;

    protected Car() {}                 // для JPA

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Long getId() { return id; }
    public String getModel() { return model; }
    public int getSeries() { return series; }

    // без публичных сеттеров, чтобы не ломать hashCode в коллекциях
    void setModel(String model) { this.model = model; }
    void setSeries(int series) { this.series = series; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (org.hibernate.Hibernate.getClass(this) != org.hibernate.Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return series == car.series && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, series);
    }
}
