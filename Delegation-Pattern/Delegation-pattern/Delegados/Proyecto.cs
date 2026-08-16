using Delegation_pattern.Interfaces;

namespace Delegation_pattern.Delegados
{
    public class Proyecto : IDescription
    {
        public string NombreProyecto { get; set; }
        public int HorasAsignadas { get; set; }

        public Proyecto(string nombreProyecto, int horasAsignadas)
        {
            NombreProyecto = nombreProyecto;
            HorasAsignadas = horasAsignadas;
        }

        public string ObtenerDescripcion()
        {
            return $"Proyecto: {NombreProyecto} = {HorasAsignadas} horas asignadas";
        }
    }
}
