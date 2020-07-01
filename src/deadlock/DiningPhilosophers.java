package deadlock;

/**
 * 哲学家就餐问题:
 */
public class DiningPhilosophers {

    public static class Philosoper implements Runnable{

        private Object leftChopstick;
        private Object rightChopstick;

        public Philosoper(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            while(true){
                try {
                    doAction("Thinking");   //哲学家思考
                    synchronized (leftChopstick){
                        doAction("picked up left chopstick");
                        synchronized (rightChopstick){
                            doAction("picked up right chopstick - eating");
                            doAction("put down right chopstick");
                        }
                        doAction("put down right chopstick");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName()+ "" + action);
            Thread.sleep((long) Math.random()*10); //休眠随机秒数
        }
    }

    public static void main(String[] args) {
        Philosoper[] philosopers = new Philosoper[5];
        Object[] chopsticks = new Object[philosopers.length];

        for(int i = 0; i< chopsticks.length; i++){
            chopsticks[i] = new Object();
        }

        for(int i = 0; i<philosopers.length ; i++){
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i+1)%philosopers.length];
            philosopers[i] = new Philosoper(leftChopstick, rightChopstick);
            new Thread(philosopers[i], "哲学家"+(i+1)+"号").start();
        }
    }

}
