package br.edu.unirn.tcc.constants;

public enum CondicaoDoenca {
	
	ALC("ALC"),
    EPI("EPI"),
    HAN("HAN"),
    CHA("CHA"),
    GES("GES"),
    HA("HA"),
    DIA("DIA"),
    TB("TB");

    private String condicaoDoenca;

    private CondicaoDoenca (String s){
        this.condicaoDoenca = s;
    }

    public String getCondicaoDoenca(){
        return condicaoDoenca;
    }

    @Override
    public String toString(){
        return condicaoDoenca;
    }

}
