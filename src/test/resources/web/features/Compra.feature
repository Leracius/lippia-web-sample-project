Feature: Compra
  Como usuario de saucedemo
  Quiero poder inicar una compra, agregar productos al carrito
  Y poder concretar el proceso de compra exitosamente con mis datos

  Background:
    Given El usuario ingreso correctamente y se encuentra en la seccion de productos

  @Smoke @CompraExitosa
  Scenario: Compra completa y validación de datos
    When El usuario agrega los siguientes productos al carrito:
      | productName           |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    And El usuario navega a la página del carrito
    And El usuario inicia el proceso de checkout
    And El usuario completa el formulario de checkout con:
      | firstName | lastName | postalCode |
      | Axel      | Quintana | 5000       |
    And El usuario continúa hacia la página de resumen
    Then La página de resumen debería mostrar los siguientes productos y precios:
      | productName           | price   |
      | Sauce Labs Backpack   | $29.99  |
      | Sauce Labs Bike Light | $9.99   |
    When El usuario finaliza la compra
    Then El mensaje de confirmación de compra debería ser "Thank you for your order!"

