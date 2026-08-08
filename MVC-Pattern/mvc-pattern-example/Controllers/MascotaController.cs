using Microsoft.AspNetCore.Mvc;
using mvc_pattern_example.Models;

namespace mvc_pattern_example.Controllers
{
    public class MascotaController : Controller
    {
        // GET: /Mascota/Index
        public IActionResult Index()
        {
            var listaMascotas = new List<Mascota>
            {
                new Mascota { Id = 1, Name = "Firulais", Especies = "Perro" },
                new Mascota { Id = 2, Name = "Michi", Especies = "Gato" }
            };

            // El controlador le entrega el modelo (los datos) a la Vista
            return View(listaMascotas);
        }
    }
}
