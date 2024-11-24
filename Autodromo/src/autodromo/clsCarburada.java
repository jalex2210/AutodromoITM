package autodromo;

public class clsCarburada extends clsPiloto {
    private String Carburada;
    private String Fabricante;
    
    public clsCarburada() {
    Carburada = "";
    Fabricante = "";
    }

    public clsCarburada(String Cedula, String Nombre, String Apellido, int FechaNacimiento, 
                                     String Rh, String IDMoto, int Tiempo, String Carburada, String Fabricante ) {
        
        super(Cedula, Nombre, Apellido,FechaNacimiento, Rh, IDMoto, Tiempo);
        this.Carburada = Carburada;
        this.Fabricante = Fabricante;
    }

    //GETTERS

    public String getCarburada() {
        return Carburada;
    }

    public String getFabricante() {
        return Fabricante;
    }

    //SETTERS
    public void setCarburada(String Carburada) {
        this.Carburada = Carburada;
    }

    public void setFabricante(String Fabricante) {
        this.Fabricante = Fabricante;
    }    
    
}
