package br.edu.unirn.constants.tcc;

public enum UF {
	
	AC("AC"),
    AL("AL"),
    AP("AP"),
    AM("AM"),
    BA("BA"),
    CE("CE"),
    DF("DF"),
    ES("ES"),
    GO("GO"),
    MA("MA"),
    MT("MT"),
    MS("MS"),
    MG("MG"),
    PA("PA"),
    PB("PB"),
    PR("PR"),
    PE("PE"),
    PI("PI"),
    RJ("RJ"),
    RS("RS"),
    RN("RN"),
    RO("RO"),
    RR("RR"),
    SC("SC"),
    SP("SP"),
    SE("SE"),
    TO("TO");

    private String uf;

    private UF (String s){
        this.uf = s;
    }

    public String getUF(){
        return uf;
    }

    @Override
    public String toString(){
        return uf;
    }

}
