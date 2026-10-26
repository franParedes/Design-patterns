package Interpreter;

// Muestra cada empleado con las empresas en las que ha trabajado
public class InterpreteEmpresaEmpleado implements Interprete {
    @Override
    public String interpretar(Contexto contexto) {
        StringBuilder sb = new StringBuilder();
        for (String empleado : contexto.getEmpleados()) {
            sb.append("Empleado: ").append(empleado).append("\n");
            sb.append("Empresas: ")
                    .append(String.join(", ", contexto.getEmpresasPorEmpleado(empleado)))
                    .append("\n\n");
        }
        return sb.toString();
    }
}