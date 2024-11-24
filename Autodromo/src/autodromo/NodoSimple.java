package autodromo;

//realizo diamantado con tipo de dato object(recibe cualquier tipo de dato(clase
//int,float,double...
//recibe cualquier cantidad de datos
public class NodoSimple<Object> {

    private Object elemento;
    //declaro la clase del mismo tipo de dato que es, se convierte en un tipo de dato
    //reserva un espacio de memoria(direccion de memoria)
    private NodoSimple siguiente;
    
    /*Construye el nodo,que esta dividido en 2,
    uno para el elemento y otro para el siguiente que
    es la direccion de memoria del nodocon el que se enlaza*/        
    
    //recibe un dato en particular(object elemento), lo llamo solo para enviarle datos
    public NodoSimple(Object elemento){
     //cada vez que lo contruye la hace igual al elemento que me llega como parametro
        this.elemento = elemento;
        //se iguala a null por que al momento de enviarle el objeto no conoce  
        //su direccion de memoria
        this.siguiente = null;
    }
    public Object getElemento() {
        return elemento;
    }
    public NodoSimple getSiguiente() {
        return siguiente;
    }
    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }    
    
}


