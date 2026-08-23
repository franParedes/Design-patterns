# Guía Didáctica: El Patrón de Diseño Factory Method (Método de Fábrica)

¿Has ido a una pizzería donde tú solo pides "una pizza" y dejas que el chef decida exactamente cómo prepararla según el tipo de masa que elegiste al entrar? Tú no te metes a la cocina a amasar, solo confías en que el restaurante tiene un "método" para entregarte lo que pediste.

Así funciona el patrón **Factory Method**: defines una interfaz para crear un objeto, pero dejas que sean las subclases (los diferentes chefs) quienes decidan qué clase específica instanciar.

---

## ¿Qué es y para qué sirve?

Es un patrón de diseño **Creacional**. Su propósito principal es **delegar la creación de objetos a las subclases**. 

A diferencia del *Abstract Factory* (que crea *familias enteras* de objetos como coches y scooters eléctricos), el *Factory Method* se enfoca en crear **un solo tipo de producto**, pero con diferentes variaciones.

**¿Para qué sirve?**
Sirve para quitarle a tu clase principal la responsabilidad de usar la palabra `new`. Esto hace que tu código cumpla con el principio de **Abierto/Cerrado** (Open/Closed Principle): puedes agregar nuevos tipos de pedidos (ej. `PedidoCriptomonedas`) sin tener que modificar el código de la clase `Cliente` original.

---

## ¿Cuándo se utiliza?

Deberías usar este patrón cuando:
1. **No sabes de antemano qué tipo exacto de objetos necesita tu código:** Sabes que procesarás un `Pedido`, pero no sabes si será de `Crédito` o de `Contado` hasta que el usuario hace clic en un botón.
2. **Quieres centralizar la lógica de creación:** Si un objeto requiere una configuración compleja antes de ser usado, pones esa lógica en el *Factory Method* en lugar de repetirla por todas partes.

---

## ¿Cómo trabaja? (La estructura)

El patrón consta de cuatro componentes clave:

1. **El Producto Abstracto (`Pedido`):** La clase padre o interfaz que define qué puede hacer el objeto (ej. `valida()`, `paga()`).
2. **Los Productos Concretos (`PedidoCredito`, `PedidoContado`):** Las implementaciones exactas. Cada uno tiene sus propias reglas de validación.
3. **El Creador Abstracto (`Cliente`):** La clase que declara el "Factory Method" (`creaPedido()`). Nota importante: esta clase *también* puede tener lógica que use el producto (como el método `nuevoPedido()`).
4. **Los Creadores Concretos (`ClienteCredito`, `ClienteContado`):** Las clases que heredan del Creador y sobreescriben el método de fábrica para devolver el producto correcto.

---

## Organización de Carpetas

Para mantener el proyecto ordenado y profesional, dividimos las responsabilidades así:

* **`modelos/` (Los Productos):** Aquí están las clases `Pedido`, `PedidoCredito` y `PedidoContado`. Son los datos y sus reglas de validación.
* **`creadores/` (La Fábrica):** Aquí viven `Cliente` y sus subclases. Son los encargados de decidir qué producto crear.
* **`db/`:** Para la clase de conexión a MySQL.
* **`ui/`:** Para las ventanas gráficas. La interfaz gráfica solo interactúa con los `creadores`, nunca crea los `modelos` directamente.

---

## Diferencia clave: Factory Method vs Abstract Factory

Es muy común confundirlos, pero la regla general es:
* **Factory Method:** Se usa para crear **un solo producto** mediante herencia (un método que se sobrescribe). Ejemplo: Crear un `Pedido`.
* **Abstract Factory:** Se usa para crear **familias de productos** mediante composición (un objeto que agrupa varias fábricas). Ejemplo: Crear un `Automóvil` Y un `Scooter` que combinan entre sí.

## Conclusión

El **Factory Method** es el salvavidas perfecto cuando tu sistema necesita ser flexible frente a nuevos requerimientos. Al delegar la creación de los objetos a las subclases, logras que tu sistema base (el `Cliente`) no se vuelva frágil al añadir nuevas formas de pago o nuevos tipos de pedidos en el futuro.
