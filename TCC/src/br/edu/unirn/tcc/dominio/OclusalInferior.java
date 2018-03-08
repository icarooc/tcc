/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirn.tcc.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import br.edu.unirn.tcc.constants.*;
import br.edu.unirn.tcc.dao.PersistDB;

/**
 *
 * @author Ã�caroCosta
 */
@Entity
public class OclusalInferior implements Serializable, PersistDB {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoFoto tipoFoto;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[] fotos;

    public TipoFoto getTipoFoto() {
        return tipoFoto;
    }

    public void setTipoFoto(TipoFoto tipoFoto) {
        this.tipoFoto = tipoFoto;
    }

    public byte[] getFotos() {
        return fotos;
    }

    public void setFotos(byte[] fotos) {
        this.fotos = fotos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OclusalInferior)) {
            return false;
        }
        OclusalInferior other = (OclusalInferior) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.OclusalInferior[ id=" + id + " ]";
    }
    
}
