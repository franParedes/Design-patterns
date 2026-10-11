# Guia Didactica: El Patron de Diseno Decorator (Decorador)

Al comprar un vehiculo basico, a menudo se te ofrece anadir paquetes opcionales: asientos de cuero, sistema de navegacion, o rines deportivos. El coche base sigue siendo el mismo, pero lo estas "decorando" dinamicamente con nuevas caracteristicas.

El patron **Decorator** te permite anadir responsabilidades y funcionalidades a un objeto de forma dinamica, envolviendolo dentro de objetos especiales llamados "decoradores", sin necesidad de modificar el codigo de la clase original ni crear una jerarquia interminable de subclases.

---

## 1. Que es y para que sirve?

Es un patron de diseno **Estructural**. Funciona como un envoltorio (wrapper) alrededor de un objeto existente.

**Para que sirve?**
Sirve para extender la funcionalidad de una clase de manera flexible. Si intentaras hacer esto usando herencia pura, terminarias con una combinacion explosiva de clases (ej. `VehiculoConLogo`, `VehiculoConInfoTecnica`, `VehiculoConLogoYInfoTecnica`). El Decorator elimina este problema permitiendote apilar las funcionalidades como capas.

---

## 2. Cuando se utiliza?

Deberias usar este patron cuando:
* **Necesitas anadir responsabilidades extra a los objetos en tiempo de ejecucion:** El usuario puede decidir en la interfaz si quiere anadir la informacion tecnica, el logotipo o ambos, de forma dinamica.
* **La herencia es inviable o genera demasiadas clases:** Cuando tienes multiples comportamientos opcionales que se pueden combinar de muchas maneras.
* **No quieres alterar el codigo base:** Permite respetar el principio de Abierto/Cerrado (Abierto para extension, cerrado para modificacion).

---

## 3. Como trabaja? (La estructura)

1. **Componente (`ComponenteGraficoVehiculo`):** La interfaz o clase abstracta comun tanto para el objeto base como para los decoradores. 
2. **Componente Concreto (`VistaVehiculo`):** El objeto principal que sera envuelto. Representa el vehiculo basico.
3. **Decorador Base (`Decorador`):** Una clase que implementa la interfaz del Componente y almacena una referencia a un objeto Componente. Su trabajo principal es delegar todas las peticiones a ese componente envuelto.
4. **Decoradores Concretos (`MarcaDecorador`, `ModeloDecorador`):** Heredan del Decorador Base, ejecutan el metodo del objeto envuelto y luego anaden su comportamiento extra (como visualizar el logo o la info tecnica).

---

## 4. Organizacion de Carpetas

* **`modelos/`:** Contiene las interfaces y clases del patron Decorator. Es el nucleo de la logica de negocio.
* **`db/`:** Maneja exclusivamente la conexion y ejecucion de sentencias SQL.
* **`ui/`:** Administra las ventanas. Aqui es donde se decide, en base a las casillas de verificacion del usuario, cuantas "capas" de decoracion aplicar al vehiculo antes de mostrarlo y guardarlo.

---

## 5. Conclusion

El patron Decorator es la alternativa moderna y agil a la herencia multiple para anadir estado o comportamiento. Al envolver objetos dentro de otros objetos que comparten la misma interfaz, logras un diseno altamente modular, donde las funcionalidades complejas se arman ensamblando pequenas piezas intercambiables en tiempo de ejecucion.
