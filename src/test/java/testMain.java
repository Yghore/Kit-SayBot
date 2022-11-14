import fr.yghore.Models.Data;
import fr.yghore.Models.Joke;
import fr.yghore.Models.User;
import fr.yghore.dyglib.Configuration.Configuration;
import fr.yghore.dyglib.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class testMain {

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {



        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage welcome = ImageIO.read(testMain.class.getClassLoader().getResourceAsStream("images/welcome_background.png"));


        BufferedImage bufferedImage = new BufferedImage(welcome.getWidth(), welcome.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();


        // Create a graphics which can be used to draw into the buffered image

        // fill all the image with white
        g2d.drawImage(ImageIO.read(new URI("https://cdn.discordapp.com/avatars/190168705190068224/88f36eca086a2ce134ec3fbdb7026481.png?size=256").toURL()), 380,5, 255,255, null);
        g2d.drawImage(welcome, 0,0, null);
        g2d.setColor(Color.CYAN);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawOval(389,5,248, 248);

        g2d.setColor(Color.WHITE);
        g2d.setFont(Font.createFont(Font.TRUETYPE_FONT, testMain.class.getClassLoader().getResourceAsStream("Ubuntu-Bold.ttf")).deriveFont(72F));
        g2d.drawString("Bienvenue", 250, 339);

        // 773, 767

        // Disposes of this graphics context and releases any system resources that it is using.
        g2d.dispose();

        // Save as PNG
        File file = new File("myimage.png");
        ImageIO.write(bufferedImage, "png", file);

    }


}
