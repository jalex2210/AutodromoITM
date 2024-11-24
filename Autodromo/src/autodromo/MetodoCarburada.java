package autodromo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;

public class MetodoCarburada {
    private Pila<clsCarburada> pilaCompetidor;
    private Pila<clsCarburada> AuxCompetidor;
    private String cadena;
    private int contCompetidor;
    private static final String Carburada = "Carburada";

public MetodoCarburada() {
        pilaCompetidor = new Pila();
        AuxCompetidor = new Pila();
        cadena = "";
        contCompetidor = 0;
        
    }
    
public static void menuCRUDCarburada() {
    int opcion;
    MetodoCarburada objP = new MetodoCarburada();

    do {
        String[] opciones = {"Registrar Competidor", "Modificar Competidor", "Eliminar Competidor", "Buscar Competidor", "Imprimir Competidor","Ganador", "Volver"};
        opcion = JOptionPane.showOptionDialog(
            null,
            "Bienvenido\n¿Qué deseas realizar?",
            "Menú de Cuatrimotos Carburadas",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        switch (opcion) {
            case 0:
                JOptionPane.showMessageDialog(null, "Has seleccionado Registrar un competidor.");
                JOptionPane.showMessageDialog(null, objP.RegistrarCuatri());
                break;
            case 1:
                String cedulaCompetidor = JOptionPane.showInputDialog("Introduce la cédula del competidor a modificar:");
                if (objP.existeCedula(cedulaCompetidor)) {
                    int opcionModificar = menuSeleccionarOpcionModificar();
                    String resultado = objP.ModificarCuatri(cedulaCompetidor, opcionModificar);
                    JOptionPane.showMessageDialog(null, resultado);
                } else {
                    JOptionPane.showMessageDialog(null, "La cédula del competidor no existe.");
                }
                break;
            case 2:
                String IDCompetidor = JOptionPane.showInputDialog("Ingrese la cédula del competidor a eliminar:");
                String resultado = objP.EliminarCuatri(IDCompetidor);
                JOptionPane.showMessageDialog(null, resultado);
                break;
            case 3:
                String IDCompetidorBuscar = JOptionPane.showInputDialog("Ingrese la cédula del competidor que desea buscar:");
                String resultadoBuscar = objP.BuscarCuatri(IDCompetidorBuscar);
                JOptionPane.showMessageDialog(null, resultadoBuscar);
                break;
            case 4:
                String resultadoImprimir = objP.ImprimirCuatri();
                JOptionPane.showMessageDialog(null, resultadoImprimir);
                break;
            case 5: 
                objP.Ganador();
                break;
            case 6:
                JOptionPane.showMessageDialog(null, "GRACIAS POR COMPETIR EN CUATRIMOTO CARBURADA\n");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
                break;
        }
    } while (opcion != 6);
}

public String RegistrarCuatri() {
    int contCompetidor = 0;
    Random random = new Random();
    
    while (JOptionPane.showConfirmDialog(null, "¿Desea agregar un competidor?") == JOptionPane.YES_OPTION) {
        String nuevaCedula = Validar_Cedula();
        String nuevoIDMoto = Validar_IDMoto();
        
        if (!existeCedula(nuevaCedula)&&(!existeID(nuevoIDMoto))) {

            int tiempoAleatorio = random.nextInt(31) + 50;

            String nombre = Validar_Nombre();
            String apellido = Validar_Apellido();
            int fechaNacimiento = Validar_Fecha();
            String nuevoRh = Validar_RH();
            int nuevoTiempo = tiempoAleatorio;
            String nuevaCarburada = Carburada;
            String nuevoFabricante = JOptionPane.showInputDialog("Ingrese el fabricante del competidor");
            
            // Mostrar el número de competidor que se está agregando
            JOptionPane.showMessageDialog(null, "Competidor " + (contCompetidor + 1) + " agregado con éxito");

            // Crear el objeto Cuatri con los datos de la persona actual y apilarlo
            pilaCompetidor.apilar(new clsCarburada(
                nuevaCedula,
                nombre,
                apellido,
                fechaNacimiento,
                nuevoRh,
                nuevoIDMoto,
                nuevoTiempo,
                nuevaCarburada,
                nuevoFabricante
            ));
            contCompetidor++;
        } else {
            JOptionPane.showMessageDialog(null, "El competidor ya existe en esta modalidad. Intente nuevamente.");
        }
    }

    cadena = "Se agregaron " + contCompetidor + " competidores";
    return cadena;
}
    
public String EliminarCuatri(String IDCompetidor) {
    boolean banderita = false;
    cadena = "";
    while (!pilaCompetidor.estaVacia()) {
        clsCarburada objCuatris;
        objCuatris = pilaCompetidor.getElemento();

        if (objCuatris.getCedula().equalsIgnoreCase(IDCompetidor)) {
            banderita = true;
            contCompetidor--;
            pilaCompetidor.desapilar();
        } else {
            AuxCompetidor.apilar(pilaCompetidor.getElemento());
            pilaCompetidor.desapilar();
        }
    }

    if (banderita)
        cadena = "El registro del competidor fue encontrado y eliminado";
    else
        cadena = "La cedula del competidor no fue encontrada";

    retPilaCompetidor(AuxCompetidor);
    return cadena;
}

public String ModificarCuatri(String IDCompetidor, int opc) {
    boolean banderita = false;
    String datoen = "";
    cadena = "";

    while (!pilaCompetidor.estaVacia()) {
        clsCarburada objCuatris;
        objCuatris = pilaCompetidor.getElemento();

        if (objCuatris.getCedula().equalsIgnoreCase(IDCompetidor)) {
            banderita = true;
            switch (opc) {
                case 1:
                    objCuatris.setNombre(Validar_Nombre());
                    cadena = "Nombre modificado con éxito";
                    break;
                case 2:
                    objCuatris.setApellido(Validar_Apellido());
                    cadena = "Apellido modificado con éxito";
                    break;
                case 3:
                    objCuatris.setFechaNacimiento(Validar_Fecha());
                    cadena = "Fecha de Nacimiento modificada con éxito";
                    break;
                case 4:
                    objCuatris.setRh(Validar_RH());
                    cadena = "RH modificado con éxito";
                    break;
                case 5:
                    objCuatris.setIDMoto(Validar_IDMoto());
                    cadena = "ID de la cuatrimoto modificado con éxito";
                    break;
                case 6:
                    objCuatris.setTiempo(Validar_Tiempo());
                    cadena = "Tiempo modificado con éxito";
                    break;
                case 7:
                    objCuatris.setCarburada(Validar_Carburada());
                    cadena = "Tipo de motor modificado con éxito";
                    break;
                case 8:
                    objCuatris.setFabricante(JOptionPane.showInputDialog("Cual es el nuevo fabricante de la cuatrimoto"));
                    cadena = "Fabricante de la cuatrimoto modificado con éxito";
                    break;
                default:
                    cadena = "Saliendo de modificar";
                    break;  // Agregar un break aquí para terminar el switch
            }
        }

        AuxCompetidor.apilar(new clsCarburada(
            objCuatris.getCedula(),
            objCuatris.getNombre(),
            objCuatris.getApellido(),
            objCuatris.getFechaNacimiento(),
            objCuatris.getRh(),
            objCuatris.getIDMoto(),
            objCuatris.getTiempo(),
            objCuatris.getCarburada(),
            objCuatris.getFabricante()
        ));

        pilaCompetidor.desapilar();
    }

    if (banderita) {
        datoen = "Competidor Modificado!!!\n\n";
    } else {
        // El ID del Cuatri no fue encontrado, ofrecer la opción de volver a intentar.
        int respuesta = JOptionPane.showConfirmDialog(null, "El ID del Competidor no fue encontrado. ¿Desea intentar de nuevo?", "ID no encontrado", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            return "Volver a intentar la modificación del Competidor.";
        }
        cadena = "El ID del Competidor no fue encontrado.";
    }

    retPilaCompetidor(AuxCompetidor);
    return datoen + cadena;
}

public static int menuSeleccionarOpcionModificar() {
    String[] opciones = {
        "1. Modificar Nombre del competidor",
        "2. Modificar Apellido del competidor",
        "3. Modificar Año de nacimiento del Competidor",
        "4. Modificar RH del Competidor",
        "5. Modificar ID de la cuatrimoto",
        "6. Modificar Tiempo del Competidor",
        "7. Modificar Tipo de Motor",
        "8. Modificar Fabricante",
        "0. Atras"
    };
    int seleccion;

    do {
        String input = JOptionPane.showInputDialog("Selecciona el numero:\n" + String.join("\n", opciones));
        try {
            seleccion = Integer.parseInt(input);
            if (seleccion < 0 || seleccion > opciones.length - 1) {
                JOptionPane.showMessageDialog(null, "Por favor, elige una opción válida.");
            } else {
                return seleccion;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error seleccione una opcion valida");
        }
    } while (true);
}

public String BuscarCuatri(String IDCompetidor) {
    boolean CuatriEncontrado = false;
    String resultado = "";
    Pila<clsCarburada> auxPila = new Pila<>();

    while (!pilaCompetidor.estaVacia()) {
        clsCarburada CuatriActual = pilaCompetidor.getElemento();

        if (CuatriActual.getCedula().equalsIgnoreCase(IDCompetidor)) {
            CuatriEncontrado = true;
            // Imprimir la información del primer Competidor
            resultado += "Información del primer Competidor:\n" +
                    "Cedula del competidor: " + CuatriActual.getCedula() + "\n" +
                    "Nombres " + CuatriActual.getNombre() + "\n" +
                    "Apellidos: " + CuatriActual.getApellido() + "\n" +
                    "Edad: " + CuatriActual.getFechaNacimiento() + "\n" +
                    "RH: " + CuatriActual.getRh() + "\n" +
                    "ID de la cuatrimoto: " + CuatriActual.getIDMoto() + "\n" +
                    "Tiempo de vuelta: " + CuatriActual.getTiempo() + "\n" +
                    "Tipo de cuatrimoto: " + CuatriActual.getCarburada() + "\n" +
                    "Fabricante: " + CuatriActual.getFabricante() + "\n";

            // Dejamos de recorrer la pila
            break;
        }

        auxPila.apilar(CuatriActual);
        pilaCompetidor.desapilar();
    }

    // Devolvemos los Cuatris a la pila original
    retPilaCompetidor(auxPila);

    if (CuatriEncontrado) {
        return "¡El Competidor fue encontrado exitosamente!\n\n" + resultado;
    } else {
        return "La cedula del competidor no fue encontrada.";
    }
}

public String ImprimirCuatri() {
    cadena = "LISTADO DE COMPETIDORES \n\n";
    while (!pilaCompetidor.estaVacia()) {
        clsCarburada objCuatris;
        objCuatris = pilaCompetidor.getElemento();
        cadena += "ICedula del competidori: " + objCuatris.getCedula() + "\n"
                + "Nombre: " + objCuatris.getNombre() + "\n"
                + "Apellido: " + objCuatris.getApellido() + "\n"
                + "Edad: " + objCuatris.getFechaNacimiento() + "\n"
                + "RH: " + objCuatris.getRh() + "\n"
                + "ID de la cuatrimoto: " + objCuatris.getIDMoto() + "\n"
                + "Tiempo de vuelta: " + objCuatris.getTiempo() + "\n"
                + "Tipo de cuatrimoto: " + objCuatris.getCarburada() + "\n"
                + "Fabricante: " + objCuatris.getFabricante() + "\n"
                + "---------------------------------\n\n";

        AuxCompetidor.apilar(pilaCompetidor.getElemento());
        pilaCompetidor.desapilar();
    }
    retPilaCompetidor(AuxCompetidor);
    return cadena;
}

public String ContadorCompetidor(){
        return "El total de competidores es: " + contCompetidor;
    }

private void retPilaCompetidor(Pila<clsCarburada> AuxCompetidor) {

        while (!AuxCompetidor.estaVacia()) {
            pilaCompetidor.apilar(AuxCompetidor.getElemento());
            AuxCompetidor.desapilar();
        }

    }

private String Validar_Nombre() {
    String nombre;

    while (true) {
        nombre = JOptionPane.showInputDialog(null, "¿Cuál es el nombre del Competidor?");

        if (nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            return nombre; // Nombre válido, se sale del método
        } else {
            JOptionPane.showMessageDialog(
                null,
                "El nombre no puede contener caracteres especiales.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

private String Validar_Apellido() {
    String apellido;

    do {
        apellido = JOptionPane.showInputDialog(null, "¿Cuál es el Apellido del Competidor?");

        if (apellido.trim().isEmpty() || apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*$")) {
            return apellido; // Apellido válido, se sale del método
        } else {
            JOptionPane.showMessageDialog(
                null,
                "El apellido no puede contener caracteres especiales, excepto letras y espacios. Deja el campo en blanco o ingresa un apellido válido.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    } while (true);
}
     
private String Validar_Cedula() {
    String cedula;
    boolean userInputValid = false;

    do {
        cedula = JOptionPane.showInputDialog(null, "Ingrese la cedula del Competidor \n");

        if (!esNumero(cedula)) {
            JOptionPane.showMessageDialog(
                null,
                "La cedula debe ser un número.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            userInputValid = true;
        }
    } while (!userInputValid);

    return cedula;
}

private boolean esNumero(String cadena) {
    try {
        // Intentar convertir la cadena en un número
        Long.parseLong(cadena);
        return true;
    } catch (NumberFormatException e) {
        // Si no se puede convertir, es porque no es un número
        return false;
    }
}

private int Validar_Fecha() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date fechaNac = null;
    boolean fechaValida = false;

    do {
        String fechaNacimiento = JOptionPane.showInputDialog(null, "Ingrese fecha dd/MM/yyyy");
        try {
            fechaNac = sdf.parse(fechaNacimiento);

            Calendar fechaActual = Calendar.getInstance();
            fechaActual.setTime(new Date());

            Calendar fechaNacimientoCal = Calendar.getInstance();
            fechaNacimientoCal.setTime(fechaNac);

            if (fechaNacimientoCal.get(Calendar.YEAR) < 1900 || fechaNacimientoCal.get(Calendar.YEAR) > fechaActual.get(Calendar.YEAR)) {
                JOptionPane.showMessageDialog(null, "La fecha de nacimiento debe estar entre 1900 y el año actual.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                fechaValida = true;
                int edad = fechaActual.get(Calendar.YEAR) - fechaNacimientoCal.get(Calendar.YEAR);

                if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNacimientoCal.get(Calendar.DAY_OF_YEAR)) {
                    edad--;
                }
                return edad;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Fecha de nacimiento inválida. Ingresa la fecha en formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } while (!fechaValida);

    return 0;
}

private String Validar_RH() {
        String[] opciones = {
            "",
            "A+",
            "A-",
            "B+",
            "B-",
            "AB+",
            "AB-",
            "O+",
            "O-"
        };

        String Rh;

        do {
            String seleccion = (String) JOptionPane.showInputDialog(
                null, "Seleccione el Rh:", "Validación de Tipo de Sangre", 
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]
            );

            if (seleccion != null && seleccion != "") {
                Rh = seleccion;
                return Rh; 
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un Rh válido.");
            }
        } while (true);
    }

private String Validar_IDMoto() {
        String idMoto = "";

        do {
            idMoto = JOptionPane.showInputDialog("Ingrese el ID de la moto: ");

            if (idMoto.matches("^[a-zA-Z0-9]+$")) {
                return idMoto; // La cadena de texto es válida, se sale del bucle
            } else {
                JOptionPane.showMessageDialog(null, "ID de moto no válido.");
            }
        } while (true);
    }

private int Validar_Tiempo() {
        int tiempo = 0;
        boolean tiempoValido = false;

        do {
            try {
                String input = JOptionPane.showInputDialog("Ingrese el tiempo de la carrera en segundos: ");
                tiempo = Integer.parseInt(input);
                tiempoValido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Debe ingresar un número entero.");
            }
        } while (!tiempoValido);

        return tiempo;
    }

private String Validar_Carburada() {
    String[] opciones = {
        "Carburada",
        "inyeccion Electronica",
    };

    while (true) {
        String seleccion = (String) JOptionPane.showInputDialog(
            null, "Seleccione el tipo de motor:", "Validación de motor",
            JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]
        );

        if (seleccion != null) {
            return seleccion; // Tipo de motor válido, se sale del método
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de motor válido.");
        }
    }
}

public boolean existeCedula(String cedula) {
    Pila<clsCarburada> tempPila = new Pila();
    boolean existe = false;

    while (!pilaCompetidor.estaVacia()) {
        clsCarburada objCuatris = pilaCompetidor.getElemento();
        if (objCuatris.getCedula().equalsIgnoreCase(cedula)) {
            existe = true;
        }
        tempPila.apilar(pilaCompetidor.getElemento());
        pilaCompetidor.desapilar();
    }

    // Restaurar la pila original
    retPilaCompetidor(tempPila);
    return existe;
}

public boolean existeID(String id) {
    Pila<clsCarburada> tempPila = new Pila();
    boolean existe = false;

    while (!pilaCompetidor.estaVacia()) {
        clsCarburada objCuatris = pilaCompetidor.getElemento();
        if (objCuatris.getIDMoto().equalsIgnoreCase(id)) {
            existe = true;
        }
        tempPila.apilar(pilaCompetidor.getElemento());
        pilaCompetidor.desapilar();
    }

    // Restaurar la pila original
    retPilaCompetidor(tempPila);
    return existe;
}

public void Ganador() {
    if (contCompetidor == 0) {
        JOptionPane.showMessageDialog(null, "No hay competidores registrados en la modalidad de Cuatrimotos Inyeccion.");
        return;
    }

    clsCarburada competidorMasRapido = null;
    int tiempoMasRapido = Integer.MAX_VALUE;

    while (!pilaCompetidor.estaVacia()) {
        clsCarburada competidorActual = pilaCompetidor.getElemento();
        if (competidorActual.getTiempo() < tiempoMasRapido) {
            tiempoMasRapido = competidorActual.getTiempo();
            competidorMasRapido = competidorActual;
        }
        AuxCompetidor.apilar(pilaCompetidor.getElemento());
        pilaCompetidor.desapilar();
    }

    if (competidorMasRapido != null) {
        JOptionPane.showMessageDialog(null, "Competidor más rápido:\n" +
                "Cédula del competidor: " + competidorMasRapido.getCedula() + "\n" +
                "Nombre: " + competidorMasRapido.getNombre() + "\n" +
                "Tiempo de vuelta: " + competidorMasRapido.getTiempo() + " segundos");
    } else {
        JOptionPane.showMessageDialog(null, "No se pudo determinar al competidor más rápido.");
    }

    // Devolver los competidores a la pila original
    retPilaCompetidor(AuxCompetidor);
}

}
