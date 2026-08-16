# Guía Didáctica: El Patrón de Diseño Delegation (Delegación)

¿Alguna vez has sido el líder de un proyecto escolar y te has dado cuenta de que no puedes hacerlo todo tú solo? ¿Qué haces? Le encargas el diseño a tu compañero creativo y la base de datos al que sabe de SQL. Tú presentas el trabajo final, pero **delegaste** las tareas específicas a los expertos. 

¡Así exactamente funciona el patrón **Delegation** en el desarrollo de software!

---

## ¿Qué es y para qué sirve?

El patrón de Delegación es una técnica donde un objeto (el Jefe) expone un comportamiento, pero en lugar de realizar el trabajo por sí mismo, le pasa la responsabilidad a un objeto interno (el Ayudante/Delegado).

**¿Para qué sirve?** 
Sirve principalmente como la **mejor alternativa a la herencia**. En lugar de crear un árbol genealógico enorme de clases padre e hijas (que a la larga se vuelve un dolor de cabeza de mantener), usas la **composición**: tu clase simplemente "tiene un" ayudante que hace el trabajo por ella.

---

## ¿Cuándo se utiliza?

Deberías usar este patrón cuando:
1. **Necesitas cambiar el comportamiento en tiempo de ejecución:** Imagina un personaje de videojuego. A veces usa una espada, a veces un arco. En lugar de crear las clases `GuerreroConEspada` y `GuerreroConArco`, creas un `Guerrero` que *delega* su ataque a su `Arma`. Si recoge un arco, simplemente le cambias el arma y su forma de atacar cambia al instante.
2. **Quieres evitar el "Problema del Diamante" (Herencia Múltiple):** En lenguajes como C# o Java, una clase no puede heredar de dos padres a la vez. Si una clase necesita comportarse como dos cosas distintas, delegas los comportamientos en lugar de heredar.
3. **Tienes roles dinámicos:** Como en el caso del Personal Universitario. Una persona puede ser Estudiante, Profesor y trabajar en un Proyecto ¡al mismo tiempo!

---

## ¿Cómo trabaja? (La estructura)

El patrón funciona orquestando tres piezas fundamentales:

1. **La Interfaz (El Contrato):** Define qué es lo que se tiene que hacer (ej. `IDescripcion` con su método `ObtenerDescripcion()`).
2. **El Delegado (El Especialista):** Las clases concretas que implementan la interfaz y saben cómo hacer el trabajo real (ej. `Estudiante`, `Profesor`, `Proyecto`).
3. **El Delegante (El Jefe):** La clase principal que el usuario interactúa. Tiene una lista (o una variable) del Delegado y, cuando le piden que haga algo, secretamente invoca al Delegado para que lo haga (ej. `PersonalUniversitario`).

---

## Organización de Carpetas (Separación de responsabilidades)

Si estuvieras armando un proyecto profesional (por ejemplo, en .NET o Java), no pondrías todo en un solo archivo. Lo organizarías así para mantener todo limpio:

* **`Interfaces/`**: Aquí guardas tus contratos.
  * `IDescripcion.cs` (Solo dice *qué* hacer, no *cómo*).
* **`Delegados/` (o `Roles/`)**: Aquí viven los especialistas. Cada uno sabe hacer su propio trabajo. Si mañana agregas el rol "Conserje", creas un archivo nuevo aquí sin tocar a los demás.
  * `Estudiante.cs`
  * `Profesor.cs`
  * `Proyecto.cs`
* **`Modelos/` (El núcleo)**: Aquí vive tu clase principal, el "Jefe".
  * `PersonalUniversitario.cs` (Sabe quiénes son sus delegados y les pasa la pelota cuando le piden información).

**¿Cuál es el punto de esta separación?**
La regla de oro del software: **Alta Cohesión y Bajo Acoplamiento**. Si el día de mañana la fórmula para calcular el sueldo del `Profesor` cambia, vas directo a `Profesor.cs`. No tienes que leer ni tocar el código de `PersonalUniversitario` ni de `Estudiante`. ¡Un cambio en un lugar no rompe el resto del sistema!

---

## ¿Cómo se implementa? (Un vistazo rápido)

Para implementarlo, solo necesitas que tu clase principal tenga una referencia a la interfaz. 

```csharp
// 1. El contrato
public interface IArma { void Atacar(); }

// 2. Los especialistas (Delegados)
public class Espada : IArma { 
    public void Atacar() { Console.WriteLine("Daño cuerpo a cuerpo!"); } 
}
public class Arco : IArma { 
    public void Atacar() { Console.WriteLine("Daño a distancia!"); } 
}

// 3. El Jefe (Delegante)
public class Jugador {
    private IArma _armaActual; // ¡Aquí guarda a su especialista!

    public Jugador(IArma armaFija) {
        _armaActual = armaFija;
    }

    public void CambiarArma(IArma nuevaArma) {
        _armaActual = nuevaArma; // Cambia el comportamiento en vivo
    }

    public void AccionAtacar() {
        // El jugador NO ataca por sí mismo, DELEGA el ataque al arma
        _armaActual.Atacar(); 
    }
}
```

## Conclusión

El patrón Delegation te enseña que **favorecer la composición sobre la herencia** es el camino hacia un código flexible. En lugar de ser un "Sabelotodo" (clases gigantes y pesadas), tu código se convierte en un excelente "Gerente", delegando tareas a componentes pequeños, fáciles de probar y totalmente reemplazables.
