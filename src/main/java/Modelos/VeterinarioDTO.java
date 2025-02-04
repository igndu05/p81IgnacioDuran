/*
 * Clase para mapear los datos de la tabla Persona
 */

package Modelos;

import java.util.Objects;

/**
 *
 * @author ignacio
 */

public class VeterinarioDTO {
    private int idnumVet;
    private String nif;
    private String nomVet;
    private String dirVet;
    private int tlfnVet;
    private String emailVet;

    public VeterinarioDTO(int idnumVet, String nif, String nomVet, String dirVet, int tlfnVet, String emailVet) {
        this.idnumVet = idnumVet;
        this.nif = nif;
        this.nomVet = nomVet;
        this.dirVet = dirVet;
        this.tlfnVet = tlfnVet;
        this.emailVet = emailVet;
    }

    public VeterinarioDTO() {
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

    public int getTlfnVet() {
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

    public void setTlfnVet(int tlfnVet) {
        this.tlfnVet = tlfnVet;
    }

    public void setEmailVet(String emailVet) {
        this.emailVet = emailVet;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idnumVet;
        hash = 79 * hash + Objects.hashCode(this.nif);
        hash = 79 * hash + Objects.hashCode(this.nomVet);
        hash = 79 * hash + Objects.hashCode(this.dirVet);
        hash = 79 * hash + this.tlfnVet;
        hash = 79 * hash + Objects.hashCode(this.emailVet);
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
        final VeterinarioDTO other = (VeterinarioDTO) obj;
        if (this.idnumVet != other.idnumVet) {
            return false;
        }
        if (this.tlfnVet != other.tlfnVet) {
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
        return Objects.equals(this.emailVet, other.emailVet);
    }

    @Override
    public String toString() {
        return "VeterinarioDTO{" + "idnumVet=" + idnumVet + ", nif=" + nif + ", nomVet=" + nomVet + ", dirVet=" + dirVet + ", tlfnVet=" + tlfnVet + ", emailVet=" + emailVet + '}';
    }
    
    
    
}
    

    