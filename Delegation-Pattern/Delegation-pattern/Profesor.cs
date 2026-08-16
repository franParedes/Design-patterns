using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Delegation_pattern
{
    public class Profesor : IDescription
    {
        public string Departamento { get; set; }
        public double Sueldo { get; set; }

        public Profesor(string departamento, double sueldo)
        {
            Departamento = departamento;
            Sueldo = sueldo;
        }

        public string ObtenerDescripcion()
        {
            return $"{Departamento} = {Sueldo}";
        }
    }
}
