package br.edu.unirn.constants.tcc;

public enum AbastecimentoAgua {
	
	REDEPUBLICA("rede pública"),
    POCONASCENTE("poço/nascente"),
    CLORACAO("cloração");

    private String abastecimentoAqua;

    private AbastecimentoAgua (String s){
        this.abastecimentoAqua = s;
    }

    public String getAbastecimentoAgua(){
        return abastecimentoAqua;
    }

    @Override
    public String toString(){
        return abastecimentoAqua;
    }

}
