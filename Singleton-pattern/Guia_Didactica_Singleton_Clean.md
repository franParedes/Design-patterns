# Guia Didactica: El Patron de Diseno Singleton

Imagina que en un reino solo puede haber un rey al mismo tiempo. Si alguien quiere hablar con el rey, no va y crea un rey nuevo, sino que acude al que ya esta en el trono. 

Asi funciona el patron **Singleton**: garantiza que una clase tenga una unica instancia en toda la aplicacion y proporciona un punto de acceso global a ella.

---

## 1. Que es y para que sirve?

Es el patron de diseno **Creacional** mas simple, pero uno de los mas poderosos. Consiste en una clase que se encarga de instanciarse a si misma de manera exclusiva.

**Para que sirve?**
Evita que diferentes partes del programa creen copias innecesarias de un objeto que maneja recursos compartidos. Sirve para mantener un estado unico y consistente a lo largo de toda la ejecucion del software.

---

## 2. Cuando se utiliza?

Deberias usar este patron cuando:
* **Control de recursos compartidos:** Necesitas gestionar conexiones a bases de datos, sistemas de archivos o colas de impresion, donde multiples instancias causarian bloqueos o colisiones.
* **Configuracion global:** Tienes una clase que almacena las preferencias de usuario o la configuracion del sistema (como nuestra clase `Comercial`) y quieres que cualquier pantalla de la aplicacion acceda exactamente a los mismos datos.
* **Ahorro de memoria:** El objeto es pesado de construir y su estado interno no necesita cambiar dependiendo del contexto.

---

## 3. Como trabaja? (La estructura)

Para crear un Singleton, solo necesitas aplicar tres reglas estrictas a tu clase:

1. **Constructor Privado (`private Comercial()`):** Impides que cualquier otra clase use la palabra `new` para crear copias independientes.
2. **Variable Estatica Privada (`private static Comercial _instance`):** Declaras una variable oculta que almacenara la unica copia permitida.
3. **Metodo Estatico Publico (`public static Comercial Instance()`):** Creas una "puerta de acceso". Cuando alguien solicita el objeto por primera vez, este metodo lo instancia y lo guarda en la variable estatica. Si alguien lo vuelve a solicitar, devuelve la misma copia guardada.

---

## 4. Organizacion de Carpetas

* **`modelos/`:** La clase `Comercial` con su logica de instancia unica.
* **`db/`:** Logica de guardado en MySQL.
* **`ui/`:** Interfaces graficas. Al usar `Comercial.Instance()`, cualquier ventana de esta carpeta vera siempre la misma informacion del comercial, asegurando sincronizacion perfecta.

---

## 5. Conclusion

El patron Singleton es la solucion definitiva para el control centralizado. Al restringir la instanciacion, te aseguras de que todos los componentes de tu sistema interactuen con la misma "fuente de la verdad", eliminando errores de estado y optimizando el uso de recursos.
