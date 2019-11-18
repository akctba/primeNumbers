package ca.ciccc.ak.primeNumbers.interfaces;

public interface View {
	
	void start();

    void setTaskProgress(long progress);

    void setStatusText(String statusText);

    void addOutput(String text);

}
