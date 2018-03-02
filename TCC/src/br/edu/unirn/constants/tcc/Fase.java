package br.edu.unirn.constants.tcc;

public enum Fase {
	
	CRIANCA("crianca"),
    ADULTO("adulto");

    private String tipoFase;

    private Fase (String f){
        this.tipoFase = f;
    }

    public String getTipoFase(){
        return tipoFase;
    }

    @Override
    public String toString(){
        return tipoFase;
    }

}
