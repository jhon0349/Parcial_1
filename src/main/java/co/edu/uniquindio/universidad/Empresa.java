package co.edu.uniquindio.universidad;

public class Empresa {
    private int MAX_CLIENTES = 50;
    private int MAX_DESARROLLADORES = 50;
    private int MAX_PROYECTOS = 100;
    private int MAX_SERVICIOS = 50;

    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String paginaWeb;

    private Cliente[] clientes = new Cliente[MAX_CLIENTES];
    private int numClientes = 0;

    private Desarrollador[] desarrolladores = new Desarrollador[MAX_PROYECTOS];
    private int numDesarrolladores = 0;

    private Proyecto[] proyectos = new Proyecto[MAX_SERVICIOS];
    private int numProyectos = 0;

    private ServicioAdicional[] servicioAdicional = new ServicioAdicional[MAX_SERVICIOS];
    private int numServicios = 0;

    public Empresa(String nombre, String nit, String direccion, String telefono, String paginaWeb) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    //clientes
    public boolean agregarCliente(Cliente c){
        if (c == null)
            return false;
        if (numClientes >= MAX_CLIENTES)
            return false;
        for (int i = 0; i < numClientes; i++){
            if (clientes[i] != null){
                if (clientes[i].getDocumento().equals(c.getDocumento()))
                    return false;
                if (clientes[i].getTelefono().equals(c.getTelefono()))
                    return false;
            }
        }
        clientes[numClientes++] = c;
        return true;
    }

    public Cliente buscarClientePorTelefono(String tel){
        for (int i = 0; i < numClientes; i++){
            if  (clientes[i] !=null && clientes[i].getTelefono().equals(tel))
                return clientes[i];
        }
        return null;
    }

    public Cliente buscarClientePorDocumento(String documento){
        for (int i = 0; i < numClientes; i++){
            if (clientes[i] !=null && clientes[i].getDocumento().equals(documento))
                return clientes[i];
        }
        return null;
    }

    public boolean actualizarCliente(String documento, Cliente nuevosDatos){
        for (int i = 0; i < numClientes; i++){
            if (clientes[i] !=null && clientes[i].getDocumento().equals(documento)) {
                for (int j = 0; j < numClientes; j++) {
                    if (j != i && clientes[j] != null && clientes[j].getTelefono().equals(nuevosDatos.getTelefono())) {
                        return false;
                    }
                }
                clientes[i] = nuevosDatos;
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCliente(String documento){
        for (int i = 0; i < numClientes; i++){
            if (clientes[i] !=null && clientes[i].getDocumento().equals(documento)){
                for (int j = i; j < numClientes - 1; j++) clientes[j] = clientes[j+1];
                clientes[numClientes - 1] = null;
                numClientes--;
                return true;
            }
        }
        return false;
    }

    public Cliente[] getClientesArray() {return clientes;}
    public int getNumClientes(){return numClientes;}

    //desarrolladores



}
