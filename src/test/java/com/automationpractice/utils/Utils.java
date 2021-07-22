package com.automationpractice.utils;

import static com.codeborne.selenide.Selenide.screenshot;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

public class Utils {
	public static void capturarScreenshot(String nomeEvidencia) {
		try {
			Thread.sleep(1500);
			// Print tirado pelo Selenide
			String tempShot = screenshot("temp_shot");

			// Transformando a img em binário para anexar no Allure
			assert tempShot != null;
			BufferedImage bimage = ImageIO.read(new File(tempShot));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			ImageIO.write(bimage, "png", baos);

			byte[] finalShot = baos.toByteArray();
			io.qameta.allure.Allure.addAttachment(nomeEvidencia, new ByteArrayInputStream(finalShot));

		} catch (Exception ex) {
			System.out.println("Erro ao anexar evidência. Trace =>" + ex);
		}
	}
}
