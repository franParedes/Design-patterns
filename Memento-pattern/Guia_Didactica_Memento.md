# Guía Didáctica: El Patrón de Diseño Memento (Recuerdo)

Imagina que juegas un videojuego y llegas a un punto difícil. Antes de enfrentar al jefe, guardas la partida. Si pierdes, cargas ese punto de guardado y vuelves exactamente a como estabas. El archivo de guardado no es el juego en sí: es una foto del estado del juego en ese momento. Y el menú de "cargar partida" solo guarda y entrega esas fotos, sin saber qué hay dentro de ellas.

Así funciona el patrón **Memento**: permite capturar el estado interno de un objeto y guardarlo fuera de él, de modo que el objeto pueda volver a ese estado más adelante, sin exponer sus detalles internos.

---

## ¿Qué es y para qué sirve?

Es un patrón de diseño de **Comportamiento**. Se enfoca en guardar y restaurar el estado de un objeto sin romper su encapsulamiento.

**¿Para qué sirve?** Sirve cuando necesitas "puntos de guardado": deshacer cambios, volver a una versión anterior o guardar el progreso. Quien guarda los recuerdos no necesita saber cómo está hecho el objeto, y el objeto no necesita saber dónde ni cómo se almacenan sus recuerdos.

---

## ¿Cuándo se utiliza?

Deberías usar este patrón cuando:

1. **Necesitas guardar y restaurar el estado de un objeto.** (Ej. guardar una partida, un borrador, la configuración de un editor).
2. **Quieres conservar varias versiones del estado.** Cada recuerdo es una versión que se puede recuperar después.
3. **No quieres exponer el estado interno del objeto.** Solo el propio objeto sabe crear y leer sus recuerdos.
4. **Quieres separar la responsabilidad de guardar de la de usar el objeto.** El objeto se dedica a su trabajo y otra clase se encarga de custodiar los recuerdos.

---

## ¿Cómo trabaja? (La estructura)

1. **El Originador (`Originador`):** El objeto cuyo estado se quiere guardar. Es el único que sabe crear un recuerdo a partir de su estado (`crearRecuerdo()`) y restaurar su estado a partir de un recuerdo (`setRecuerdo()`).
2. **El Recuerdo (`Recuerdo`):** Es la "foto" del estado. Guarda la `Partida` tal como estaba al momento de crearlo.
3. **El Conserje (`Conserje`):** Custodia los recuerdos. Los guarda (`setRecuerdo`), los entrega (`getRecuerdo`) y los elimina, pero nunca mira dentro de ellos. En este proyecto guarda los recuerdos en la base de datos a través de `RecuerdoDAO`.
4. **El Estado (`Partida`, `FechaHora`):** La información que se guarda: nombre, fase y fecha de la partida.
5. **La Interfaz Gráfica (`VentanaMemento`):** Hace de cliente. Permite escribir la partida actual, guardarla como recuerdo, restaurar un recuerdo seleccionado y eliminarlo.

### Flujo de una operación

**Guardar:**

1. El usuario escribe el nombre y la fase y pulsa "Guardar recuerdo".
2. La ventana asigna la nueva `Partida` al `Originador`.
3. El `Originador` crea un `Recuerdo` con esa partida (`crearRecuerdo()`).
4. La ventana se lo entrega al `Conserje`, que lo guarda en la base de datos.

**Restaurar:**

1. El usuario selecciona un recuerdo de la tabla y pulsa "Restaurar seleccionado".
2. La ventana le pide el recuerdo al `Conserje` (`getRecuerdo(indice)`).
3. La ventana se lo entrega al `Originador` (`setRecuerdo()`), que recupera la partida guardada.

### Nota sobre esta implementación

La `Partida` no tiene métodos para modificarla después de creada (solo tiene getters). Por eso el recuerdo guarda la partida original sin riesgo de que cambie con el tiempo. Si el estado fuera modificable, el recuerdo tendría que guardar una copia.

---

## Estructura de la Base de Datos

Una sola tabla, `recuerdo`:

- **`id`:** Clave primaria autoincremental.
- **`nombre`:** Nombre de la partida.
- **`fase`:** Fase en la que estaba.
- **`fecha`:** Fecha y hora en que se creó la partida.

---

## Organización de Carpetas

Para este proyecto, la estructura óptima es:

- **`modelos/` (El Estado):** Clases `Partida` y `FechaHora`.
- **`originador/`:** La clase `Originador`.
- **`recuerdo/` (El Memento):** La clase `Recuerdo`.
- **`conserje/` (El Custodio):** La clase `Conserje`.
- **`dao/`:** La interfaz `RecuerdoDAO` y su implementación `RecuerdoDAOImpl`.
- **`db/`:** Conexión a la base de datos (`ConexionBD`).
- **`ui/`:** La ventana `VentanaMemento` y la clase `PruebaPatron` que la inicia.

---

## Conclusión

El patrón Memento te da una forma ordenada de volver atrás. El `Originador` conserva el control sobre su propio estado, el `Conserje` se limita a custodiar los recuerdos sin abrirlos, y el estado queda protegido porque nadie más lo manipula. Agregar nuevas formas de guardado (por ejemplo, en un archivo en lugar de una base de datos) solo implica cambiar la forma en que el `Conserje` almacena los recuerdos, sin tocar al `Originador`.
