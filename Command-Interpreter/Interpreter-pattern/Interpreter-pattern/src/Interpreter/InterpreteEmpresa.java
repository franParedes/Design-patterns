package Interpreter;

// Muestra las empresas
public class InterpreteEmpresa implements Interprete {
    @Override
    public String interpretar(Contexto contexto) {
        return "Empresas: " + String.join(", ", contexto.getEmpresas()) + "\n";
    }
}