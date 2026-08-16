using Delegation_pattern.Interfaces;

namespace Delegation_pattern.Delegados
{
    public class Estudiante : IDescription
    {
        public string Universidad {  get; set; }
        public string Titulacion {  get; set; }
        public Estudiante(string universidad, string titulacion)
        {
            Universidad = universidad;
            Titulacion = titulacion;
        }

        public string ObtenerDescripcion()
        {
            return $"{Universidad} = {Titulacion}";
        }
    }
}
