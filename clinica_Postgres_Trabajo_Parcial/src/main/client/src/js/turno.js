// Cuando el document cargue haga lo siguiente ---> el $ es como
// Cuando declarabamos metodos dentro de javacript

$(() => {

  $("#save").on("click", async () => {
    
      let datos = await save();
      if (!datos) return;

      let fechaHora = $("#fechaHora").val();  // Captura el valor antes de vaciarlo
      $("#id_Paciente").val("");
      $("#id_Odontologo").val("");
      $("#fechaHora").val("");

      $("#modal-title").text(`Turno Guardado`);
      $("#modal-body").html(`Fecha y Hora del Turno: ${fechaHora}`);
      $("#Modal").modal("show");
   
  });

  $(`#getById`).on("click", async () => {
    let datos = await getById();
    $("#id").val("");
    $("#modal-title").text(`Turno Encontrado`);
    $("#modal-body").html(
      `Fecha: ${datos.fechaHora}`
    );
    $("#Modal").modal("show");
  });

  $("#getAllAsc").on("click", async () => {

    let datos = await getAllAsc();

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Turnos Encontrados`);

      let bodyText = datos
        .map((dato) => `Fecha: ${dato.fechaHora}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron turnos.`);
      $("#Modal").modal("show");
    }
  });

  $(`#getAllDesc`).on("click", async () => {
    let datos = await getAllDesc();

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Turnos Encontrados`);

      let bodyText = datos
        .map((dato) => `Fecha: ${dato.fechaHora}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron turnos.`);
      $("#Modal").modal("show");
    }
  });

  $(`#delete`).on("click", async () => {
    let datos = await deleteById();
    $("#modal-title").text(`Turno Eliminado`);
    $("#Modal").modal("show");
  });

  $(`#getAll`).on("click", async () => {
    let datos = await getAll();

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Turnos Encontrados`);

      let bodyText = datos
        .map((dato) => `Fecha: ${dato.fechaHora} <br>`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron turnos.`);
      $("#Modal").modal("show");
    }
  });

  $(`#update`).on("click", async () => {
    let datos = await update();
    $("#actualizar_ID").val("");
    $("#modal-title").text(`Turno Actualizado`);
    $("#modal-body").text(
      `Fecha: ${datos.fechaHora}`
    );
    $("#Modal").modal("show");
  });
});

// Es para poder capturar el envio de Backend a front
// Todas las async devuelven una promesa ---> Promise<Odontologo>

// Funcion para traer a todos

async function getAll() {
  try {
    let turnosDTO = [];

    let datos = await fetch("http://localhost:8084/turnos/list", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { fechaHora } = element;
      turnosDTO.push({ fechaHora });
    });

    return turnosDTO;
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
      `http://localhost:8084/turnos/list/${idNumber}`,
      {
        method: `GET`,
      }
    );
    let json = await datos.json();
    return json;
  } catch (error) {
    alert("No se encuentra el Turno Solicitado");
  }
}

// Funcion para borrar turno

async function deleteById() {
  try {
    let id = $("#delete_ID").val(); // Añadido el signo # para seleccionar por ID

    if (!id) {
      alert("Por favor, introduzca un ID válido.");
      return;
    }

    let response = await fetch(
      `http://localhost:8084/turnos/delete/${id}`,
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

// Funcion para guardar un turno

async function save() {
  try {
    let id_Paciente = $("#id_Paciente").val();
    let id_Odontologo = $("#id_Odontologo").val();
    let fechaHora = $("#fechaHora").val();  // Asume que hay un campo de entrada para la fecha y hora

    if (id_Paciente.length === 0 || id_Odontologo.length === 0 || fechaHora.length === 0) {
      alert("Todos los campos son necesarios");
      return;
    }

    let turnoDTO = {
      fechaHora
    };

    let datos = await fetch(`http://localhost:8084/turnos/add/${id_Odontologo}/${id_Paciente}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(turnoDTO),
    });

    if (!datos.ok) {
      const errorData = await datos.json();
      throw new Error(`Error en la respuesta del servidor: ${datos.status} ${datos.statusText} ${JSON.stringify(errorData)}`);
    }
    

    return await datos.json();
  } catch (error) {
    alert("Error haciendo el fetch");
    console.error(error);
  }
}


// Traer los turnos de forma Ascendete por el nombre

async function getAllAsc() {
  try {
    let turnosDTO = [];

    let datos = await fetch("http://localhost:8084/turnos/listSortAsc", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { fechaHora } = element;
      turnosDTO.push({ fechaHora });  // Aquí está la corrección
    });

    return turnosDTO;
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}


// Traer los odontologos de forma turnos por la fecha

async function getAllDesc() {
  try {
    let turnosDTO = [];

    let datos = await fetch("http://localhost:8084/turnos/listSortDesc", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { fechaHora } = element;
      turnosDTO.push({ fechaHora });  // Aquí está la corrección
    });

    return turnosDTO;
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Actualizar un turno

async function update() {
  try {
    let id = $("#actualizar_ID").val();
    let fecha = $("#fecha_ID").val();
  
    let turnoDTO = {
      // Corrección aquí
      fechaHora:fecha
    };

    let response = await fetch(
      `http://localhost:8084/turnos/update/${id}`,
      {
        // Usar la misma variable
        method: `PUT`,
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(turnoDTO),
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
