package cat.pseudocodi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
class Config {

    @Bean
    LunarAspect lunarAspect() {
        return new LunarAspect();
    }
}
