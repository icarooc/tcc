package br.edu.unirn.constants.tcc;

public enum AbastecimentoAgua {
	
	REDEPUBLICA("rede p�blica"),
    POCONASCENTE("po�o/nascente"),
    CLORACAO("clora��o");

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
