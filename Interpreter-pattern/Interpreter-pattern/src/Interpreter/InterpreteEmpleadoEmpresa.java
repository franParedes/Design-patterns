package Interpreter;

// Muestra cada empresa con sus empleados
public class InterpreteEmpleadoEmpresa implements Interprete {
    @Override
    public String interpretar(Contexto contexto) {
        StringBuilder sb = new StringBuilder();
        for (String empresa : contexto.getEmpresas()) {
            sb.append("Empresa: ").append(empresa).append("\n");
            sb.append("Empleados: ")
                    .append(String.join(", ", contexto.getEmpleadosPorEmpresa(empresa)))
                    .append("\n\n");
        }
        return sb.toString();
    }
}