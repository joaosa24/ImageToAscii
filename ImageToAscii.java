import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ImageToAscii {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the path to the image file: ");
        String imagePath = input.nextLine(); // Read the path from user input

        try {
            // processamento da imagem
            File file = new File(imagePath); // fazer scanner para determinar o input do utilizador
            BufferedImage image = ImageIO.read(file);
            int height = image.getHeight(); // calcula altura da imagem
            int width = image.getWidth(); // calcula largura da imagem

            // responsável por gerar o output (ficheiro de texto com caracteres)
            FileWriter result = new FileWriter("output.txt"); 

            char[] characters = {'$', '@', 'B', '%', '&', 'W', '*', '+', '^'}; // ordenados por ordem crescente de brilho
            int numCharacters = characters.length - 1;

            for (int y = 0; y < height; y++) { // responsável por iterar segundo a altura da imgem
                for (int x = 0; x < width; x++) { // responsável por iterar segundo a largura da imagem
                    int rgb = image.getRGB(x, y);
                    int brightness = (rgb >> 16) & 0xFF; // para extrair o brilho da imagem, uso o conceito de 32-bits responsáveis por codificar a cor e a operação "&" para colocar a cor restante do shift igual as outras
                    double brightnessAjusted = brightness / 255.0; // para colocar o brilho num valor entre 0 e 1 (brightness estará sempre entre 0 e 255)

                    int index = (int) (brightnessAjusted * numCharacters); // para escolher o caracter responsável
                    char charOut = characters[index];

                    result.write(charOut);
                }
                result.write("\n"); // quando acabar o ciclo for (relativo ao x), devemos passar para a proxima linha do ficheiro
            }
            result.close();
            System.out.println("Imagem convertida com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
