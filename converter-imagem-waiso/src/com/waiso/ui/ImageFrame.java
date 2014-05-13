package com.waiso.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.waiso.util.ConvertImage;

/**
 * 
 * @author fabianorodrigues
 *
 */
public class ImageFrame extends JFrame {

	private JButton btnLoad;
	private JButton btnConverter;
	private JButton btnReturn;
	private JPanel panelButton;
	private JPanel panelReturn;
	
	private ConvertImage converter;
	private AreaImage areaImage;
	
	private BufferedImage image;
	private BufferedImage imageGray;
	
	private static final long serialVersionUID = 1L;

	public ImageFrame(){
		inicializeComponents();
		addComponents();
		configurationFrame();
	}
	
	/**
	 * 
	 * Configurar a janela
	 * 
	 */
	private void configurationFrame(){
		setTitle("Conversão de imagens");
		pack();
		setSize(900, 500);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Adiociona os compontes(botões e imagem) ao painel
	 */
	private void addComponents(){
		panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout());
		panelButton.add(btnLoad);
		panelButton.add(btnConverter);
		
		panelReturn = new JPanel();
		panelReturn.add(btnReturn);
		panelReturn.setVisible(false);
		
		//Cria a área de exibição da imagem
		areaImage = new AreaImage();

		add(panelReturn, BorderLayout.NORTH);
		add(areaImage, BorderLayout.CENTER);
		add(panelButton, BorderLayout.SOUTH);
	}
	
	private void inicializeComponents(){
		
		btnLoad = new JButton("Carregar Imagem");
		btnLoad.addActionListener(new LoadImageListener());

		btnConverter = new JButton("Converter Escala Cinza");
		btnConverter.addActionListener(new ConverterGrayListener());
		
		btnReturn = new JButton("Retornar");
		btnReturn.addActionListener(new ReturnListener());
		
	}
	
	/**
	 * 
	 * Listener utilizado para abrir uma imagem
	 *
	 */
	private class LoadImageListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JFileChooser fileChooser = new JFileChooser();

			int res = fileChooser.showOpenDialog(null);//Abre o dialog pra selecionar a imagem
			if (res == JFileChooser.APPROVE_OPTION) {
				File arquivo = fileChooser.getSelectedFile();

				image = null;

				try {
					image = ImageIO.read(arquivo);//Lê a imagem e buferiza
				} catch (IOException erro) {
					JOptionPane.showMessageDialog(null,	"Erro ao carregar a imagem: " + erro.getMessage());
				}

				if (image != null) {
					areaImage.image = image;
					areaImage.repaint();
				}
			}
		}
	}
	
	/**
	 * 
	 * Listener que converte a imagem para cinza
	 *
	 */
	private class ConverterGrayListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (image != null) {
				converter = new ConvertImage();
				try {
					// Chama o método para converter a escala de cinza
					imageGray = converter.toGrayscale(image);
					areaImage.image = imageGray;
					areaImage.repaint();
					panelReturn.setVisible(true);
				} catch (IOException erro) {
					JOptionPane.showMessageDialog(null, "Erro ao converter a imagem: " + erro.getMessage());
				}
			}else{
				JOptionPane.showMessageDialog(null, "Carregue uma imagem!");
			}
		}
	}
	
	/**
	 * 
	 * Listener que converte a imagem para cinza
	 *
	 */
	private class ReturnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (image != null) {
				areaImage.image = image;
				areaImage.repaint();
				panelReturn.setVisible(false);
			}
		}
	}
	
}
