/*
 * Clase para mapear los datos de la tabla Persona
 */

package Modelos;

import java.util.Objects;

/**
 *
 * @author ignacio
 */

public class VeterinariosDTO {
    private int idnumVet;
    private String nif;
    private String nomVet;
    private String dirVet;
    private String tlfnVet;
    private String emailVet;

    public VeterinariosDTO(int idnumVet, String nif, String nomVet, String dirVet, String tlfnVet, String emailVet) {
        this.idnumVet = idnumVet;
        this.nif = nif;
        this.nomVet = nomVet;
        this.dirVet = dirVet;
        this.tlfnVet = tlfnVet;
        this.emailVet = emailVet;
    }

    public VeterinariosDTO() {
    }

    public int getIdnumVet() {
        return idnumVet;
    }

    public String getNif() {
        return nif;
    }

    public String getNomVet() {
        return nomVet;
    }

    public String getDirVet() {
        return dirVet;
    }

    public String getTlfnVet() {
        return tlfnVet;
    }

    public String getEmailVet() {
        return emailVet;
    }

    public void setIdnumVet(int idnumVet) {
        this.idnumVet = idnumVet;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setNomVet(String nomVet) {
        this.nomVet = nomVet;
    }

    public void setDirVet(String dirVet) {
        this.dirVet = dirVet;
    }

    public void setTlfnVet(String tlfnVet) {
        this.tlfnVet = tlfnVet;
    }

    public void setEmailVet(String emailVet) {
        this.emailVet = emailVet;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.idnumVet;
        hash = 53 * hash + Objects.hashCode(this.nif);
        hash = 53 * hash + Objects.hashCode(this.nomVet);
        hash = 53 * hash + Objects.hashCode(this.dirVet);
        hash = 53 * hash + Objects.hashCode(this.tlfnVet);
        hash = 53 * hash + Objects.hashCode(this.emailVet);
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
        final VeterinariosDTO other = (VeterinariosDTO) obj;
        if (this.idnumVet != other.idnumVet) {
            return false;
        }
        if (!Objects.equals(this.nif, other.nif)) {
            return false;
        }
        if (!Objects.equals(this.nomVet, other.nomVet)) {
            return false;
        }
        if (!Objects.equals(this.dirVet, other.dirVet)) {
            return false;
        }
        if (!Objects.equals(this.tlfnVet, other.tlfnVet)) {
            return false;
        }
        return Objects.equals(this.emailVet, other.emailVet);
    }

    @Override
    public String toString() {
        return "VeterinariosDTO{" + "idnumVet=" + idnumVet + ", nif=" + nif + ", nomVet=" + nomVet + ", dirVet=" + dirVet + ", tlfnVet=" + tlfnVet + ", emailVet=" + emailVet + '}';
    }
    
    
    
}
    

    