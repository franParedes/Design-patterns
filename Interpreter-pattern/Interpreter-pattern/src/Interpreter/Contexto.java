package Interpreter;

import java.util.ArrayList;
import java.util.List;

// Almacena los empleados, empresas y sus relaciones (se carga desde la BD)
public class Contexto {
    private final List<String> empresas = new ArrayList<>();
    private final List<String> empleados = new ArrayList<>();
    private final List<EmpresayEmpleado> empresasyempleados = new ArrayList<>();

    public void anadirEmpresa(String empresa) { empresas.add(empresa); }
    public void anadirEmpleado(String empleado) { empleados.add(empleado); }
    public void anadirEmpresayEmpleado(EmpresayEmpleado ee) { empresasyempleados.add(ee); }

    public List<String> getEmpresas() { return empresas; }
    public List<String> getEmpleados() { return empleados; }

    // empleados que trabajan en la empresa indicada
    public List<String> getEmpleadosPorEmpresa(String empresaEn) {
        List<String> resultado = new ArrayList<>();
        for (EmpresayEmpleado ee : empresasyempleados) {
            if (empresaEn.equals(ee.getEmpresa())) resultado.add(ee.getEmpleado());
        }
        return resultado;
    }

    // empresas en las que ha trabajado el empleado indicado
    public List<String> getEmpresasPorEmpleado(String empleadoEn) {
        List<String> resultado = new ArrayList<>();
        for (EmpresayEmpleado ee : empresasyempleados) {
            if (empleadoEn.equals(ee.getEmpleado())) resultado.add(ee.getEmpresa());
        }
        return resultado;
    }
}