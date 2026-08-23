# Guía Didáctica: El Patrón de Diseño Abstract Factory (Fábrica Abstracta)

Imagina que vas a comprar muebles para tu sala. Tienes dos estilos en mente: **Moderno** o **Clásico**. Si eliges el estilo Moderno, necesitas que el sofá, la mesa y la silla combinen perfectamente. No querrías mezclar una silla moderna con una mesa clásica victoriana, ¿verdad?

El patrón **Abstract Factory** hace exactamente eso en el desarrollo de software: te asegura que un grupo de objetos (una "familia") combinen y funcionen juntos correctamente, sin que mezcles piezas que no cuadran.

---

## ¿Qué es y para qué sirve?

Es un patrón de diseño **Creacional** (porque se encarga de crear cosas). 
Te permite crear familias de objetos relacionados o dependientes sin tener que especificar sus clases concretas (es decir, sin usar la palabra `new` por todos lados en tu código principal).

**¿Para qué sirve?**
Sirve para que tu programa sea súper flexible. Si mañana tu jefe te dice: *"Ahora también vamos a fabricar vehículos Híbridos"*, no tendrás que reescribir todo tu sistema. Solo creas una nueva "Fábrica Híbrida" y el resto del programa seguirá funcionando como si nada.

---

## ¿Cuándo se utiliza?

Deberías usar este patrón cuando:
1. **Tienes múltiples "familias" de productos:** Como en nuestro ejemplo, donde tenemos vehículos Eléctricos y vehículos a Gasolina.
2. **Necesitas asegurar la compatibilidad:** Quieres estar 100% seguro de que si estás fabricando un conjunto de piezas eléctricas, no se cuele por error un motor de gasolina.
3. **Quieres ocultar la complejidad de creación:** Al usuario final (o a la interfaz de usuario) no le importa *cómo* se construye un coche línea por línea; solo quiere pedir un coche y que se lo entreguen listo.

---

## ¿Cómo trabaja? (La estructura)

Piensa en una jerarquía de empresas:

1. **La Fábrica Abstracta (El Contrato Central):** Es una interfaz que dice: *"Toda fábrica debe saber hacer Automóviles y Scooters"*. (Ej. `FabricaVehiculo`).
2. **Las Fábricas Concretas (Las Sucursales):** Son las que hacen el trabajo real según su especialidad. (Ej. `FabricaVehiculoElectricidad` y `FabricaVehiculoGasolina`).
3. **Los Productos Abstractos (Los Planos):** Dicen qué características debe tener un producto en general. (Ej. Todos los `Automovil` tienen color y modelo).
4. **Los Productos Concretos (El Objeto Final):** Son los objetos reales listos para usarse. (Ej. `AutomovilElectricidad`).

---

## Organización de Carpetas (El porqué de la separación)

En nuestra aplicación, separamos todo en carpetas (`paquetes` en Java) por una razón vital: **El Principio de Responsabilidad Única**. Si mezclas todo, tu código se vuelve un plato de espagueti imposible de desenredar.

* **`modelos/` (Los Productos):** Aquí viven las reglas de cómo es un vehículo. No les importa de dónde vienen ni a qué base de datos van.
* **`fabricas/` (Los Creadores):** Aquí ocurre la magia del patrón Abstract Factory. Estas clases son las únicas autorizadas para usar la palabra `new` y ensamblar los vehículos.
* **`db/` (La Infraestructura):** Su único trabajo es hablar el idioma de MySQL. No sabe qué es un botón ni cómo se crea un coche, solo sabe guardar datos.
* **`ui/` (La Presentación):** Dibuja las ventanas. **Aquí es donde brilla el patrón:** La ventana no sabe cómo armar un coche de gasolina; simplemente le dice a la fábrica *"Oye, el usuario eligió gasolina, dame un Automóvil"* y la fábrica se encarga del resto.

---

## ¿Cómo se implementa? (La magia en la interfaz)

La implementación se reduce a **tomar una decisión una sola vez**, y dejar que esa decisión guíe el resto del programa.

```java
// 1. El usuario elige el tipo de motor en la pantalla
String tipoMotor = "Eléctrico";

// 2. Declaramos la fábrica de manera genérica
FabricaVehiculo miFabrica;

// 3. Tomamos la decisión de qué fábrica instanciar
if (tipoMotor.equals("Eléctrico")) {
    miFabrica = new FabricaVehiculoElectricidad();
} else {
    miFabrica = new FabricaVehiculoGasolina();
}

// 4. A partir de aquí, el código no necesita saber si es eléctrico o gasolina.
// Solo pide los productos y la fábrica correcta se los da:
Automovil auto = miFabrica.creaAutomovil("Sedan", "Rojo", 150, 4.5);
Scooter moto = miFabrica.creaScooter("Urbana", "Azul", 50);
```

## Conclusión

El patrón Abstract Factory actúa como un escudo protector para tu código base. Aísla por completo la creación de objetos de las pantallas o bases de datos que los utilizan. Al adoptar esta estructura (y combinarla con una buena separación de paquetes), te aseguras de tener un sistema donde agregar nuevos tipos de productos en el futuro sea tan fácil como enchufar un cable nuevo, sin riesgo de romper todo lo que ya construiste.
