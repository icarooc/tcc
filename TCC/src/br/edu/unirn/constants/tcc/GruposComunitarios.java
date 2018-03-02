package br.edu.unirn.constants.tcc;

public enum GruposComunitarios {
	
	COOPERATIVA("cooperativa"),
    GRUPORELIGIOSO("religioso"),
    ASSOCIACOES("associações");

    private String gruposComunitarios;

    private GruposComunitarios (String s){
        this.gruposComunitarios = s;
    }

    public String getGruposComunitarios(){
        return gruposComunitarios;
    }

    @Override
    public String toString(){
        return gruposComunitarios;
    }

}
