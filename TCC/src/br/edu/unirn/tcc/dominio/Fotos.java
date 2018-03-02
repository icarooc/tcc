/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unirn.tcc.dominio;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.swing.ImageIcon;

import br.edu.unirn.constants.tcc.TipoFoto;
import br.edu.unirn.tcc.dao.PersistDB;

/**
 *
 * @author Ã�caroCosta
 */
@Entity
public class Fotos implements Serializable, PersistDB {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoFoto tipoFoto;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[] fotos;

    public Fotos(){
        fotos = null;
    }

    public TipoFoto getTipoFoto() {
        return tipoFoto;
    }

    public void setTipoFoto(TipoFoto tipoFoto) {
        this.tipoFoto = tipoFoto;
    }

    public ImageIcon byteToFoto (byte[] b){
        InputStream is = new ByteArrayInputStream(b);
        ImageIcon imagem = new ImageIcon(b);
        return imagem;
    }

    public void byteToFoto1 (byte[] b,String s) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(s+".jpg");
        fos.write(b);
        fos.close();
    }

    public byte[] fotoToByte (String caminho) throws FileNotFoundException, IOException{
        byte[] saida;
        File f1;
        FileInputStream fis;
        int tamanho = 0;
        f1 = new File(caminho);
        fis = new FileInputStream(f1);
        tamanho = (int) f1.length()+1;
        saida = new byte[tamanho];
        fis.read(saida);
        fis.close();
        return saida;
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
        if (!(object instanceof Fotos)) {
            return false;
        }
        Fotos other = (Fotos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Fotos[id=" + id + "]";
    }

}
