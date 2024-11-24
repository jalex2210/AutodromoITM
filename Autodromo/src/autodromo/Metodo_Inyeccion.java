package autodromo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;

public class Metodo_Inyeccion {
    private Pila<clsCuatrimotoInyeccion> pilaInyeccion;
    private Pila<clsCuatrimotoInyeccion> AuxInyeccion;
    private String cadena;
    private int contInyeccion;
    private static final String Inyeccion = "Inyeccion Electronica";

public Metodo_Inyeccion() {
        pilaInyeccion = new Pila();
        AuxInyeccion = new Pila();
        cadena = "";
        contInyeccion = 0;
    }
    
public static void menuCRUDInyeccion() {
    int opcion;
    Metodo_Inyeccion objP = new Metodo_Inyeccion();

    do {
        String[] opciones = {"Registrar Competidor", "Modificar Competidor", "Eliminar Competidor", "Buscar Competidor", "Imprimir Competidor","Ganador", "Volver"};
        opcion = JOptionPane.showOptionDialog(
            null,
            "Bienvenido\n¿Qué deseas realizar?",
            "Menú de Cuatrimotos Inyeccion",
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
                JOptionPane.showMessageDialog(null, "GRACIAS POR COMPETIR EN CUATRIMOTO INYECCION\n");
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
            String nuevoRh = Validar_Rh();
            int nuevoTiempo = tiempoAleatorio;
            String nuevaInyeccion = Inyeccion;
            String nuevoFabricante = JOptionPane.showInputDialog("Ingrese el fabricante del competidor");
            
            // Mostrar el número de competidor que se está agregando
            JOptionPane.showMessageDialog(null, "Competidor " + (contCompetidor + 1) + " agregado con éxito");

            // Crear el objeto con los datos de la persona actual y apilarlo
            pilaInyeccion.apilar(new clsCuatrimotoInyeccion(
                nuevaCedula,
                nombre,
                apellido,
                fechaNacimiento,
                nuevoRh,
                nuevoIDMoto,
                nuevoTiempo,
                nuevaInyeccion,
                nuevoFabricante
            ));
            contInyeccion++;
        } else {
            JOptionPane.showMessageDialog(null, "El competidor ya existe en esta modalidad. Intente nuevamente.");
        }
    }
    cadena = "Se agregaron " + contInyeccion + " Competidores al inventario";
    return cadena;
}
    
public String EliminarCuatri(String IDCompetidor) {
    boolean banderita = false;
    cadena = "";
    while (!pilaInyeccion.estaVacia()) {
        clsCuatrimotoInyeccion objCuatris;
        objCuatris = pilaInyeccion.getElemento();

        if (objCuatris.getCedula().equalsIgnoreCase(IDCompetidor)) {
            banderita = true;
            contInyeccion--;
            pilaInyeccion.desapilar();
        } else {
            AuxInyeccion.apilar(pilaInyeccion.getElemento());
            pilaInyeccion.desapilar();
        }
    }

    if (banderita)
        cadena = "El registro del competidor fue encontrado y eliminado";
    else
        cadena = "El ID del competidor no fue encontrado";

    retPilaCompetidor(AuxInyeccion);
    return cadena;
}

public String ModificarCuatri(String IDCompetidor, int opc) {
    boolean banderita = false;
    String datoen = "";
    cadena = "";

    while (!pilaInyeccion.estaVacia()) {
        clsCuatrimotoInyeccion objCuatris;
        objCuatris = pilaInyeccion.getElemento();

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
                    objCuatris.setRh(Validar_Rh());
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
                    objCuatris.setInyeccion(Validar_Inyeccion());
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

        AuxInyeccion.apilar(new clsCuatrimotoInyeccion(
            objCuatris.getCedula(),
            objCuatris.getNombre(),
            objCuatris.getApellido(),
            objCuatris.getFechaNacimiento(),
            objCuatris.getRh(),
            objCuatris.getIDMoto(),
            objCuatris.getTiempo(),
            objCuatris.getInyeccion(),
            objCuatris.getFabricante()
        ));

        pilaInyeccion.desapilar();
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

    retPilaCompetidor(AuxInyeccion);
    return datoen + cadena;
}

public static int menuSeleccionarOpcionModificar() {
    String[] opciones = {
        "1. Modificar Nombre del competidor",
        "2. Modificar Apellido del competidor",
        "3. Modificar Fecha de nacimiento del Competidor",
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
    Pila<clsCuatrimotoInyeccion> auxPila = new Pila<>();

    while (!pilaInyeccion.estaVacia()) {
        clsCuatrimotoInyeccion CuatriActual = pilaInyeccion.getElemento();

        if (CuatriActual.getCedula().equalsIgnoreCase(IDCompetidor)) {
            CuatriEncontrado = true;
            // Imprimir la información del primer Competidor
            resultado += "Información del Competidor:\n" +
                    "Cedula del competidor: " + CuatriActual.getCedula() + "\n" +
                    "Nombres " + CuatriActual.getNombre() + "\n" +
                    "Apellidos: " + CuatriActual.getApellido() + "\n" +
                    "Edad: " + CuatriActual.getFechaNacimiento() + "\n" +
                    "RH: " + CuatriActual.getRh() + "\n" +
                    "ID de la cuatrimoto: " + CuatriActual.getIDMoto() + "\n" +
                    "Tiempo de vuelta: " + CuatriActual.getTiempo() + "\n" +
                    "Tipo de cuatrimoto: " + CuatriActual.getInyeccion() + "\n" +
                    "Fabricante: " + CuatriActual.getFabricante() + "\n";

            break;
        }

        auxPila.apilar(CuatriActual);
        pilaInyeccion.desapilar();
    }

    retPilaCompetidor(auxPila);

    if (CuatriEncontrado) {
        return "¡El Competidor fue encontrado exitosamente!\n\n" + resultado;
    } else {
        return "El competidor no fue encontrado con esta cedula.";
    }
}

public String ImprimirCuatri() {
    cadena = "LISTADO DE COMPETIDORES \n\n";
    while (!pilaInyeccion.estaVacia()) {
        clsCuatrimotoInyeccion objCuatris;
        objCuatris = pilaInyeccion.getElemento();
        cadena += "Cedula del competidor: " + objCuatris.getCedula() + "\n"
                + "Nombre: " + objCuatris.getNombre() + "\n"
                + "Apellido: " + objCuatris.getApellido() + "\n"
                + "Edad: " + objCuatris.getFechaNacimiento() + "\n"
                + "RH: " + objCuatris.getRh() + "\n"
                + "ID de la cuatrimoto: " + objCuatris.getIDMoto() + "\n"
                + "Tiempo de vuelta: " + objCuatris.getTiempo() + "\n"
                + "Tipo de cuatrimoto: " + objCuatris.getInyeccion() + "\n"
                + "Fabricante: " + objCuatris.getFabricante() + "\n"
                + "---------------------------------\n\n";

        AuxInyeccion.apilar(pilaInyeccion.getElemento());
        pilaInyeccion.desapilar();
    }
    retPilaCompetidor(AuxInyeccion);
    return cadena;
}

public String ContadorCompetidor(){
        return "El total de competidores es: " + contInyeccion;
    }

private void retPilaCompetidor(Pila<clsCuatrimotoInyeccion> AuxCompetidor) {

        while (!AuxCompetidor.estaVacia()) {
            pilaInyeccion.apilar(AuxCompetidor.getElemento());
            AuxCompetidor.desapilar();
        }

    }

private String Validar_Nombre() {
        String nombre;
        boolean nombreValido = false; //inicializamos la variable para controlar el bucle
        do {
            nombre = JOptionPane.showInputDialog(null, "¿Cuál es el nombre del Competidor?"); //almacenando en la variable nombre
            if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                JOptionPane.showMessageDialog(
                        null,
                        "El nombre no puede contener caractares especiales.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE );
            } else {
                nombreValido = true;
            }
        } while (!nombreValido);
        return nombre;
    }
    
private String Validar_Apellido() {
    String apellido = "";

    while (true) {
        apellido = JOptionPane.showInputDialog(null, "¿Cuál es el Apellido del Competidor?");

        if (apellido.trim().isEmpty() || apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*$")) {
            break;
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "El apellido no puede contener caracteres especiales, excepto letras y espacios. Deja el campo en blanco o ingresa un apellido válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    return apellido;
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

    String fechaUser =(JOptionPane.showInputDialog(null, "Ingrese fecha dd/MM/yyyy" ));

        // Obtén la fecha ingresada por el usuario
        String userInput = fechaUser;// Reemplaza jTextField1 por el nombre de tu campo de entrada

        // Define un formato para la fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
        //Intenta parsear la fecha
         Date dateOfBirth = dateFormat.parse(userInput);

        //Calcula la fecha actual
            Date currentDate = new Date();

         //Calcula la diferencia en milisegundos
            long difference = currentDate.getTime() - dateOfBirth.getTime();

            // Convierte la diferencia a años
             int age = (int) (difference / (1000L * 60 * 60 * 24 * 365.25));
            return age; 
         // Ahora, la variable 'age' contiene la edad del usuario

        } catch (ParseException e) {
        // En caso de error en el formato de la fecha
        System.err.println("Formato de fecha incorrecto");
}
        return 0;
}

private String Validar_Rh() {
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

private String Validar_Inyeccion() {
        String[] opciones = {
            "Carburada",
            "Inyeccion Electronica",
        };

        String tipoMotor = "";

        do {
            String seleccion = (String) JOptionPane.showInputDialog(
                null, "Seleccione el tipo de motor:", "Validación de motor", 
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]
            );

            if (seleccion != null) {
                tipoMotor = seleccion;
                return tipoMotor; // Tipo de motor válido, salir del bucle
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de motor válido.");
            }
        } while (true);
    }

public boolean existeCedula(String cedula) {
    Pila<clsCuatrimotoInyeccion> tempPila = new Pila();
    boolean existe = false;

    while (!pilaInyeccion.estaVacia()) {
        clsCuatrimotoInyeccion objCuatris = pilaInyeccion.getElemento();
        if (objCuatris.getCedula().equalsIgnoreCase(cedula)) {
            existe = true;
        }
        tempPila.apilar(pilaInyeccion.getElemento());
        pilaInyeccion.desapilar();
    }

    // Restaurar la pila original
    retPilaCompetidor(tempPila);
    return existe;
}

public boolean existeID(String idmoto) {
    Pila<clsCuatrimotoInyeccion> tempPila = new Pila();
    boolean existe = false;

    while (!pilaInyeccion.estaVacia()) {
        clsCuatrimotoInyeccion objCuatris = pilaInyeccion.getElemento();
        if (objCuatris.getIDMoto().equalsIgnoreCase(idmoto)) {
            existe = true;
        }
        tempPila.apilar(pilaInyeccion.getElemento());
        pilaInyeccion.desapilar();
    }

    // Restaurar la pila original
    retPilaCompetidor(tempPila);
    return existe;
}

public void Ganador() {
    if (contInyeccion == 0) {
        JOptionPane.showMessageDialog(null, "No hay competidores registrados en la modalidad de Cuatrimotos Inyeccion.");
        return;
    }

    clsCuatrimotoInyeccion competidorMasRapido = null;
    int tiempoMasRapido = Integer.MAX_VALUE;

    while (!pilaInyeccion.estaVacia()) {
        clsCuatrimotoInyeccion competidorActual = pilaInyeccion.getElemento();
        if (competidorActual.getTiempo() < tiempoMasRapido) {
            tiempoMasRapido = competidorActual.getTiempo();
            competidorMasRapido = competidorActual;
        }
        AuxInyeccion.apilar(pilaInyeccion.getElemento());
        pilaInyeccion.desapilar();
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
    retPilaCompetidor(AuxInyeccion);
}
}