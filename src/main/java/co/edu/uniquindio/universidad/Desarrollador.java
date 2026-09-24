package co.edu.uniquindio.universidad;

import java.time.LocalDate;

public class Desarrollador {

    private String codigo;
    private String equipo;
    private String nivel;
    private int maxProyectosSimultaneos;
    private double tarifaPorDia;
    private String estado;

    private int MAX_ASIGNADOS_TRACK = 50;
    private Proyecto[] proyectosAsignados;
    private int numProyectosAsignados = 0;


    public Desarrollador(String codigo, String equipo, String nivel, int maxProyectosSimultaneos, double tarifaPorDia, String estado) {
        this.codigo = codigo;
        this.equipo = equipo;
        this.nivel = nivel;
        this.maxProyectosSimultaneos = maxProyectosSimultaneos;
        this.tarifaPorDia = tarifaPorDia;
        this.estado = estado;
        this.proyectosAsignados = new Proyecto[MAX_ASIGNADOS_TRACK];
    }

    //getters y setters


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getMaxProyectosSimultaneos() {
        return maxProyectosSimultaneos;
    }

    public void setMaxProyectosSimultaneos(int maxProyectosSimultaneos) {
        this.maxProyectosSimultaneos = maxProyectosSimultaneos;
    }

    public double getTarifaPorDia() {
        return tarifaPorDia;
    }

    public void setTarifaPorDia(double tarifaPorDia) {
        this.tarifaPorDia = tarifaPorDia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proyecto[] getProyectosAsignados() {
        return proyectosAsignados;
        }
        public int getNumProyectosAsignados() {
        return numProyectosAsignados;
        }

    public boolean estaDisponibleParaFechas(LocalDate inicio, LocalDate fin){
        for(int i = 0; 1 < numProyectosAsignados; i++){
            Proyecto p = proyectosAsignados[i];
            if (p == null) continue;
            if (fechasSeSolapan(inicio, fin, p.getFechaInicio(), p.getFechaEntrega()))
                return false;
        }
        return true;
    }

    private boolean fechasSeSolapan(LocalDate aStart, LocalDate aEnd, LocalDate bStart, LocalDate bEnd){
        if (aStart == null || aEnd == null || bStart == null || bEnd == null)
            return false;
        return !(aEnd.isBefore(bStart) ||aStart.isAfter(bEnd));
    }

    public boolean asignarProyecto(Proyecto p){
        if (p == null) return false;
        if (numProyectosAsignados >= maxProyectosSimultaneos) return false;
        if (!estaDisponibleParaFechas(p.getFechaInicio(),p.getFechaEntrega())) return false;
        proyectosAsignados[numProyectosAsignados++] = p;
        if(numProyectosAsignados >= maxProyectosSimultaneos) estado = "Asignado";
        return true;
    }

    public boolean liberarProyecto(Proyecto p){
        if (p == null) return false;
        for (int i = 0; i < numProyectosAsignados; i++){
            if (proyectosAsignados[i] != null && proyectosAsignados[i].getCodigo().equals(p.getCodigo())){
                for (int j = i; j < numProyectosAsignados - 1; j++) proyectosAsignados[j] = proyectosAsignados[j + 1];
                proyectosAsignados[numProyectosAsignados - 1] = null;
                numProyectosAsignados--;
                if(numProyectosAsignados == 0) estado = "Disponible";
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return codigo + " - " + nivel + " - " + estado + " - tarifa: " + tarifaPorDia + " - asignados: " + numProyectosAsignados;
    }








}
