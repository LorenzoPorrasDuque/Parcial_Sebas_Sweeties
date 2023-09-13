// Cuando el document cargue haga lo siguiente ---> el $ es como
// Cuando declarabamos metodos dentro de javacript

$(() => {
  $("#save").on("click", async () => {
    let datos = await save();
    $("#nombre").val("");
    $("#apellido").val("");
    if (!datos) return;
    $("#modal-title").text(`Paciente`);
    $("#modal-body").html(
      `Nombre: ${datos.nombre} <br> Apellido: ${datos.apellido}`
    );
    $("#Modal").modal("show");
  });

  $(`#getById`).on("click", async () => {
    let datos = await getById();
    $("#id").val("");
    $("#modal-title").text(`Paciente Encontrado`);
    $("#modal-body").html(
      `Nombre: ${datos.nombre} <br> Apellido: ${datos.apellido}`
    );
    $("#Modal").modal("show");
  });

  $("#getAllAsc").on("click", async () => {
    let datos = await getAllAsc();

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Pacientes Encontrados`);

      let bodyText = datos
        .map((dato) => `Nombre: ${dato.nombre} <br> Apellido: ${dato.apellido}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron Pacientes.`);
      $("#Modal").modal("show");
    }
  });

  $(`#getAllDesc`).on("click", async () => {
    let datos = await getAllDesc();

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Pacientes Encontrados`);

      let bodyText = datos
        .map((dato) => `Nombre: ${dato.nombre} <br> Apellido: ${dato.apellido}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron pacientes.`);
      $("#Modal").modal("show");
    }
  });

  $(`#delete`).on("click", async () => {
    let datos = await deleteById();
    $("#modal-title").text(`Paciente Eliminado`);
    $("#Modal").modal("show");
  });

  $(`#getAll`).on("click", async () => {
    let datos = await getAll();

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Pacientes Encontrados`);

      let bodyText = datos
        .map((dato) => `Nombre: ${dato.nombre} <br> Apellido: ${dato.apellido}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron pacientes.`);
      $("#Modal").modal("show");
    }
  });

  $(`#update`).on("click", async () => {
    let datos = await update();
    $("#actualizar_ID").val("");
    $("#nombre_Update").val("");
    $("#apellido_Update").val("");
    $("#modal-title").text(`Paciente Actualizado`);
    $("#modal-body").html(
      `Nombre: ${datos.nombre} <br> Apellido: ${datos.apellido}`
    );
    $("#Modal").modal("show");
  });
});

// Es para poder capturar el envio de Backend a front
// Todas las async devuelven una promesa ---> Promise<Odontologo>

// Funcion para traer a todos

async function getAll() {
  try {
    let pacientesDTO = [];

    let datos = await fetch("http://localhost:8084/pacientes/list", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { nombre, apellido } = element;
      pacientesDTO.push({ nombre, apellido });
    });

    return pacientesDTO;
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Funcion para traer por un ID especifico

async function getById() {
  try {
    let id = $("#buscar_ID").val();
    let idNumber = parseInt(id);
    let datos = await fetch(
      `http://localhost:8084/pacientes/list/${idNumber}`,
      {
        method: `GET`,
      }
    );
    let json = await datos.json();
    return json;
  } catch (error) {
    alert("No se encuentra el Paciente Solicitado");
  }
}

// Funcion para borrar paciente

async function deleteById() {
  try {
    let id = $("#delete_ID").val(); // Añadido el signo # para seleccionar por ID

    if (!id) {
      alert("Por favor, introduzca un ID válido.");
      return;
    }

    let response = await fetch(
      `http://localhost:8084/pacientes/delete/${id}`,
      {
        method: "DELETE",
      }
    );

    if (!response.ok) {
      throw new Error("Error en la respuesta del servidor");
    }
  } catch (error) {
    alert("Error haciendo el fetch: " + error.message);
  }
}

// Funcion para guardar un paciente

async function save() {
  try {
    let nombre = $("#nombre").val();
    let apellido = $("#apellido").val();

    if (nombre.length === 0 || apellido.length === 0) {
      alert("Debe tener nombre y apellido");
      return;
    }

    let pacienteDTO = {
      nombre,
      apellido,
    };

    let datos = await fetch("http://localhost:8084/pacientes/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // Indica que estás enviando JSON
      },
      body: JSON.stringify(pacienteDTO), // Convierte el objeto a JSON
    });
    return await datos.json();
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Traer los pacientes de forma Ascendete por el nombre

async function getAllAsc() {
  try {
    let pacientesDTO = [];

    let datos = await fetch("http://localhost:8084/pacientes/listSortAsc", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { nombre, apellido } = element;
      pacientesDTO.push({ nombre, apellido });
    });

    return pacientesDTO;
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Traer los pacientes de forma descendente por el nombre

async function getAllDesc() {
  try {
    let pacientesDTO = [];

    let datos = await fetch("http://localhost:8084/pacientes/listSortDesc", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { nombre, apellido } = element;
      pacientesDTO.push({ nombre, apellido });
    });

    return pacientesDTO;
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Actualizar un paciente

async function update() {
  try {
    let id = $("#actualizar_ID").val();
    let nombre = $("#nombre_Update").val();
    let apellido = $("#apellido_Update").val();

    let pacienteDTO = {
      // Corrección aquí
      nombre,
      apellido,
    };

    let response = await fetch(
      `http://localhost:8084/pacientes/update/${id}`,
      {
        // Usar la misma variable
        method: `PUT`,
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(pacienteDTO),
      }
    );

    if (!response.ok) {
      // Usar la misma variable
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await response.json();
    return json;
  } catch (error) {
    alert("Error haciendo el fetch: " + error.message); // Mostrar el mensaje del error
  }
}
