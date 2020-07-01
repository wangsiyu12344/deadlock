package deadlock;

/**
 * a++问题，两个线程同时加10000次，总数小于20000.
 * 发生原因:a++并不是一个原子性操作.
 * 即使加上volatile还是会失败，原因是volatile只保证线程之间的可见性， 而无法保证原子性。
 * volatile相当于一个轻量级的synchronized
 */
public class Aplusplus implements Runnable{
    static int a;


    public static void main(String[] args) throws InterruptedException {
        Aplusplus i1 = new Aplusplus();
        Aplusplus i2 = new Aplusplus();
        Thread t1 = new Thread(i1);
        Thread t2 = new Thread(i2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a);
    }

    @Override
    public void run() {
        for(int i = 0; i< 10000; i++){
            a++;
        }
    }
}
