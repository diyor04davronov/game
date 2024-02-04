import java.awt.*;


public class Score extends Rectangle {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
Score(int WIDTH, int HEIGHT) {
    Score.GAME_WIDTH = WIDTH;
    Score.GAME_HEIGHT = HEIGHT;
}
    public void draw(Graphics g){
     g.setColor(Color.red);
     g.setFont(new Font("Conloas", Font.PLAIN, 60));
     g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
     g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GAME_WIDTH/2) - 85,  50);
     g.drawString(String.valueOf(player2/10)+ String.valueOf(player2%10), (GAME_WIDTH/2) + 20,  50);
    }

    }


