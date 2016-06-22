package angry.naz;

/**
 * Created by Deadliest Baddest Fattest Motherfucker in Universe on 22.06.2016.
 */
public class Creature {


    private String name;

    private int hp = 100;

    private int hungry = 100;

    private int dream = 100;


    public void setName(String name) {
        this.name = name;
    }
    private void changeHP(int param){
        this.hp = this.hp + param;

        if (this.hp>100) this.hp = 100;
    }

    public void changeHungry(boolean action){

        if(action) { this.changeHP(25);
                     this.hungry = this.hungry+50; }
            else {
                    if (this.hungry <=0) dying();
                    this.changeHP(-5);
                   this.hungry = this.hungry-20; }
    }

    public void  changeDream(boolean action){

        if(action) { this.changeHP(25);
                     this.dream = 100;}
        else { this.changeHP(-10);
            if (this.hungry <=0) dying();
               this.dream -= 10; }
    }

    public int getHungry() {
        return hungry;
    }

    public int getDream() {

        return dream;
    }

    public int getHp() {
        return hp;
    }

    private void dying(){
        //System.out.println("Im dying!");
        changeHP(-5);
    }

}
