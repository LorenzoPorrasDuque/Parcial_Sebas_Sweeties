// Cuando el document cargue haga lo siguiente ---> el $ es como
// Cuando declarabamos metodos dentro de javacript

$(() => {
  $("#save").on("click", async () => {
    let datos = await save();
    $("#nombre").val("");
    $("#apellido").val("");
    if (!datos) return;
    $("#modal-title").text(`Odontologo`);
    $("#modal-body").html(
      `Nombre: ${datos.nombre} <br> Apellido: ${datos.apellido}`
    );
    $("#Modal").modal("show");
  });

  $(`#getById`).on("click", async () => {
    let datos = await getById();
    $("#id").val("");
    $("#modal-title").text(`Odontologo Encontrado`);
    $("#modal-body").html(
      `Nombre: ${datos.nombre} <br> Apellido: ${datos.apellido}`
    );
    $("#Modal").modal("show");
  });

  $("#getAllAsc").on("click", async () => {
    let datos = await getAllAsc();

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Odontologos Encontrados`);

      let bodyText = datos
        .map((dato) => `Nombre: ${dato.nombre} <br> Apellido: ${dato.apellido}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron odontólogos.`);
      $("#Modal").modal("show");
    }
  });

  $(`#getAllDesc`).on("click", async () => {
    let datos = await getAllDesc();

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Odontologos Encontrados`);

      let bodyText = datos
        .map((dato) => `Nombre: ${dato.nombre} <br> Apellido: ${dato.apellido}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron odontólogos.`);
      $("#Modal").modal("show");
    }
  });

  $(`#delete`).on("click", async () => {
    let datos = await deleteById();
    $("#modal-title").text(`Odontologo Eliminado`);
    $("#Modal").modal("show");
  });

  $(`#getAll`).on("click", async () => {
    let datos = await getAll();

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Odontologos Encontrados`);

      let bodyText = datos
        .map((dato) => `Nombre: ${dato.nombre} <br> Apellido: ${dato.apellido}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron odontólogos.`);
      $("#Modal").modal("show");
    }
  });

  $(`#update`).on("click", async () => {
    let datos = await update();
    $("#actualizar_ID").val("");
    $("#nombre_Update").val("");
    $("#apellido_Update").val("");
    $("#modal-title").text(`Odontologo Actualizado`);
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
    let odontologosDTO = [];

    let datos = await fetch("http://localhost:8084/odontologos/list", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { nombre, apellido } = element;
      odontologosDTO.push({ nombre, apellido });
    });

    return odontologosDTO;
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
      `http://localhost:8084/odontologos/list/${idNumber}`,
      {
        method: `GET`,
      }
    );
    let json = await datos.json();
    return json;
  } catch (error) {
    alert("No se encuentra el Odontologo Solicitado");
  }
}

// Funcion para borrar odontologo

async function deleteById() {
  try {
    let id = $("#delete_ID").val(); // Añadido el signo # para seleccionar por ID

    if (!id) {
      alert("Por favor, introduzca un ID válido.");
      return;
    }

    let response = await fetch(
      `http://localhost:8084/odontologos/delete/${id}`,
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

// Funcion para guardar un odontologo

async function save() {
  try {
    let nombre = $("#nombre").val();
    let apellido = $("#apellido").val();

    if (nombre.length === 0 || apellido.length === 0) {
      alert("Debe tener nombre y apellido");
      return;
    }

    let odontologoDTO = {
      nombre,
      apellido,
    };

    let datos = await fetch("http://localhost:8084/odontologos/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // Indica que estás enviando JSON
      },
      body: JSON.stringify(odontologoDTO), // Convierte el objeto a JSON
    });
    return await datos.json();
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Traer los odotologos de forma Ascendete por el nombre

async function getAllAsc() {
  try {
    let odontologosDTO = [];

    let datos = await fetch("http://localhost:8084/odontologos/listSortAsc", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { nombre, apellido } = element;
      odontologosDTO.push({ nombre, apellido });
    });

    return odontologosDTO;
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Traer los odontologos de forma descendente por el nombre

async function getAllDesc() {
  try {
    let odontologosDTO = [];

    let datos = await fetch("http://localhost:8084/odontologos/listSortDesc", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { nombre, apellido } = element;
      odontologosDTO.push({ nombre, apellido });
    });

    return odontologosDTO;
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Actualizar un odontologo

async function update() {
  try {
    let id = $("#actualizar_ID").val();
    let nombre = $("#nombre_Update").val();
    let apellido = $("#apellido_Update").val();

    let odontologoDTO = {
      // Corrección aquí
      nombre,
      apellido,
    };

    let response = await fetch(
      `http://localhost:8084/odontologos/update/${id}`,
      {
        // Usar la misma variable
        method: `PUT`,
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(odontologoDTO),
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
