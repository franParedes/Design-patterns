# Guía Didáctica: El Patrón de Diseño Prototype (Prototipo)

¿Recuerdas a la oveja Dolly? Fue el primer mamífero clonado a partir de una célula adulta. En lugar de "crear" una oveja desde cero pasando por todo el proceso biológico tradicional, los científicos tomaron un espécimen existente y lo copiaron.

Esa es exactamente la esencia del patrón **Prototype** en la programación: en lugar de crear objetos nuevos usando la palabra `new` y configurándolos desde cero (lo cual puede ser costoso en rendimiento o requerir muchos datos iniciales), tomas un objeto que ya tienes configurado y lo **clonas**.

---

## 1. ¿Qué es y para qué sirve?

Es un patrón de diseño **Creacional**. Permite copiar objetos existentes sin hacer que tu código dependa de sus clases concretas.

**¿Para qué sirve?**
Imagina que tienes una aplicación que genera reportes complejos con cientos de configuraciones. Si un usuario quiere generar 5 reportes iguales pero solo cambiarles el título, crear 5 objetos desde cero y reconfigurarlos uno por uno es ineficiente. Con Prototype, configuras el primer reporte, lo clonas 4 veces, y solo le cambias el título a las copias. Ahorras memoria, tiempo y líneas de código.

---

## 2. ¿Cuándo se utiliza?

Deberías usar este patrón cuando:
* **La creación de un objeto es muy costosa:** Requiere leer bases de datos, hacer cálculos pesados o peticiones de red. Clonar es instantáneo.
* **Necesitas aislar tu código de las clases concretas:** Como en nuestro ejemplo, la clase `DocumentacionCliente` no sabe qué documentos específicos está copiando. Solo sabe que la lista en blanco contiene "cosas que se pueden clonar".
* **Tienes muchas subclases que solo difieren en sus valores iniciales:** En lugar de crear subclases infinitas, creas unos cuantos "Prototipos" preconfigurados y los copias cuando los necesitas.

---

## 3. ¿Cómo trabaja? (La estructura)

* **La Interfaz Prototipo (`Documento` / `Cloneable`):** Declara el método para clonarse a sí mismo (en Java, usualmente el método `clone()` o `duplica()`).
* **El Prototipo Concreto (`OrdenDePedido`, `CertificadoCesion`):** Implementa la lógica real de clonación.
* **El Registro de Prototipos (`DocumentacionEnBlanco`):** Una clase (a menudo un *Singleton*) que almacena objetos preconfigurados listos para ser copiados.
* **El Cliente (`DocumentacionCliente`):** Pide al registro los prototipos, los clona y los personaliza con nueva información.

---

## 4. Organización de Carpetas

Manteniendo la arquitectura limpia:

* **`modelos/`:** Clases `Documento` y sus hijos (`OrdenDePedido`, etc.). Son los objetos clonables.
* **`gestores/`:** Clases `Documentacion`, `DocumentacionEnBlanco` y `DocumentacionCliente`. Son las que manejan y clonan las colecciones.
* **`db/`:** Lógica de guardado en MySQL.
* **`ui/`:** Interfaces gráficas.

---

## 5. Conclusión

El patrón Prototype es la herramienta definitiva para la eficiencia. Al mantener un "catálogo" de objetos prefabricados (como nuestros documentos en blanco) y simplemente fotocopiarlos cuando los necesitamos, logramos un sistema rápido, dinámico y que puede crecer para incluir nuevos tipos de documentos sin tener que reescribir la lógica de generación del cliente.
