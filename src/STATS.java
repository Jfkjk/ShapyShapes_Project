public class STATS {
    private static int numFood=100, numEnemies=3 ;
    private static int Life = 3;
    private static int Level = 1;
    private static int LowSpeed = 4;
    private static int HighSpeed = 8;


    public static int getNumFood() {
        return numFood;
    }

    public static void setNumFood(int numFood) {
        STATS.numFood = numFood;
    }

    public static int getNumEnemies() {
        return numEnemies;
    }

    public static void setNumEnemies(int numEnemies) {
        STATS.numEnemies = numEnemies;
    }

    public static int getLife() {
        return Life;
    }

    public static void setLife(int life) {
        STATS.Life = life;
    }

    public static int getLevel() {
        return Level;
    }

    public static void setLevel(int level) {
        STATS.Level = level;
    }

    public static int getLowSpeed() {
        return LowSpeed;
    }

    public static void setLowSpeed(int lowSpeed) {
        STATS.LowSpeed = lowSpeed;
    }

    public static int getHighSpeed() {
        return HighSpeed;
    }

    public static void setHighSpeed(int highSpeed) {
        STATS.HighSpeed = highSpeed;
    }

    public static void updateLevel(){
        switch(Level){
            case 1:
                setNumFood(100);
                setNumEnemies(3);
                setLowSpeed(4);
                setHighSpeed(4);
            case 2:
                setNumFood(90);
                setNumEnemies(4);
                setLowSpeed(4);
                setHighSpeed(4);
        }
    }
}
