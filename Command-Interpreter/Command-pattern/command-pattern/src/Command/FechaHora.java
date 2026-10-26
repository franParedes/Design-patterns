package Command;

import java.util.*;
class FechaHora {
    //atributos
    private int anyo;
    private int mes;
    private int dia;
    private int hora;
    private int min;
    //constructores
    public FechaHora(int dia, int mes, int anyo, int hora, int min) {
        this.dia = dia;
        this.mes = mes;
        this.anyo = anyo;
        this.hora = hora;
        this.min = min;
    }
    public FechaHora(String cadenaFecha) {
        StringTokenizer st = new StringTokenizer(cadenaFecha, "/:");
        String dd = st.nextToken();
        String mm = st.nextToken();
        String aa = st.nextToken();
        String ho = st.nextToken();
        String mi = st.nextToken();
        this.dia = Integer.parseInt(dd);
        this.mes = Integer.parseInt(mm);
        this.anyo = Integer.parseInt(aa);
        this.hora = Integer.parseInt(ho);
        this.min = Integer.parseInt(mi);
    }
    public FechaHora() {
        Calendar hoy = Calendar.getInstance();
        anyo = hoy.get(Calendar.YEAR);
        mes = hoy.get(Calendar.MONTH) + 1;
        dia = hoy.get(Calendar.DAY_OF_MONTH);
        hora = hoy.get(Calendar.HOUR_OF_DAY);
        min = hoy.get(Calendar.MINUTE);
    }
    //m´etodos
    @Override
    public String toString() {
        if (min < 10) {
            return dia + "/" + mes + "/" + anyo + "/" + hora + ":0" + min;
        } else {
            return dia + "/" + mes + "/" + anyo + "/" + hora + ":" + min;
        }
    }
    public int getAnyo() {
        return this.anyo;
    }
    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }
    public int getDia() {
        return this.dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getMes() {
        return this.mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getHora() {
        return this.hora;
    }
    public void setHora(int hora) {
        this.hora = hora;
    }
    public int getMin() {
        return this.min;
    }
    public void setMin(int min) {
        this.min = min;
    }
}