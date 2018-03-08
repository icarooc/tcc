package br.edu.unirn.tcc.constants;

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
