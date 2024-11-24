package autodromo;

public class ListaSimple <Object>{
    //Apuntadores que apuntan a los nodos
      private NodoSimple<Object> inicio, actual;
      
    //
    public ListaSimple(){
        establecer(null);
    }
    
    //El constructor de la sobrecarga
    public ListaSimple(Object elemento){
        establecer (new NodoSimple(elemento));
    }
    
    //
    private void establecer (NodoSimple valor){ //valor es los datos que se ingreso. crea las direcciones de memoria para el primer nodo solo lo hace una vez
        this.inicio = this.actual = valor;
    }
    
    public void reiniciar(){
        this.actual=this.inicio;
    }
    
    //
    public void enlazaA(Object elemento){
        NodoSimple nodo = new  NodoSimple (elemento);
        nodo.setSiguiente(inicio);
        establecer(nodo);
    }
    
    //
    public void enlazaA(NodoSimple nodo, Object elemento){
        NodoSimple aux = nodo.getSiguiente(); //Trae lo que tiene en siguiente 
        nodo.setSiguiente(new NodoSimple(elemento)); //Asigna 
        nodo.getSiguiente().setSiguiente(aux); //Trae el valor y lo asigna al aux 
    }
    
    public void anadir(Object elemento){
        if(this.estaVacia())
            this.establecer(new NodoSimple(elemento));
        else
            this.enlazaA(this.actual, elemento);
    }
    
    public void anadir(){
        this.anadir(null);
    }
    
    public boolean siguiente(){
        this.actual=this.actual.getSiguiente();
        return this.actual !=null;
    }
    
    public Object getElemento(){
        return this.actual.getElemento();
    }
    
    public void setElemento (Object Elemento){
        this.actual.setElemento(Elemento);
    }
    
    public Object getElementoInicio(){
        return this.inicio.getElemento();
    }
    
    public void setElementoInicio (Object elemento){
        this.inicio.setElemento(elemento);
    }
    
    public boolean estaVacia(){
        return this.inicio == null;  //si la respuesta es true esta vacia si es false esta llena
    }
    
    public void insertarAlInicio(Object elemento){
        if(estaVacia()){ //Se hace la pregunta de si esta vacia o no
            this.establecer(new NodoSimple(elemento)); // Lleva el elemento a nodoSimple y genera el primer nodo
            return; //Se para la funcion 
        }
        Object aux =this.inicio.getElemento(); //Si no estavacia entra aca
        this.inicio.setElemento(elemento);
        this.enlazaA(this.inicio, aux);
    }
    
    public void insertarAlInicio(){
        this.insertarAlInicio(null); //se llama el mismo
    }
    
    public void eliminarInicio(){
        NodoSimple aux = this.inicio.getSiguiente();
        if(this.actual == this.inicio){
            this.actual = this.inicio.getSiguiente();
        }
        this.inicio.setSiguiente(null);
        this.inicio =aux;
    }
    
}
