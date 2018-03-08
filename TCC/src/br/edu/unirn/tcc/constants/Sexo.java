package br.edu.unirn.tcc.constants;

public enum Sexo {
	
	MASCULINO("masculino"),
    FEMININO("feminino");

    private String tipoSexo;

    private Sexo (String s){
        this.tipoSexo = s;
    }

    public String getTipoSexo(){
        return tipoSexo;
    }

    @Override
    public String toString(){
        return tipoSexo;
    }

}
