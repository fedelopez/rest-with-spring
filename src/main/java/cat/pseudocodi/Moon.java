package cat.pseudocodi;

public class Moon {

    private int id;
    private String name;
    private String planet;

    public Moon() {
    }

    public Moon(String name, String planet) {
        this.name = name;
        this.planet = planet;
    }

    public String getName() {
        return name;
    }

    public String getPlanet() {
        return planet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moon moon = (Moon) o;

        if (!name.equals(moon.name)) return false;
        return planet.equals(moon.planet);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + planet.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Moon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                '}';
    }
}
