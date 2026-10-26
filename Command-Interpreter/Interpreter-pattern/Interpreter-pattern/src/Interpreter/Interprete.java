package Interpreter;

/*
 * Interfaz Interprete. Define un método interpretar que cada intérprete
 * implementa de un modo distinto. Devuelve el resultado como texto
 * para poder mostrarlo en la interfaz gráfica.
 */
public interface Interprete {
    String interpretar(Contexto contexto);
}