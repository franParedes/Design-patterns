namespace Delegation_pattern
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
