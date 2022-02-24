package com.sanches.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sanches.cursomc.services.exceptions.FileException;

@Service
public class ImageService {

	public BufferedImage getJpegImageFromFile(MultipartFile uploadFile) {
		String ext = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
		if (!"png".equals(ext) && !"jpeg".equals(ext)) {
			throw new FileException("Somente imagens PNG e JPEG são permitidas");
		}

		try {
			BufferedImage img = ImageIO.read(uploadFile.getInputStream());
			if ("png".equals(ext)) {
				img = pngToJpeg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	public BufferedImage pngToJpeg(BufferedImage img) {
		BufferedImage jpegImage = new BufferedImage(img.getTileWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpegImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpegImage;
	}

	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler aquivo");
		}
	}

}
