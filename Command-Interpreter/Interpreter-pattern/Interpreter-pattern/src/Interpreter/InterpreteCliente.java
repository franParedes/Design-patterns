package Interpreter;

import java.util.StringTokenizer;

public class InterpreteCliente {
    private final Contexto contexto;

    public InterpreteCliente(Contexto contexto) {
        this.contexto = contexto;
    }

    // Se interpreta la expresión introducida por el usuario y se devuelve el resultado
    public String interpreta(String expresion) {
        StringTokenizer tokens = new StringTokenizer(expresion.toLowerCase());
        char mostrar = ' ';      // qué hay que mostrar: 'E' empresas, 'T' empleados (trabajadores)
        char mostrarPor = ' ';   // por qué cosa hay que agruparlo
        boolean usaPor = false;  // ¿se usó la palabra "por"?

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (token.equals("muestra")) {
                continue;
            } else if (token.equals("empresas")) {
                if (mostrar == ' ') mostrar = 'E';
                else if (usaPor && mostrarPor == ' ') mostrarPor = 'E';
            } else if (token.equals("empleados")) {
                if (mostrar == ' ') mostrar = 'T';
                else if (usaPor && mostrarPor == ' ') mostrarPor = 'T';
            } else if (token.equals("por")) {
                usaPor = true;
            }
        }

        Interprete interprete = null;
        if (mostrar == 'T') {
            interprete = (mostrarPor == 'E') ? new InterpreteEmpleadoEmpresa() : new InterpreteEmpleado();
        } else if (mostrar == 'E') {
            interprete = (mostrarPor == 'T') ? new InterpreteEmpresaEmpleado() : new InterpreteEmpresa();
        }

        if (interprete == null) {
            return "Expresión no válida.\nEjemplos:\n  muestra empleados\n  muestra empresas\n"
                    + "  muestra empleados por empresas\n  muestra empresas por empleados\n";
        }
        return interprete.interpretar(contexto);
    }
}