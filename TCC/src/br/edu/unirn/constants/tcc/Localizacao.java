package br.edu.unirn.constants.tcc;

public enum Localizacao {
	
	ZONAURBANA("zona urbana"),
    ZONARURAL("zona rural");

    private String localizacao;

    private Localizacao (String s){
        this.localizacao = s;
    }

    public String getLocalizacao(){
        return localizacao;
    }

    @Override
    public String toString(){
        return localizacao;
    }

}
