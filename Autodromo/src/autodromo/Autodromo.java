package autodromo;
import javax.swing.JOptionPane;

public class Autodromo {

    public static void main(String[] args) {
        int opcion;
        
        while (true) {
        String[] opciones = {
            "Carreras de motos 650 C.C",
            "Carreras de motos 1000 C.C",
            "Carreras de cuatrimotos carburadas",
            "Carreras de cuatrimotos Inyección",
            "Salir"
        };

            opcion = JOptionPane.showOptionDialog(
                            null, 
                            "Seleccione donde desea competir", 
                            "Menú de Carreras", 
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE, 
                            null,
                            opciones, 
                            opciones[0]
                    );
                    
            switch (opcion){
                
                case 0:
                    MetodoCompetidorMoto650.menuCRUDMoto650();
                break;
                case 1:
                    metodoMoto1000cc.menuCRUDMoto1000();
                break;
                case 2:
                    MetodoCarburada.menuCRUDCarburada();
                break;
                case 3:
                    Metodo_Inyeccion.menuCRUDInyeccion();
                break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Muchas gracias por su permanencia. \n           Saliendo del sistema.");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Por favor seleccione una opcion valida");
                        }
                    }
            }
}