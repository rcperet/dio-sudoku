package br.com.dio.enums;

public enum GameStatusEnum {
	
	NON_STARTED("Não iniciao"),
	INCOMPLETE("Imcompleto"),
	COMPLETE("Completo");
	
	private String label;
	
	GameStatusEnum(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
