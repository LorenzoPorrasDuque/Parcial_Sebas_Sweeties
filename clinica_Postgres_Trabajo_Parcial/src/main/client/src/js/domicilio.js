// Cuando el document cargue haga lo siguiente ---> el $ es como
// Cuando declarabamos metodos dentro de javacript

$(() => {
  $("#save").on("click", async () => {
    let datos = await save();
    $("#calle").val("");
    $("#localidad").val("");
    $("#provincia").val("");
    if (!datos) return;
    $("#modal-title").text(`Domicilio`);
    $("#modal-body").html(
      `Calle: ${datos.calle} <br> Localidad: ${datos.localidad} <br> Provincia: ${datos.provincia}`
    );
    $("#Modal").modal("show");
  });

  $(`#getById`).on("click", async () => {
    let datos = await getById();
    $("#id").val("");
    $("#modal-title").text(`Domicilio Encontrado`);
    $("#modal-body").html(
      `Calle: ${datos.calle} <br> Localidad: ${datos.localidad} <br> Provincia: ${datos.provincia}`
    );
    $("#Modal").modal("show");
  });

  $("#getAllAsc").on("click", async () => {
    let datos = await getAllAsc();

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Domicilios Encontrados`);

      let bodyText = datos
        .map((dato) => `Calle: ${dato.calle} <br> Localidad: ${dato.localidad} <br> Provincia: ${dato.provincia}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron Domicilios.`);
      $("#Modal").modal("show");
    }
  });

  $(`#getAllDesc`).on("click", async () => {
    let datos = await getAllDesc()

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Domicilios Encontrados`);

      let bodyText = datos
        .map((dato) => `Calle: ${dato.calle} <br> Localidad: ${dato.localidad} <br> Provincia: ${dato.provincia}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron Domicilios.`);
      $("#Modal").modal("show");
    }
  });

  $(`#delete`).on("click", async () => {
    let datos = await deleteById();
    $("#modal-title").text(`Domicilio Eliminado`);
    $("#Modal").modal("show");
  });

  $(`#getAll`).on("click", async () => {
    let datos = await getAllDesc()

    if (datos && datos.length > 0) {
      $("#modal-title").text(`Domicilios Encontrados`);

      let bodyText = datos
        .map((dato) => `Calle: ${dato.calle} <br> Localidad: ${dato.localidad} <br> Provincia: ${dato.provincia}`)
        .join("<br><br>");
      $("#modal-body").html(bodyText); // Usa .html en lugar de .text

      $("#Modal").modal("show");
    } else {
      $("#modal-title").text(`Sin resultados`);
      $("#modal-body").text(`No se encontraron Domicilios.`);
      $("#Modal").modal("show");
    }
  });

  $(`#update`).on("click", async () => {
    let datos = await update();
    $("#calle_Update").val("");
    $("#localidad_Update").val("");
    $("#provincia_Update").val("");
    $("#modal-title").text(`Domicilio Actualizado`);
    $("#modal-body").html(
      `Calle: ${datos.calle} <br> Localidad: ${datos.localidad} <br> Provincia: ${datos.provincia}`
    );
    $("#Modal").modal("show");
  });
});

// Es para poder capturar el envio de Backend a front
// Todas las async devuelven una promesa ---> Promise<Odontologo>

// Funcion para traer a todos

async function getAll() {
  try {
    let domiciliosDTO = [];

    let datos = await fetch("http://localhost:8084/domicilios/list", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { calle, localidad, provincia } = element;
      domiciliosDTO.push({ calle, localidad, provincia });
    });

    return domiciliosDTO;
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
      `http://localhost:8084/domicilios/list/${idNumber}`,
      {
        method: `GET`,
      }
    );
    let json = await datos.json();
    return json;
  } catch (error) {
    alert("No se encuentra el Domicilio Solicitado");
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
      `http://localhost:8084/domicilios/delete/${id}`,
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
    let calle = $("#calle").val();
    let localidad = $("#localidad").val();
    let provincia = $('#provincia').val();

    if (calle.length === 0 || localidad.length === 0 || provincia.length === 0) {
      alert("Debe tener calle, localidad y provincia");
      return;
    }

    let domicilioDTO = {
      calle,
      localidad,
      provincia
    };

    let datos = await fetch("http://localhost:8084/domicilios/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // Indica que estás enviando JSON
      },
      body: JSON.stringify(domicilioDTO), // Convierte el objeto a JSON
    });
    return await datos.json();
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Traer los odotologos de forma Ascendete por el nombre

async function getAllAsc() {
  try {
    let domiciliosDTO = [];

    let datos = await fetch("http://localhost:8084/domicilios/listSortAsc", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { calle, localidad, provincia } = element;
      domiciliosDTO.push({ calle, localidad, provincia });
    });

    return domiciliosDTO;
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Traer los odontologos de forma descendente por el nombre

async function getAllDesc() {
  try {
    let domiciliosDTO = [];

    let datos = await fetch("http://localhost:8084/domicilios/listSortDesc", {
      method: "GET",
    });

    if (!datos.ok) {
      throw new Error("Error en la respuesta del servidor");
    }

    let json = await datos.json();

    json.forEach((element) => {
      const { calle, localidad, provincia } = element;
      domiciliosDTO.push({ calle, localidad, provincia });
    });

    return domiciliosDTO;
  } catch (error) {
    alert("Error haciendo el fetch");
  }
}

// Actualizar un odontologo

async function update() {
  try {
    let id = $("#id_Update").val();
    let calle = $("#calle_Update").val();
    let localidad = $("#localidad_Update").val();
    let provincia = $("#provincia_Update").val();

    let odontologoDTO = {
      calle,
      localidad,
      provincia
    };

    let response = await fetch(
      `http://localhost:8084/domicilios/update/${id}`,
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
