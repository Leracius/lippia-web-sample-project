Feature: Agregar y eliminar productos del carrito
  Como usuario de Saucedemo
  Quiero agregar y eliminar productos del carrito
  Para controlar los ítems que deseo comprar

  Background:
    Given El usuario está en la página de ingreso

  @Smoke @PruebaCarrito
  Scenario: Agregar y eliminar un producto del carrito
    When El usuario inicia sesión con credenciales "standard_user", "secret_sauce"
    And El usuario agrega al carrito el producto "Sauce Labs Backpack"
    Then El contador del carrito debería mostrar "1"
    When El usuario remueve del carrito el producto "Sauce Labs Backpack"
    Then El contador del carrito debería estar vacío