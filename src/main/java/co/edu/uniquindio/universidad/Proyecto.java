package co.edu.uniquindio.universidad;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Proyecto {
    private String codigo;
    private LocalDate fechaSolicitud;
    private LocalDate fechaInicio;
    private LocalDate fechaEntrega;
    private String estado;
    private String metodoPago;
    private Cliente cliente;

    private int MAX_DESARROLLADORES_POR_PROYECTO = 10;
    private int MAX_SERVICIOS_POR_PROYECTO = 10;

    private Desarrollador[] desarrolladores;
    private int numDesarrolladores = 0;

    private ServicioAdicional[] servicios;
    private int numServicios = 0;

    private double descuento;

    public Proyecto(String codigo, LocalDate fechaSolicitud, LocalDate fechaInicio, LocalDate fechaEntrega,
                    String estado, String metodoPago, Cliente cliente, double descuento) {
        this.codigo = codigo;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.descuento = descuento;
        this.desarrolladores = new Desarrollador[MAX_DESARROLLADORES_POR_PROYECTO];
        this.servicios = new ServicioAdicional[MAX_SERVICIOS_POR_PROYECTO];
    }

    //getters and setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Desarrollador[] getDesarrolladores() {
        return desarrolladores;
    }
    public int getNumDesarrolladores() {
        return numDesarrolladores;
    }
    public int getNumServicios() {
        return numServicios;
    }

    public boolean agregarDesarrollador(Desarrollador d) {
        if (d == null)
            return false;
        if (numDesarrolladores >= MAX_DESARROLLADORES_POR_PROYECTO)
            return false;
        boolean ok = d.asignarProyecto(this);
        if (!ok)
            return false;
        desarrolladores[numDesarrolladores++] = d;
        return true;
    }

    public boolean removerDesarrollador(Desarrollador d) {
        if (d == null)
            return false;
        for (int i = 0; i < numDesarrolladores; i++) {
            if (desarrolladores[i] != null && desarrolladores[i].getCodigo().equals(d.getCodigo())) {
                d.liberarProyecto(this);
                for (int j = i; j < numDesarrolladores - 1; j++) desarrolladores[j] = desarrolladores[j + 1];
                desarrolladores[numDesarrolladores - 1] = null;
                numDesarrolladores--;
                return true;
            }
        }
        return false;
    }

    public boolean agregarServicio(ServicioAdicional s){
        if (s == null)
            return false;
        if (numServicios >= MAX_SERVICIOS_POR_PROYECTO)
            return false;
        if (!s.isDisponible())
            return false;
        servicios[numServicios++] = s;
        return true;
    }

    public boolean removerServicio(ServicioAdicional s){
        if (s == null)
            return false;
        for (int i = 0; i < numServicios; i++) {
            if (servicios[i] != null && servicios[i].getCodigo().equals(s.getCodigo())){
                for (int j = i; j < numServicios - 1; j++) servicios[j] = servicios[j + 1];
                servicios[numServicios - 1] = null;
                numServicios--;
                return true;
            }
        }
        return false;
    }

    public double calcularValorTotal(){
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaEntrega);
        if (dias <= 0) dias = 1;
        double sumaTarifas = 0.0;
        for (int i = 0; i < numDesarrolladores; i++) {
            sumaTarifas += desarrolladores[i].getTarifaPorDia() * dias;
        }
        double sumaServicios = 0.0;
        for (int i = 0; i < numServicios; i++) {
            sumaServicios += servicios[i].getPrecio();
        }
        double subtotal = sumaTarifas - sumaServicios;
        double total = subtotal * (1 - descuento / 100.0);
        return total;
    }

    public String toString(){
        return codigo + " - " + estado + " - Solicitud: " + fechaSolicitud + " - Valor estimado: " + calcularValorTotal();
    }





}
