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

public class UncompressPrimaryStage extends Application{
	/**
	 * 解压场景的布局
	 **/
	protected BorderPane borderPane=new BorderPane();
	protected HBox hBox1=new HBox();
	protected HBox hBox2=new HBox();
	protected VBox vBox=new VBox();
	/*控件*/
	protected Label titleLabel=new Label("解压图片");
	protected Label uncPathLabel=new Label("huf文件路径  ");
	protected Label uncLabel=new Label();
	protected TextField tf=new TextField();
	protected Button choosebt=new Button("选择文件");
	private FileChooser fileChooser;
	private Stage mainStage = null;
	protected Button returnbt=new Button("返回");
	protected Button beginUncbt=new Button("开始解压");
	
	protected Stage stage=new Stage();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*向布局中添加控件*/
		/*设置控件*/
		hBox1.getChildren().addAll(uncPathLabel,tf,choosebt);
		vBox.getChildren().addAll(hBox1,uncLabel);
		hBox2.getChildren().addAll(returnbt,beginUncbt);
		borderPane.setTop(titleLabel);
		borderPane.setCenter(vBox);
		borderPane.setBottom(hBox2);
		
		titleLabel.setPadding(new Insets(15));
		titleLabel.setFont(new Font(40));
		vBox.setPadding(new Insets(70));
		hBox1.setSpacing(15);
		returnbt.setMinWidth(120);
		returnbt.setMaxWidth(120);
		beginUncbt.setMinWidth(120);
		beginUncbt.setMaxWidth(120);
		choosebt.setMinWidth(90);
		choosebt.setMaxWidth(90);
		uncPathLabel.setFont(new Font(26));
		tf.setMinWidth(240);
		vBox.setSpacing(20);
		uncLabel.setTextFill(Color.GRAY);
		uncLabel.setFont(new Font(18));
		
		hBox2.setPadding(new Insets(30,30,50,30));
		hBox2.setSpacing(360);
		
		/**
		 * 事件
		 */
		beginUncbt.setOnAction(e->{
			String file = tf.getText();
            //This is where a real application would save the file.
			uncLabel.setText("正在解压: " + file );
            try {
				HuffmanZip.uncompress(file);
				uncLabel.setText("已完成解压: " + file.substring(0, file.length()-4));	
			} catch (IOException error) {
				// TODO Auto-generated catch block
				error.printStackTrace();
				uncLabel.setText("错误:"+error.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				uncLabel.setText("错误:"+e1.getMessage());
			}
		});
		
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
		
		choosebt.setOnAction(e->{
			fileChooser= new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				    new FileChooser.ExtensionFilter("HUF", "*.huf")
				);
			File selectedFile = fileChooser.showOpenDialog(mainStage);
			tf.setText(selectedFile.getPath());
		});
		
		/**
		 * 场景
		 */
		Scene uncompressScene=new Scene(borderPane,660,500);
		primaryStage.setScene(uncompressScene);
		primaryStage.setTitle("解压图片");
		primaryStage.getIcons().add(new Image("headIcon.jpg"));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public void showWindow() throws Exception {
		start(stage);
	}

}
