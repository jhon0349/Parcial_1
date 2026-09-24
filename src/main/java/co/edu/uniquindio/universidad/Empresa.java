package co.edu.uniquindio.universidad;

import java.time.LocalDate;

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

    public boolean agregarDesarrollador(Desarrollador d){
        if (d == null)
            return false;
        if (numDesarrolladores >= MAX_DESARROLLADORES)
            return false;
        for (int i = 0; i < numDesarrolladores; i++){
            if (desarrolladores[i] != null && desarrolladores[i].getCodigo().equals(d.getCodigo()))
                return false;
        }
        desarrolladores[numDesarrolladores++] = d;
        return true;
    }

    public  Desarrollador buscarDesarrolladorPorCodigo(String codigo){
        for (int i = 0; i < numDesarrolladores; i++){
            if (desarrolladores[i] != null && desarrolladores[i].getCodigo().equals(codigo))
                return desarrolladores[i];
        }
        return null;
    }

    public boolean actualizarDesarrollador(String codigo, Desarrollador nuevosDatos){
        for (int i = 0; i < numDesarrolladores; i++){
            if (desarrolladores[i] != null && desarrolladores[i].getCodigo().equals(codigo)) {
                if (!codigo.equals(nuevosDatos.getCodigo())) {
                    for (int j = 0; j < numDesarrolladores; j++){
                        if (desarrolladores[j] != null && desarrolladores[j].getCodigo().equals(nuevosDatos.getCodigo()))
                            return false;
                    }
                }
                desarrolladores[i] = nuevosDatos;
                return true;
            }
        }
        return false;
    }

    public  boolean eliminarDesarrollador(String codigo){
        for (int i = 0; i < numDesarrolladores; i++){
            if (desarrolladores[i] != null && desarrolladores[i].getCodigo().equals(codigo)){
                Desarrollador d = desarrolladores[i];
                Proyecto[] asignados = d.getProyectosAsignados();
                for (int k = 0; k < d.getNumProyectosAsignados(); k++){
                    Proyecto p = asignados[k];
                    if (p != null) p.removerDesarrollador(d);
                }
                for (int j = i; j < numDesarrolladores - 1; j++) desarrolladores[j] = desarrolladores[j+1];
                desarrolladores[numDesarrolladores - 1] = null;
                numDesarrolladores--;
                return true;
            }
        }
        return false;
    }

    public Desarrollador[] getDesarrolladoresArray() {return desarrolladores;}
    public int getNumDesarrolladores(){return numDesarrolladores;}

    //servicios
    public boolean agregarServicio(ServicioAdicional s){
        if (s == null)
            return false;
        if (numServicios >= MAX_SERVICIOS)
            return false;
        for (int i = 0; i < numServicios; i++){
            if (servicios[i] != null && servicios[i].getCodigo().equals(s.getCodigo()))
                return false;
        }
        servicios[numServicios++] = s;
        return true;
    }

    public ServicioAdicional buscarServicioPorCodigo(String codigo){
        for (int i = 0; i < numServicios; i++){
            if (servicios[i] != null && servicios[i].getCodigo().equals(codigo))
                return servicios[i];
        }
        return null;
    }

    public boolean actualizarServicio(String codigo, ServicioAdicional nuevosDatos){
        for (int i = 0; i < numServicios; i++){
            if (servicios[i] != null && servicios[i].getCodigo.equals(codigo)){
                if (!codigo.equals(nuevosDatos.getCodigo())) {
                    for (int j = 0; j < numServicios; j++){
                        if (servicios[j] != null && servicios[j].getCodigo.equals(nuevosDatos.getCodigo()))
                            return false;
                    }
                }
                servicios[i] = nuevosDatos;
                return true;
            }
        }
        return false;
    }

    public boolean eliminarServicio(String codigo){
        for (int i = 0; i < numServicios; i++){
            if (servicios[i] != null && servicios[i].getCodigo().equals(codigo)){
                for (int p = 0; p < numProyectos; p++){
                    if (proyectos[p] != null) proyectos[p].removerServicio(servicios[i]);
                }
                for (int j = i; j < numServicios - 1; j++) servicios[j] = servicios[j + 1];
                servicios[numServicios - 1] = null;
                numServicios--;
                return true;

            }
        }
        return false;
    }

    public ServicioAdicional[] getServiciosArray(){
        return servicios;
    }
    public int getNumServicios(){return numServicios;}

    //proyectos
    public boolean agregarProyecto(Proyecto p) {
        if (p == null)
            return false;
        if (numProyectos >= MAX_PROYECTOS)
            return false;
        for (int i = 0; i < numProyectos; i++) {
            if (proyectos[i] != null && proyectos[i].getCodigo().equals(p.getCodigo()))
                return false;
        }
        proyectos[numProyectos++] = p;
        Cliente c = p.getCliente();
        if (c != null) c.agregarProyecto(p);
        return true;
    }

    public Proyecto buscarProyectoPorCodigo(String codigo) {
        for (int i = 0; i < numProyectos; i++) {
            if (proyectos[i] != null && proyectos[i].getCodigo().equals(codigo))
                return proyectos[i];
        }
        return null;
    }

    public boolean actualizarProyecto(String codigo, Proyecto nuevosDatos) {
        for (int i = 0; i < numProyectos; i++) {
            if (proyectos[i] != null && proyectos[i].getCodigo().equals(codigo)) {
                if (!codigo.equals(nuevosDatos.getCodigo())) {
                    for (int j = 0; j < numProyectos; j++) {
                        if (proyectos[j] != null && proyectos[j].getCodigo().equals(nuevosDatos.getCodigo()))
                            return false;
                    }
                }
                proyectos[i] = nuevosDatos;
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProyecto(String codigo) {
        for (int i = 0; i < numProyectos; i++) {
            if (proyectos[i] != null && proyectos[i].getCodigo().equals(codigo)) {
                Proyecto p = proyectos[i];
                Desarrollador[] ds = p.getDesarrolladores();
                for (int k = 0; k < p.getNumDesarrolladores(); k++) {
                    if (ds[k] != null) ds[k].liberarProyecto(p);
                }
                for (int j = i; j < numProyectos - 1; j++) proyectos[j] = proyectos[j + 1];
                proyectos[numProyectos - 1] = null;
                numProyectos--;
                return true;
            }
        }
        return false;
    }

    public Proyecto[] getProyectosArray() {
        return proyectos; }
    public int getNumProyectos() {
        return numProyectos; }

    //funciones del contexto
    public boolean esNumeroPerfecto(long n) {
        if (n <= 1) return false;
        long suma = 1;
        long limite = (long)Math.sqrt(n);
        for (long i = 2; i <= limite; i++) {
            if (n % i == 0) {
                suma += i;
                long otro = n / i;
                if (otro != i) suma += otro;
            }
        }
        return suma == n;
    }

    public double ingresosPorFecha(LocalDate fecha) {
        double total = 0.0;
        for (int i = 0; i < numProyectos; i++) {
            Proyecto p = proyectos[i];
            if (p != null && p.getFechaSolicitud().equals(fecha)) total += p.calcularValorTotal();
        }
        return total;
    }




}
