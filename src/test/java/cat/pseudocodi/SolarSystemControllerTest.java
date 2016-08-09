package cat.pseudocodi;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

/**
 * @author fede
 */
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SolarSystemControllerTest {

    @Test
    public void whenNoMoons_thenReturnEmptyList() throws Exception {
        List<Moon> moons = get("/moons").as(List.class);
        assertTrue(moons.isEmpty());
    }

    @Test
    public void whenExistingMoons_thenReturnThemOnList() throws Exception {
        Moon europa = new Moon("Europa", "Jupiter");
        Moon titan = new Moon("Titan", "Saturn");
        given().contentType(ContentType.JSON).body(europa).when().post("/moons");
        given().contentType(ContentType.JSON).body(titan).when().post("/moons");
        JsonPath jsonPath = get("/moons").jsonPath();
        List<Moon> moons = jsonPath.getList(".", Moon.class);
        assertEquals(2, moons.size());
        assertTrue(moons.contains(europa));
        assertTrue(moons.contains(titan));
    }

    @Test
    public void whenPostingMoon_thenReturn201() throws Exception {
        Response post = given().contentType("application/json").body(new Moon("Calisto", "Jupiter")).when().post("/moons");
        post.then().assertThat().statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void whenPostingMoon_thenReturnLocation() throws Exception {
        Response post = given().contentType(ContentType.JSON).body(new Moon("Calisto", "Jupiter")).when().post("/moons");
        assertTrue(post.header("Location").endsWith("/moons/calisto"));
    }

    @Test
    public void whenPostingMoon_thenSubsequentGetShouldReturn200() throws Exception {
        given().contentType(ContentType.JSON).body(new Moon("adrastea", "Jupiter")).when().post("/moons");
        get("/moons/adrastea").then().assertThat().statusCode(HttpStatus.OK.value());

    }

    @Test
    public void whenNotExistingMoon_thenReturn404() throws Exception {
        get("/moons/ganymede").then().assertThat().statusCode(HttpStatus.NOT_FOUND.value());
    }
}
