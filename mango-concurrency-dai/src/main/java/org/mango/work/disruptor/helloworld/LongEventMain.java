package org.mango.work.disruptor.helloworld;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LongEventMain {

    public static void main(String[] args) throws InterruptedException {

        Executor executor = Executors.newCachedThreadPool();

        LongEventFactory eventFactory = new LongEventFactory();

        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory, bufferSize, executor);

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer buffer = ByteBuffer.allocate(8);
        for (long l =0; true; l++){
            buffer.putLong(0,l);
            producer.onData(buffer);
            Thread.sleep(1000);
        }

    }

}
