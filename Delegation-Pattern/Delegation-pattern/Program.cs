// See https://aka.ms/new-console-template for more information
using Delegation_pattern.Delegados;
using Delegation_pattern.Modelos;

Console.WriteLine("============ PATRON DELEGATION ============");

var persona = new PersonalUniversitario("001-123456-0000A", "Juan Pérez", "León, Nicaragua");

// Le asignamos los roles originales del ejercicio
persona.SetRol(new Estudiante("UNAN-León", "Ingeniería de Software"));
persona.SetRol(new Profesor("Computación", 1500.50));

// AGREGAMOS EL NUEVO ROL DE PROYECTO (Resolución de la tarea)
persona.SetRol(new Proyecto("Sistema de Gestión Web", 120));

// Imprimimos el resultado
Console.WriteLine("=== DATOS DEL PERSONAL ===");
Console.WriteLine(persona.ObtenerDescripcion());
Console.ReadLine();