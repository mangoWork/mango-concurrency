package org.mango.work.disruptor.helloworld;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer buffer){
         long sequence = ringBuffer.next();
         try {
             LongEvent event = ringBuffer.get(sequence);
             event.setValue(buffer.getLong(0));
             System.out.println("==================》我生产了数据："+buffer.getLong(0));
         }finally {
             ringBuffer.publish(sequence);
         }

    }
}
