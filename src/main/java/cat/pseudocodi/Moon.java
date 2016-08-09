package cat.pseudocodi;

/**
 * @author fede
 */
public class Moon {

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
}
