package br.edu.unirn.constants.tcc;

public enum TratamentoAgua {
	
	FILTRACAO("filtração"),
    FERVURA("fervura"),
    SEMTRATAMENTO("sem tratamento");

    private String tratamentoAqua;

    private TratamentoAgua (String s){
        this.tratamentoAqua = s;
    }

    public String getTratamentoAgua(){
        return tratamentoAqua;
    }

    @Override
    public String toString(){
        return tratamentoAqua;
    }

}
