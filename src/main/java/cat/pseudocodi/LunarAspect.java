package cat.pseudocodi;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Random;

@Aspect
@Component
class LunarAspect {

    @Before("cat.pseudocodi.SolarSystemStore.addMoon(moon)")
    void assignId(Moon moon) {
        System.out.println("Assign random id to " + moon);
        moon.setId(new Random().nextInt());
    }

}
