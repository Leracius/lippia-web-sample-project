package lippia.web.steps;
import com.crowdar.core.PageSteps;
import io.cucumber.java.en.*;
import lippia.web.services.SwagLabsServices;

public class LoginSteps extends PageSteps {

    @Given("El usuario está en la página de login")
    public void ingresarUsuario(){
        SwagLabsServices.navegarWeb();
    }

    @When("Ingresa el usuario correcto {string} y la contraseña {string}")
    public void ingresoCredencialesCorrectas(String username, String password){
        SwagLabsServices.swagLogin(username, password);
    }

    @Then("Debería visualizar el título {string}")
    public void validResult(String expectedTitle){
        SwagLabsServices.validExpected(expectedTitle, "css:.title");

    }

    @When("Ingresa el usuario fallido {string} y la contraseña {string}")
    public void ingresoCredencialesFallidas(String username, String password){
        SwagLabsServices.swagLogin(username, password);
    }

    @Then("Deberia aparecer un mensaje de error que dice: {string}")
    public void validMessage(String errorMessage){
        SwagLabsServices.validExpected(errorMessage, "css:.error-message-container");
    }


}
