package deadlock;

/**
 * 必定发生死锁的情况
 */
public class MustDeadLock implements Runnable{
    int flag =1;

    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        MustDeadLock i1 = new MustDeadLock();
        MustDeadLock i2 = new MustDeadLock();
        i1.flag = 1;
        i2.flag = 0;
        Thread t1 = new Thread(i1);
        Thread t2 = new Thread(i2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        System.out.println("flag=" + flag);

        if(flag==1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName()+"成功拿到2把锁.");
                }
            }
        }
        if(flag==0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName()+"成功拿到2把锁.");
                }
            }
        }
    }


}
