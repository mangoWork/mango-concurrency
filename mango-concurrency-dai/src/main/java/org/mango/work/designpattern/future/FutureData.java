package org.mango.work.designpattern.future;

public class FutureData implements Data{

    private RealData realData;
    private boolean isReady = false;

    public RealData getRealData() {
        return realData;
    }

    public synchronized  void setRealData(RealData realData) {
        if (isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized  String getResult() throws InterruptedException {
        if (!isReady){
            wait();
        }
        return realData.getResult();
    }
}
