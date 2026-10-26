package Interpreter;

// Relaciona un empleado con una empresa
public class EmpresayEmpleado {
    private final String empresa;
    private final String empleado;

    public EmpresayEmpleado(String empresa, String empleado) {
        this.empresa = empresa;
        this.empleado = empleado;
    }

    public String getEmpresa() { return this.empresa; }
    public String getEmpleado() { return this.empleado; }
}