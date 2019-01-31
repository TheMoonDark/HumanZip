package huffman_zip;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CompressPrimaryStage extends Application{
	/**
	 * ѹ�������Ĳ���
	 **/
	
	protected BorderPane borderPane=new BorderPane();
	protected HBox hBox1=new HBox();
	protected HBox hBox2=new HBox();
	protected VBox vBox=new VBox();
	/*�ؼ�*/
	protected Label titleLabel1=new Label("ѹ��ͼƬ");
	protected Label cPathLabel=new Label("ͼƬ·��");
	protected Label cLabel=new Label();
	protected Button choosebt=new Button("ѡ���ļ�");
	private FileChooser fileChooser;
	private Stage mainStage = null;
	protected TextField tf1=new TextField();
	protected Button returnbt=new Button("����");
	protected Button beginCbt=new Button("��ʼѹ��");
	
	protected Stage stage=new Stage();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*�򲼾�����ӿؼ�*/ 
		/*���ÿؼ�*/
		hBox1.getChildren().addAll(cPathLabel,tf1,choosebt);
		vBox.getChildren().addAll(hBox1,cLabel);
		hBox2.getChildren().addAll(returnbt,beginCbt);
		borderPane.setTop(titleLabel1);
		borderPane.setCenter(vBox);
		borderPane.setBottom(hBox2);
		
		titleLabel1.setPadding(new Insets(15));
		titleLabel1.setFont(new Font(40));
		vBox.setPadding(new Insets(70));
		hBox1.setSpacing(15);
		returnbt.setMinWidth(120);
		returnbt.setMaxWidth(120);
		beginCbt.setMinWidth(120);
		beginCbt.setMaxWidth(120);
		choosebt.setMinWidth(90);
		choosebt.setMaxWidth(90);
		cPathLabel.setFont(new Font(26));
		tf1.setMinWidth(280);
		vBox.setSpacing(20);
		cLabel.setTextFill(Color.GRAY);
		cLabel.setFont(new Font(18));
		
		hBox2.setPadding(new Insets(30,30,50,30));
		hBox2.setSpacing(360);
		
		/**
		 * �¼�
		 */
		returnbt.setOnAction(e->{
			MainController controller=new MainController();
			try {
				controller.changeWindowToMain();
				Stage stage=(Stage) borderPane.getScene().getWindow();
				stage.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		beginCbt.setOnAction(e->{
			String file = tf1.getText();
            cLabel.setText("��ѹ���ļ�: " + file);
            try {
				HuffmanZip.compress(file);
				cLabel.setText("�����ѹ��: " + file);	
			} catch (IOException error) {
				error.printStackTrace();
				cLabel.setText("����:"+error.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				cLabel.setText("����:"+e1.getMessage());
			}
		});
		
		choosebt.setOnAction(e->{
			fileChooser= new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				    new FileChooser.ExtensionFilter("All Images", "*.*"),
				    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				    new FileChooser.ExtensionFilter("GIF", "*.gif"),
				    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
				    new FileChooser.ExtensionFilter("PNG", "*.png")
				);
			File selectedFile = fileChooser.showOpenDialog(mainStage);
			tf1.setText(selectedFile.getPath());
		});
		
		/**
		 * ����
		 */
		Scene compressScene=new Scene(borderPane,660,500);
		primaryStage.setScene(compressScene);
		primaryStage.setTitle("ѹ��ͼƬ");
		primaryStage.getIcons().add(new Image("headIcon.jpg"));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public void showWindow() throws Exception {
		start(stage);
	}


}
