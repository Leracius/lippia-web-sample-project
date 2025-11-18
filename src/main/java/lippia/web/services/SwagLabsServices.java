package lippia.web.services;

import com.crowdar.core.PropertyManager;
import com.crowdar.core.actions.ActionManager;
import lippia.web.constants.SwagLabConstants;
import org.testng.Assert;

import static com.crowdar.core.actions.WebActionManager.navigateTo;

public class SwagLabsServices extends ActionManager{
    public static void navegarWeb(){
        navigateTo(PropertyManager.getProperty("web.base.url"));
    }

    public static void swagLogin(String username, String password){
        setInput(SwagLabConstants.INPUT_USERNAME, username );
        setInput(SwagLabConstants.INPUT_PASSWORD, password);
        click(SwagLabConstants.LOGIN_BUTTON);
    }

    public static void validExpected(String expectedText, String locator) {
        String text = getText(locator);
        Assert.assertEquals(expectedText, text);
    }

    public static String normalizarNombreProducto(String nombre) {
        return nombre.toLowerCase().replace(" ", "-");
    }

    public static void agregarProducto(String nombreProducto) {
        String normalizado = normalizarNombreProducto(nombreProducto);
        String locator = "id:add-to-cart-" + normalizado;
        click(locator);
    }

    public static void removerProducto(String nombreProducto) {
        String normalizado = normalizarNombreProducto(nombreProducto);
        String locator = "id:remove-" + normalizado;
        click(locator);
    }

}
