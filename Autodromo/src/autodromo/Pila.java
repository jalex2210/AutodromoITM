package autodromo;

public class Pila<Object> {
    //llama las funciones de simpla simple
    private ListaSimple<Object> elementos;
    //el metodo contructor llamo al alemento constructor para instanciar
    public Pila(){
       this.elementos = new ListaSimple();
    }
    //apilar recibe un dato y lo envia a insertar al inicio
    public void apilar(Object elemento){
       this.elementos.insertarAlInicio(elemento);
    }
    
    public void desapilar(){
        this.elementos.eliminarInicio();
    } 
    
    public boolean estaVacia(){
       return this.elementos.estaVacia();
    }
    
    public Object getElemento(){
       return this.elementos.getElemento();
    }

    public void setElementos(Object elemento) {
        this.elementos.setElemento(elemento);
    }
    
}

