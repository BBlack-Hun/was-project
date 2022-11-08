package org.example.counter;

public class RaceConditionDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();
        /**
         * 싱클톤 객체에서 하나의 객체를 공유하게 되면, 원치 않는 결과가 나오게 된다.
         * RaceCondition이란. 여러 프로세스/쓰레드가 하나의 자원에 접근하기 위해 경쟁하는 상태
         *
         * 싱글톤 객체에서는 상태를 유지(stateful)하게 설계하면 안됨.
         */
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
