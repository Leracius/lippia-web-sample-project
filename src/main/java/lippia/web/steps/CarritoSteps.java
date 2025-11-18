package lippia.web.steps;
import com.crowdar.core.PageSteps;
import io.cucumber.java.en.*;
import lippia.web.services.SwagLabsServices;
import org.testng.Assert;

import static com.crowdar.core.actions.ActionManager.*;

public class CarritoSteps extends PageSteps{

    @Given("El usuario está en la página de ingreso")
    public void primerIngreso(){
        SwagLabsServices.navegarWeb();
    }

    @When("El usuario inicia sesión con credenciales {string}, {string}")
    public void ingresoCredenciales(String username, String password){
        SwagLabsServices.swagLogin(username, password);
    }

    @And("El usuario agrega al carrito el producto {string}")
    public void agregarAlCarrito(String product){
        SwagLabsServices.agregarProducto(product);
    }

    @Then("El contador del carrito debería mostrar {string}")
    public void validarCantidadCarrito(String cantidad){
        SwagLabsServices.validExpected(cantidad, "css:.shopping_cart_badge");

    }

    @When("El usuario remueve del carrito el producto {string}")
    public void removerProducto(String producto){
        SwagLabsServices.removerProducto(producto);
    }

    @Then("El contador del carrito debería estar vacío")
    public void validarCarritoVacio(){
        boolean exists;
        try {
            exists = isVisible("css:.shopping_cart_badge");
        } catch (Exception e) {
            exists = false;
        };
        Assert.assertFalse(
                exists,
                "Se esperaba que el carrito esté vacío, pero el badge del carrito sigue presente."

        );
    }

}
