package org.mango.work.disruptor.helloworld;


import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {


    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {

    }
}
