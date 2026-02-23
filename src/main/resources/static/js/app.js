console.log("App.js cargado correctamente");

const API = "/api";

document.addEventListener("DOMContentLoaded", () => {
  loadHome();
  loadCadis();
});

function loadHome() {
  document.getElementById("mainContent").innerHTML = `
        <h1>Inicio</h1>
        <p>Bienvenido al repositorio académico.</p>
    `;
}

/* ===============================
   CARGAR CADIS (MATERIAS)
================================ */
async function loadCadis() {
  const res = await fetch(`${API}/cadis`);
  const cadis = await res.json();

  const menu = document.getElementById("menu");

  // 🔥 Solo borrar elementos dinámicos
  document.querySelectorAll(".cadi-item, .area-item")
    .forEach(e => e.remove());

  cadis.forEach((cadi) => {
    const li = document.createElement("li");
    li.classList.add("cadi-item");
    li.textContent = "📚 " + cadi.nombre;
    li.onclick = () => loadReas(cadi.id, li);
    menu.appendChild(li);
  });
}

/* ===============================
   CARGAR REAS POR CADI
================================ */
async function loadReas(cadiId, element) {

  document.querySelectorAll(".area-item").forEach(e => e.remove());

  const res = await fetch(`${API}/reas`);
  const reas = await res.json();

  const filtradas = reas.filter((r) => r.cadi.id === cadiId);

  filtradas.forEach((rea) => {
    const sub = document.createElement("li");
    sub.textContent = "📂 " + rea.nombre;
    sub.classList.add("area-item");
    sub.onclick = () => loadActividades(rea.id);
    element.after(sub);
  });
}

/* ===============================
   CARGAR ACTIVIDADES POR REA
================================ */
async function loadActividades(reaId) {
  const res = await fetch(`${API}/actividades`);
  const actividades = await res.json();

  const filtradas = actividades.filter((a) => a.rea.id === reaId);

  const main = document.getElementById("mainContent");
  main.innerHTML = `
        <h2>Actividades</h2>
        <button onclick="crearActividad(${reaId})">➕ Nueva Actividad</button>
        <div class="card-container"></div>
    `;

  const container = document.querySelector(".card-container");

  filtradas.forEach((act) => {
    const card = document.createElement("div");
    card.classList.add("card");

    let botonVer = "";

    if (act.archivo) {
      botonVer = `
            <button class="btn-view" onclick="verArchivo('${act.archivo}')">
                Ver Archivo
            </button>
            <button class="btn-delete" onclick="eliminarArchivo(${act.id})">
                Eliminar Archivo
            </button>
        `;
    }

    card.innerHTML = `
        <h3>${act.titulo}</h3>
        <p>${act.descripcion || ""}</p>

        <button class="btn-upload" onclick="subirArchivo(${act.id})">
            Subir Archivo
        </button>

        ${botonVer}

        <button class="btn-delete" onclick="eliminarActividad(${act.id})">
            Eliminar Actividad
        </button>
    `;

    container.appendChild(card);
  });
}

/* ===============================
   CREAR ACTIVIDAD
================================ */
async function crearActividad(reaId) {
  const titulo = prompt("Título:");
  const descripcion = prompt("Descripción:");

  await fetch(`${API}/actividades?reaId=${reaId}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ titulo, descripcion }),
  });

  alert("Actividad creada");
  loadActividades(reaId);
}

/* ===============================
   ELIMINAR ACTIVIDAD
================================ */
async function eliminarActividad(id) {
  await fetch(`${API}/actividades/${id}`, {
    method: "DELETE",
  });

  alert("Actividad eliminada");
  location.reload();
}

/* ===============================
   SUBIR ARCHIVO
================================ */
function subirArchivo(id) {
  const input = document.createElement("input");
  input.type = "file";

  input.onchange = async () => {
    const file = input.files[0];
    const formData = new FormData();
    formData.append("file", file);

    await fetch(`${API}/actividades/${id}/archivo`, {
      method: "POST",
      body: formData,
    });

    alert("Archivo subido");
    location.reload();
  };

  input.click();
}

/* ===============================
   VER ARCHIVO
================================ */
function verArchivo(ruta) {
  window.open(`/uploads/${ruta}`, "_blank");
}

/* ===============================
   ELIMINAR ARCHIVO
================================ */
async function eliminarArchivo(id) {
  await fetch(`${API}/actividades/${id}/archivo`, {
    method: "DELETE",
  });

  alert("Archivo eliminado");
  location.reload();
}