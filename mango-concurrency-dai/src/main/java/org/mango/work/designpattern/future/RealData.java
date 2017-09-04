package org.mango.work.designpattern.future;

public class RealData implements Data{

    private String data;


    public RealData(String data) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
    }

    @Override
    public String getResult() throws InterruptedException {
        return this.data;
    }
}
