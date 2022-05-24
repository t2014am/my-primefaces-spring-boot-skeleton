package es.test.test_controllers.charts;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@ViewScoped
public class GraphicImageView implements Serializable {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-method")
	public StreamedContent getChart() throws IOException {
		// Chart
		JFreeChart jfreechart = ChartFactory.createPieChart("Cities", createDataset(), true, true, false);
		ByteArrayInputStream chartStream = createInMemoryChartStream(jfreechart);
		return DefaultStreamedContent.builder().stream(() -> chartStream).contentEncoding("image/png").build();
	}

	private static ByteArrayInputStream createInMemoryChartStream(JFreeChart jfreechart) throws IOException {
		BufferedImage objBufferedImage=jfreechart.createBufferedImage(300,400);
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		ImageIO.write(objBufferedImage, "png", bas);
		byte[] byteArray=bas.toByteArray();
		return new ByteArrayInputStream(byteArray);
	}

	private static PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("New York", 45.0);
		dataset.setValue("London", 15.0);
		dataset.setValue("Paris", 25.2);
		dataset.setValue("Berlin", 14.8);

		return dataset;
	}
}