package helloworld.android.transfuse.datecalculator;

public enum Operation {
	PLUS("+",1), MINUS("-",-1);
	String display;
	int signBase;
	private  Operation(String display, int signBase){
		this.display = display;
		this.signBase = signBase;
	}
}
