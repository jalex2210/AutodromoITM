package autodromo;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.Random;

public class metodoMoto1000cc {
    private Pila<clsMoto1000cc> pila1000cc;
    private Pila<clsMoto1000cc> Aux1000cc;
    private String cadena;
    private int contCompet;
    private static final String CILINDRAJE = "1000";

public metodoMoto1000cc() {
        pila1000cc = new Pila();
        Aux1000cc = new Pila();
        cadena = "";
        contCompet = 0;
    }

public static void menuCRUDMoto1000() {
    int opcion;
    metodoMoto1000cc objP = new metodoMoto1000cc();

    do {
        String[] opciones = {"Registrar Competidor", "Modificar Competidor", "Eliminar Competidor", "Buscar Competidor", "Imprimir Competidor","Ganador", "Volver"};
        opcion = JOptionPane.showOptionDialog(
            null,
            "Bienvenido\n¿Qué deseas realizar?",
            "Menú de Motos 1000cc",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        switch (opcion) {
            case 0:
                JOptionPane.showMessageDialog(null, "Has seleccionado Registrar un competidor.");
                JOptionPane.showMessageDialog(null, objP.AgregarcompetidorMoto1000cc());
                break;
            case 1:
                String cedulaCompetidor = JOptionPane.showInputDialog("Introduce la cédula del competidor a modificar:");
                if (objP.existeCedula(cedulaCompetidor)) {
                    int opcionModificar = menuSeleccionarOpcionModificar();
                    String resultado = objP.ModificarCompetidorMoto1000cc(cedulaCompetidor, opcionModificar);
                    JOptionPane.showMessageDialog(null, resultado);
                } else {
                    JOptionPane.showMessageDialog(null, "La cédula del competidor no existe.");
                }
                break;
            case 2:
                String IDCompetidor = JOptionPane.showInputDialog("Ingrese la cédula del competidor a eliminar:");
                String resultado = objP.EliminarCompetidorMoto1000cc(IDCompetidor);
                JOptionPane.showMessageDialog(null, resultado);
                break;
            case 3:
                String IDCompetidorBuscar = JOptionPane.showInputDialog("Ingrese la cédula del competidor que desea buscar:");
                String resultadoBuscar = objP.BuscarCompetidorMoto1000cc(IDCompetidorBuscar);
                JOptionPane.showMessageDialog(null, resultadoBuscar);
                break;
            case 4:
                String resultadoImprimir = objP.ImprimirCompetidorMoto1000cc();
                JOptionPane.showMessageDialog(null, resultadoImprimir);
                break;
            case 5: 
                objP.Ganador();
                break;
            case 6:
                JOptionPane.showMessageDialog(null, "GRACIAS POR COMPETIR EN MOTOS 1000 CC\n");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
                break;
        }
    } while (opcion != 6);
}
    
public String AgregarcompetidorMoto1000cc() {
        Random random = new Random();
                
        while (JOptionPane.showConfirmDialog(null,
                "¿Desea agregar un competidor de moto 1000cc?")
                == JOptionPane.YES_NO_OPTION) {
            String cedula = Validar_Cedula();
            String nuevoIDMoto = ValidarIdMoto();
            
            if (!existeCedula(cedula)&&(!existeID(nuevoIDMoto))) {
                int tiempoAleatorio = random.nextInt(31) + 50;
                
            pila1000cc.apilar (new clsMoto1000cc (
                    cedula,
                    ValidarNombre(),
                    ValidarApellido(),
                    Validar_Fecha(),
                    ValidarRh(),
                    ValidarIdMoto(),
                    tiempoAleatorio,
                    CILINDRAJE,
                    ValidarTipo())
                    );
            
            contCompet++;
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe la cedula ingresada");
            }
            
        }
        cadena = "Se agregaron " + contCompet + " competidores ";
        return cadena; 
    }
    
public String EliminarCompetidorMoto1000cc(String Cedula){
        boolean banderita = false;
        cadena = "";
        while (!pila1000cc.estaVacia()) {
            clsMoto1000cc objMoto1000cc;
            objMoto1000cc = pila1000cc.getElemento();
            
            if(objMoto1000cc.getCedula().equalsIgnoreCase(Cedula)){
                banderita = true;
                contCompet--;
                pila1000cc.desapilar();
            }else{
                Aux1000cc.apilar(pila1000cc.getElemento());
                pila1000cc.desapilar();
            }
        }
        
        if(banderita)
            cadena = "El registro fue encontrado y eliminado";
        else
            cadena = "El registro no fue encontrado";
        
        retPilaMoto1000cc(Aux1000cc);
        return cadena;
    }
    
public String BuscarCompetidorMoto1000cc(String Cedula){
        boolean banderita = false;
        String datoen = "";
        cadena = "";
        while (!pila1000cc.estaVacia()) {
            clsMoto1000cc objMoto1000cc;
            objMoto1000cc = pila1000cc.getElemento();
            
            if(objMoto1000cc.getCedula().equalsIgnoreCase(Cedula)){
                banderita = true;
                cadena += "REGISTRO DEL COMPETIDOR \n\n" +
                        "Cedula del competidor: " + objMoto1000cc.getCedula()+ "\n"
                        + "Nombre del competidor: " + objMoto1000cc.getNombre()+ "\n"
                        + "Apellido del competidor: " + objMoto1000cc.getApellido()+ "\n"
                        + "Edad del competidor: " + objMoto1000cc.getFechaNacimiento()+ "\n"
                        + "Rh del competidor: " + objMoto1000cc.getRh()+ "\n"
                        + "ID moto del competidor: " + objMoto1000cc.getIDMoto()+ "\n"
                        + "Tiempo: " + objMoto1000cc.getTiempo()+ "\n"
                        + "Cilindraje de la moto del competidor: " + objMoto1000cc.getCilindraje()+ "\n"
                        + "Tipo: " + objMoto1000cc.getTipo()+ "\n";

                Aux1000cc.apilar(pila1000cc.getElemento());
                pila1000cc.desapilar();
            }else{
                Aux1000cc.apilar(pila1000cc.getElemento());
                pila1000cc.desapilar();
            }
        }
        
        if(banderita)
            datoen = "¡La cedula del competidor fue encontrado éxitosamente!\n\n";
        else
            cadena = "La cedula del competidor no fue encontrada";
        
        retPilaMoto1000cc(Aux1000cc);
        return datoen + cadena;
    }
    
public static int menuSeleccionarOpcionModificar() {
    String[] opciones = {
        "1. Modificar Nombre del competidor",
        "2. Modificar Apellido del competidor",
        "3. Modificar Fecha de nacimiento del Competidor",
        "4. Modificar RH del Competidor",
        "5. Modificar ID de la moto",
        "6. Modificar Tiempo del Competidor",
        "7. Modificar Cilindraje de Motor",
        "8. Modificar Tipo",
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
    
public String ModificarCompetidorMoto1000cc(String Cedula, int opc){
        boolean banderita = false;
    String datoen = "";
    cadena = "";

    while (!pila1000cc.estaVacia()) {
        clsMoto1000cc objMoto;
        objMoto = pila1000cc.getElemento();

        if (objMoto.getCedula().equalsIgnoreCase(Cedula)) {
            banderita = true;
            switch (opc) {
                case 1:
                    objMoto.setNombre(ValidarNombre());
                    cadena = "Nombre modificado con éxito";
                    break;
                case 2:
                    objMoto.setApellido(ValidarApellido());
                    cadena = "Apellido modificado con éxito";
                    break;
                case 3:
                    objMoto.setFechaNacimiento(Validar_Fecha());
                    cadena = "Fecha de Nacimiento modificada con éxito";
                    break;
                case 4:
                    objMoto.setRh(ValidarRh());
                    cadena = "RH modificado con éxito";
                    break;
                case 5:
                    objMoto.setIDMoto(ValidarIdMoto());
                    cadena = "ID de la moto modificado con éxito";
                    break;
                case 6:
                    objMoto.setTiempo(ValidarTiempo());
                    cadena = "Tiempo modificado con éxito";
                    break;
                case 7:
                    objMoto.setCilindraje(ValidarCilindraje());
                    cadena = "Cilindraje de la moto modificado con éxito";
                    break;
                case 8:
                    objMoto.setTipo(ValidarTipo());
                    cadena = "Tipo de motor modificado con éxito";
                    break;
                default:
                    cadena = "Saliendo de modificar";
                    break;  // Agregar un break aquí para terminar el switch
            }
        }
                Aux1000cc.apilar(new clsMoto1000cc(
            objMoto.getCedula(),
            objMoto.getNombre(),
            objMoto.getApellido(),
            objMoto.getFechaNacimiento(),
            objMoto.getRh(),
            objMoto.getIDMoto(),
            objMoto.getTiempo(),
            objMoto.getCilindraje(),
            objMoto.getTipo()
        ));

        pila1000cc.desapilar();
    }
    
    if (banderita) {
        datoen = "Competidor Modificado!!!\n\n";
    } else {
        int respuesta = JOptionPane.showConfirmDialog(null,
                "La cedula del Competidor no fue encontrada. ¿Desea intentar de nuevo?",
                "ID no encontrado",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            return "Volver a intentar la modificación del Competidor.";
        }
        cadena = "La cedula del Competidor no fue encontrada.";
    }

    retPilaMoto1000cc(Aux1000cc);
    return datoen + cadena;
}

public String ImprimirCompetidorMoto1000cc() {
        cadena = "LISTADO DE COMPETIDORES MOTO650\n\n";
        while (!pila1000cc.estaVacia()) {
            clsMoto1000cc objMoto1000cc;
            objMoto1000cc= pila1000cc.getElemento();
            cadena += "Cedula del competidor: " + objMoto1000cc.getCedula()+ "\n"
                        + "Nombre del competidor: " + objMoto1000cc.getNombre()+ "\n"
                        + "Apellido del competidor: " + objMoto1000cc.getApellido()+ "\n"
                        + "Edad del competidor: " + objMoto1000cc.getFechaNacimiento()+ "\n"
                        + "Rh del competidor: " + objMoto1000cc.getRh()+ "\n"
                        + "ID moto del competidor: " + objMoto1000cc.getIDMoto()+ "\n"
                        + "Tiempo: " + objMoto1000cc.getTiempo()+ "\n"
                        + "Cilindraje de la moto del competidor: " + objMoto1000cc.getCilindraje()+ "\n"
                        + "Tipo: " + objMoto1000cc.getTipo()+ "\n"
                        + "---------------------------------\n\n";
            Aux1000cc.apilar(pila1000cc.getElemento());
            pila1000cc.desapilar();

        }
        retPilaMoto1000cc(Aux1000cc);
        return cadena;
    }
    
public String ContadorMoto1000cc(){
        return "El total de competidores de motos con 1000cc registrados es " + contCompet;
    }
    
private void retPilaMoto1000cc(Pila<clsMoto1000cc> Aux1000cc) {

        while (!Aux1000cc.estaVacia()) {
            pila1000cc.apilar(Aux1000cc.getElemento());
            Aux1000cc.desapilar();
        }
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
    
private String ValidarRh() {
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

private String ValidarNombre() {
       String nombre = "";
        boolean nombreC = false;
        do {
            nombre = JOptionPane.showInputDialog("Ingrese nombre del competidor: ");
            if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                JOptionPane.showMessageDialog( null, "El nombre no puede contener caractares especiales");
                JOptionPane.showMessageDialog(null, "Ingrese un nombre valido");
            } else {
                nombreC = true;
            }
        } while (!nombreC);
        return nombre;
    }
    
private String ValidarApellido() {
       String apellido = "";
        boolean apellidoC = false;
        do {
            apellido = JOptionPane.showInputDialog("Ingrese apellido del competidor: ");
            if (!apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                JOptionPane.showMessageDialog( null, "El apellido no puede contener caracteres especiales");
                JOptionPane.showMessageDialog(null, "Ingrese un apellido valido");
            } else {
                apellidoC = true;
            }
        } while (!apellidoC);
        return apellido;
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
   
private boolean esNumero(String cadena) {
    try {
        Long.parseLong(cadena);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
  
private String ValidarIdMoto() {
        String IdMoto;

        do {
            IdMoto = JOptionPane.showInputDialog("Ingrese el ID de la moto: ");

            if (IdMoto.matches("^[a-zA-Z0-9]+$")) {
                return IdMoto; // La cadena de texto es válida, se sale del bucle
            } else {
                JOptionPane.showMessageDialog(null, "ID de moto no válido.");
            }
        } while (true);
    }
   
private String ValidarTipo() {
       String tipo = "";
        boolean tipoC = false;
        do {
            tipo = JOptionPane.showInputDialog("Ingrese tipo: ");
            if (!tipo.matches("^[0-9a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                JOptionPane.showMessageDialog( null, "El tipo no puede contener caractares especiales");
                JOptionPane.showMessageDialog(null, "Ingrese un tipo valido");
            } else {
                tipoC = true;
            }
        } while (!tipoC);
        return tipo;
    }
   
private int ValidarTiempo() {
       String numero = "";
        boolean numeroC = false;
        do {
            numero = JOptionPane.showInputDialog("Ingrese el tiempo del competidor");
            if (!esNumero(numero)) {
                JOptionPane.showMessageDialog( null, "El tiempo ingresado no es valido");                
            } else {
                numeroC = true;
                }
        } while (!numeroC);
        return Integer.parseInt(numero);
    }    

private String ValidarCilindraje() {
        String[] opciones = {
            "650 C.C",
            "1000 C.C",
        };

        String cilindrajeMotor = "";

        do {
            String seleccion = (String) JOptionPane.showInputDialog(
                null, "Seleccione el cilindraje:", "Validación de motor", 
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]
            );

            if (seleccion != null) {
                cilindrajeMotor = seleccion;
                return cilindrajeMotor; // Tipo de motor válido, salir del bucle
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un cilindraje de motor válido.");
            }
        } while (true);
    }
    
public boolean existeCedula(String cedula) {
    Pila<clsMoto1000cc> tempPila = new Pila();
    boolean existe = false;

    while (!pila1000cc.estaVacia()) {
        clsMoto1000cc obj1000cc = pila1000cc.getElemento();
        if (obj1000cc.getCedula().equalsIgnoreCase(cedula)) {
            existe = true;
        }
        tempPila.apilar(pila1000cc.getElemento());
        pila1000cc.desapilar();
    }

    // Restaurar la pila original
    retPilaMoto1000cc(tempPila);
    return existe;
}

public boolean existeID(String id) {
    Pila<clsMoto1000cc> tempPila = new Pila();
    boolean existe = false;

    while (!pila1000cc.estaVacia()) {
        clsMoto1000cc obj1000cc = pila1000cc.getElemento();
        if (obj1000cc.getIDMoto().equalsIgnoreCase(id)) {
            existe = true;
        }
        tempPila.apilar(pila1000cc.getElemento());
        pila1000cc.desapilar();
    }

    // Restaurar la pila original
    retPilaMoto1000cc(tempPila);
    return existe;
}

public void Ganador() {
    if (contCompet == 0) {
        JOptionPane.showMessageDialog(null, "No hay competidores registrados en la modalidad de Motos 1000cc.");
        return;
    }

    clsMoto1000cc competidorMasRapido = null;
    int tiempoMasRapido = Integer.MAX_VALUE;

    while (!pila1000cc.estaVacia()) {
        clsMoto1000cc competidorActual = pila1000cc.getElemento();
        if (competidorActual.getTiempo() < tiempoMasRapido) {
            tiempoMasRapido = competidorActual.getTiempo();
            competidorMasRapido = competidorActual;
        }
        Aux1000cc.apilar(pila1000cc.getElemento());
        pila1000cc.desapilar();
    }

    if (competidorMasRapido != null) {
        JOptionPane.showMessageDialog(null, "Competidor más rápido:\n" +
                "Cédula del competidor: " + competidorMasRapido.getCedula() + "\n" +
                "Nombre: " + competidorMasRapido.getNombre() + "\n" +
                "Tiempo de vuelta: " + competidorMasRapido.getTiempo() + " segundos");
    } else {
        JOptionPane.showMessageDialog(null, "No se pudo determinar al competidor más rápido.");
    }

    retPilaMoto1000cc(Aux1000cc);
}
    
}
