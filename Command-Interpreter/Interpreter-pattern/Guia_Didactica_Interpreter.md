# Guía Didáctica: El Patrón de Diseño Interpreter (Intérprete)

Imagina que viajas a otro país y contratas a un traductor. Tú le dices una frase en tu idioma y él la entiende, la analiza palabra por palabra y la convierte en una acción o en una respuesta que la otra persona puede comprender. El traductor no inventa nada: conoce las reglas del idioma (la gramática) y sabe qué significa cada parte de la frase.

Así funciona el patrón **Interpreter**: define una gramática para un lenguaje sencillo y una forma de interpretar las frases escritas en ese lenguaje. En nuestro caso, el lenguaje son consultas como `muestra empleados por empresas`, y el resultado es el listado correspondiente obtenido de la base de datos.

---

## ¿Qué es y para qué sirve?

Es un patrón de diseño de **Comportamiento**. Se enfoca en representar la gramática de un lenguaje mediante clases, de modo que cada regla de la gramática sea una clase capaz de interpretar su parte de la expresión.

**¿Para qué sirve?** Sirve para evitar llenar el código de condicionales enormes que analizan cadenas de texto. En lugar de eso, cada tipo de consulta tiene su propio intérprete, y agregar una nueva consulta consiste en crear un intérprete nuevo sin modificar los existentes.

---

## ¿Cuándo se utiliza?

Deberías usar este patrón cuando:

1. **Tienes un lenguaje sencillo y bien definido que debe ser interpretado.** (Ej. consultas, filtros, comandos de texto, expresiones matemáticas).
2. **La gramática es simple.** Si el lenguaje crece mucho, el número de clases se vuelve difícil de mantener y conviene usar un analizador sintáctico especializado.
3. **Quieres poder extender el lenguaje con facilidad.** Cada regla nueva es una clase nueva.
4. **Las mismas expresiones deben poder interpretarse sobre un conjunto de datos común (el contexto).**

---

## La gramática del ejemplo

El lenguaje que entiende este proyecto es:

```
consulta ::= "muestra" objeto [ "por" objeto ]
objeto   ::= "empresas" | "empleados"
```

Las expresiones válidas y el intérprete que las resuelve son:

| Expresión | Intérprete | Resultado |
|---|---|---|
| `muestra empresas` | `InterpreteEmpresa` | Lista de empresas |
| `muestra empleados` | `InterpreteEmpleado` | Lista de empleados |
| `muestra empresas por empleados` | `InterpreteEmpresaEmpleado` | Cada empleado con sus empresas |
| `muestra empleados por empresas` | `InterpreteEmpleadoEmpresa` | Cada empresa con sus empleados |

---

## ¿Cómo trabaja? (La estructura)

1. **La Expresión Abstracta (`Interprete`):** La interfaz que declara el método `interpretar(Contexto contexto)`. Todos los intérpretes la implementan y devuelven el resultado como texto.
2. **Las Expresiones Concretas (`InterpreteEmpresa`, `InterpreteEmpleado`, `InterpreteEmpresaEmpleado`, `InterpreteEmpleadoEmpresa`):** Cada una implementa la interpretación de una regla de la gramática, consultando los datos del contexto.
3. **El Contexto (`Contexto`):** Contiene la información global sobre la que se interpreta: las empresas, los empleados y sus relaciones (`EmpresayEmpleado`). En este proyecto se carga desde la base de datos a través del DAO.
4. **El Cliente (`InterpreteCliente`):** Recibe la frase escrita por el usuario, la analiza token por token (`muestra`, `empresas`, `empleados`, `por`), decide qué intérprete corresponde y lo ejecuta con el contexto.
5. **La Interfaz Gráfica (`VentanaInterprete`):** Permite agregar empresas y empleados, relacionarlos, escribir la expresión y ver el resultado.

### Flujo de una consulta

1. El usuario escribe `muestra empleados por empresas` y pulsa "Interpretar".
2. La ventana crea un `InterpreteCliente` con el contexto cargado de la base de datos.
3. El cliente analiza la frase: la primera palabra clave indica qué mostrar (empleados) y la que sigue a `por` indica cómo agruparlo (empresas).
4. El cliente elige `InterpreteEmpleadoEmpresa` y llama a `interpretar(contexto)`.
5. El intérprete recorre las empresas del contexto, obtiene los empleados de cada una y devuelve el texto, que la ventana muestra en pantalla.

### Nota sobre esta implementación

Esta es una versión simplificada del patrón. En la versión clásica la frase se convierte en un árbol de expresiones (terminales y no terminales) que se interpretan de forma recursiva. Aquí el cliente analiza la frase y selecciona directamente el intérprete adecuado, lo cual es suficiente para una gramática tan pequeña y es más fácil de entender al empezar.

---

## Estructura de la Base de Datos

- **`empresa`:** `id` y `nombre` (único).
- **`empleado`:** `id` y `nombre` (único).
- **`empresa_empleado`:** relación muchos a muchos entre empresas y empleados, con clave primaria compuesta por `empresa_id` y `empleado_id`.

---

## Organización de Carpetas

Para este proyecto, la estructura óptima es:

- **`modelos/` (El Contexto):** Clases `Contexto` y `EmpresayEmpleado`.
- **`interpretes/` (Las Expresiones):** La interfaz `Interprete` y sus implementaciones concretas.
- **`cliente/`:** La clase `InterpreteCliente` que analiza la frase y elige el intérprete.
- **`dao/`:** La interfaz `ContextoDAO` y su implementación `ContextoDAOImpl`.
- **`db/`:** Conexión a la base de datos (`ConexionBD`).
- **`ui/`:** La ventana `VentanaInterprete` y la clase `EjecutarPatron` que la inicia.

---

## Conclusión

El patrón Interpreter te permite tratar un pequeño lenguaje como parte del diseño del programa. Cada regla de la gramática vive en su propia clase, por lo que agregar una nueva consulta (como `muestra empleados por cantidad`) es tan fácil como crear un intérprete nuevo y reconocer la palabra en el cliente. Su límite es la complejidad: funciona muy bien con gramáticas pequeñas, pero si el lenguaje crece demasiado, el número de clases se dispara y es mejor recurrir a herramientas de análisis sintáctico.
