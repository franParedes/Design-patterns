# Guía Didáctica: El Patrón de Diseño Builder (Constructor)

Imagina que vas a pedir un "Combo" en un restaurante de comida rápida. No pides "un pan, una carne, una lechuga, papas y un refresco" por separado. Simplemente le dices al cajero (el Director): "Quiero un Combo 1". El cajero le da las instrucciones paso a paso al cocinero (el Builder) para que arme la hamburguesa, sirva las papas y llene el vaso, entregándote la bandeja lista.

Así funciona el patrón **Builder**: separa la construcción de un objeto complejo de su representación, de modo que el mismo proceso de construcción pueda crear diferentes representaciones (un combo normal, un combo agrandado, o en nuestro caso, un PDF o un HTML).

---

## ¿Qué es y para qué sirve?

Es un patrón de diseño **Creacional**. Se enfoca en construir objetos complejos paso a paso.

**¿Para qué sirve?**
Sirve para evitar constructores gigantes con 10 o 15 parámetros (conocido como el *Telescoping Constructor Anti-Pattern*). En lugar de pasar un montón de datos de golpe, usas un objeto "constructor" especializado que va ensamblando las piezas una por una, y al final le pides el resultado.

---

## ¿Cuándo se utiliza?

Deberías usar este patrón cuando:
1. **El algoritmo para crear un objeto complejo debe ser independiente de las partes que lo componen.**
2. **El proceso de construcción debe permitir diferentes representaciones del objeto construido.** (Ej. Generar el mismo documento, pero uno en HTML y otro en PDF).
3. **Quieres controlar el proceso de construcción paso a paso.**

---

## ¿Cómo trabaja? (La estructura)

1. **El Producto (`Documentacion`):** El objeto complejo que estamos construyendo.
2. **El Builder Abstracto (`ConstructorDocumentacionVehiculo`):** La interfaz que especifica los pasos para construir el producto (ej. `construyeSolicitudPedido()`, `construyeSolicitudMatriculacion()`).
3. **El Builder Concreto (`ConstructorHtml`, `ConstructorPdf`):** Implementa los pasos y guarda el producto que está ensamblando.
4. **El Director (`Vendedor`):** Conoce el orden exacto en el que deben ejecutarse los pasos. Recibe un Builder, lo ejecuta en el orden correcto y termina el proceso.

---

## Organización de Carpetas

Para este proyecto, la estructura óptima es:

* **`modelos/` (Los Productos):** Clases `Documentacion`, `DocumentacionHtml` y `DocumentacionPdf`.
* **`builders/` (Los Constructores):** El `ConstructorDocumentacionVehiculo` y sus implementaciones concretas.
* **`director/` (El Orquestador):** La clase `Vendedor` que dirige a los builders.
* **`db/`:** Conexión a la base de datos.
* **`ui/`:** Las ventanas estéticas.

## Conclusión

El patrón Builder te da el control absoluto sobre **cómo** se ensambla un objeto. Al usar a un "Director" (como el Vendedor), garantizas que nunca se te olvide un paso (como pedir la matriculación antes de generar el documento), y al usar "Builders", te aseguras de que agregar un nuevo formato (como un documento en XML o Word) sea tan fácil como crear un Builder nuevo sin tocar el código del Vendedor.
