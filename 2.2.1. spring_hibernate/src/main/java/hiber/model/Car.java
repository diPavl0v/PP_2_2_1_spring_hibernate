package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int series;

    public Car() {}
    public Car(String model, int series) { this.model = model; this.series = series; }

    public Long getId() { return id; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getSeries() { return series; }
    public void setSeries(int series) { this.series = series; }
}
