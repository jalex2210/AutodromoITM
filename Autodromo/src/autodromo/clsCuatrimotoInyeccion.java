package autodromo;

public class clsCuatrimotoInyeccion extends clsPiloto {
    private String Inyeccion;
    private String Fabricante;
    
    public clsCuatrimotoInyeccion() {
    Inyeccion = "Inyeccion";
    Fabricante = "";
    }

    public clsCuatrimotoInyeccion(String Cedula, String Nombre, String Apellido, int FechaNacimiento, 
                                                    String Rh, String IDMoto, int Tiempo, String Inyeccion, String Fabricante ) {
        super(Cedula, Nombre, Apellido,FechaNacimiento, Rh, IDMoto, Tiempo);
        this.Inyeccion = Inyeccion;
        this.Fabricante = Fabricante;
    }

    //GETTERS

    public String getInyeccion() {
        return Inyeccion;
    }

    public String getFabricante() {
        return Fabricante;
    }

    //SETTERS
    public void setInyeccion(String Inyeccion) {
        this.Inyeccion = Inyeccion;
    }

    public void setFabricante(String Fabricante) {
        this.Fabricante = Fabricante;
    }    
    
}
