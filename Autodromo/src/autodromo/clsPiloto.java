package autodromo;

public class clsPiloto {
    private String Cedula;
    private String Nombre;
    private String Apellido;
    private int FechaNacimiento;
    private String Rh;
    private String IDMoto;
    private int Tiempo;

    
    public clsPiloto() {
    Cedula = "";
    Nombre ="";
    Apellido = "";
    FechaNacimiento = 0;
    Rh = "";
    IDMoto = "";
    Tiempo = 0;
    }

    public clsPiloto(String Cedula, String Nombre, String Apellido, int FechaNacimiento, String Rh, String IDMoto, int Tiempo ) {
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.FechaNacimiento = FechaNacimiento;
        this.Rh = Rh;
        this.IDMoto = IDMoto;
        this.Tiempo = Tiempo;
    }

    //GETTERS
    public String getCedula() {
        return Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public int getFechaNacimiento() {
        return FechaNacimiento;
    }

    public String getRh() {
        return Rh;
    }

    public String getIDMoto() {
        return IDMoto;
    }

    public int getTiempo() {
        return Tiempo;
    }

    //SETTERS
    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setFechaNacimiento(int FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public void setRh(String Rh) {
        this.Rh = Rh;
    }

    public void setIDMoto(String IDMoto) {
        this.IDMoto = IDMoto;
    }

    public void setTiempo(int Tiempo) {
        this.Tiempo = Tiempo;
    }
    
}
