package autodromo;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.Random;

public class MetodoCompetidorMoto650 {
    private Pila<clsMoto650> pilaMoto650;
    private Pila<clsMoto650> AuxMoto650;
    private String cadena;
    private int contMoto650;
    private static final String CILINDRAJE = "650";

public MetodoCompetidorMoto650() {
        pilaMoto650 = new Pila();
        AuxMoto650 = new Pila();
        cadena = "";
        contMoto650 = 0;
    }
    
public static void menuCRUDMoto650() {
    int opcion;
    MetodoCompetidorMoto650 objP = new MetodoCompetidorMoto650();

    do {
        String[] opciones = {"Registrar Competidor", "Modificar Competidor", "Eliminar Competidor", "Buscar Competidor", "Imprimir Competidor","Ganador", "Volver"};
        opcion = JOptionPane.showOptionDialog(
            null,
            "Bienvenido\n¿Qué deseas realizar?",
            "Menú de Motos 650",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        switch (opcion) {
            case 0:
                JOptionPane.showMessageDialog(null, "Has seleccionado Registrar un competidor.");
                JOptionPane.showMessageDialog(null, objP.AgregarcompetidorMoto650());
                break;
            case 1:
                String cedulaCompetidor = JOptionPane.showInputDialog("Introduce la cédula del competidor a modificar:");
                if (objP.existeCedula(cedulaCompetidor)) {
                    int opcionModificar = menuSeleccionarOpcionModificar();
                    String resultado = objP.ModificarCompetidorMoto650(cedulaCompetidor, opcionModificar);
                    JOptionPane.showMessageDialog(null, resultado);
                } else {
                    JOptionPane.showMessageDialog(null, "La cédula del competidor no existe.");
                }
                break;
            case 2:
                String IDCompetidor = JOptionPane.showInputDialog("Ingrese la cédula del competidor a eliminar:");
                String resultado = objP.EliminarCompetidorMoto650(IDCompetidor);
                JOptionPane.showMessageDialog(null, resultado);
                break;
            case 3:
                String IDCompetidorBuscar = JOptionPane.showInputDialog("Ingrese la cédula del competidor que desea buscar:");
                String resultadoBuscar = objP.BuscarCompetidorMoto650(IDCompetidorBuscar);
                JOptionPane.showMessageDialog(null, resultadoBuscar);
                break;
            case 4:
                String resultadoImprimir = objP.ImprimirCompetidorMoto650();
                JOptionPane.showMessageDialog(null, resultadoImprimir);
                break;
            case 5: 
                objP.Ganador();
                break;
            case 6:
                JOptionPane.showMessageDialog(null, "GRACIAS POR COMPETIR EN MOTOS 650\n");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
                break;
        }
    } while (opcion != 6);
}
    
public String AgregarcompetidorMoto650() {
        Random random = new Random();
                
        while (JOptionPane.showConfirmDialog(null,
                "¿Desea agregar un competidor de moto 650?")
                == JOptionPane.YES_NO_OPTION) {
            String cedula = Validar_Cedula();
            String nuevoIDMoto = ValidarIdMoto();
            
            if (!existeCedula(cedula)&&(!existeID(nuevoIDMoto))) {
                int tiempoAleatorio = random.nextInt(31) + 50;
                
            pilaMoto650.apilar (new clsMoto650 (
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
            
            contMoto650++;
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe la cedula ingresada");
            }
            
        }
        cadena = "Se agregaron " + contMoto650 + " competidores ";
        return cadena; 
    }
    
public String EliminarCompetidorMoto650(String Cedula){
        boolean banderita = false;
        cadena = "";
        while (!pilaMoto650.estaVacia()) {
            clsMoto650 objMoto650;
            objMoto650 = pilaMoto650.getElemento();
            
            if(objMoto650.getCedula().equalsIgnoreCase(Cedula)){
                banderita = true;
                contMoto650--;
                pilaMoto650.desapilar();
            }else{
                AuxMoto650.apilar(pilaMoto650.getElemento());
                pilaMoto650.desapilar();
            }
        }
        
        if(banderita)
            cadena = "El registro fue encontrado y eliminado";
        else
            cadena = "El registro no fue encontrado";
        
        retPilaMoto650(AuxMoto650);
        return cadena;
    }
    
public String BuscarCompetidorMoto650(String Cedula){
        boolean banderita = false;
        String datoen = "";
        cadena = "";
        while (!pilaMoto650.estaVacia()) {
            clsMoto650 objMoto650;
            objMoto650 = pilaMoto650.getElemento();
            
            if(objMoto650.getCedula().equalsIgnoreCase(Cedula)){
                banderita = true;
                cadena += "REGISTRO DEL PASAJERO \n\n" +
                        "Cedula del competidor: " + objMoto650.getCedula()+ "\n"
                        + "Nombre del competidor: " + objMoto650.getNombre()+ "\n"
                        + "Apellido del competidor: " + objMoto650.getApellido()+ "\n"
                        + "Edad del competidor: " + objMoto650.getFechaNacimiento()+ "\n"
                        + "Rh del competidor: " + objMoto650.getRh()+ "\n"
                        + "ID moto del competidor: " + objMoto650.getIDMoto()+ "\n"
                        + "Tiempo: " + objMoto650.getTiempo()+ "\n"
                        + "Cilindraje de la moto del competidor: " + objMoto650.getCilindraje()+ "\n"
                        + "Tipo: " + objMoto650.getTipo()+ "\n";

                AuxMoto650.apilar(pilaMoto650.getElemento());
                pilaMoto650.desapilar();
            }else{
                AuxMoto650.apilar(pilaMoto650.getElemento());
                pilaMoto650.desapilar();
            }
        }
        
        if(banderita)
            datoen = "¡La cedula del competidor fue encontrado éxitosamente!\n\n";
        else
            cadena = "La cedula del competidor no fue encontrada";
        
        retPilaMoto650(AuxMoto650);
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
    
public String ModificarCompetidorMoto650(String Cedula, int opc){
        boolean banderita = false;
    String datoen = "";
    cadena = "";

    while (!pilaMoto650.estaVacia()) {
        clsMoto650 objMoto;
        objMoto = pilaMoto650.getElemento();

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
                AuxMoto650.apilar(new clsMoto650(
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

        pilaMoto650.desapilar();
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

    retPilaMoto650(AuxMoto650);
    return datoen + cadena;
}

public String ImprimirCompetidorMoto650() {
        cadena = "LISTADO DE COMPETIDORES MOTO650\n\n";
        while (!pilaMoto650.estaVacia()) {
            clsMoto650 objMoto650;
            objMoto650= pilaMoto650.getElemento();
            cadena += "Cedula del competidor: " + objMoto650.getCedula()+ "\n"
                        + "Nombre del competidor: " + objMoto650.getNombre()+ "\n"
                        + "Apellido del competidor: " + objMoto650.getApellido()+ "\n"
                        + "Edad del competidor: " + objMoto650.getFechaNacimiento()+ "\n"
                        + "Rh del competidor: " + objMoto650.getRh()+ "\n"
                        + "ID moto del competidor: " + objMoto650.getIDMoto()+ "\n"
                        + "Tiempo: " + objMoto650.getTiempo()+ "\n"
                        + "Cilindraje de la moto del competidor: " + objMoto650.getCilindraje()+ "\n"
                        + "Tipo: " + objMoto650.getTipo()+ "\n"
                        + "---------------------------------\n\n";
            AuxMoto650.apilar(pilaMoto650.getElemento());
            pilaMoto650.desapilar();

        }
        retPilaMoto650(AuxMoto650);
        return cadena;
    }
    
public String ContadorMoto650(){
        return "El total de competidores moto 650 registrados es " + contMoto650;
    }
    
private void retPilaMoto650(Pila<clsMoto650> AuxMoto650) {

        while (!AuxMoto650.estaVacia()) {
            pilaMoto650.apilar(AuxMoto650.getElemento());
            AuxMoto650.desapilar();
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
    Pila<clsMoto650> tempPila = new Pila();
    boolean existe = false;

    while (!pilaMoto650.estaVacia()) {
        clsMoto650 objCuatris = pilaMoto650.getElemento();
        if (objCuatris.getCedula().equalsIgnoreCase(cedula)) {
            existe = true;
        }
        tempPila.apilar(pilaMoto650.getElemento());
        pilaMoto650.desapilar();
    }

    // Restaurar la pila original
    retPilaMoto650(tempPila);
    return existe;
}

public boolean existeID(String id) {
    Pila<clsMoto650> tempPila = new Pila();
    boolean existe = false;

    while (!pilaMoto650.estaVacia()) {
        clsMoto650 objCuatris = pilaMoto650.getElemento();
        if (objCuatris.getIDMoto().equalsIgnoreCase(id)) {
            existe = true;
        }
        tempPila.apilar(pilaMoto650.getElemento());
        pilaMoto650.desapilar();
    }

    // Restaurar la pila original
    retPilaMoto650(tempPila);
    return existe;
}

public void Ganador() {
    if (contMoto650 == 0) {
        JOptionPane.showMessageDialog(null, "No hay competidores registrados en la modalidad de Motos 650.");
        return;
    }

    clsMoto650 competidorMasRapido = null;
    int tiempoMasRapido = Integer.MAX_VALUE;

    while (!pilaMoto650.estaVacia()) {
        clsMoto650 competidorActual = pilaMoto650.getElemento();
        if (competidorActual.getTiempo() < tiempoMasRapido) {
            tiempoMasRapido = competidorActual.getTiempo();
            competidorMasRapido = competidorActual;
        }
        AuxMoto650.apilar(pilaMoto650.getElemento());
        pilaMoto650.desapilar();
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
    retPilaMoto650(AuxMoto650);
}
    
}
