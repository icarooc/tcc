package br.edu.unirn.constants.tcc;

public enum Raca {
	
	CAUCASIANO("caucasiano"),
    NEGRO("negro");

    private String tipoRaca;

    private Raca (String r){
        this.tipoRaca = r;
    }

    public String getTipoRaca(){
        return tipoRaca;
    }

    @Override
    public String toString(){
        return tipoRaca;
    }

}
