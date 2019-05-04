import java.util.Random;

//the main program
public class Main 
{    
    public static void main(String[] args) 
    {
        Semaphore chopsticks[];
        Philosopher philosopher[];
       
        chopsticks=new Semaphore[5];  //Created an array of five Semaphores 

       
        for (int i=0; i<5;i++)
        {
            chopsticks[i] =new Semaphore(1); //Semaphore initial to value '1'

        }
       
        philosopher = new Philosopher[5];   //Created five philosopher thread object array reference handles

       
        for(int i=0;i<5;i++)
        {
            philosopher[i] = new Philosopher(i,chopsticks);
            philosopher[i].start();
        }
    }
    
}
class Philosopher extends Thread
{
    private int name;
    private Semaphore chopsticks[];

    public Philosopher(int name,Semaphore chopsticks[]){
        this.name=name;
        this.chopsticks=chopsticks;
    }

    public void run()
    {
        while (true)
        {
            System.out.println("philosopher "+ name+"is thinking");
            try{
                Thread.sleep(new Random().nextInt(100)+50);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Philosopher "+name+"is hungry.");

            chopsticks[name].p(); //Acquire right chopstick
            System.out.println("Philosopher takes the chopstick: "+name);

            chopsticks[(name+1)%5].p(); //Acquire left chopstick
            System.out.println("Philosopher takes the chopstick: "+(name+1));

            System.out.println("Philosophers "+name+"is eating");
            try{
                Thread.sleep(new Random().nextInt(100)+50);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            chopsticks[name].v();
            // released right chopstick
            System.out.println("Philosopher release the chopstick: "+name);

            chopsticks[(name+1)%5].v();
            //released left chopstick
            System.out.println("Philosopher release the chopstick: "+(name+1));

        }
    }
}

