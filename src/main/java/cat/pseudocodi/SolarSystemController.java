package cat.pseudocodi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @author fede
 */
@Controller
class SolarSystemController {

    @Autowired
    private SolarSystemStore store;

    @RequestMapping("/moons")
    @ResponseBody
    List<Moon> getMoons() {
        return store.getMoons();
    }

    @RequestMapping("/moons/{name}")
    @ResponseBody
    ResponseEntity<Moon> moonByName(@PathVariable String name) {
        Optional<Moon> moon = store.getMoon(name);
        return moon.map(m -> new ResponseEntity<>(m, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(path = "/moons", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<?> addMoon(@RequestBody Moon moon) {
        store.addMoon(moon);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(getLocationForCreated(moon));
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    private URI getLocationForCreated(Moon moon) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(moon.getName().toLowerCase()).toUri();
    }
}
