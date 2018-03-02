package br.edu.unirn.constants.tcc;

public enum TipoCasa {
	
	TIJOLOADOBE("tijolo/adobe"),
    TAIPAREVESTIDA("taipa revestida"),
    TAIPANAOREVESTIDA("taipa não revestida"),
    MADEIRA("madeira"),
    MATERIALAPROVEITADO("material aproveitado");

    private String tipoCasa;

    private TipoCasa (String s){
        this.tipoCasa = s;
    }

    public String getTipoCasa(){
        return tipoCasa;
    }

    @Override
    public String toString(){
        return tipoCasa;
    }

}
