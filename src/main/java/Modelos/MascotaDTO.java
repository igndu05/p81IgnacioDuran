/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author ignacio
 */
public class MascotaDTO {
    private int idnumMasc;
    private int numChip;
    private String nomMasc;
    private double pesoMasc;
    private LocalDate fecnacMasc;
    private String tipoMasc;

    public MascotaDTO() {
    }

    public MascotaDTO(int idnumMasc, int numChip, String nomMasc, double pesoMasc, LocalDate fecnacMasc, String tipoMasc) {
        this.idnumMasc = idnumMasc;
        this.numChip = numChip;
        this.nomMasc = nomMasc;
        this.pesoMasc = pesoMasc;
        this.fecnacMasc = fecnacMasc;
        this.tipoMasc = tipoMasc;
    }

    public int getIdnumMasc() {
        return idnumMasc;
    }

    public void setIdnumMasc(int idnumMasc) {
        this.idnumMasc = idnumMasc;
    }

    public int getNumChip() {
        return numChip;
    }

    public void setNumChip(int numChip) {
        this.numChip = numChip;
    }

    public String getNomMasc() {
        return nomMasc;
    }

    public void setNomMasc(String nomMasc) {
        this.nomMasc = nomMasc;
    }

    public double getPesoMasc() {
        return pesoMasc;
    }

    public void setPesoMasc(double pesoMasc) {
        this.pesoMasc = pesoMasc;
    }

    public LocalDate getFecnacMasc() {
        return fecnacMasc;
    }

    public void setFecnacMasc(LocalDate fecnacMasc) {
        this.fecnacMasc = fecnacMasc;
    }

    public String getTipoMasc() {
        return tipoMasc;
    }

    public void setTipoMasc(String tipoMasc) {
        this.tipoMasc = tipoMasc;
    }

    @Override
    public String toString() {
        return "MascotaDTO{" + "idnumMasc=" + idnumMasc + ", numChip=" + numChip + ", nomMasc=" + nomMasc + ", pesoMasc=" + pesoMasc + ", fecnacMasc=" + fecnacMasc + ", tipoMasc=" + tipoMasc + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idnumMasc;
        hash = 29 * hash + this.numChip;
        hash = 29 * hash + Objects.hashCode(this.nomMasc);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.pesoMasc) ^ (Double.doubleToLongBits(this.pesoMasc) >>> 32));
        hash = 29 * hash + Objects.hashCode(this.fecnacMasc);
        hash = 29 * hash + Objects.hashCode(this.tipoMasc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MascotaDTO other = (MascotaDTO) obj;
        if (this.idnumMasc != other.idnumMasc) {
            return false;
        }
        if (this.numChip != other.numChip) {
            return false;
        }
        if (Double.doubleToLongBits(this.pesoMasc) != Double.doubleToLongBits(other.pesoMasc)) {
            return false;
        }
        if (!Objects.equals(this.nomMasc, other.nomMasc)) {
            return false;
        }
        if (!Objects.equals(this.tipoMasc, other.tipoMasc)) {
            return false;
        }
        return Objects.equals(this.fecnacMasc, other.fecnacMasc);
    }
    
    
}
