package lippia.web.steps;
import com.crowdar.core.PageSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import lippia.web.services.SwagLabsServices;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static com.crowdar.core.actions.ActionManager.*;
import static com.crowdar.core.actions.ActionManager.getText;

public class CompraSteps extends PageSteps{

    @Given("El usuario ingreso correctamente y se encuentra en la seccion de productos")
    public void inicioDeCompra(){
        SwagLabsServices.navegarWeb();
        SwagLabsServices.swagLogin("standard_user", "secret_sauce");
    }

    @When("El usuario agrega los siguientes productos al carrito:")
    public void agregarProductos(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String productName = row.get("productName");
            SwagLabsServices.agregarProducto(productName);
        }
    }

    @And("El usuario navega a la página del carrito")
    public void goToCart(){
        click("css:.shopping_cart_link");
    }

    @And("El usuario inicia el proceso de checkout")
    public void checkingCart(){
        click("id:checkout");
    }

    @And("El usuario completa el formulario de checkout con:")
    public void checkingInfo(DataTable table) {
        // La tabla tiene header + 1 fila => la convierto en lista de maps
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        Map<String, String> data = rows.get(0); // primera (y única) fila

        String nombre = data.get("firstName");
        String apellido = data.get("lastName");
        String postalCode = data.get("postalCode");

        setInput("id:first-name", nombre);
        setInput("id:last-name", apellido);
        setInput("id:postal-code", postalCode);
    }

    @And("El usuario continúa hacia la página de resumen")
    public void continueToOrder(){
        click("id:continue");
    }

    @Then("La página de resumen debería mostrar los siguientes productos y precios:")
    public void orderResult(DataTable table){

        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String expectedName = row.get("productName");
            String expectedPrice = row.get("price");

            String actualName = getText("xpath://div[text()='" + expectedName + "']");
            Assert.assertEquals(actualName, expectedName,
                    "El nombre del producto no coincide");

            String actualPrice = getText("xpath://div[text()='" + expectedName + "']/../..//div[@class='inventory_item_price']");
            Assert.assertEquals(actualPrice, expectedPrice,
                    "El precio del producto no coincide");
        }
    }

    @When("El usuario finaliza la compra")
    public void finalSteps(){
        click("name:finish");
    }

    @Then("El mensaje de confirmación de compra debería ser {string}")
    public void checkGreetingMsg(String message){
        SwagLabsServices.validExpected(message, "css:.complete-header");
    }
}
