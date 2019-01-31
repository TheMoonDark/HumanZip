package huffman_zip;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainPrimaryStage extends Application{
	/**
	 * �������Ĳ���
	 * */
	protected BorderPane mainPane=new BorderPane();
	protected HBox hBox=new HBox(3);
	/*�ؼ�*/
	protected Button cbt=new Button("ѹ��ͼƬ");
	protected Button uncbt=new Button("��ѹͼƬ");
	protected Button exitbt=new Button("�˳�");
	
	protected int i=0;
	
	protected Stage stage=new Stage();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
		/*�򲼾�����ӿؼ�*/
		/*���ÿؼ�*/
		
		mainPane.setBottom(hBox);
		Image image=new Image("icon.png");
		ImageView imageView=new ImageView(image);
		imageView.setFitWidth(660);
		imageView.setFitHeight(380);
		mainPane.setCenter(imageView);
		hBox.getChildren().addAll(cbt,uncbt,exitbt);
		hBox.setPadding(new Insets(40,70,70,70));
		hBox.setSpacing(75);
		cbt.setMinWidth(120);
		cbt.setMaxWidth(120);
		uncbt.setMinWidth(120);
		uncbt.setMaxWidth(120);
		exitbt.setMinWidth(120);
		exitbt.setMaxWidth(120);
		
		/**
		 * �¼�
		 */
		cbt.setOnAction(e->{
			MainController controller=new MainController();
			try {
				controller.changeWindowToCompress();
				Stage stage=(Stage) cbt.getScene().getWindow();
				stage.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		uncbt.setOnAction(e->{
			MainController controller=new MainController(); 
			try {
				controller.changeWindowToUncompress();
				Stage stage=(Stage) uncbt.getScene().getWindow();
				stage.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		exitbt.setOnAction(e->{
			System.exit(0);
		});
		
		/**
		 * ����
		 */
		
		Scene mainScene=new Scene(mainPane,660,500);
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Huffman������");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("headIcon.jpg"));
		primaryStage.show();
		if(i==0) {
			i=1;
//			MainController controller=new MainController(); 
//			controller.changeWindowToSign();
		}
		
		
		
	}
	
	public void showWindow() throws Exception {
		start(stage);
	}
	
	
	

}
