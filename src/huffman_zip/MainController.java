package huffman_zip;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
/**
 * ´°¿ÚÇÐ»»¿ØÖÆÆ÷
 * @author ºÂµÂè¡
 *
 */
public class MainController implements Initializable {
	public MainController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void changeWindowToCompress() throws Exception {
		huffman_zip.CompressPrimaryStage compressPrimaryStage = new CompressPrimaryStage();
		compressPrimaryStage.showWindow();

	}

	public void changeWindowToUncompress() throws Exception {
		huffman_zip.UncompressPrimaryStage uncompressPrimaryStage = new UncompressPrimaryStage();
		uncompressPrimaryStage.showWindow();

	}

	public void changeWindowToMain() throws Exception {
		huffman_zip.MainPrimaryStage mainPrimaryStage = new MainPrimaryStage();
		mainPrimaryStage.showWindow();

	}

}
