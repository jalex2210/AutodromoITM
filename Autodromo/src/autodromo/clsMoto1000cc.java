package autodromo;

// propiedad de herencia con Extend
public class clsMoto1000cc extends clsPiloto {

    private String Cilindraje;
    private String tipo;

    public clsMoto1000cc() {
        
    }

    public clsMoto1000cc(String Cedula, String Nombre, String Apellido, int FechaNacimiento, String Rh, String IDMoto, int Tiempo, String Cilindraje, String tipo) {
        super(Cedula, Nombre, Apellido, FechaNacimiento, Rh, IDMoto, Tiempo);
        this.Cilindraje = Cilindraje;
        this.tipo = tipo;
    }

    public String getCilindraje() {
        return Cilindraje;
    }

    public void setCilindraje(String Cilindraje) {
        this.Cilindraje = Cilindraje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}