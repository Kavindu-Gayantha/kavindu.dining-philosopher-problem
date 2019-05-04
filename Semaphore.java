class Semaphore{
    private int value;

    public Semaphore(int value)
    {
        this.value=value;

    }

    public synchronized void p()
    {
        while(value==0)
        {
            try {
                System.out.println("chopstick is being used");
                wait(); // waits until semaphore becomes free
            }catch (InterruptedException e){}
        }
        value=value-1;
    }
    public synchronized void v()
    {
        value=value+1;
        notify();
    }
}
