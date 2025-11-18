Feature: Login
  Como usuario de Saucedemo
  Quiero iniciar sesión en el sistema
  Para acceder a mis productos o recibir el mensaje de error correspondiente

  Background:
    Given El usuario está en la página de login

  @Smoke @LoginExitoso
  Scenario: El usuario inicia sesión correctamente
    When Ingresa el usuario correcto 'standard_user' y la contraseña 'secret_sauce'
    Then Debería visualizar el título 'Products'

  @Smoke @LoginFallido
  Scenario: El usuario inicia sesión de forma fallida
    When Ingresa el usuario fallido 'locked_out_user' y la contraseña 'secret_sauce'
    Then Deberia aparecer un mensaje de error que dice: 'Epic sadface: Sorry, this user has been locked out.'