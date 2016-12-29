package cat.pseudocodi;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
class SolarSystemStore {

    private final List<Moon> moons = new ArrayList<>();

    @Pointcut("execution(* cat.pseudocodi.SolarSystemStore.addMoon(..)) && args(moon)")
    void addMoon(Moon moon) {
        moons.add(moon);
    }

    Optional<Moon> getMoon(String name) {
        return moons.stream().filter(moon -> moon.getName().equalsIgnoreCase(name)).findFirst();
    }

    List<Moon> getMoons() {
        return Collections.unmodifiableList(moons);
    }
}
