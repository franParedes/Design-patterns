# Guia Didactica: El Patron de Diseno Composite

En el mundo empresarial y del desarrollo de software, es comun encontrar estructuras jerarquicas similares a un arbol. Un conglomerado corporativo (Empresa Madre) esta compuesto por varias sucursales locales, y estas a su vez pueden tener departamentos mas pequenos o operar de forma independiente (Empresas Sin Filial).

El patron **Composite** permite tratar a los objetos individuales (las hojas) y a las composiciones de objetos (las ramas) de manera completamente uniforme.

---

## 1. Definicion y Proposito

El patron Composite es un patron de diseno **Estructural**. Su proposito principal es componer objetos en estructuras de arbol para representar jerarquias de parte-todo. 

Gracias a este patron, el cliente (tu codigo principal o interfaz de usuario) puede ignorar las diferencias entre las composiciones de objetos y los objetos individuales. Si le pides a una entidad que calcule su coste de mantenimiento, lo hara de forma transparente, sin importar si es una pequena sucursal o un holding internacional gigantesco.

---

## 2. Escenarios de Uso

La implementacion de este patron es altamente recomendada cuando:

* **Necesitas representar jerarquias complejas:** Como sistemas de archivos (carpetas que contienen archivos u otras carpetas), interfaces graficas (ventanas que contienen paneles que contienen botones), o estructuras corporativas.
* **Buscas simplificar el codigo cliente:** Deseas que el cliente pueda ejecutar una accion sobre toda la estructura sin tener que escribir sentencias condicionales complejas para diferenciar entre un nodo simple y un nodo complejo.

---

## 3. Estructura y Funcionamiento

El patron se apoya en tres componentes fundamentales:

1. **Componente Abstracto (`Empresa`):** Define la interfaz comun tanto para los objetos simples como para los compuestos. Establece metodos compartidos, como `calculaCosteMantenimiento()` o `agregaVehiculo()`.
2. **Objeto Hoja (`EmpresaSinFilial`):** Representa los objetos al final de la cadena, es decir, aquellos que no tienen sub-elementos (filiales). Implementa el comportamiento real del calculo basado unicamente en sus propios recursos.
3. **Objeto Compuesto (`EmpresaMadre`):** Representa objetos que tienen sub-elementos. Contiene una coleccion (`List<Empresa>`) de objetos Componente. Su implementacion del metodo de calculo itera sobre sus hijos, delegandoles la operacion y sumando finalmente sus propios valores.

---

## 4. Estructura de Directorios del Proyecto

La separacion de responsabilidades se mantiene integra en la aplicacion:

* **`modelos/`:** Aloja la estructura del patron (Componente, Hoja, Compuesto). Aqui reside el motor logico del sistema.
* **`db/`:** Aisla el codigo de persistencia JDBC, encargado unicamente de interactuar con MySQL.
* **`ui/`:** Administra la presentacion mediante Swing. La clase `VentanaRegistro` ilustra el potencial del patron al mantener una `EmpresaMadre` global, permitiendo observar como la suma automatica en arbol gestiona la complejidad subyacente.

---

## 5. Conclusion

El patron Composite es esencial para sistemas que manejan recursividad. Garantiza un codigo escalable donde anadir nuevos tipos de elementos compuestos u hojas requiere minimas o nulas alteraciones en el codigo cliente existente, promoviendo asi el principio de responsabilidad unica y la encapsulacion total de la jerarquia.
