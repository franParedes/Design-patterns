using System.Text;

namespace Delegation_pattern
{
    public class PersonalUniversitario
    {
        public string Dni { get; set; }
        public string Nombre { get; set; }
        public string Direccion { get; set; }

        // En C# usamos List genérico en lugar de ArrayList
        private List<IDescription> roles;

        public PersonalUniversitario(string dni, string nombre, string direccion)
        {
            Dni = dni;
            Nombre = nombre;
            Direccion = direccion;
            roles = new List<IDescription>();
        }

        public void SetRol(IDescription rol)
        {
            roles.Add(rol);
        }

        public void BorraRol(IDescription rol)
        {
            roles.Remove(rol);
        }

        public string ObtenerDescripcion()
        {
            // Usamos StringBuilder para un mejor rendimiento al concatenar texto
            StringBuilder desc = new StringBuilder();
            desc.AppendLine($"{Dni} = {Nombre} = {Direccion}");

            foreach (var rol in roles)
            {
                // Aquí ocurre la delegación: PersonalUniversitario no sabe CÓMO 
                // se describen los roles, simplemente delega la tarea llamando al método
                desc.AppendLine(" - " + rol.ObtenerDescripcion());
            }

            return desc.ToString();
        }
    }
}