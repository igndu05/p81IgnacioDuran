/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ignacio
 */
public class MascotasDTO {
    private int idnumMasc;
    private Integer idnumVet;
    private String numChip;
    private String nomMasc;
    private double pesoMasc;
    private Date fecnacMasc;
    private String tipoMasc;

    public MascotasDTO() {
    }

    public MascotasDTO(int idnumMasc, int idnumVet, String numChip, String nomMasc, double pesoMasc, Date fecnacMasc, String tipoMasc) {
        this.idnumMasc = idnumMasc;
        this.idnumVet = idnumVet;
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

    public int getIdnumVet() {
        return idnumVet;
    }

    public void setIdnumVet(Integer idnumVet) {
        this.idnumVet = idnumVet;
    }

    public String getNumChip() {
        return numChip;
    }

    public void setNumChip(String numChip) {
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

    public Date getFecnacMasc() {
        return fecnacMasc;
    }

    public void setFecnacMasc(Date fecnacMasc) {
        this.fecnacMasc = fecnacMasc;
    }

    public String getTipoMasc() {
        return tipoMasc;
    }

    public void setTipoMasc(String tipoMasc) {
        this.tipoMasc = tipoMasc;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idnumMasc;
        hash = 41 * hash + Objects.hashCode(this.idnumVet);
        hash = 41 * hash + Objects.hashCode(this.numChip);
        hash = 41 * hash + Objects.hashCode(this.nomMasc);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.pesoMasc) ^ (Double.doubleToLongBits(this.pesoMasc) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.fecnacMasc);
        hash = 41 * hash + Objects.hashCode(this.tipoMasc);
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
        final MascotasDTO other = (MascotasDTO) obj;
        if (this.idnumMasc != other.idnumMasc) {
            return false;
        }
        if (Double.doubleToLongBits(this.pesoMasc) != Double.doubleToLongBits(other.pesoMasc)) {
            return false;
        }
        if (!Objects.equals(this.numChip, other.numChip)) {
            return false;
        }
        if (!Objects.equals(this.nomMasc, other.nomMasc)) {
            return false;
        }
        if (!Objects.equals(this.tipoMasc, other.tipoMasc)) {
            return false;
        }
        if (!Objects.equals(this.idnumVet, other.idnumVet)) {
            return false;
        }
        return Objects.equals(this.fecnacMasc, other.fecnacMasc);
    }

    @Override
    public String toString() {
        return "MascotasDTO{" + "idnumMasc=" + idnumMasc + ", idnumVet=" + idnumVet + ", numChip=" + numChip + ", nomMasc=" + nomMasc + ", pesoMasc=" + pesoMasc + ", fecnacMasc=" + fecnacMasc + ", tipoMasc=" + tipoMasc + '}';
    }

    
    
}
