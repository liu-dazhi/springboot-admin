package cn.iu.admin;

import cn.iu.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Test;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Slf4j
@SpringBootTest
class Boot05WebAdminApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void testList(){

        // Initialize Tesseract instance
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("D://");
        tesseract.setLanguage("eng");

        try {

            BufferedImage processedImg = getAuthCode();

            // 保存修改后的图像到指定路径
            String outputPath = "D://1/modified_image.png";
            ImageIO.write(processedImg, "png", new File(outputPath));

            // Perform OCR on the smoothedImg
            String result = tesseract.doOCR(processedImg).replaceAll("\\s","");

            // Print the recognized text
            System.out.println("Recognized Text: " + result);
        } catch (TesseractException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 去除噪点
     *
     * @param
     */
    private static BufferedImage getAuthCode() throws IOException {
        BufferedImage img = ImageIO.read(new File("D:/1/20.png"));
        // 获取图片的高宽
        int width = img.getWidth();
        int height = img.getHeight();

        int blueThreshold = 72;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(img.getRGB(x, y));

                int blue = color.getBlue();
                int red = color.getRed();
                int green = color.getGreen();

                if (blue == 255 && red <= blueThreshold && green <= blueThreshold){
                    img.setRGB(x,y,Color.BLACK.getRGB());
                }else {
                    img.setRGB(x,y,Color.WHITE.getRGB());
                }

                /*// 判断色值是不等于白色,不等于白色进行下一步判断(其他颜色)
                if (color.getRGB() != Color.WHITE.getRGB()) {

                    // 判断颜色是否等于蓝色,(蓝色就是验证码的颜色)
                    if (color.getRGB() == Color.BLUE.getRGB()) {
                        // 等于蓝色设置为黑色
                        img.setRGB(x, y, Color.BLACK.getRGB());
                    } else {
                        // 不等于蓝色,设置为白色
                        img.setRGB(x, y, Color.WHITE.getRGB());
                    }
                } else {
                    // 是白色,继续设置为白色
                    img.setRGB(x, y, Color.WHITE.getRGB());

                }*/
            }
        }



        return img;

    }



}
