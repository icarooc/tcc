/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unirn.tcc.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
//import javax.persistence.SequenceGenerator;

import br.edu.unirn.constants.tcc.*;
import br.edu.unirn.tcc.dao.PersistDB;

/**
 *
 * @author Ã�caroCosta
 */
@Entity
public class Paciente implements Serializable, PersistDB {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int idade;
    @Enumerated(EnumType.STRING)
    private Raca raca;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="pacienteid")
    private List<Fotos> fotos;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="pacienteid")
    private List<FrontalOclusao> frontalOclusao;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="pacienteid")
    private List<FrontalDesoclusao> frontalDesoclusao;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="pacienteid")
    private List<LateralDireita> lateralDireita;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="pacienteid")
    private List<LateralEsquerda> lateralEsquerda;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="pacienteid")
    private List<OclusalSuperior> oclusalSuperior;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="pacienteid")
    private List<OclusalInferior> oclusalInferior;
    @Enumerated(EnumType.STRING)
    private UF uf;
    private String ubs;
    private String ESFResponsavel;
    private String endereco;
    private String bairro;
    private String municipio;
    private String area;
    private String microArea;
    @Enumerated(EnumType.STRING)
    private Localizacao localizacao;
    private String tipoCasa;
    private int numeroComodos;
    private boolean energiaEletrica;
    @Enumerated(EnumType.STRING)
    private DestinoLixo destinoLixo;
    @Enumerated(EnumType.STRING)
    private AbastecimentoAgua abastecimentoAgua;
    @Enumerated(EnumType.STRING)
    private TratamentoAgua tratamentoAgua;
    @Enumerated(EnumType.STRING)
    private DestinoDejetos destinoDejetos;
    private boolean planoSaude;
    private int pessoasCobertas;
    @Enumerated(EnumType.STRING)
    private Ajuda ajuda;
    private String meiosComunicacao;
    private String meiosTransporte;
    private String gruposComunitarios;
    private boolean atividadesPDomiciliares;
    private boolean itensDental;
    private int classificacaoRisco;
    private String nome;
    private boolean alfabetizado;
    private boolean trabalhoAtual;
    private String ocupacao;
    private double rendaDeclarada;
    private String condicaoDoenca;
    private boolean frequentaEscola;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    private String dataCadastro;
    private String diagnostico;

    public List<FrontalOclusao> getFrontalOclusao() {
        return frontalOclusao;
    }

    public void setFrontalOclusao(List<FrontalOclusao> frontalOclusao) {
        this.frontalOclusao = frontalOclusao;
    }

    public List<FrontalDesoclusao> getFrontalDesoclusao() {
        return frontalDesoclusao;
    }

    public void setFrontalDesoclusao(List<FrontalDesoclusao> frontalDesoclusao) {
        this.frontalDesoclusao = frontalDesoclusao;
    }

    public List<LateralDireita> getLateralDireita() {
        return lateralDireita;
    }

    public void setLateralDireita(List<LateralDireita> lateralDireita) {
        this.lateralDireita = lateralDireita;
    }

    public List<LateralEsquerda> getLateralEsquerda() {
        return lateralEsquerda;
    }

    public void setLateralEsquerda(List<LateralEsquerda> lateralEsquerda) {
        this.lateralEsquerda = lateralEsquerda;
    }

    public List<OclusalSuperior> getOclusalSuperior() {
        return oclusalSuperior;
    }

    public void setOclusalSuperior(List<OclusalSuperior> oclusalSuperior) {
        this.oclusalSuperior = oclusalSuperior;
    }

    public List<OclusalInferior> getOclusalInferior() {
        return oclusalInferior;
    }

    public void setOclusalInferior(List<OclusalInferior> oclusalInferior) {
        this.oclusalInferior = oclusalInferior;
    }
    
    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getGruposComunitarios() {
        return gruposComunitarios;
    }

    public void setGruposComunitarios(String gruposComunitarios) {
        this.gruposComunitarios = gruposComunitarios;
    }

    public String getESFResponsavel() {
        return ESFResponsavel;
    }

    public void setESFResponsavel(String ESFResponsavel) {
        this.ESFResponsavel = ESFResponsavel;
    }

    public AbastecimentoAgua getAbastecimentoAgua() {
        return abastecimentoAgua;
    }

    public void setAbastecimentoAgua(AbastecimentoAgua abastecimentoAgua) {
        this.abastecimentoAgua = abastecimentoAgua;
    }

    public Ajuda getAjuda() {
        return ajuda;
    }

    public void setAjuda(Ajuda ajuda) {
        this.ajuda = ajuda;
    }

    public boolean isAlfabetizado() {
        return alfabetizado;
    }

    public void setAlfabetizado(boolean alfabetizado) {
        this.alfabetizado = alfabetizado;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isAtividadesPDomiciliares() {
        return atividadesPDomiciliares;
    }

    public void setAtividadesPDomiciliares(boolean atividadesPDomiciliares) {
        this.atividadesPDomiciliares = atividadesPDomiciliares;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getClassificacaoRisco() {
        return classificacaoRisco;
    }

    public void setClassificacaoRisco(int classificacaoRisco) {
        this.classificacaoRisco = classificacaoRisco;
    }

    public String getCondicaoDoenca() {
        return condicaoDoenca;
    }

    public void setCondicaoDoenca(String condicaoDoenca) {
        this.condicaoDoenca = condicaoDoenca;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public DestinoDejetos getDestinoDejetos() {
        return destinoDejetos;
    }

    public void setDestinoDejetos(DestinoDejetos destinoDejetos) {
        this.destinoDejetos = destinoDejetos;
    }

    public DestinoLixo getDestinoLixo() {
        return destinoLixo;
    }

    public void setDestinoLixo(DestinoLixo destinoLixo) {
        this.destinoLixo = destinoLixo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isEnergiaEletrica() {
        return energiaEletrica;
    }

    public void setEnergiaEletrica(boolean energiaEletrica) {
        this.energiaEletrica = energiaEletrica;
    }

    public boolean isFrequentaEscola() {
        return frequentaEscola;
    }

    public void setFrequentaEscola(boolean frequentaEscola) {
        this.frequentaEscola = frequentaEscola;
    }

    public String getGuposcomunitarios() {
        return gruposComunitarios;
    }

    public void setGuposcomunitarios(String guposComunitarios) {
        this.gruposComunitarios = guposComunitarios;
    }

    public boolean isItensDental() {
        return itensDental;
    }

    public void setItensDental(boolean itensDental) {
        this.itensDental = itensDental;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public String getMeiosComunicacao() {
        return meiosComunicacao;
    }

    public void setMeiosComunicacao(String meiosComunicacao) {
        this.meiosComunicacao = meiosComunicacao;
    }

    public String getMeiosTransporte() {
        return meiosTransporte;
    }

    public void setMeiosTransporte(String meiosTransporte) {
        this.meiosTransporte = meiosTransporte;
    }

    public String getMicroArea() {
        return microArea;
    }

    public void setMicroArea(String microArea) {
        this.microArea = microArea;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroComodos() {
        return numeroComodos;
    }

    public void setNumeroComodos(int numeroComodos) {
        this.numeroComodos = numeroComodos;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public int getPessoasCobertas() {
        return pessoasCobertas;
    }

    public void setPessoasCobertas(int pessoasCobertas) {
        this.pessoasCobertas = pessoasCobertas;
    }

    public boolean isPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(boolean planoSaude) {
        this.planoSaude = planoSaude;
    }

    public double getRendaDeclarada() {
        return rendaDeclarada;
    }

    public void setRendaDeclarada(double rendaDeclarada) {
        this.rendaDeclarada = rendaDeclarada;
    }

    public String getTipoCasa() {
        return tipoCasa;
    }

    public void setTipoCasa(String tipoCasa) {
        this.tipoCasa = tipoCasa;
    }

    public boolean isTrabalhoAtual() {
        return trabalhoAtual;
    }

    public void setTrabalhoAtual(boolean trabalhoAtual) {
        this.trabalhoAtual = trabalhoAtual;
    }

    public TratamentoAgua getTratamentoAgua() {
        return tratamentoAgua;
    }

    public void setTratamentoAgua(TratamentoAgua tratamentoAgua) {
        this.tratamentoAgua = tratamentoAgua;
    }

    public String getUbs() {
        return ubs;
    }

    public void setUbs(String ubs) {
        this.ubs = ubs;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }


    public Paciente(){
        this.idade = 0;
        this.raca = null;
        this.sexo = null;
        this.fotos = null;
    }

    public List<Fotos> getFotos() {
        return fotos;
    }

    public void setFotos(List<Fotos> fotos) {
        this.fotos = fotos;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
