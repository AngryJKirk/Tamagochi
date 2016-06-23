package angry.naz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by Deadliest Baddest Fattest Motherfucker in Universe on 22.06.2016.
 */
public class FSM {

    enum States {HAPPY, WANTEAT, WANTSLEEP, EATING, SLEEPING, WANTBOTH}
    States state = States.HAPPY;
    private Creature creature = new Creature();
    TelegramBot telegramBot = new TelegramBot();

    FSM(String name){ this.creature.setName(name);
        telegramBot.registerBot();
        }

    public void setNextState() throws InterruptedException, IOException {

        telegramBot.notify("IM ALIVE");
        switch (state){
            case HAPPY: happy();
            case WANTEAT: wantEat();
            case WANTSLEEP: wantSleep();
            case WANTBOTH: wantBoth();
            case EATING: eating();
            case SLEEPING: sleeping();
        }
    }

    private void wantEat() throws InterruptedException, IOException {


        while ((creature.getHp() > 0) && (creature.getDream() > 50)) {
            System.out.println("Want eat!");
            if (input().equals("Eat")) {
                creature.changeHungry(true);
                state = States.EATING;
                setNextState();
            }
            creature.changeDream(false);
            creature.changeHungry(false);
        }
        if (creature.getHp() <= 0) {
            System.out.println("Your creature is dead");
            death();
        }
        if (creature.getDream() <= 50) state = States.WANTBOTH;

        setNextState();

    }

    private void wantBoth() throws IOException, InterruptedException {
                String answer = new String();
        while (creature.getHp() > 0) {
            System.out.println("Want eat and sleep!");
            answer = input();
            if (answer.equals("Eat")) {
                creature.changeHungry(true);
                state = States.EATING;
                setNextState();
            }

            if (answer.equals("Sleep")) {
                creature.changeDream(true);
                state = States.SLEEPING;
                setNextState();
            }
            creature.changeDream(false);
            creature.changeHungry(false);
        }
        System.out.println("Your creature is dead");
        death();
    }

    private void wantSleep() throws InterruptedException, IOException {
        while ((creature.getHp() > 0) && (creature.getDream() > 50)) {
            System.out.println("Want sleep!");
            if (input().equals("Sleep")) {
                creature.changeDream(true);
                state = States.SLEEPING;
                setNextState();
            }
            creature.changeDream(false);
            creature.changeHungry(false);
        }
        if (creature.getHp() <= 0) {
            System.out.println("Your creature is dead");
            death();
        }
        if (creature.getDream() < 50) state = States.WANTBOTH;

        setNextState();
    }

    private void eating() throws InterruptedException, IOException {
        for(int i=3;i>0;i--){
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Omnomnom");

        }
        if (creature.getDream() > 50 ) state = States.HAPPY;
            else state = States.WANTSLEEP;

        setNextState();
    }

    private void sleeping() throws InterruptedException, IOException {
        for(int i=10;i>0;i--){
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Hrrr...");

        }
        if (creature.getDream() > 50 ) state = States.HAPPY;
            else state = States.WANTEAT;

        setNextState();
    }

    private void happy() throws InterruptedException, IOException {
       // while (creature.getDream() > 50 && creature.getHungry() > 50 ){
        while (true){
           TimeUnit.SECONDS.sleep(3);
            System.out.println("I AM HAPPY!");
            telegramBot.notify("I AM HAPPY!");
            creature.changeDream(false);
            creature.changeHungry(false);
            if (creature.getHungry() <50 & creature.getDream() <50) {state = States.WANTBOTH; break; }
            else if (creature.getHungry() < 50) { state = States.WANTEAT; break; }
                else if (creature.getDream() < 50){ state = States.WANTSLEEP; break; }


        }
        setNextState();



    }

    private String input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < 10000/2
                && !in.ready()) {}
        if(in.ready()) return in.readLine();
            else return "None";

    }

    private void death(){
        System.exit(0);
    }


}
