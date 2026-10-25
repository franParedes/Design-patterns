# Guía Didáctica: El Patrón de Diseño Command (Comando)

Imagina que vas a un restaurante. No entras a la cocina a decirle al cocinero qué preparar. Le dices tu pedido al mesero, que lo anota en una comanda y se la entrega a la cocina. La comanda es un objeto: se puede guardar, se puede cancelar, se puede repetir y se puede revisar después. El mesero no sabe cocinar y el cocinero no necesita saber quién hizo el pedido.

Así funciona el patrón **Command**: convierte una petición en un objeto independiente que contiene toda la información necesaria para ejecutarla. Esto permite pasar la petición como parámetro, guardarla en una lista y, en nuestro caso, deshacerla y rehacerla.

---

## ¿Qué es y para qué sirve?

Es un patrón de diseño de **Comportamiento**. Se enfoca en encapsular una acción dentro de un objeto, de modo que quien la solicita (el invocador) quede desacoplado de quien la realiza (el receptor).

**¿Para qué sirve?** Sirve para evitar que la interfaz gráfica o el código cliente llamen directamente a los métodos del objeto de negocio. En lugar de escribir `reunion.setLocalizacion(...)` dentro de un botón, el botón le pide a un comando que se ejecute. Como cada comando recuerda lo que hizo, se pueden implementar fácilmente las operaciones **deshacer** y **rehacer**, o llevar un historial de acciones.

---

## ¿Cuándo se utiliza?

Deberías usar este patrón cuando:

1. **Necesitas parametrizar objetos con acciones.** (Ej. un botón que ejecuta cualquier comando que se le asigne).
2. **Necesitas deshacer y rehacer operaciones.** Cada comando guarda el estado anterior y el nuevo.
3. **Quieres desacoplar quien invoca la operación de quien la realiza.** El invocador no conoce los detalles de la reunión.
4. **Necesitas registrar, encolar o persistir las operaciones.** Como cada acción es un objeto, se puede almacenar o guardar el resultado en una base de datos.

---

## ¿Cómo trabaja? (La estructura)

1. **El Comando (`Comando`):** La interfaz que declara el método `ejecutar(String param)`. Todos los comandos concretos la implementan.
2. **El Comando con Deshacer (`ComandoDeshacer`):** Extiende `Comando` y agrega `deshacer()` y `rehacer()`.
3. **El Comando Concreto (`ComandoCambiarLocalizacion`, `ComandoCambiarFecha`):** Implementan la acción. Antes de modificar la reunión guardan el valor antiguo y el valor nuevo, y con eso pueden revertir o repetir el cambio.
4. **El Receptor (`Reunion`):** El objeto que realmente sabe hacer el trabajo. Los comandos llaman a sus métodos (`setLocalizacion`, `setFecha`).
5. **El Invocador (`Invocador`):** Ejecuta los comandos y mantiene dos pilas, una de deshacer y otra de rehacer. No sabe qué hace cada comando, solo sabe ejecutarlo, deshacerlo o rehacerlo. En este proyecto también guarda el resultado en la base de datos mediante el DAO después de cada operación.
6. **El Cliente (`VentanaReuniones`):** Crea el comando concreto, le asigna su receptor, se lo entrega al invocador y le pide que lo ejecute.

### Flujo de una operación

1. El usuario pulsa "Cambiar localización" en la ventana.
2. El cliente crea un `ComandoCambiarLocalizacion` y le asigna la `Reunion` seleccionada.
3. El cliente se lo entrega al `Invocador` y llama a `ejecutaComando("Sala reunion 2")`.
4. El comando guarda la localización antigua y aplica la nueva en la reunión.
5. El invocador guarda el comando en la pila de deshacer, vacía la pila de rehacer y actualiza la base de datos.

Al pulsar **Deshacer**, el invocador saca el último comando de la pila, llama a `deshacer()` y lo mueve a la pila de rehacer. **Rehacer** hace el recorrido inverso.

---

## Organización de Carpetas

Para este proyecto, la estructura óptima es:

- **`modelos/` (Los Receptores):** Clases `Reunion` y `FechaHora`.
- **`comandos/` (Los Comandos):** La interfaz `Comando`, la interfaz `ComandoDeshacer` y las implementaciones concretas `ComandoCambiarLocalizacion` y `ComandoCambiarFecha`.
- **`invocador/` (El Orquestador):** La clase `Invocador` con las pilas de deshacer y rehacer.
- **`dao/`:** La interfaz `ReunionDAO` y su implementación `ReunionDAOImpl`.
- **`db/`:** Conexión a la base de datos (`ConexionBD`).
- **`ui/`:** La ventana `VentanaReuniones` y la clase `Cliente` que la inicia.

---

## Conclusión

El patrón Command te da control sobre **qué** acciones se realizan y **cuándo** se pueden revertir. Al encapsular cada operación en un objeto, la interfaz gráfica no necesita conocer la lógica de cada cambio, y agregar una nueva acción (como cambiar los participantes o el motivo de la reunión) es tan fácil como crear un comando nuevo sin tocar el código del invocador. Además, el historial de deshacer y rehacer se obtiene casi sin esfuerzo, porque cada comando ya sabe cómo revertirse a sí mismo.
