package co.edu.uniquindio.universidad;

public class Cliente {

    private String nombre;
    private String documento;
    private String telefono;
    private String correo;
    private String pais;

    //capacidad cliente
    private int MAX_PROYECTOS_POR_CLIENTE = 20;
    private Proyecto[] proyectos;
    private  int numProyectos = 0;

    public Cliente(String nombre, String documento, String telefono, String correo, String pais) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
        this.pais = pais;
        this.proyectos = new  Proyecto[MAX_PROYECTOS_POR_CLIENTE];
    }


    //getters y setters


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean agregarProyecto(Proyecto p) {
        if (p == null)
            return false;
        if (numProyectos >= MAX_PROYECTOS_POR_CLIENTE)
            return false;
        proyectos[numProyectos++] = p;
        return true;
    }

    public Proyecto[] getProyectos() {
        return proyectos;}
    public int getNumProyectos() {
        return numProyectos;}

    public String toString(){
        return nombre + "(" +  documento + ") - " + telefono;
    }


}
