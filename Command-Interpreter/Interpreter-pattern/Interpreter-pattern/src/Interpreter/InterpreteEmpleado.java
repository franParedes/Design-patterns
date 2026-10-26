package Interpreter;

// Muestra los empleados
public class InterpreteEmpleado implements Interprete {
    @Override
    public String interpretar(Contexto contexto) {
        return "Empleados: " + String.join(", ", contexto.getEmpleados()) + "\n";
    }
}