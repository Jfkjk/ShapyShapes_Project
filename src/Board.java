import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {
    Game game;
    Timer timer;
    int paddingnum=25;
    ArrayList<Sprite>actors;
    long nextMoment;
    public Board(Game game){
        this.game=game;
        setPreferredSize(new Dimension(600, 800));
        setBackground(Color.BLACK);
    }
    public void setup(){
        actors=new ArrayList<>();
        actors.add(new Player(Color.GREEN,getWidth()/2,getHeight()/2,50,50,this,game));
        for(int i=0;i< STATS.getNumFood();i++){
            actors.add(new Food(Color.ORANGE,(int)(Math.random()*(getWidth()-paddingnum)+paddingnum),(int)(Math.random()*(getHeight()-paddingnum)+paddingnum),20,20,this));
        }
        for(int i=0;i< STATS.getNumEnemies();i++){
            actors.add(new Enemy(Color.RED,(int)(Math.random()*(getWidth()-paddingnum)+paddingnum),(int)(Math.random()*(getHeight()-paddingnum)+paddingnum),50,50,this));
        }
        timer=new Timer(1000/60,this);
        timer.start();
    }


    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g) {
        int stringLen = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        int start = width / 2 - stringLen / 2;
        g.drawString(s, start + XPos, YPos);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(GameStates.isMENU()){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.ITALIC,72));
            printSimpleString("ShapyShapes",getWidth(),0,150,g);
            g.setFont(new Font("Arial",Font.BOLD,36));
            printSimpleString("Click to play",getWidth(),0,300,g);
        }
        if(GameStates.isPLAY()) {
            for (Sprite thisguy : actors) {
                thisguy.paint(g);
            }
        }
    }
    public void checkCollisions(){
        for(int i=1;i<actors.size();i++){
            if(actors.get(0).collidesWidth(actors.get(i))){
                if(actors.get(i)instanceof Enemy){
                    actors.get(0).shrink();
                }
                else
                    actors.get(i).setRemove();
                    actors.get(0).grow();
            }
        }
        for(int i= actors.size()-1;i>=0;i--){
            if(actors.get(i).isRemove()){
                actors.remove(i);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(GameStates.isPLAY()) {
            nextMoment = System.currentTimeMillis();
            if (nextMoment - game.moment >= 5000) {
                checkCollisions();
            }
            if (game.isMouseClicked()) {
                for (Sprite thisguy : actors) {
                    thisguy.move();
                }
            }
            if (actors.size() <= STATS.getNumEnemies() + 1) {
                System.out.println("killed em all");
                game.setMouseClicked();
            }
        }
        if(game.isMouseClicked()){
            GameStates.setPLAY(true);
            GameStates.setMENU(false);
        }
        repaint();
    }
}
